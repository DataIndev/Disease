/*     */ package com.hmmcrunchy.disease.System;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.inventory.FurnaceRecipe;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ import org.bukkit.inventory.ShapedRecipe;
/*     */ import org.bukkit.inventory.ShapelessRecipe;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.Plugin;
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
/*     */ public class ItemFactory
/*     */ {
/*     */   Disease disease;
/*     */   
/*     */   public ItemFactory(Disease diseasePlugin) {
/*  32 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createSyringe(String name, String desc) {
/*  39 */     Bukkit.getLogger().info(ChatColor.GREEN + "Creating " + name + " Recipe");
/*     */     
/*  41 */     NamespacedKey key = new NamespacedKey((Plugin)this.disease, "bytesyringe");
/*     */     
/*  43 */     ItemStack syringeItem = new ItemStack(Material.TRIPWIRE_HOOK, 1);
/*  44 */     ItemMeta syringe = syringeItem.getItemMeta();
/*     */ 
/*     */ 
/*     */     
/*  48 */     syringe.setDisplayName(ChatColor.GREEN + name);
/*  49 */     ArrayList<String> lore = new ArrayList<>();
/*  50 */     lore.add(desc);
/*  51 */     syringe.setLore(lore);
/*     */     
/*  53 */     syringeItem.setItemMeta(syringe);
/*     */ 
/*     */ 
/*     */     
/*  57 */     ShapedRecipe syringeRecipe = new ShapedRecipe(key, syringeItem);
/*  58 */     syringeRecipe.shape(new String[] { "  L", " G ", "S  " });
/*  59 */     syringeRecipe.setIngredient('S', Material.STICK);
/*  60 */     syringeRecipe.setIngredient('G', Material.GLASS);
/*  61 */     syringeRecipe.setIngredient('L', Material.LEVER);
/*     */     
/*  63 */     Bukkit.addRecipe((Recipe)syringeRecipe);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createVaccine(String name, String desc) {
/*  69 */     Bukkit.getLogger().info(ChatColor.GREEN + "Creating vaccines Recipe");
/*     */     
/*  71 */     NamespacedKey key = new NamespacedKey((Plugin)this.disease, "bytevaccine");
/*     */     
/*  73 */     ItemStack vaccineItem = new ItemStack(Material.TRIPWIRE_HOOK, 1);
/*  74 */     ItemMeta vaccine = vaccineItem.getItemMeta();
/*     */ 
/*     */ 
/*     */     
/*  78 */     vaccine.setDisplayName(ChatColor.GREEN + name);
/*  79 */     ArrayList<String> lore = new ArrayList<>();
/*  80 */     lore.add(desc);
/*  81 */     vaccine.setLore(lore);
/*     */     
/*  83 */     vaccineItem.setItemMeta(vaccine);
/*     */ 
/*     */ 
/*     */     
/*  87 */     ShapelessRecipe vaccineRecipe = new ShapelessRecipe(key, vaccineItem);
/*     */     
/*  89 */     vaccineRecipe.addIngredient(Material.TRIPWIRE_HOOK);
/*  90 */     vaccineRecipe.addIngredient(Material.POTION);
/*     */     
/*  92 */     Bukkit.addRecipe((Recipe)vaccineRecipe);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDiseaseVial(String name, String desc) {
/*  98 */     Bukkit.getLogger().info(ChatColor.GREEN + "Creating Disease Vial Recipe");
/*     */     
/* 100 */     NamespacedKey key = new NamespacedKey((Plugin)this.disease, "bytediseasevial");
/*     */     
/* 102 */     ItemStack vialItem = new ItemStack(Material.SPLASH_POTION, 1);
/* 103 */     ItemMeta vial = vialItem.getItemMeta();
/*     */ 
/*     */ 
/*     */     
/* 107 */     vial.setDisplayName(ChatColor.GREEN + name);
/* 108 */     ArrayList<String> lore = new ArrayList<>();
/* 109 */     lore.add(desc);
/* 110 */     vial.setLore(lore);
/*     */     
/* 112 */     vialItem.setItemMeta(vial);
/*     */ 
/*     */ 
/*     */     
/* 116 */     ShapelessRecipe vialRecipe = new ShapelessRecipe(key, vialItem);
/*     */     
/* 118 */     vialRecipe.addIngredient(Material.TRIPWIRE_HOOK);
/* 119 */     vialRecipe.addIngredient(Material.GLASS_BOTTLE);
/*     */     
/* 121 */     Bukkit.addRecipe((Recipe)vialRecipe);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDiseaseArrow(String name, String desc) {
/* 128 */     Bukkit.getLogger().info(ChatColor.GREEN + "Creating Disease Arrow Recipe");
/*     */     
/* 130 */     NamespacedKey key = new NamespacedKey((Plugin)this.disease, "bytediseasearrow");
/*     */     
/* 132 */     ItemStack arrowItem = new ItemStack(Material.ARROW, 1);
/* 133 */     ItemMeta dArrow = arrowItem.getItemMeta();
/*     */ 
/*     */ 
/*     */     
/* 137 */     dArrow.setDisplayName(ChatColor.GREEN + name);
/* 138 */     ArrayList<String> lore = new ArrayList<>();
/* 139 */     lore.add(desc);
/* 140 */     dArrow.setLore(lore);
/*     */     
/* 142 */     arrowItem.setItemMeta(dArrow);
/*     */ 
/*     */ 
/*     */     
/* 146 */     ShapelessRecipe arrowRecipe = new ShapelessRecipe(key, arrowItem);
/*     */     
/* 148 */     arrowRecipe.addIngredient(Material.TRIPWIRE_HOOK);
/* 149 */     arrowRecipe.addIngredient(Material.ARROW);
/*     */     
/* 151 */     Bukkit.addRecipe((Recipe)arrowRecipe);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createColdWater(String coldWaterName) {
/* 159 */     Bukkit.getLogger().info(ChatColor.GREEN + "Creating cold water recipe");
/*     */     
/* 161 */     NamespacedKey key = new NamespacedKey((Plugin)this.disease, "bytecoldwater");
/*     */     
/* 163 */     ItemStack coldWaterItem = new ItemStack(Material.POTION, 1);
/* 164 */     ItemMeta coldWater = coldWaterItem.getItemMeta();
/*     */ 
/*     */     
/* 167 */     coldWater.setDisplayName(ChatColor.GREEN + coldWaterName);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     coldWaterItem.setItemMeta(coldWater);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     ShapelessRecipe coldWaterRecipe = new ShapelessRecipe(key, coldWaterItem);
/* 179 */     coldWaterRecipe.addIngredient(Material.POTION);
/* 180 */     coldWaterRecipe.addIngredient(Material.SNOWBALL);
/*     */     
/* 182 */     Bukkit.addRecipe((Recipe)coldWaterRecipe);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createHotMilk(String hotMilkName) {
/* 188 */     Bukkit.getLogger().info(ChatColor.GREEN + "Creating boiling milk recipe");
/*     */     
/* 190 */     ItemStack hotMilkItem = new ItemStack(Material.MILK_BUCKET, 1);
/* 191 */     ItemMeta hotMilk = hotMilkItem.getItemMeta();
/*     */ 
/*     */     
/* 194 */     hotMilk.setDisplayName(ChatColor.GREEN + hotMilkName);
/*     */ 
/*     */ 
/*     */     
/* 198 */     hotMilkItem.setItemMeta(hotMilk);
/*     */ 
/*     */ 
/*     */     
/* 202 */     FurnaceRecipe hotMilkRecipe = new FurnaceRecipe(hotMilkItem, Material.MILK_BUCKET);
/*     */     
/* 204 */     Bukkit.addRecipe((Recipe)hotMilkRecipe);
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\ItemFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */