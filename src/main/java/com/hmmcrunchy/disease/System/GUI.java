/*     */ package com.hmmcrunchy.disease.System;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.classes.DDisease;
/*     */ import com.hmmcrunchy.disease.classes.DPlayer;
/*     */ import com.hmmcrunchy.disease.classes.Remedy;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
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
/*     */ 
/*     */ public class GUI
/*     */ {
/*     */   Disease disease;
/*     */   
/*     */   public GUI(Disease diseasePlugin) {
/*  37 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void openBook(Player player) {
/*  44 */     Inventory book = Bukkit.createInventory(null, 27, "Disease - Menu");
/*     */ 
/*     */     
/*  47 */     ItemStack HealthItem = new ItemStack(Material.GOLDEN_APPLE);
/*  48 */     ItemMeta healthMeta = HealthItem.getItemMeta();
/*  49 */     healthMeta.setDisplayName("Health");
/*  50 */     List<String> loreList = new ArrayList<>();
/*  51 */     loreList.clear();
/*  52 */     loreList.add("Check Your Health");
/*  53 */     healthMeta.setLore(loreList);
/*  54 */     HealthItem.setItemMeta(healthMeta);
/*     */     
/*  56 */     book.setItem(0, HealthItem);
/*     */ 
/*     */     
/*  59 */     ItemStack tempItem = new ItemStack(Material.TORCH);
/*  60 */     ItemMeta tempMeta = tempItem.getItemMeta();
/*  61 */     tempMeta.setDisplayName("Temperature");
/*  62 */     List<String> loreTempList = new ArrayList<>();
/*  63 */     loreTempList.clear();
/*  64 */     loreTempList.add("Check your temperature and surroundings");
/*  65 */     tempMeta.setLore(loreTempList);
/*  66 */     tempItem.setItemMeta(tempMeta);
/*     */     
/*  68 */     book.setItem(1, tempItem);
/*     */ 
/*     */     
/*  71 */     ItemStack immunityItem = new ItemStack(Material.CHAIN);
/*  72 */     ItemMeta immunityMeta = immunityItem.getItemMeta();
/*  73 */     immunityMeta.setDisplayName("Immunity");
/*  74 */     List<String> loreImmunityList = new ArrayList<>();
/*  75 */     loreImmunityList.clear();
/*  76 */     loreImmunityList.add("Check your immunity to disease");
/*  77 */     immunityMeta.setLore(loreImmunityList);
/*  78 */     immunityItem.setItemMeta(immunityMeta);
/*     */     
/*  80 */     book.setItem(2, immunityItem);
/*     */     
/*  82 */     ItemStack diseaseItem = new ItemStack(Material.BOOK);
/*  83 */     ItemMeta diseaseMeta = diseaseItem.getItemMeta();
/*  84 */     diseaseMeta.setDisplayName("Diseases");
/*  85 */     List<String> loreDiseaseList = new ArrayList<>();
/*  86 */     loreDiseaseList.clear();
/*  87 */     loreDiseaseList.add("List all diseases");
/*  88 */     diseaseMeta.setLore(loreDiseaseList);
/*  89 */     diseaseItem.setItemMeta(diseaseMeta);
/*     */     
/*  91 */     book.setItem(9, diseaseItem);
/*     */     
/*  93 */     ItemStack remedyItem = new ItemStack(Material.GLASS_BOTTLE);
/*  94 */     ItemMeta remedyMeta = remedyItem.getItemMeta();
/*  95 */     remedyMeta.setDisplayName("Remedies");
/*  96 */     List<String> loreRemedyList = new ArrayList<>();
/*  97 */     loreRemedyList.clear();
/*  98 */     loreRemedyList.add("Discover remedies");
/*  99 */     remedyMeta.setLore(loreRemedyList);
/* 100 */     remedyItem.setItemMeta(remedyMeta);
/*     */     
/* 102 */     book.setItem(18, remedyItem);
/*     */ 
/*     */     
/* 105 */     player.openInventory(book);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inventoryClick(InventoryClickEvent event) {
/* 111 */     ItemStack item = event.getCurrentItem();
/* 112 */     Player player = (Player)event.getWhoClicked();
/*     */     
/* 114 */     InventoryView inventory = event.getView();
/* 115 */     Boolean shift = Boolean.valueOf(event.isShiftClick());
/*     */     
/* 117 */     if (this.disease.debug) this.disease.getLogger().info(player.getName() + " inventory click");
/*     */     
/* 119 */     String invName = inventory.getTitle();
/*     */     
/* 121 */     if (event.getCurrentItem() == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 126 */     if (invName.equals("Disease - Menu")) {
/*     */       
/* 128 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " clicked disease menu");
/*     */ 
/*     */ 
/*     */       
/* 132 */       if (event.getRawSlot() >= 0 && event.getRawSlot() <= 26 && event.getCurrentItem().getType() != Material.AIR)
/*     */       
/*     */       { 
/* 135 */         if (this.disease.debug) this.disease.getLogger().info(player.getName() + " clicked category");
/*     */         
/* 137 */         event.setCancelled(true);
/*     */         
/* 139 */         String catName = event.getCurrentItem().getItemMeta().getDisplayName();
/*     */         
/* 141 */         if (catName.equalsIgnoreCase("Health"))
/*     */         {
/* 143 */           openCategory(player, "health");
/*     */         }
/* 145 */         else if (catName.equalsIgnoreCase("Temperature"))
/*     */         {
/* 147 */           openCategory(player, "temperature");
/*     */         }
/* 149 */         else if (catName.equalsIgnoreCase("Immunity"))
/*     */         {
/* 151 */           openCategory(player, "immunity");
/*     */         }
/* 153 */         else if (catName.equalsIgnoreCase("Diseases"))
/*     */         {
/* 155 */           openCategory(player, "diseases");
/*     */         }
/* 157 */         else if (catName.equalsIgnoreCase("Remedies"))
/*     */         {
/* 159 */           openCategory(player, "remedies");
/*     */         }
/* 161 */         else if (catName.equalsIgnoreCase("Back"))
/*     */         {
/* 163 */           openBook(player);
/*     */         
/*     */         }
/*     */         
/*     */          }
/*     */       
/* 169 */       else if (this.disease.debug) { this.disease.getLogger().info("not in  disease menu"); }
/*     */ 
/*     */     
/* 172 */     } else if (invName.equals("Diseases")) {
/*     */       
/* 174 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " clicked disease list menu"); 
/* 175 */       event.setCancelled(true);
/*     */       
/* 177 */       if (event.getCurrentItem().getType() == Material.BARRIER)
/*     */       {
/* 179 */         openBook(player);
/*     */       }
/*     */     }
/* 182 */     else if (invName.equalsIgnoreCase("Remedies")) {
/*     */       
/* 184 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " clicked remedy list menu"); 
/* 185 */       event.setCancelled(true);
/*     */       
/* 187 */       if (event.getCurrentItem().getType() == Material.BARRIER)
/*     */       {
/* 189 */         openBook(player);
/*     */       }
/*     */     }
/* 192 */     else if (invName.equalsIgnoreCase("Temperature")) {
/*     */       
/* 194 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " clicked temp menu"); 
/* 195 */       event.setCancelled(true);
/*     */       
/* 197 */       if (event.getCurrentItem().getType() == Material.BARRIER)
/*     */       {
/* 199 */         openBook(player);
/*     */       }
/*     */     }
/* 202 */     else if (invName.equalsIgnoreCase("Immunity")) {
/*     */       
/* 204 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " clicked immnuity menu"); 
/* 205 */       event.setCancelled(true);
/*     */       
/* 207 */       if (event.getCurrentItem().getType() == Material.BARRIER)
/*     */       {
/* 209 */         openBook(player);
/*     */       }
/*     */     }
/* 212 */     else if (invName.equalsIgnoreCase("Health")) {
/*     */       
/* 214 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " clicked health menu"); 
/* 215 */       event.setCancelled(true);
/*     */       
/* 217 */       if (event.getCurrentItem().getType() == Material.BARRIER)
/*     */       {
/* 219 */         openBook(player);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void openCategory(Player player, String category) {
/* 230 */     Inventory catView = Bukkit.createInventory(null, 27, "Disease - Menu");
/* 231 */     if (this.disease.debug) this.disease.getLogger().info(player.getName() + " opening category menu = " + category);
/*     */ 
/*     */     
/* 234 */     if (category.equalsIgnoreCase("diseases")) {
/*     */       
/* 236 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " in diseases"); 
/* 237 */       catView = DiseaseInventory();
/*     */     }
/* 239 */     else if (category.equalsIgnoreCase("remedies")) {
/*     */       
/* 241 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " in remedies"); 
/* 242 */       catView = RemedyInventory();
/*     */     }
/* 244 */     else if (category.equalsIgnoreCase("temperature")) {
/*     */       
/* 246 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " in temp"); 
/* 247 */       catView = TemperatureInventory(player);
/*     */     }
/* 249 */     else if (category.equalsIgnoreCase("immunity")) {
/*     */       
/* 251 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " in immunity"); 
/* 252 */       catView = immunityInventory(player);
/*     */     }
/* 254 */     else if (category.equalsIgnoreCase("health")) {
/*     */       
/* 256 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " in health"); 
/* 257 */       catView = HealthInventory(player);
/*     */     }
/*     */     else {
/*     */       
/* 261 */       if (this.disease.debug) this.disease.getLogger().info(player.getName() + " filling items into category ");
/*     */ 
/*     */       
/* 264 */       ItemStack backItem = new ItemStack(Material.BARRIER);
/* 265 */       ItemMeta backMeta = backItem.getItemMeta();
/* 266 */       backMeta.setDisplayName("Back");
/* 267 */       List<String> loreList = new ArrayList<>();
/* 268 */       loreList.clear();
/* 269 */       loreList.add("Back to previous Menu");
/* 270 */       backMeta.setLore(loreList);
/* 271 */       backItem.setItemMeta(backMeta);
/*     */       
/* 273 */       catView.setItem(0, backItem);
/*     */     } 
/*     */ 
/*     */     
/* 277 */     player.openInventory(catView);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Inventory DiseaseInventory() {
/* 284 */     Inventory catView = Bukkit.createInventory(null, 27, "Diseases");
/*     */     
/* 286 */     ItemStack backItem = new ItemStack(Material.BARRIER);
/* 287 */     ItemMeta backMeta = backItem.getItemMeta();
/* 288 */     backMeta.setDisplayName("Back");
/* 289 */     List<String> loreList = new ArrayList<>();
/* 290 */     loreList.clear();
/* 291 */     loreList.add("Back to previous Menu");
/* 292 */     backMeta.setLore(loreList);
/* 293 */     backItem.setItemMeta(backMeta);
/*     */     
/* 295 */     catView.setItem(0, backItem);
/*     */ 
/*     */     
/* 298 */     int i = 1;
/*     */     
/* 300 */     for (DDisease dDisease : this.disease.diseases.values()) {
/*     */ 
/*     */ 
/*     */       
/* 304 */       ItemStack itemInfo = new ItemStack(Material.SLIME_BLOCK);
/* 305 */       ItemMeta meta = itemInfo.getItemMeta();
/* 306 */       meta.setDisplayName(dDisease.name);
/* 307 */       List<String> diseaseLoreList = new ArrayList<>();
/* 308 */       diseaseLoreList.clear();
/* 309 */       diseaseLoreList.add(dDisease.description);
/* 310 */       diseaseLoreList.add("Infection cause: " + dDisease.infectionMethod + " " + dDisease.infectionMethodType);
/* 311 */       diseaseLoreList.add("Cure: " + dDisease.cure);
/* 312 */       meta.setLore(diseaseLoreList);
/* 313 */       itemInfo.setItemMeta(meta);
/*     */       
/* 315 */       catView.setItem(i, itemInfo);
/*     */       
/* 317 */       i++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 322 */     return catView;
/*     */   }
/*     */ 
/*     */   
/*     */   Inventory RemedyInventory() {
/* 327 */     Inventory catView = Bukkit.createInventory(null, 27, "Remedies");
/*     */     
/* 329 */     ItemStack backItem = new ItemStack(Material.BARRIER);
/* 330 */     ItemMeta backMeta = backItem.getItemMeta();
/* 331 */     backMeta.setDisplayName("Back");
/* 332 */     List<String> loreList = new ArrayList<>();
/* 333 */     loreList.clear();
/* 334 */     loreList.add("Back to previous Menu");
/* 335 */     backMeta.setLore(loreList);
/* 336 */     backItem.setItemMeta(backMeta);
/*     */     
/* 338 */     catView.setItem(0, backItem);
/*     */ 
/*     */     
/* 341 */     int i = 1;
/*     */     
/* 343 */     for (Remedy remedy : this.disease.remedies.values()) {
/*     */ 
/*     */ 
/*     */       
/* 347 */       ItemStack itemInfo = new ItemStack(Material.GLASS_BOTTLE);
/* 348 */       ItemMeta meta = itemInfo.getItemMeta();
/* 349 */       meta.setDisplayName(remedy.name);
/* 350 */       List<String> remedyLoreList = new ArrayList<>();
/* 351 */       remedyLoreList.clear();
/* 352 */       remedyLoreList.add(remedy.description);
/* 353 */       remedyLoreList.add("Crafted: " + remedy.craftDescription);
/* 354 */       remedyLoreList.add("Cures: " + remedy.curedDisease);
/* 355 */       meta.setLore(remedyLoreList);
/* 356 */       itemInfo.setItemMeta(meta);
/*     */       
/* 358 */       catView.setItem(i, itemInfo);
/*     */       
/* 360 */       i++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 365 */     return catView;
/*     */   }
/*     */ 
/*     */   
/*     */   Inventory immunityInventory(Player player) {
/* 370 */     Inventory catView = Bukkit.createInventory(null, 27, "Immunity");
/*     */     
/* 372 */     ItemStack backItem = new ItemStack(Material.BARRIER);
/* 373 */     ItemMeta backMeta = backItem.getItemMeta();
/* 374 */     backMeta.setDisplayName("Back");
/* 375 */     List<String> loreList = new ArrayList<>();
/* 376 */     loreList.clear();
/* 377 */     loreList.add("Back to previous Menu");
/* 378 */     backMeta.setLore(loreList);
/* 379 */     backItem.setItemMeta(backMeta);
/*     */     
/* 381 */     catView.setItem(0, backItem);
/*     */ 
/*     */ 
/*     */     
/* 385 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(player.getUniqueId().toString());
/* 386 */     int i = 1;
/*     */     
/* 388 */     for (String immunity : dPlayer.dImmunity.keySet()) {
/*     */ 
/*     */ 
/*     */       
/* 392 */       ItemStack itemInfo = new ItemStack(Material.NETHER_STAR);
/* 393 */       ItemMeta meta = itemInfo.getItemMeta();
/* 394 */       meta.setDisplayName(immunity);
/* 395 */       List<String> remedyLoreList = new ArrayList<>();
/* 396 */       remedyLoreList.clear();
/* 397 */       remedyLoreList.add(Integer.toString(((Integer)dPlayer.dImmunity.get(immunity)).intValue()));
/* 398 */       meta.setLore(remedyLoreList);
/* 399 */       itemInfo.setItemMeta(meta);
/*     */       
/* 401 */       catView.setItem(i, itemInfo);
/*     */       
/* 403 */       i++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 408 */     return catView;
/*     */   }
/*     */ 
/*     */   
/*     */   Inventory TemperatureInventory(Player player) {
/* 413 */     Inventory catView = Bukkit.createInventory(null, 27, "Temperature");
/*     */     
/* 415 */     ItemStack backItem = new ItemStack(Material.BARRIER);
/* 416 */     ItemMeta backMeta = backItem.getItemMeta();
/* 417 */     backMeta.setDisplayName("Back");
/* 418 */     List<String> loreList = new ArrayList<>();
/* 419 */     loreList.clear();
/* 420 */     loreList.add("Back to previous Menu");
/* 421 */     backMeta.setLore(loreList);
/* 422 */     backItem.setItemMeta(backMeta);
/*     */     
/* 424 */     catView.setItem(0, backItem);
/*     */     
/* 426 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(player.getUniqueId().toString());
/* 427 */     double bodyTemp = (Math.round(dPlayer.temp * 100.0D) / 100L);
/* 428 */     double localTemp = this.disease.ct.calcHeat(player, true);
/*     */     
/* 430 */     double regionTemp = (int)this.disease.ct.locationTemp(player);
/* 431 */     double armour = this.disease.ct.calculateArmourHeatValue(player);
/* 432 */     double heatHeld = this.disease.ct.detectHeldHeat(player);
/* 433 */     double heatSources = this.disease.ct.detectHeat(player);
/* 434 */     double waterHeatLoss = this.disease.ct.isInWater(player);
/* 435 */     double storm = this.disease.ct.isRaining(player);
/*     */     
/* 437 */     ItemStack bodyTempIconItem = new ItemStack(Material.PLAYER_HEAD);
/* 438 */     ItemMeta bodyTempIconMeta = bodyTempIconItem.getItemMeta();
/* 439 */     bodyTempIconMeta.setDisplayName("Your personal temperature");
/* 440 */     bodyTempIconItem.setItemMeta(bodyTempIconMeta);
/* 441 */     catView.setItem(2, bodyTempIconItem);
/*     */ 
/*     */     
/* 444 */     ItemStack bodyTempItem = new ItemStack(getMaterial(bodyTemp, "body"));
/* 445 */     ItemMeta bodyTempMeta = bodyTempItem.getItemMeta();
/* 446 */     bodyTempMeta.setDisplayName("Body Temperature");
/* 447 */     List<String> lorebodytempList = new ArrayList<>();
/* 448 */     lorebodytempList.clear();
/* 449 */     lorebodytempList.add(Double.toString(bodyTemp) + "C / " + Double.toString(this.disease.messaging.getFahrenheit(bodyTemp)) + "F");
/* 450 */     bodyTempMeta.setLore(lorebodytempList);
/* 451 */     bodyTempItem.setItemMeta(bodyTempMeta);
/*     */     
/* 453 */     catView.setItem(3, bodyTempItem);
/*     */ 
/*     */     
/* 456 */     ItemStack ambientTempItem = new ItemStack(getMaterial(localTemp, "ambient"));
/* 457 */     ItemMeta ambientTempMeta = ambientTempItem.getItemMeta();
/* 458 */     ambientTempMeta.setDisplayName("Ambient Temperature");
/* 459 */     List<String> loreAmbientTempList = new ArrayList<>();
/* 460 */     loreAmbientTempList.clear();
/* 461 */     loreAmbientTempList.add(Double.toString(localTemp) + "C / " + Double.toString(this.disease.messaging.getFahrenheit(localTemp)) + "F");
/* 462 */     ambientTempMeta.setLore(loreAmbientTempList);
/* 463 */     ambientTempItem.setItemMeta(ambientTempMeta);
/*     */     
/* 465 */     catView.setItem(5, ambientTempItem);
/*     */     
/* 467 */     ItemStack ambientTempIconItem = new ItemStack(Material.FERN);
/* 468 */     ItemMeta ambientTempIconMeta = ambientTempIconItem.getItemMeta();
/* 469 */     ambientTempIconMeta.setDisplayName("Temperature in the area around you");
/* 470 */     ambientTempIconItem.setItemMeta(ambientTempIconMeta);
/* 471 */     catView.setItem(6, ambientTempIconItem);
/*     */ 
/*     */ 
/*     */     
/* 475 */     ItemStack regionTempIconItem = new ItemStack(Material.GRASS_BLOCK);
/* 476 */     ItemMeta regionTempIconMeta = regionTempIconItem.getItemMeta();
/* 477 */     regionTempIconMeta.setDisplayName("Temperature of the block you are on");
/* 478 */     regionTempIconItem.setItemMeta(regionTempIconMeta);
/* 479 */     catView.setItem(10, regionTempIconItem);
/*     */     
/* 481 */     ItemStack armourHeatIconItem = new ItemStack(Material.DIAMOND_CHESTPLATE);
/* 482 */     ItemMeta armourHeatIconMeta = armourHeatIconItem.getItemMeta();
/* 483 */     armourHeatIconMeta.setDisplayName("Amount of armour you are wearing");
/* 484 */     armourHeatIconItem.setItemMeta(armourHeatIconMeta);
/* 485 */     catView.setItem(11, armourHeatIconItem);
/*     */     
/* 487 */     ItemStack heldHeatIconItem = new ItemStack(Material.TORCH);
/* 488 */     ItemMeta heldHeatIconMeta = heldHeatIconItem.getItemMeta();
/* 489 */     heldHeatIconMeta.setDisplayName("Hot items in your hand");
/* 490 */     heldHeatIconItem.setItemMeta(heldHeatIconMeta);
/* 491 */     catView.setItem(12, heldHeatIconItem);
/*     */     
/* 493 */     ItemStack ambientHeatIconItem = new ItemStack(Material.CAMPFIRE);
/* 494 */     ItemMeta ambientHeatIconMeta = ambientHeatIconItem.getItemMeta();
/* 495 */     ambientHeatIconMeta.setDisplayName("Heat near to you");
/* 496 */     ambientHeatIconItem.setItemMeta(ambientHeatIconMeta);
/* 497 */     catView.setItem(13, ambientHeatIconItem);
/*     */     
/* 499 */     ItemStack waterIconItem = new ItemStack(Material.WATER_BUCKET);
/* 500 */     ItemMeta waterHeatIconMeta = waterIconItem.getItemMeta();
/* 501 */     waterHeatIconMeta.setDisplayName("Standing in water cools you");
/* 502 */     waterIconItem.setItemMeta(waterHeatIconMeta);
/* 503 */     catView.setItem(14, waterIconItem);
/*     */     
/* 505 */     ItemStack stormIconItem = new ItemStack(Material.NETHER_STAR);
/* 506 */     ItemMeta stormHeatIconMeta = stormIconItem.getItemMeta();
/* 507 */     stormHeatIconMeta.setDisplayName("Storms cool you down");
/* 508 */     stormIconItem.setItemMeta(stormHeatIconMeta);
/* 509 */     catView.setItem(15, stormIconItem);
/*     */ 
/*     */     
/* 512 */     ItemStack regionTempItem = new ItemStack(getMaterial(regionTemp, "region"));
/* 513 */     ItemMeta regionTempMeta = regionTempItem.getItemMeta();
/* 514 */     regionTempMeta.setDisplayName("Region Temperature");
/* 515 */     List<String> loreRegionTempList = new ArrayList<>();
/* 516 */     loreRegionTempList.clear();
/* 517 */     loreRegionTempList.add(Double.toString(regionTemp) + "C / " + Double.toString(this.disease.messaging.getFahrenheit(regionTemp)) + "F");
/* 518 */     regionTempMeta.setLore(loreRegionTempList);
/* 519 */     regionTempItem.setItemMeta(regionTempMeta);
/*     */     
/* 521 */     catView.setItem(19, regionTempItem);
/*     */ 
/*     */     
/* 524 */     ItemStack armourHeatItem = new ItemStack(getMaterial(armour, "pos"));
/* 525 */     ItemMeta armourHeatMeta = armourHeatItem.getItemMeta();
/* 526 */     armourHeatMeta.setDisplayName("Armour Heat");
/* 527 */     List<String> loreArmourHeatList = new ArrayList<>();
/* 528 */     loreArmourHeatList.clear();
/* 529 */     loreArmourHeatList.add("+" + Double.toString(armour) + "C / " + Double.toString(this.disease.messaging.getFahrenheit(armour)) + "F");
/* 530 */     armourHeatMeta.setLore(loreArmourHeatList);
/* 531 */     armourHeatItem.setItemMeta(armourHeatMeta);
/*     */     
/* 533 */     catView.setItem(20, armourHeatItem);
/*     */ 
/*     */     
/* 536 */     ItemStack heldHeatItem = new ItemStack(getMaterial(heatHeld, "pos"));
/* 537 */     ItemMeta heldHeatMeta = heldHeatItem.getItemMeta();
/* 538 */     heldHeatMeta.setDisplayName("Held Heat Sources");
/* 539 */     List<String> loreHeldHeatList = new ArrayList<>();
/* 540 */     loreHeldHeatList.clear();
/* 541 */     loreHeldHeatList.add("+" + Double.toString(heatHeld) + "C / " + Double.toString(this.disease.messaging.getFahrenheit(heatHeld)) + "F");
/* 542 */     heldHeatMeta.setLore(loreHeldHeatList);
/* 543 */     heldHeatItem.setItemMeta(heldHeatMeta);
/*     */     
/* 545 */     catView.setItem(21, heldHeatItem);
/*     */ 
/*     */     
/* 548 */     ItemStack ambientHeatItem = new ItemStack(getMaterial(heatSources, "pos"));
/* 549 */     ItemMeta ambientHeatMeta = ambientHeatItem.getItemMeta();
/* 550 */     ambientHeatMeta.setDisplayName("Ambient Heat Sources");
/* 551 */     List<String> loreAmbientHeatList = new ArrayList<>();
/* 552 */     loreAmbientHeatList.clear();
/* 553 */     loreAmbientHeatList.add("+" + Double.toString(heatSources) + "C / " + Double.toString(this.disease.messaging.getFahrenheit(heatSources)) + "F");
/* 554 */     ambientHeatMeta.setLore(loreAmbientHeatList);
/* 555 */     ambientHeatItem.setItemMeta(ambientHeatMeta);
/*     */     
/* 557 */     catView.setItem(22, ambientHeatItem);
/*     */ 
/*     */     
/* 560 */     ItemStack waterHeatItem = new ItemStack(getMaterial(waterHeatLoss, "neg"));
/* 561 */     ItemMeta waterHeatMeta = waterHeatItem.getItemMeta();
/* 562 */     waterHeatMeta.setDisplayName("Water Heat Loss");
/* 563 */     List<String> loreWaterHeatList = new ArrayList<>();
/* 564 */     loreWaterHeatList.clear();
/* 565 */     loreWaterHeatList.add("-" + Double.toString(waterHeatLoss) + "C / " + Double.toString(this.disease.messaging.getFahrenheit(waterHeatLoss)) + "F");
/* 566 */     waterHeatMeta.setLore(loreWaterHeatList);
/* 567 */     waterHeatItem.setItemMeta(waterHeatMeta);
/*     */     
/* 569 */     catView.setItem(23, waterHeatItem);
/*     */ 
/*     */     
/* 572 */     ItemStack stormHeatItem = new ItemStack(getMaterial(storm, "neg"));
/* 573 */     ItemMeta stormHeatMeta = stormHeatItem.getItemMeta();
/* 574 */     stormHeatMeta.setDisplayName("Storm Heat Loss");
/* 575 */     List<String> loreStormHeatList = new ArrayList<>();
/* 576 */     loreStormHeatList.clear();
/* 577 */     loreStormHeatList.add("-" + Double.toString(storm) + "C / " + Double.toString(this.disease.messaging.getFahrenheit(storm)) + "F");
/* 578 */     stormHeatMeta.setLore(loreStormHeatList);
/* 579 */     stormHeatItem.setItemMeta(stormHeatMeta);
/*     */     
/* 581 */     catView.setItem(24, stormHeatItem);
/*     */ 
/*     */     
/* 584 */     return catView;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   Inventory HealthInventory(Player player) {
/* 590 */     Inventory catView = Bukkit.createInventory(null, 27, "Health");
/*     */     
/* 592 */     ItemStack backItem = new ItemStack(Material.BARRIER);
/* 593 */     ItemMeta backMeta = backItem.getItemMeta();
/* 594 */     backMeta.setDisplayName("Back");
/* 595 */     List<String> loreList = new ArrayList<>();
/* 596 */     loreList.clear();
/* 597 */     loreList.add("Back to previous Menu");
/* 598 */     backMeta.setLore(loreList);
/* 599 */     backItem.setItemMeta(backMeta);
/*     */     
/* 601 */     catView.setItem(0, backItem);
/*     */     
/* 603 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(player.getUniqueId().toString());
/* 604 */     double sickness = dPlayer.sickness;
/* 605 */     double temp = dPlayer.temp;
/* 606 */     String infection = dPlayer.infection;
/* 607 */     DDisease dDisease = (DDisease)this.disease.diseases.get(dPlayer.infection);
/*     */ 
/*     */     
/* 610 */     ItemStack headItem = new ItemStack(getBody(sickness, "helmet"));
/* 611 */     ItemMeta headMeta = headItem.getItemMeta();
/* 612 */     headMeta.setDisplayName("Player");
/* 613 */     headItem.setItemMeta(headMeta);
/* 614 */     catView.setItem(2, headItem);
/*     */     
/* 616 */     ItemStack chestItem = new ItemStack(getBody(sickness, "chestplate"));
/* 617 */     ItemMeta chestItemMeta = chestItem.getItemMeta();
/* 618 */     chestItemMeta.setDisplayName("Player");
/* 619 */     chestItem.setItemMeta(chestItemMeta);
/* 620 */     catView.setItem(11, chestItem);
/*     */     
/* 622 */     ItemStack leggingItem = new ItemStack(getBody(sickness, "leggings"));
/* 623 */     ItemMeta leggingItemMeta = leggingItem.getItemMeta();
/* 624 */     leggingItemMeta.setDisplayName("Player");
/* 625 */     leggingItem.setItemMeta(leggingItemMeta);
/* 626 */     catView.setItem(20, leggingItem);
/*     */     
/* 628 */     ItemStack sickItem = new ItemStack(Material.SLIME_BALL);
/* 629 */     ItemMeta bootItemMeta = sickItem.getItemMeta();
/* 630 */     bootItemMeta.setDisplayName("Sickness");
/* 631 */     sickItem.setItemMeta(bootItemMeta);
/* 632 */     catView.setItem(13, sickItem);
/*     */     
/* 634 */     if (infection.equals("none")) {
/*     */       
/* 636 */       ItemStack diseaseItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
/* 637 */       ItemMeta diseaseItemMeta = diseaseItem.getItemMeta();
/* 638 */       diseaseItemMeta.setDisplayName("In Fine Health");
/* 639 */       List<String> diseaseItemList = new ArrayList<>();
/* 640 */       diseaseItemList.clear();
/* 641 */       diseaseItemList.add("you have no infections or injuries");
/* 642 */       diseaseItemMeta.setLore(diseaseItemList);
/* 643 */       diseaseItem.setItemMeta(diseaseItemMeta);
/* 644 */       catView.setItem(14, diseaseItem);
/*     */     }
/*     */     else {
/*     */       
/* 648 */       ItemStack diseaseItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
/* 649 */       ItemMeta diseaseItemMeta = diseaseItem.getItemMeta();
/* 650 */       diseaseItemMeta.setDisplayName("Unwell!");
/* 651 */       List<String> diseaseItemList = new ArrayList<>();
/* 652 */       diseaseItemList.clear();
/* 653 */       diseaseItemList.add("Diagnosis : " + dDisease.name);
/* 654 */       diseaseItemList.add("Sickness  : " + sickness);
/* 655 */       diseaseItemList.add("Infectious: " + dDisease.infectious);
/* 656 */       diseaseItemMeta.setLore(diseaseItemList);
/* 657 */       diseaseItem.setItemMeta(diseaseItemMeta);
/* 658 */       catView.setItem(14, diseaseItem);
/*     */     } 
/*     */     
/* 661 */     ItemStack temperatureItem = new ItemStack(Material.CAMPFIRE);
/* 662 */     ItemMeta temperatureItemMeta = sickItem.getItemMeta();
/* 663 */     temperatureItemMeta.setDisplayName("Temperature");
/* 664 */     temperatureItem.setItemMeta(temperatureItemMeta);
/* 665 */     catView.setItem(16, temperatureItem);
/*     */     
/* 667 */     if (dPlayer.temp <= 34.0D) {
/*     */       
/* 669 */       ItemStack tempItem = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
/* 670 */       ItemMeta tempItemMeta = tempItem.getItemMeta();
/* 671 */       tempItemMeta.setDisplayName("Temperature");
/* 672 */       List<String> tempItemList = new ArrayList<>();
/* 673 */       tempItemList.clear();
/* 674 */       tempItemList.add("Temperature : " + dPlayer.temp);
/* 675 */       tempItemList.add("You are getting too cold!");
/* 676 */       tempItemMeta.setLore(tempItemList);
/* 677 */       tempItem.setItemMeta(tempItemMeta);
/* 678 */       catView.setItem(17, tempItem);
/*     */     }
/* 680 */     else if (dPlayer.temp > 34.0D && dPlayer.temp < 38.0D) {
/*     */       
/* 682 */       ItemStack tempItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
/* 683 */       ItemMeta tempItemMeta = tempItem.getItemMeta();
/* 684 */       tempItemMeta.setDisplayName("Temperature");
/* 685 */       List<String> tempItemList = new ArrayList<>();
/* 686 */       tempItemList.clear();
/* 687 */       tempItemList.add("Temperature : " + (Math.round(dPlayer.temp * 100.0D) / 100L) + "C / " + this.disease.messaging.getFahrenheit((Math.round(dPlayer.temp * 100.0D) / 100L)) + "F");
/* 688 */       tempItemList.add("You are nice and warm");
/* 689 */       tempItemMeta.setLore(tempItemList);
/* 690 */       tempItem.setItemMeta(tempItemMeta);
/* 691 */       catView.setItem(17, tempItem);
/*     */     } else {
/*     */       
/* 694 */       ItemStack tempItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
/* 695 */       ItemMeta tempItemMeta = tempItem.getItemMeta();
/* 696 */       tempItemMeta.setDisplayName("Temperature");
/* 697 */       List<String> tempItemList = new ArrayList<>();
/* 698 */       tempItemList.clear();
/* 699 */       tempItemList.add("Temperature : " + (Math.round(dPlayer.temp * 100.0D) / 100L) + "C / " + this.disease.messaging.getFahrenheit((Math.round(dPlayer.temp * 100.0D) / 100L)) + "F");
/* 700 */       tempItemList.add("You are getting too hot!");
/* 701 */       tempItemMeta.setLore(tempItemList);
/* 702 */       tempItem.setItemMeta(tempItemMeta);
/* 703 */       catView.setItem(17, tempItem);
/*     */     } 
/*     */     
/* 706 */     return catView;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Material getBody(double sickness, String type) {
/* 713 */     if (type.equals("helmet")) {
/*     */       
/* 715 */       if (sickness <= 20.0D)
/* 716 */         return Material.DIAMOND_HELMET; 
/* 717 */       if (sickness > 20.0D && sickness <= 40.0D)
/* 718 */         return Material.GOLDEN_HELMET; 
/* 719 */       if (sickness > 40.0D && sickness <= 60.0D)
/* 720 */         return Material.IRON_HELMET; 
/* 721 */       if (sickness > 60.0D && sickness <= 80.0D)
/* 722 */         return Material.CHAINMAIL_HELMET; 
/* 723 */       if (sickness > 80.0D && sickness <= 100.0D) {
/* 724 */         return Material.LEATHER_HELMET;
/*     */       }
/*     */     }
/* 727 */     else if (type.equals("chestplate")) {
/*     */       
/* 729 */       if (sickness <= 20.0D)
/* 730 */         return Material.DIAMOND_CHESTPLATE; 
/* 731 */       if (sickness > 20.0D && sickness <= 40.0D)
/* 732 */         return Material.GOLDEN_CHESTPLATE; 
/* 733 */       if (sickness > 40.0D && sickness <= 60.0D)
/* 734 */         return Material.IRON_CHESTPLATE; 
/* 735 */       if (sickness > 60.0D && sickness <= 80.0D)
/* 736 */         return Material.CHAINMAIL_CHESTPLATE; 
/* 737 */       if (sickness > 80.0D && sickness <= 100.0D) {
/* 738 */         return Material.LEATHER_CHESTPLATE;
/*     */       }
/* 740 */     } else if (type.equals("leggings")) {
/*     */       
/* 742 */       if (sickness <= 20.0D)
/* 743 */         return Material.DIAMOND_LEGGINGS; 
/* 744 */       if (sickness > 20.0D && sickness <= 40.0D)
/* 745 */         return Material.GOLDEN_LEGGINGS; 
/* 746 */       if (sickness > 40.0D && sickness <= 60.0D)
/* 747 */         return Material.IRON_LEGGINGS; 
/* 748 */       if (sickness > 60.0D && sickness <= 80.0D)
/* 749 */         return Material.CHAINMAIL_LEGGINGS; 
/* 750 */       if (sickness > 80.0D && sickness <= 100.0D) {
/* 751 */         return Material.LEATHER_LEGGINGS;
/*     */       }
/* 753 */     } else if (type.equals("boots")) {
/*     */       
/* 755 */       if (sickness <= 20.0D)
/* 756 */         return Material.DIAMOND_BOOTS; 
/* 757 */       if (sickness > 20.0D && sickness <= 40.0D)
/* 758 */         return Material.GOLDEN_BOOTS; 
/* 759 */       if (sickness > 40.0D && sickness <= 60.0D)
/* 760 */         return Material.IRON_BOOTS; 
/* 761 */       if (sickness > 60.0D && sickness <= 80.0D)
/* 762 */         return Material.CHAINMAIL_BOOTS; 
/* 763 */       if (sickness > 80.0D && sickness <= 100.0D) {
/* 764 */         return Material.LEATHER_BOOTS;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 770 */     return Material.BARRIER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Material getMaterial(double temp, String type) {
/* 777 */     if (type.equalsIgnoreCase("body")) {
/*     */       
/* 779 */       if (temp >= 38.0D)
/* 780 */         return Material.RED_STAINED_GLASS_PANE; 
/* 781 */       if (temp <= 34.0D) {
/* 782 */         return Material.BLUE_STAINED_GLASS_PANE;
/*     */       }
/* 784 */       return Material.GREEN_STAINED_GLASS_PANE;
/*     */     } 
/* 786 */     if (type.equalsIgnoreCase("ambient")) {
/*     */       
/* 788 */       if (temp >= 30.0D)
/* 789 */         return Material.RED_STAINED_GLASS_PANE; 
/* 790 */       if (temp <= 5.0D) {
/* 791 */         return Material.BLUE_STAINED_GLASS_PANE;
/*     */       }
/* 793 */       return Material.GREEN_STAINED_GLASS_PANE;
/*     */     } 
/* 795 */     if (type.equalsIgnoreCase("region")) {
/*     */       
/* 797 */       if (temp >= 30.0D)
/* 798 */         return Material.RED_STAINED_GLASS_PANE; 
/* 799 */       if (temp <= 5.0D) {
/* 800 */         return Material.BLUE_STAINED_GLASS_PANE;
/*     */       }
/* 802 */       return Material.GREEN_STAINED_GLASS_PANE;
/*     */     } 
/*     */     
/* 805 */     if (type.equalsIgnoreCase("pos")) {
/*     */       
/* 807 */       if (temp > 0.0D)
/*     */       {
/* 809 */         return Material.RED_STAINED_GLASS_PANE;
/*     */       }
/*     */       
/* 812 */       return Material.GREEN_STAINED_GLASS_PANE;
/*     */     } 
/*     */     
/* 815 */     if (type.equalsIgnoreCase("neg")) {
/*     */ 
/*     */       
/* 818 */       if (temp > 0.0D)
/*     */       {
/* 820 */         return Material.BLUE_STAINED_GLASS_PANE;
/*     */       }
/*     */       
/* 823 */       return Material.GREEN_STAINED_GLASS_PANE;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 828 */     return Material.GREEN_STAINED_GLASS_PANE;
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\GUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */