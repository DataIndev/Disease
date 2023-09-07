/*     */ package com.hmmcrunchy.disease.calculate;
/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
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
/*     */ public class InventoryProcess
/*     */ {
/*     */   void processMilk(Player p, boolean debug) {
/*  26 */     if (p.getInventory().getContents() != null) {
/*     */       
/*  28 */       if (debug) {
/*  29 */         Bukkit.getLogger().info(p.getName() + " processing inventory for milk and water ");
/*     */       }
/*     */       
/*  32 */       for (ItemStack item : p.getInventory().getContents()) {
/*     */         
/*  34 */         if (item != null) {
/*  35 */           if (item.getType().equals(Material.MILK_BUCKET)) {
/*     */             
/*  37 */             if (debug) {
/*  38 */               Bukkit.getLogger().info(p.getName() + " milk found ");
/*     */             }
/*     */             
/*  41 */             if (item.hasItemMeta())
/*     */             {
/*  43 */               if (debug) {
/*  44 */                 Bukkit.getLogger().info(p.getName() + " found " + item.getItemMeta().getDisplayName());
/*     */               }
/*     */               
/*  47 */               if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Boiling Milk")) {
/*     */                 
/*  49 */                 ItemMeta im = item.getItemMeta();
/*  50 */                 im.setDisplayName(ChatColor.GREEN + "Hot Milk");
/*  51 */                 item.setItemMeta(im);
/*     */               }
/*  53 */               else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Hot Milk")) {
/*     */                 
/*  55 */                 ItemMeta im = item.getItemMeta();
/*  56 */                 im.setDisplayName(ChatColor.GREEN + "Warm Milk");
/*  57 */                 item.setItemMeta(im);
/*     */               }
/*  59 */               else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Warm Milk")) {
/*     */                 
/*  61 */                 ItemMeta im = item.getItemMeta();
/*  62 */                 im.setDisplayName(ChatColor.WHITE + "Milk");
/*  63 */                 item.setItemMeta(im);
/*     */               } 
/*     */ 
/*     */               
/*  67 */               p.updateInventory();
/*     */             }
/*     */           
/*  70 */           } else if (item.getType().equals(Material.POTION)) {
/*     */             
/*  72 */             if (debug) {
/*  73 */               Bukkit.getLogger().info(p.getName() + " potion found ");
/*     */             }
/*     */             
/*  76 */             if (item.hasItemMeta()) {
/*     */               
/*  78 */               if (debug) {
/*  79 */                 Bukkit.getLogger().info(p.getName() + " found " + item.getItemMeta().getDisplayName());
/*     */               }
/*     */               
/*  82 */               if (item.getItemMeta().hasDisplayName()) {
/*     */                 
/*  84 */                 if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Freezing Water")) {
/*     */                   
/*  86 */                   ItemMeta im = item.getItemMeta();
/*  87 */                   im.setDisplayName(ChatColor.GREEN + "Cold Water");
/*  88 */                   item.setItemMeta(im);
/*     */                 }
/*  90 */                 else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Cold Water")) {
/*     */                   
/*  92 */                   ItemMeta im = item.getItemMeta();
/*  93 */                   im.setDisplayName(ChatColor.GREEN + "Cool Water");
/*  94 */                   item.setItemMeta(im);
/*     */                 }
/*  96 */                 else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Cool Water")) {
/*     */                   
/*  98 */                   ItemMeta im = item.getItemMeta();
/*  99 */                   im.setDisplayName(ChatColor.WHITE + "Water Bottle");
/* 100 */                   item.setItemMeta(im);
/*     */                 } 
/*     */ 
/*     */                 
/* 104 */                 p.updateInventory();
/*     */               } 
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
/*     */ 
/*     */   
/*     */   int drinkMilk(Player p, ItemStack item) {
/* 122 */     int heat = 0;
/*     */     
/* 124 */     if (item.hasItemMeta())
/*     */     {
/* 126 */       if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Boiling Milk")) {
/*     */         
/* 128 */         heat = 5;
/* 129 */         p.damage(3.0D);
/* 130 */         p.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " The boiling milk scalds your mouth");
/*     */       }
/* 132 */       else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Hot Milk")) {
/*     */         
/* 134 */         heat = 4;
/* 135 */         p.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " The hot milk warms you");
/*     */       }
/* 137 */       else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Warm Milk")) {
/*     */         
/* 139 */         heat = 2;
/* 140 */         p.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " The warm milk warms you");
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 145 */     return heat;
/*     */   }
/*     */ 
/*     */   
/*     */   int drinkWater(Player p, ItemStack item) {
/* 150 */     int heat = 0;
/*     */     
/* 152 */     if (item.hasItemMeta())
/*     */     {
/* 154 */       if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Freezing Water")) {
/*     */         
/* 156 */         heat = -5;
/* 157 */         p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1, 1));
/* 158 */         p.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " The freezing water gives you brain freeze");
/*     */       }
/* 160 */       else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Cold Water")) {
/*     */         
/* 162 */         heat = -4;
/* 163 */         p.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " The cold water chills you");
/*     */       }
/* 165 */       else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Cool Water")) {
/*     */         
/* 167 */         heat = -2;
/* 168 */         p.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " The cool water cools you");
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 173 */     return heat;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getArrowName(Player p, Boolean debug) {
/* 178 */     if (p.getInventory().getContents() != null) {
/*     */       
/* 180 */       if (debug.booleanValue()) {
/* 181 */         Bukkit.getLogger().info(p.getName() + " processing inventory for virus arrows");
/*     */       }
/*     */       
/* 184 */       for (ItemStack item : p.getInventory().getContents()) {
/*     */         
/* 186 */         if (item != null && 
/* 187 */           item.getType().equals(Material.ARROW)) {
/*     */           
/* 189 */           if (debug.booleanValue()) {
/* 190 */             Bukkit.getLogger().info(p.getName() + " arrow found ");
/*     */           }
/* 192 */           String name = item.getItemMeta().getDisplayName();
/* 193 */           if (name == null) {
/* 194 */             name = "Arrow";
/*     */           }
/*     */           
/* 197 */           return name;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     return "nothing";
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\calculate\InventoryProcess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */