/*     */ package com.hmmcrunchy.disease.calculate;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.classes.DDisease;
/*     */ import com.hmmcrunchy.disease.classes.DPlayer;
/*     */ import com.hmmcrunchy.disease.classes.Remedy;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
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
/*     */ public class InteractCalculations
/*     */ {
/*     */   private Disease disease;
/*     */   
/*     */   public InteractCalculations(Disease diseasePlugin) {
/*  37 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPlayerInteraction(Player p, PlayerInteractEvent event) {
/*  44 */     if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - processing interaction");
/*     */     
/*  46 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*  47 */     ItemStack item = event.getItem();
/*     */ 
/*     */ 
/*     */     
/*  51 */     if (event.getAction() == Action.RIGHT_CLICK_AIR) {
/*     */       
/*  53 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " right clicked air");
/*     */       
/*  55 */       if (p.getItemInHand() != null)
/*     */       
/*  57 */       { if (this.disease.debug) this.disease.getLogger().info("has item in hand " + item.getType().name()); 
/*  58 */         if (p.getItemInHand().hasItemMeta()) {
/*     */           
/*  60 */           if (this.disease.debug) this.disease.getLogger().info("item has meta"); 
/*  61 */           if (p.getItemInHand().getItemMeta().getDisplayName() != null) {
/*     */             
/*  63 */             if (this.disease.debug) this.disease.getLogger().info("item has name " + p.getItemInHand().getItemMeta().getDisplayName()); 
/*  64 */             item = p.getItemInHand();
/*     */             
/*  66 */             if (dPlayer.infection.equalsIgnoreCase("none")) {
/*     */               
/*  68 */               if (this.disease.debug) this.disease.getLogger().info("clicked player not infected - look for innoculations");
/*     */               
/*  70 */               for (DDisease dDisease : this.disease.diseases.values()) {
/*     */ 
/*     */                 
/*  73 */                 if (item.getItemMeta().hasDisplayName()) {
/*  74 */                   if (this.disease.debug) this.disease.getLogger().info("item has display name"); 
/*  75 */                   if ((ChatColor.GREEN + dDisease.name + " Vaccine").equalsIgnoreCase(item.getItemMeta().getDisplayName())) {
/*     */                     
/*  77 */                     if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " matches used item name = add to player imunity"); 
/*  78 */                     if (((Remedy)this.disease.remedies.get(dDisease.cure)).innoculationPossible) {
/*     */                       
/*  80 */                       if (this.disease.debug) this.disease.getLogger().info(((Remedy)this.disease.remedies.get(dDisease.cure)).name + " can innoculate - increasing by " + this.disease.ds.vaccineImmunityIncrease); 
/*  81 */                       int resultingImmunity = ((Integer)dPlayer.dImmunity.get(dDisease.name)).intValue() + this.disease.ds.vaccineImmunityIncrease;
/*  82 */                       if (resultingImmunity > 100) resultingImmunity = 100; 
/*  83 */                       dPlayer.dImmunity.put(dDisease.name, Integer.valueOf(resultingImmunity));
/*  84 */                       this.disease.messaging.actionBar(p, dDisease.name + " Immunity increased by " + this.disease.ds.vaccineImmunityIncrease);
/*  85 */                       item.setAmount(item.getAmount() - 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/*     */                       return;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } else {
/*  97 */               if (this.disease.debug) this.disease.getLogger().info("looking for remedy that cures " + dPlayer.infection);
/*     */               
/*  99 */               for (Remedy dRemedy : this.disease.remedies.values()) {
/*     */ 
/*     */ 
/*     */                 
/* 103 */                 if (dRemedy.curedDisease.equals(dPlayer.infection)) {
/*     */                   
/* 105 */                   if (this.disease.debug) this.disease.getLogger().info(dRemedy.name + " - cures the required disease that the player has '" + dRemedy.curedDisease + "'");
/*     */                   
/* 107 */                   if (item.hasItemMeta()) {
/* 108 */                     if (this.disease.debug) this.disease.getLogger().info("has meta"); 
/* 109 */                     if (item.getItemMeta().hasDisplayName()) {
/* 110 */                       if (this.disease.debug) this.disease.getLogger().info("has display name"); 
/* 111 */                       if ((ChatColor.GREEN + dRemedy.name).equalsIgnoreCase(item.getItemMeta().getDisplayName())) {
/*     */                         
/* 113 */                         if (this.disease.debug) this.disease.getLogger().info(dRemedy.name + " matches used item name = curing player"); 
/* 114 */                         dPlayer.playerCure(false, dRemedy.cureMessage);
/* 115 */                         item.setAmount(item.getAmount() - 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                         
/*     */                         return;
/*     */                       } 
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 135 */         if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Syringe"))
/*     */         {
/* 137 */           if (this.disease.debug) this.disease.getLogger().info("right click with syringe");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 143 */           ItemStack syringeItem = p.getItemInHand();
/* 144 */           ItemMeta syringe = syringeItem.getItemMeta();
/* 145 */           ArrayList<String> lore = new ArrayList<>();
/* 146 */           p.damage(1.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 151 */           if (dPlayer.infection == "none") {
/*     */             
/* 153 */             if (this.disease.debug) this.disease.getLogger().info("no infection so plain blood");
/*     */             
/* 155 */             syringe.setDisplayName("Full Syringe");
/* 156 */             lore.add("Uninfected Blood");
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 161 */             DDisease dDisease = (DDisease)this.disease.diseases.get(dPlayer.infection);
/* 162 */             if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " diseased blood"); 
/* 163 */             if (dDisease.vialEnabled == true || dDisease.arrowEnabled == true) {
/*     */               
/* 165 */               if (this.disease.debug) this.disease.getLogger().info("disease able to be used - creating syringe"); 
/* 166 */               syringe.setDisplayName(ChatColor.GREEN + dDisease.name + " Syringe");
/* 167 */               lore.add(dDisease.name + " Infected Blood");
/*     */             } else {
/*     */               
/* 170 */               if (this.disease.debug) this.disease.getLogger().info("disease not able to be used - plain blood syringe"); 
/* 171 */               syringe.setDisplayName("Full Syringe");
/* 172 */               lore.add("Uninfected Blood");
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 177 */           syringe.setLore(lore);
/* 178 */           syringeItem.setItemMeta(syringe);
/*     */         
/*     */         }
/*     */         
/*     */          }
/*     */       
/* 184 */       else if (this.disease.debug) { this.disease.getLogger().info("player has nothing in hand "); }
/*     */ 
/*     */     
/*     */     }
/* 188 */     else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
/*     */       
/* 190 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " left clicked block");
/*     */       
/* 192 */       for (DDisease dDisease : this.disease.diseases.values()) {
/*     */ 
/*     */         
/* 195 */         if (dDisease.infectionMethod.equalsIgnoreCase("interactHitBlock")) {
/*     */           
/* 197 */           if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " - has the requirement 'interactHitBlock' and block type '" + dDisease.infectionMethodType + "'");
/*     */           
/* 199 */           if (event.getItem().getType().name().equalsIgnoreCase(dDisease.infectionMethodType)) {
/*     */ 
/*     */             
/* 202 */             if (this.disease.debug) this.disease.getLogger().info("item type matches disease infection");
/*     */             
/* 204 */             int chance = dDisease.infectionChance;
/*     */             
/* 206 */             if (chance == 0) {
/* 207 */               if (this.disease.debug) this.disease.getLogger().info("Disease is disabled with zero chance - " + dDisease.name);
/*     */               
/*     */               return;
/*     */             } 
/* 211 */             Random random = new Random();
/* 212 */             int rand = random.nextInt(100);
/* 213 */             if (rand < chance) {
/* 214 */               if (this.disease.debug) this.disease.getLogger().info(rand + " is within " + chance + "%");
/*     */               
/* 216 */               dPlayer.playerInfect(dDisease.name, dDisease.infectionMessage);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 226 */     } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
/*     */       
/* 228 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " right clicked block");
/*     */       
/* 230 */       if (p.getItemInHand() != null) {
/*     */         
/* 232 */         if (this.disease.debug) this.disease.getLogger().info(p.getName() + " holding a " + p.getItemInHand().getType());
/*     */         
/* 234 */         if (p.getItemInHand().hasItemMeta())
/*     */         {
/* 236 */           if (p.getItemInHand().getItemMeta().getDisplayName() != null)
/*     */           {
/*     */             
/* 239 */             for (DDisease dDisease : this.disease.diseases.values()) {
/*     */               
/* 241 */               if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + dDisease.name + " Vaccine")) {
/*     */                 
/* 243 */                 if (this.disease.debug) this.disease.getLogger().info("item is a vaccine hook so stop placement"); 
/* 244 */                 event.setCancelled(true);
/*     */ 
/*     */ 
/*     */                 
/*     */                 return;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 257 */       for (DDisease dDisease : this.disease.diseases.values()) {
/*     */ 
/*     */         
/* 260 */         if (dDisease.infectionMethod.equalsIgnoreCase("interactBlock")) {
/*     */ 
/*     */           
/* 263 */           if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " - has the requirement 'interactBlock' and block type '" + dDisease.infectionMethodType + "'");
/*     */           
/* 265 */           if (event.getItem().getType().name().equalsIgnoreCase(dDisease.infectionMethodType)) {
/*     */ 
/*     */             
/* 268 */             if (this.disease.debug) this.disease.getLogger().info("item type matches disease infection");
/*     */             
/* 270 */             int chance = dDisease.infectionChance;
/*     */             
/* 272 */             if (chance == 0) {
/* 273 */               if (this.disease.debug) this.disease.getLogger().info("Disease is disabled with zero chance - " + dDisease.name);
/*     */               
/*     */               return;
/*     */             } 
/* 277 */             Random random = new Random();
/* 278 */             int rand = random.nextInt(100);
/* 279 */             if (rand < chance) {
/* 280 */               if (this.disease.debug) this.disease.getLogger().info(rand + " is within " + chance + "%");
/*     */               
/* 282 */               dPlayer.playerInfect(dDisease.name, dDisease.infectionMessage);
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPlayerInteractEntity(Player p, PlayerInteractEntityEvent event) {
/*     */     DPlayer ePlayer;
/*     */     Player helpedPlayer;
/* 300 */     if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - processing interact other player");
/*     */     
/* 302 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/* 303 */     Entity e = event.getRightClicked();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 308 */     if (e instanceof Player) {
/* 309 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " helped a player"); 
/* 310 */       helpedPlayer = (Player)e;
/* 311 */       ePlayer = (DPlayer)this.disease.dPlayers.get(helpedPlayer.getUniqueId().toString());
/*     */     } else {
/*     */       
/* 314 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " clicked a non player - returning");
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/* 319 */     if (p.getItemInHand() != null)
/*     */     
/* 321 */     { if (this.disease.debug) this.disease.getLogger().info("has item in hand"); 
/* 322 */       if (p.getItemInHand().hasItemMeta()) {
/*     */         
/* 324 */         if (this.disease.debug) this.disease.getLogger().info("item has meta"); 
/* 325 */         if (p.getItemInHand().getItemMeta().getDisplayName() != null) {
/*     */           
/* 327 */           if (this.disease.debug) this.disease.getLogger().info("item has name " + p.getItemInHand().getItemMeta().getDisplayName()); 
/* 328 */           ItemStack item = p.getItemInHand();
/*     */           
/* 330 */           if (!ePlayer.infection.equalsIgnoreCase("none")) {
/*     */             
/* 332 */             if (this.disease.debug) this.disease.getLogger().info("clicked player not infected - look for innoculations");
/*     */             
/* 334 */             for (DDisease dDisease : this.disease.diseases.values())
/*     */             {
/*     */               
/* 337 */               if (item.getItemMeta().hasDisplayName()) {
/* 338 */                 if (this.disease.debug) this.disease.getLogger().info("item has display name"); 
/* 339 */                 if ((ChatColor.GREEN + dDisease.name + " Vaccine").equalsIgnoreCase(item.getItemMeta().getDisplayName()))
/*     */                 {
/* 341 */                   if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " matches used item name = add to player imunity"); 
/* 342 */                   if (((Remedy)this.disease.remedies.get(dDisease.cure)).innoculationPossible)
/*     */                   {
/* 344 */                     if (this.disease.debug) this.disease.getLogger().info(((Remedy)this.disease.remedies.get(dDisease.cure)).name + " can innoculate - increasing by " + this.disease.ds.vaccineImmunityIncrease); 
/* 345 */                     int resultingImmunity = ((Integer)ePlayer.dImmunity.get(dDisease.name)).intValue() + this.disease.ds.vaccineImmunityIncrease;
/* 346 */                     if (resultingImmunity > 100) resultingImmunity = 100; 
/* 347 */                     ePlayer.dImmunity.put(dDisease.name, Integer.valueOf(resultingImmunity));
/* 348 */                     this.disease.messaging.actionBar((Player)e, dDisease.name + " Immunity increased by " + this.disease.ds.vaccineImmunityIncrease);
/* 349 */                     this.disease.messaging.actionBar(p, "Player has been immunised");
/* 350 */                     item.setAmount(item.getAmount() - 1);
/*     */                   }
/*     */                 
/*     */                 }
/*     */               
/*     */               }
/*     */             
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 361 */             if (this.disease.debug) this.disease.getLogger().info("clicked player infected with " + ePlayer.infection);
/*     */             
/* 363 */             for (Remedy dRemedy : this.disease.remedies.values()) {
/*     */ 
/*     */ 
/*     */               
/* 367 */               if (dRemedy.curedDisease.equals(ePlayer.infection)) {
/*     */                 
/* 369 */                 if (this.disease.debug) this.disease.getLogger().info(dRemedy.name + " - cures the required disease that the player has '" + dRemedy.curedDisease + "'");
/*     */                 
/* 371 */                 if (item.hasItemMeta()) {
/* 372 */                   if (this.disease.debug) this.disease.getLogger().info("has meta"); 
/* 373 */                   if (item.getItemMeta().hasDisplayName()) {
/* 374 */                     if (this.disease.debug) this.disease.getLogger().info("has display name"); 
/* 375 */                     if ((ChatColor.GREEN + dRemedy.name).equalsIgnoreCase(item.getItemMeta().getDisplayName())) {
/*     */                       
/* 377 */                       if (this.disease.debug) this.disease.getLogger().info(dRemedy.name + " matches used item name = curing player"); 
/* 378 */                       ePlayer.playerCure(false, dRemedy.cureMessage);
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 392 */           if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Syringe")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 398 */             ItemStack syringeItem = p.getItemInHand();
/* 399 */             ItemMeta syringe = syringeItem.getItemMeta();
/* 400 */             ArrayList<String> lore = new ArrayList<>();
/* 401 */             helpedPlayer.damage(1.0D);
/*     */ 
/*     */             
/* 404 */             if (dPlayer.infection == "none") {
/*     */ 
/*     */               
/* 407 */               syringe.setDisplayName("Full Syringe");
/* 408 */               lore.add("Uninfected Blood");
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 413 */               DDisease dDisease = (DDisease)this.disease.diseases.get(dPlayer.infection);
/* 414 */               if (dDisease.vialEnabled == true || dDisease.arrowEnabled == true) {
/*     */                 
/* 416 */                 syringe.setDisplayName(ChatColor.GREEN + dDisease.name + " Syringe");
/* 417 */                 lore.add(dDisease.name + " Infected Blood");
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 422 */             syringe.setLore(lore);
/* 423 */             syringeItem.setItemMeta(syringe);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 430 */       if (p.getItemInHand().getType() == Material.ENDER_EYE)
/*     */       {
/* 432 */         if (this.disease.debug) this.disease.getLogger().info(p.getName() + " has ender eye");
/*     */         
/* 434 */         if (p.hasPermission("disease.diagnose"))
/*     */         {
/* 436 */           if (this.disease.debug) this.disease.getLogger().info(helpedPlayer.getName() + " is being diagnosed"); 
/* 437 */           if (this.disease.debug) this.disease.getLogger().info("PERFORMING disease check " + helpedPlayer.getName()); 
/* 438 */           p.performCommand("disease check " + helpedPlayer.getName());
/* 439 */           event.setCancelled(true);
/*     */         }
/*     */       
/*     */       }
/*     */        }
/*     */     
/* 445 */     else if (this.disease.debug) { this.disease.getLogger().info("player has nothing in hand "); }
/*     */   
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\calculate\InteractCalculations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */