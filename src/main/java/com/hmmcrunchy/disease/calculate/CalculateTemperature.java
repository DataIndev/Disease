/*     */ package com.hmmcrunchy.disease.calculate;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.classes.DPlayer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.block.BlastFurnace;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.Furnace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalculateTemperature
/*     */ {
/*     */   public int heatradius;
/*     */   public int torchHeat;
/*     */   public int fireHeat;
/*     */   public int campFireHeat;
/*     */   public int furnaceHeat;
/*     */   public int blastFurnaceHeat;
/*     */   public int lavaHeat;
/*     */   public int jackolanternHeat;
/*     */   public int redstoneTorchHeat;
/*     */   public int torchHeatHeld;
/*     */   public int fireHeatHeld;
/*     */   public int lavaBucketHeatHeld;
/*     */   public int jackolanternHeatHeld;
/*     */   public int redstoneTorchHeatHeld;
/*  49 */   int tempLevel = 0;
/*     */   
/*     */   Disease disease;
/*     */   
/*     */   public CalculateTemperature(Disease mainDisease) {
/*  54 */     this.disease = mainDisease;
/*     */     
/*  56 */     Bukkit.getLogger().info("Custom heat values reloaded into calculator");
/*  57 */     Bukkit.getLogger().info("radius " + this.heatradius);
/*  58 */     Bukkit.getLogger().info("fire " + this.fireHeat);
/*  59 */     Bukkit.getLogger().info("torch " + this.torchHeat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double calcHeat(Player p, boolean command) {
/*  66 */     if (this.disease.debug) Bukkit.getLogger().info(p.getName() + " calc heat ");
/*     */     
/*  68 */     double temp = 0.0D;
/*  69 */     temp = locationTemp(p);
/*     */ 
/*     */ 
/*     */     
/*  73 */     temp -= isInWater(p);
/*  74 */     temp -= isRaining(p);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     temp += calculateArmourHeatValue(p);
/*     */ 
/*     */ 
/*     */     
/*  83 */     temp += detectHeldHeat(p);
/*     */ 
/*     */ 
/*     */     
/*  87 */     temp += detectHeat(p);
/*     */ 
/*     */     
/*  90 */     if (this.disease.debug) Bukkit.getLogger().info(p.getName() + " ambient heat bfore effects: " + temp);
/*     */     
/*  92 */     if (!command)
/*     */     {
/*  94 */       heatEffects(p, temp);
/*     */     }
/*     */     
/*  97 */     return temp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int detectHeat(Player p) {
/* 103 */     int heat = 0;
/* 104 */     int radius = this.heatradius;
/* 105 */     int fires = 0;
/*     */     
/* 107 */     if (this.disease.debug) {
/* 108 */       Bukkit.getLogger().info("Calculating nearby heat");
/* 109 */       Bukkit.getLogger().info("radius " + this.heatradius);
/* 110 */       Bukkit.getLogger().info("fire " + this.fireHeat);
/* 111 */       Bukkit.getLogger().info("torch " + this.torchHeat);
/*     */     } 
/*     */ 
/*     */     
/* 115 */     World world = p.getWorld();
/* 116 */     int x1 = p.getLocation().getBlock().getX();
/* 117 */     int y1 = p.getLocation().getBlock().getY();
/* 118 */     int z1 = p.getLocation().getBlock().getZ();
/*     */ 
/*     */     
/* 121 */     for (int x = x1 - radius; x <= x1 + radius; x++) {
/*     */       
/* 123 */       for (int y = y1 - radius; y <= y1 + radius; y++) {
/*     */         
/* 125 */         for (int z = z1 - radius; z <= z1 + radius; z++) {
/*     */ 
/*     */ 
/*     */           
/* 129 */           if (world.getBlockAt(x, y, z).getType().equals(Material.FIRE)) {
/* 130 */             heat += this.fireHeat;
/* 131 */             fires++;
/* 132 */           } else if (world.getBlockAt(x, y, z).getType().equals(Material.TORCH)) {
/* 133 */             heat += this.torchHeat;
/* 134 */           } else if (world.getBlockAt(x, y, z).getType().equals(Material.REDSTONE_TORCH)) {
/* 135 */             heat += this.redstoneTorchHeat;
/* 136 */           } else if (world.getBlockAt(x, y, z).getType().equals(Material.LAVA)) {
/* 137 */             heat += this.lavaHeat;
/* 138 */           } else if (world.getBlockAt(x, y, z).getType().equals(Material.JACK_O_LANTERN)) {
/* 139 */             heat += this.jackolanternHeat;
/* 140 */           } else if (world.getBlockAt(x, y, z).getType().equals(Material.CAMPFIRE)) {
/* 141 */             heat += this.campFireHeat;
/* 142 */           } else if (world.getBlockAt(x, y, z).getType().equals(Material.FURNACE)) {
/* 143 */             Furnace f = (Furnace)world.getBlockAt(x, y, z).getState();
/* 144 */             if (f.getBurnTime() > 0) {
/* 145 */               heat += this.furnaceHeat;
/*     */             }
/*     */           }
/* 148 */           else if (world.getBlockAt(x, y, z).getType().equals(Material.BLAST_FURNACE)) {
/* 149 */             BlastFurnace bf = (BlastFurnace)world.getBlockAt(x, y, z).getState();
/* 150 */             if (bf.getBurnTime() > 0) {
/* 151 */               heat += this.blastFurnaceHeat;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     if (fires > 1) {
/* 162 */       heat -= (fires - 1) * this.fireHeat;
/* 163 */       if (this.disease.debug) {
/* 164 */         Bukkit.getLogger().info("More than one fire reducing total heat by" + ((fires - 1) * this.fireHeat));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 169 */     return heat;
/*     */   }
/*     */ 
/*     */   
/*     */   public int detectHeldHeat(Player p) {
/* 174 */     int heat = 0;
/*     */     
/* 176 */     if (p.getItemInHand() != null)
/*     */     {
/*     */       
/* 179 */       if (p.getItemInHand().getType() == Material.TORCH) {
/*     */         
/* 181 */         heat += this.torchHeatHeld;
/*     */       }
/* 183 */       else if (p.getItemInHand().getType() == Material.REDSTONE_TORCH) {
/*     */         
/* 185 */         heat += this.redstoneTorchHeatHeld;
/*     */       }
/* 187 */       else if (p.getItemInHand().getType() == Material.LAVA_BUCKET) {
/*     */         
/* 189 */         heat += this.lavaBucketHeatHeld;
/*     */       }
/* 191 */       else if (p.getItemInHand().getType() == Material.JACK_O_LANTERN) {
/*     */         
/* 193 */         heat += this.jackolanternHeatHeld;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 198 */     return heat;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOutside(Player p) {
/* 203 */     boolean outside = true;
/* 204 */     int ypos = (int)p.getLocation().getY();
/*     */     
/* 206 */     for (int i = ypos + 1; i < p.getLocation().getWorld().getMaxHeight(); i++) {
/*     */       
/* 208 */       Location yLoc = new Location(p.getLocation().getWorld(), (int)p.getLocation().getX(), i, (int)p.getLocation().getZ());
/* 209 */       if (yLoc.getBlock().getType() != Material.AIR) {
/* 210 */         outside = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 215 */     return outside;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int isInWater(Player p) {
/* 221 */     int inWater = 0;
/*     */     
/* 223 */     Block block = p.getLocation().getBlock();
/*     */     
/* 225 */     if (block.getType().equals(Material.WATER))
/*     */     {
/* 227 */       inWater = 5;
/*     */     }
/*     */ 
/*     */     
/* 231 */     return inWater;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double locationTemp(Player p) {
/* 237 */     double temp = 0.0D;
/*     */     
/* 239 */     Biome biome = p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ());
/* 240 */     boolean night = isNight(p);
/* 241 */     boolean outside = isOutside(p);
/*     */     
/* 243 */     if (biome.equals(Biome.SNOWY_PLAINS) || biome.equals(Biome.ICE_SPIKES) || biome.equals(Biome.SNOWY_TAIGA) || biome.equals(Biome.FROZEN_RIVER) || biome.equals(Biome.FROZEN_OCEAN) || biome.equals(Biome.SNOWY_BEACH)) {
/*     */ 
/*     */       
/* 246 */       if (night == true) {
/*     */         
/* 248 */         temp = -10.0D;
/*     */       } else {
/*     */         
/* 251 */         temp = -5.0D;
/*     */       } 
/*     */       
/* 254 */       if (!outside)
/*     */       {
/* 256 */         temp += 10.0D;
/*     */       }
/* 258 */       this.tempLevel = -1;
/*     */     }
/* 260 */     else if (biome.equals(Biome.DRIPSTONE_CAVES) || biome.equals(Biome.JAGGED_PEAKS) || biome.equals(Biome.STONY_PEAKS) || biome.equals(Biome.WINDSWEPT_GRAVELLY_HILLS)) {
/*     */ 
/*     */       
/* 263 */       if (night == true) {
/*     */         
/* 265 */         temp = 0.0D;
/*     */       } else {
/*     */         
/* 268 */         temp = 5.0D;
/*     */       } 
/*     */       
/* 271 */       if (!outside)
/*     */       {
/* 273 */         temp += 10.0D;
/*     */       }
/* 275 */       this.tempLevel = -1;
/*     */     }
/* 277 */     else if (biome.equals(Biome.TAIGA) || biome.equals(Biome.DARK_FOREST) || biome.equals(Biome.OLD_GROWTH_PINE_TAIGA) || biome.equals(Biome.OLD_GROWTH_BIRCH_FOREST) || biome.equals(Biome.OLD_GROWTH_SPRUCE_TAIGA)) {
/*     */ 
/*     */       
/* 280 */       if (night == true) {
/*     */         
/* 282 */         temp = 5.0D;
/*     */       } else {
/*     */         
/* 285 */         temp = 10.0D;
/*     */       } 
/*     */       
/* 288 */       if (!outside)
/*     */       {
/* 290 */         temp += 10.0D;
/*     */       }
/*     */     }
/* 293 */     else if (biome.equals(Biome.PLAINS) || biome.equals(Biome.SUNFLOWER_PLAINS) || biome.equals(Biome.FOREST) || biome.equals(Biome.FLOWER_FOREST) || biome.equals(Biome.BIRCH_FOREST) || biome.equals(Biome.LUSH_CAVES) || biome.equals(Biome.DARK_FOREST) || biome.equals(Biome.WOODED_BADLANDS) || biome.equals(Biome.FLOWER_FOREST) || biome.equals(Biome.SWAMP) || biome.equals(Biome.RIVER) || biome.equals(Biome.BEACH)) {
/*     */ 
/*     */       
/* 296 */       if (night == true) {
/*     */         
/* 298 */         temp = 15.0D;
/*     */       } else {
/*     */         
/* 301 */         temp = 20.0D;
/*     */       } 
/*     */       
/* 304 */       if (!outside)
/*     */       {
/* 306 */         temp += 5.0D;
/*     */       }
/*     */     }
/* 309 */     else if (biome.equals(Biome.JUNGLE) || biome.equals(Biome.BAMBOO_JUNGLE) || biome.equals(Biome.SPARSE_JUNGLE) || biome.equals(Biome.WARPED_FOREST) || biome.equals(Biome.BAMBOO_JUNGLE) || biome.equals(Biome.MUSHROOM_FIELDS) || biome.equals(Biome.MUSHROOM_FIELDS)) {
/*     */ 
/*     */       
/* 312 */       if (night == true) {
/*     */         
/* 314 */         temp = 25.0D;
/*     */       } else {
/*     */         
/* 317 */         temp = 30.0D;
/* 318 */         this.tempLevel = 1;
/*     */       } 
/*     */       
/* 321 */       if (!outside)
/*     */       {
/* 323 */         temp -= 5.0D;
/*     */       }
/*     */     }
/* 326 */     else if (biome.equals(Biome.SAVANNA) || biome.equals(Biome.SAVANNA_PLATEAU) || biome.equals(Biome.BADLANDS) || biome.equals(Biome.ERODED_BADLANDS) || biome.equals(Biome.SAVANNA_PLATEAU)) {
/*     */ 
/*     */       
/* 329 */       if (night == true) {
/*     */         
/* 331 */         temp = 30.0D;
/*     */       } else {
/*     */         
/* 334 */         temp = 40.0D;
/*     */       } 
/*     */       
/* 337 */       if (!outside)
/*     */       {
/* 339 */         temp -= 10.0D;
/*     */       }
/* 341 */       this.tempLevel = 1;
/*     */     }
/* 343 */     else if (biome.equals(Biome.DESERT) || biome.equals(Biome.SOUL_SAND_VALLEY)) {
/*     */ 
/*     */       
/* 346 */       if (night == true) {
/*     */         
/* 348 */         temp = 5.0D;
/* 349 */         this.tempLevel = -1;
/*     */       }
/*     */       else {
/*     */         
/* 353 */         temp = 40.0D;
/* 354 */         this.tempLevel = 1;
/*     */ 
/*     */         
/* 357 */         if (p.getInventory().getHelmet() != null) {
/* 358 */           temp -= 10.0D;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 364 */       if (!outside)
/*     */       {
/* 366 */         temp -= 10.0D;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 371 */     return temp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int isRaining(Player p) {
/* 377 */     if (p.getWorld().isThundering())
/*     */     {
/*     */       
/* 380 */       return 2;
/*     */     }
/*     */     
/* 383 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isNight(Player p) {
/* 387 */     boolean night = false;
/* 388 */     World world = p.getWorld();
/*     */     
/* 390 */     if (world.getTime() < 12300L || world.getTime() > 23850L) {
/* 391 */       night = false;
/*     */     } else {
/* 393 */       night = true;
/*     */     } 
/* 395 */     return night;
/*     */   }
/*     */ 
/*     */   
/*     */   public int calculateArmourHeatValue(Player p) {
/* 400 */     int armourVal = 0;
/*     */     
/* 402 */     if (p.getInventory().getHelmet() != null) {
/* 403 */       ItemStack helm = p.getInventory().getHelmet();
/*     */ 
/*     */       
/* 406 */       if (helm.getType() == Material.LEATHER_HELMET) {
/* 407 */         armourVal += 3;
/* 408 */       } else if (helm.getType() == Material.IRON_HELMET) {
/* 409 */         armourVal++;
/* 410 */       } else if (helm.getType() == Material.GOLDEN_HELMET) {
/* 411 */         armourVal++;
/* 412 */       } else if (helm.getType() == Material.CHAINMAIL_HELMET) {
/* 413 */         armourVal++;
/* 414 */       } else if (helm.getType() == Material.DIAMOND_HELMET) {
/* 415 */         armourVal += 2;
/* 416 */       } else if (helm.getType() == Material.NETHERITE_HELMET) {
/* 417 */         armourVal++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 422 */     if (p.getInventory().getChestplate() != null) {
/*     */       
/* 424 */       ItemStack chestPlate = p.getInventory().getChestplate();
/*     */ 
/*     */       
/* 427 */       if (chestPlate.getType() == Material.LEATHER_CHESTPLATE) {
/* 428 */         armourVal += 5;
/* 429 */       } else if (chestPlate.getType() == Material.IRON_CHESTPLATE) {
/* 430 */         armourVal += 2;
/* 431 */       } else if (chestPlate.getType() == Material.GOLDEN_CHESTPLATE) {
/* 432 */         armourVal += 2;
/* 433 */       } else if (chestPlate.getType() == Material.CHAINMAIL_CHESTPLATE) {
/* 434 */         armourVal++;
/* 435 */       } else if (chestPlate.getType() == Material.DIAMOND_CHESTPLATE) {
/* 436 */         armourVal += 3;
/* 437 */       } else if (chestPlate.getType() == Material.NETHERITE_CHESTPLATE) {
/* 438 */         armourVal += 2;
/*     */       } 
/*     */     } 
/*     */     
/* 442 */     if (p.getInventory().getLeggings() != null) {
/*     */       
/* 444 */       ItemStack leggings = p.getInventory().getLeggings();
/*     */ 
/*     */       
/* 447 */       if (leggings.getType() == Material.LEATHER_LEGGINGS) {
/* 448 */         armourVal += 4;
/* 449 */       } else if (leggings.getType() == Material.IRON_LEGGINGS) {
/* 450 */         armourVal += 2;
/* 451 */       } else if (leggings.getType() == Material.GOLDEN_LEGGINGS) {
/* 452 */         armourVal += 2;
/* 453 */       } else if (leggings.getType() == Material.CHAINMAIL_LEGGINGS) {
/* 454 */         armourVal++;
/* 455 */       } else if (leggings.getType() == Material.DIAMOND_LEGGINGS) {
/* 456 */         armourVal += 2;
/* 457 */       } else if (leggings.getType() == Material.NETHERITE_LEGGINGS) {
/* 458 */         armourVal++;
/*     */       } 
/*     */     } 
/*     */     
/* 462 */     if (p.getInventory().getBoots() != null) {
/*     */       
/* 464 */       ItemStack boots = p.getInventory().getBoots();
/*     */ 
/*     */       
/* 467 */       if (boots.getType() == Material.LEATHER_BOOTS) {
/* 468 */         armourVal += 2;
/* 469 */       } else if (boots.getType() == Material.IRON_BOOTS) {
/* 470 */         armourVal++;
/* 471 */       } else if (boots.getType() == Material.GOLDEN_BOOTS) {
/* 472 */         armourVal++;
/* 473 */       } else if (boots.getType() == Material.CHAINMAIL_BOOTS) {
/* 474 */         armourVal++;
/* 475 */       } else if (boots.getType() == Material.DIAMOND_BOOTS) {
/* 476 */         armourVal += 2;
/* 477 */       } else if (boots.getType() == Material.NETHERITE_BOOTS) {
/* 478 */         armourVal++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 485 */     return armourVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void heatEffects(Player p, double temp) {
/* 490 */     if (this.disease.debug) Bukkit.getLogger().info(p.getName() + " heat effect level " + this.tempLevel); 
/* 491 */     if (this.tempLevel == -1) {
/*     */ 
/*     */       
/* 494 */       p.getWorld().playEffect(p.getEyeLocation(), Effect.SMOKE, 2);
/*     */     }
/* 496 */     else if (this.tempLevel == 1) {
/*     */ 
/*     */       
/* 499 */       p.getWorld().spawnParticle(Particle.DRIP_WATER, p.getEyeLocation(), 20, 0.0D, 1.0D, -1.0D);
/*     */     } 
/*     */     
/* 502 */     this.tempLevel = 0;
/*     */     
/* 504 */     if (this.disease.debug) Bukkit.getLogger().info("ambiant temp = " + temp);
/*     */     
/* 506 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/* 507 */     if (this.disease.debug) Bukkit.getLogger().info("player temp before alteration = " + dPlayer.temp);
/*     */ 
/*     */     
/* 510 */     if (temp <= 0.0D) {
/*     */       
/* 512 */       if (temp < 0.0D) temp = 0.0D - temp; 
/* 513 */       if (temp == 0.0D) temp = 1.0D;
/*     */       
/* 515 */       if (dPlayer.temp <= 37.0D) {
/* 516 */         dPlayer.temp -= temp / 100.0D;
/* 517 */       } else if (dPlayer.temp > 37.0D) {
/*     */         
/* 519 */         dPlayer.temp -= 0.3D;
/*     */       }
/*     */     
/* 522 */     } else if (temp <= 5.0D) {
/*     */       
/* 524 */       if (dPlayer.temp <= 37.0D) {
/* 525 */         dPlayer.temp -= temp / 100.0D;
/* 526 */       } else if (dPlayer.temp > 37.0D) {
/*     */         
/* 528 */         dPlayer.temp -= 0.2D;
/*     */       }
/*     */     
/* 531 */     } else if (temp <= 30.0D && temp > 5.0D) {
/*     */       
/* 533 */       if (dPlayer.temp <= 37.0D) {
/* 534 */         dPlayer.temp += 0.1D;
/* 535 */       } else if (dPlayer.temp > 37.0D) {
/*     */         
/* 537 */         dPlayer.temp -= 0.1D;
/*     */       }
/*     */     
/* 540 */     } else if (temp > 30.0D) {
/*     */       
/* 542 */       if (dPlayer.temp <= 37.0D) {
/* 543 */         dPlayer.temp += 0.4D;
/* 544 */       } else if (dPlayer.temp >= 37.0D) {
/*     */         
/* 546 */         dPlayer.temp += temp / 100.0D;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 551 */     this.disease.dPlayers.put(p.getUniqueId().toString(), dPlayer);
/*     */     
/* 553 */     if (this.disease.debug) Bukkit.getLogger().info(p.getName() + " calc heat effects - player temp = " + dPlayer.temp);
/*     */ 
/*     */     
/* 556 */     if (dPlayer.temp < 30.0D) {
/* 557 */       if (this.disease.debug) Bukkit.getLogger().info(p.getName() + "  heat effects for under 30 = " + dPlayer.temp); 
/* 558 */       p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, this.disease.ds.timeBetweenChecks, 3));
/* 559 */       p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, this.disease.ds.timeBetweenChecks, 3));
/* 560 */       p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.disease.ds.timeBetweenChecks, 1));
/* 561 */       p.addPotionEffect(new PotionEffect(PotionEffectType.HARM, this.disease.ds.timeBetweenChecks, 1));
/*     */     }
/* 563 */     else if (dPlayer.temp < 32.0D) {
/*     */       
/* 565 */       if (this.disease.debug) Bukkit.getLogger().info(p.getName() + "  heat effects for under 32 = " + dPlayer.temp); 
/* 566 */       p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, this.disease.ds.timeBetweenChecks, 3));
/* 567 */       p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.disease.ds.timeBetweenChecks, 1));
/*     */     }
/* 569 */     else if (dPlayer.temp < 34.0D) {
/*     */       
/* 571 */       if (this.disease.debug) Bukkit.getLogger().info(p.getName() + "  heat effects for under 34 = " + dPlayer.temp); 
/* 572 */       p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.disease.ds.timeBetweenChecks, 1));
/*     */     } 
/*     */ 
/*     */     
/* 576 */     if (dPlayer.temp > 42.0D) {
/*     */       
/* 578 */       if (this.disease.debug) Bukkit.getLogger().info(p.getName() + "  heat effects for over 42 = " + dPlayer.temp); 
/* 579 */       p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.disease.ds.timeBetweenChecks, 3));
/* 580 */       p.addPotionEffect(new PotionEffect(PotionEffectType.HARM, this.disease.ds.timeBetweenChecks, 1));
/* 581 */       p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.disease.ds.timeBetweenChecks, 3));
/* 582 */     } else if (dPlayer.temp > 40.0D) {
/*     */       
/* 584 */       if (this.disease.debug) Bukkit.getLogger().info(p.getName() + "  heat effects for over 44 = " + dPlayer.temp); 
/* 585 */       p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.disease.ds.timeBetweenChecks, 3));
/* 586 */       p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.disease.ds.timeBetweenChecks, 3));
/*     */     }
/* 588 */     else if (dPlayer.temp > 38.0D) {
/*     */       
/* 590 */       if (this.disease.debug) Bukkit.getLogger().info(p.getName() + "  heat effects for over 38 = " + dPlayer.temp); 
/* 591 */       p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.disease.ds.timeBetweenChecks, 3));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\calculate\CalculateTemperature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */