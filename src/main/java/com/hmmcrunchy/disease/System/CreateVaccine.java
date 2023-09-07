/*     */ package com.hmmcrunchy.disease.System;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.classes.DDisease;
/*     */ import com.hmmcrunchy.disease.classes.Remedy;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.PrepareItemCraftEvent;
/*     */ import org.bukkit.inventory.CraftingInventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
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
/*     */ public class CreateVaccine
/*     */ {
/*     */   private Disease disease;
/*     */   
/*     */   public CreateVaccine(Disease diseasePlugin) {
/*  34 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createVaccine(PrepareItemCraftEvent event, boolean debug) {
/*  40 */     CraftingInventory inventory = event.getInventory();
/*  41 */     if (event.getViewers().size() > 0) {
/*     */       
/*  43 */       HumanEntity p = event.getViewers().get(0);
/*  44 */       Player player = (Player)p;
/*     */       
/*  46 */       Recipe recipe = event.getRecipe();
/*  47 */       if (debug) Bukkit.getLogger().info("craft prep event ");
/*     */       
/*  49 */       for (ItemStack item : inventory.getMatrix()) {
/*     */ 
/*     */         
/*  52 */         if (item != null)
/*     */         {
/*     */           
/*  55 */           if (item.getType().equals(Material.TRIPWIRE_HOOK)) {
/*     */             
/*  57 */             if (item.getItemMeta() != null && item.getItemMeta().getDisplayName() != null) {
/*     */ 
/*     */               
/*  60 */               if (debug) Bukkit.getLogger().info("has meta "); 
/*  61 */               if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Syringe")) {
/*     */ 
/*     */                 
/*  64 */                 if (debug) Bukkit.getLogger().info("recipe includes syringe");
/*     */ 
/*     */                 
/*  67 */                 for (ItemStack item2 : inventory.getMatrix()) {
/*     */ 
/*     */                   
/*  70 */                   if (item2 != null)
/*     */                   {
/*  72 */                     if (item2.getType().equals(Material.POTION)) {
/*     */                       
/*  74 */                       if (debug) Bukkit.getLogger().info("includes potion");
/*     */                       
/*  76 */                       if (item2.getItemMeta() != null && item2.getItemMeta().getDisplayName() != null) {
/*     */                         
/*  78 */                         if (debug) Bukkit.getLogger().info("names not null");
/*     */                         
/*  80 */                         for (Remedy dRemedy : this.disease.remedies.values()) {
/*     */ 
/*     */                           
/*  83 */                           if (item2.getItemMeta().getDisplayName().equals(ChatColor.GREEN + dRemedy.name)) {
/*     */                             
/*  85 */                             if (debug) Bukkit.getLogger().info("item matches a remedy name " + dRemedy.name);
/*     */                             
/*  87 */                             if (dRemedy.innoculationPossible) {
/*     */                               
/*  89 */                               if (debug) Bukkit.getLogger().info("remedy innoculation possible - " + dRemedy.name); 
/*  90 */                               ItemStack diseaseVaccine = new ItemStack(Material.TRIPWIRE_HOOK, 1);
/*  91 */                               ItemMeta pv = diseaseVaccine.getItemMeta();
/*     */ 
/*     */                               
/*  94 */                               pv.setDisplayName(ChatColor.GREEN + dRemedy.curedDisease + " Vaccine");
/*  95 */                               ArrayList<String> lore = new ArrayList<>();
/*  96 */                               lore.add("Single shot " + dRemedy.curedDisease + " Vaccine");
/*  97 */                               pv.setLore(lore);
/*     */                               
/*  99 */                               diseaseVaccine.setItemMeta(pv);
/* 100 */                               inventory.setResult(diseaseVaccine);
/*     */ 
/*     */                               
/*     */                               return;
/*     */                             } 
/*     */                           } 
/*     */                         } 
/*     */ 
/*     */                         
/* 109 */                         ItemStack nothing = new ItemStack(Material.AIR);
/* 110 */                         inventory.setResult(nothing);
/* 111 */                         if (debug) Bukkit.getLogger().info("no meta on remedy potion");
/*     */                       
/*     */                       } 
/*     */                     } 
/*     */                   }
/*     */                 } 
/*     */                 
/*     */                 break;
/*     */               } 
/* 120 */               if (item.getItemMeta().getDisplayName().contains(" Syringe")) {
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 125 */                 String originalName = item.getItemMeta().getDisplayName();
/* 126 */                 if (debug) Bukkit.getLogger().info("infected syringe found");
/*     */                 
/* 128 */                 for (ItemStack item2 : inventory.getMatrix()) {
/*     */ 
/*     */                   
/* 131 */                   if (item2 != null && !item2.getType().equals(Material.AIR)) {
/*     */                     
/* 133 */                     if (debug) Bukkit.getLogger().info("second item found " + item2.getType());
/*     */                     
/* 135 */                     if (item2.getType().equals(Material.GLASS_BOTTLE)) {
/*     */                       
/* 137 */                       if (debug) Bukkit.getLogger().info("includes infected syringe & empty bottle");
/*     */                       
/* 139 */                       for (DDisease dDisease : this.disease.diseases.values())
/*     */                       {
/*     */                         
/* 142 */                         if (dDisease.vialEnabled)
/*     */                         {
/*     */                           
/* 145 */                           if (originalName.equalsIgnoreCase(ChatColor.GREEN + dDisease.name + " Syringe")) {
/*     */ 
/*     */ 
/*     */                             
/* 149 */                             if (debug) Bukkit.getLogger().info("has disease syringe for" + dDisease.name);
/*     */                             
/* 151 */                             ItemStack diseaseVial = new ItemStack(Material.SPLASH_POTION, 1);
/* 152 */                             ItemMeta dv = diseaseVial.getItemMeta();
/*     */ 
/*     */ 
/*     */                             
/* 156 */                             dv.setDisplayName(ChatColor.GREEN + dDisease.name + " Vial");
/* 157 */                             ArrayList<String> lore = new ArrayList<>();
/* 158 */                             lore.add("Vial containing " + dDisease.name);
/* 159 */                             dv.setLore(lore);
/* 160 */                             dv.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
/* 161 */                             diseaseVial.setItemMeta(dv);
/* 162 */                             inventory.setResult(diseaseVial);
/*     */ 
/*     */                             
/*     */                             return;
/*     */                           } 
/*     */                           
/* 168 */                           ItemStack noVial = new ItemStack(Material.AIR);
/* 169 */                           inventory.setResult(noVial);
/* 170 */                           if (debug) Bukkit.getLogger().info("no meta on vial potion");
/*     */                         
/*     */                         }
/*     */                       
/*     */                       }
/*     */                     
/* 176 */                     } else if (item2.getType().equals(Material.ARROW)) {
/*     */                       
/* 178 */                       if (debug) Bukkit.getLogger().info("includes infected syringe & arrow");
/*     */ 
/*     */                       
/* 181 */                       for (DDisease dDisease : this.disease.diseases.values()) {
/*     */ 
/*     */                         
/* 184 */                         if (dDisease.arrowEnabled) {
/*     */                           
/* 186 */                           if (debug) Bukkit.getLogger().info(dDisease.name + " has arrow enabled");
/*     */                           
/* 188 */                           if (originalName.equalsIgnoreCase(ChatColor.GREEN + dDisease.name + " Syringe")) {
/*     */ 
/*     */ 
/*     */                             
/* 192 */                             if (debug) Bukkit.getLogger().info("has " + dDisease.name + " Syringe");
/*     */                             
/* 194 */                             if (inventory.getResult() == null) {
/*     */                               
/* 196 */                               if (debug) Bukkit.getLogger().info("craft result is null");
/*     */                               
/*     */                               break;
/*     */                             } 
/* 200 */                             if (debug) Bukkit.getLogger().info("arrow recipe ok ");
/*     */                             
/* 202 */                             ItemStack diseaseArrow = inventory.getResult();
/* 203 */                             ItemMeta pv = diseaseArrow.getItemMeta();
/* 204 */                             if (debug) Bukkit.getLogger().info("got meta");
/*     */                             
/* 206 */                             pv.setDisplayName(ChatColor.GREEN + dDisease.name + " Arrow");
/* 207 */                             ArrayList<String> lore = new ArrayList<>();
/* 208 */                             lore.add("Arrow embued with " + dDisease.name);
/* 209 */                             pv.setLore(lore);
/* 210 */                             if (debug) Bukkit.getLogger().info("setting arrow meta"); 
/* 211 */                             diseaseArrow.setItemMeta(pv);
/* 212 */                             inventory.setResult(diseaseArrow);
/*     */                             
/*     */                             return;
/*     */                           } 
/*     */                           
/* 217 */                           ItemStack nothing = new ItemStack(Material.AIR, 1);
/* 218 */                           inventory.setResult(nothing);
/* 219 */                           if (debug) Bukkit.getLogger().info("no meta on potion");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                           
/*     */                           break;
/*     */                         } 
/*     */                       } 
/*     */                     } else {
/* 229 */                       if (debug) Bukkit.getLogger().info("no glass bottle or arrow"); 
/* 230 */                       ItemStack nothing = new ItemStack(Material.AIR, 1);
/* 231 */                       inventory.setResult(nothing);
/*     */ 
/*     */                       
/*     */                       break;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               
/* 243 */               for (ItemStack item2 : inventory.getMatrix()) {
/*     */ 
/*     */                 
/* 246 */                 if (item2 != null && 
/* 247 */                   item2.getType().equals(Material.POTION)) {
/*     */                   
/* 249 */                   if (debug) Bukkit.getLogger().info("no meta on hook"); 
/* 250 */                   ItemStack nothing = new ItemStack(Material.AIR, 1);
/* 251 */                   inventory.setResult(nothing);
/*     */ 
/*     */ 
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */ 
/*     */               
/*     */               break;
/*     */             } 
/*     */ 
/*     */             
/* 264 */             for (ItemStack item2 : inventory.getMatrix()) {
/*     */ 
/*     */               
/* 267 */               if (item2 != null)
/*     */               {
/* 269 */                 if (item2.getType().equals(Material.POTION)) {
/*     */                   
/* 271 */                   if (debug) Bukkit.getLogger().info("no meta on hook"); 
/* 272 */                   ItemStack nothing = new ItemStack(Material.AIR, 1);
/* 273 */                   inventory.setResult(nothing);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 290 */       if (inventory.getResult() != null) {
/*     */         
/* 292 */         if (debug) Bukkit.getLogger().info("recipe result detected");
/*     */ 
/*     */         
/* 295 */         if (inventory.getResult().getItemMeta() != null) {
/*     */           
/* 297 */           if (debug) Bukkit.getLogger().info("recipe result has meta");
/*     */ 
/*     */ 
/*     */           
/* 301 */           if (inventory.getResult().getItemMeta().getDisplayName() != null) {
/*     */             
/* 303 */             String name = inventory.getResult().getItemMeta().getDisplayName();
/* 304 */             if (debug) Bukkit.getLogger().info("recipe name detected: " + name + " crafting ");
/*     */ 
/*     */ 
/*     */             
/* 308 */             for (Remedy dRemedy : this.disease.remedies.values()) {
/*     */ 
/*     */               
/* 311 */               if (name.equalsIgnoreCase(ChatColor.GREEN + dRemedy.name)) {
/*     */                 
/* 313 */                 if (player.hasPermission("disease.craft." + dRemedy.name) || player.hasPermission("disease.craft.*")) {
/* 314 */                   if (debug) Bukkit.getLogger().info("recipe permissions present for " + name);  continue;
/*     */                 } 
/* 316 */                 if (debug) Bukkit.getLogger().info("recipe permissions disallowed for " + name); 
/* 317 */                 inventory.setResult(null);
/*     */                 
/* 319 */                 player.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " You do not have permission for this recipe");
/*     */               } 
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 326 */             for (DDisease dDisease : this.disease.diseases.values()) {
/*     */               
/* 328 */               if (name.equalsIgnoreCase(ChatColor.GREEN + dDisease.name + " Syringe")) {
/*     */                 
/* 330 */                 if (player.hasPermission("disease.craft." + dDisease.name + "syringe") || player.hasPermission("disease.craft.*")) {
/* 331 */                   if (debug) Bukkit.getLogger().info("recipe permissions present for " + name);  continue;
/*     */                 } 
/* 333 */                 if (debug) Bukkit.getLogger().info("recipe permissions disallowed for " + name); 
/* 334 */                 inventory.setResult(null);
/*     */                 
/* 336 */                 player.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " You do not have permission for this recipe");
/*     */               } 
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 344 */             for (DDisease dDisease : this.disease.diseases.values()) {
/*     */               
/* 346 */               if (name.equalsIgnoreCase(ChatColor.GREEN + dDisease.name + " vial")) {
/*     */                 
/* 348 */                 if (player.hasPermission("disease.craft." + dDisease.name + "vial") || player.hasPermission("disease.craft.*")) {
/* 349 */                   if (debug) Bukkit.getLogger().info("recipe permissions present for " + name);  continue;
/*     */                 } 
/* 351 */                 if (debug) Bukkit.getLogger().info("recipe permissions disallowed for " + name); 
/* 352 */                 inventory.setResult(null);
/*     */                 
/* 354 */                 player.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " You do not have permission for this recipe");
/*     */               } 
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 364 */             if (name.equalsIgnoreCase(ChatColor.GREEN + "Syringe"))
/*     */             {
/* 366 */               if (player.hasPermission("disease.craft.syringe") || player.hasPermission("disease.craft.*")) {
/* 367 */                 if (debug) Bukkit.getLogger().info("recipe permissions present for " + name); 
/*     */               } else {
/* 369 */                 if (debug) Bukkit.getLogger().info("recipe permissions disallowed for " + name); 
/* 370 */                 inventory.setResult(null);
/*     */                 
/* 372 */                 player.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " You do not have permission for this recipe");
/*     */               } 
/*     */             }
/*     */ 
/*     */             
/* 377 */             for (DDisease dDisease : this.disease.diseases.values()) {
/*     */               
/* 379 */               if (name.equalsIgnoreCase(ChatColor.GREEN + dDisease.name + " Arrow")) {
/*     */                 
/* 381 */                 if (player.hasPermission("disease.craft.arrow." + dDisease.name) || player.hasPermission("disease.craft.arrow.*")) {
/* 382 */                   if (debug) Bukkit.getLogger().info("recipe permissions present for " + name);  continue;
/*     */                 } 
/* 384 */                 if (debug) Bukkit.getLogger().info("recipe permissions disallowed for " + name); 
/* 385 */                 inventory.setResult(null);
/*     */                 
/* 387 */                 player.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " You do not have permission for this recipe");
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\CreateVaccine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */