/*     */ package com.hmmcrunchy.disease.classes;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ import org.bukkit.inventory.ShapedRecipe;
/*     */ import org.bukkit.inventory.ShapelessRecipe;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Remedy
/*     */ {
/*     */   private Disease disease;
/*     */   public String name;
/*     */   public String description;
/*     */   public String craftDescription;
/*     */   public String curedDisease;
/*     */   public String cureMessage;
/*     */   public ItemStack item;
/*     */   public boolean innoculationPossible;
/*     */   
/*     */   public Remedy(Disease diseasePlugin) {
/*  36 */     this.disease = diseasePlugin;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean activateRemedy(String dRemedy) throws IOException {
/*  55 */     File file = new File(this.disease.remedyData + File.separator + dRemedy + ".yml");
/*     */ 
/*     */     
/*  58 */     if (!file.exists()) {
/*     */       
/*  60 */       this.disease.getLogger().info("Remedy " + dRemedy + " does not have a configuration file - SKIPPING ");
/*  61 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  65 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
/*  66 */     this.disease.getLogger().info("Loading Remedy " + dRemedy);
/*     */ 
/*     */     
/*  69 */     this.name = yamlConfiguration.getString("name");
/*  70 */     this.description = yamlConfiguration.getString("description");
/*  71 */     this.craftDescription = yamlConfiguration.getString("craftDescription");
/*  72 */     this.curedDisease = yamlConfiguration.getString("curedDisease");
/*  73 */     this.cureMessage = yamlConfiguration.getString("cureMessage");
/*     */     
/*  75 */     this.innoculationPossible = yamlConfiguration.getBoolean("innoculationPossible");
/*     */     
/*  77 */     if (yamlConfiguration.getString("recipeType").equalsIgnoreCase("shaped")) {
/*  78 */       this.item = createShapedCustomRemedy(this.name, this.description, yamlConfiguration.getString("cureRecipe.1"), yamlConfiguration.getString("cureRecipe.2"), yamlConfiguration.getString("cureRecipe.3"), yamlConfiguration.getString("cureRecipe.4"), yamlConfiguration.getString("cureRecipe.5"), yamlConfiguration.getString("cureRecipe.6"), yamlConfiguration.getString("cureRecipe.7"), yamlConfiguration.getString("cureRecipe.8"), yamlConfiguration.getString("cureRecipe.9"), yamlConfiguration.getString("craftResultMaterial"), yamlConfiguration.getInt("craftResultAmount"));
/*     */     } else {
/*     */       
/*  81 */       this.item = createShapelessCustomRemedy(this.name, this.description, yamlConfiguration.getString("cureRecipe.1"), yamlConfiguration.getString("cureRecipe.2"), yamlConfiguration.getString("cureRecipe.3"), yamlConfiguration.getString("cureRecipe.4"), yamlConfiguration.getString("cureRecipe.5"), yamlConfiguration.getString("cureRecipe.6"), yamlConfiguration.getString("cureRecipe.7"), yamlConfiguration.getString("cureRecipe.8"), yamlConfiguration.getString("cureRecipe.9"), yamlConfiguration.getString("craftResultMaterial"), yamlConfiguration.getInt("craftResultAmount"));
/*     */     } 
/*     */ 
/*     */     
/*  85 */     this.disease.getLogger().info("Loaded Remedy " + dRemedy);
/*     */     
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   ItemStack createShapedCustomRemedy(String name, String desc, String m1, String m2, String m3, String m4, String m5, String m6, String m7, String m8, String m9, String item, int amount) {
/*  93 */     this.disease.getLogger().info("Building remedy " + name + " Shaped Recipe");
/*     */     
/*  95 */     NamespacedKey key = new NamespacedKey("byte" + name.toLowerCase(), name.toLowerCase());
/*     */     
/*  97 */     ItemStack recipeItem = new ItemStack(Material.getMaterial(item), amount);
/*  98 */     ItemMeta itemMeta = recipeItem.getItemMeta();
/*     */ 
/*     */ 
/*     */     
/* 102 */     itemMeta.setDisplayName(ChatColor.GREEN + name);
/*     */     
/*     */     try {
/* 105 */       ArrayList<String> lore = new ArrayList<>();
/* 106 */       lore.add(desc);
/* 107 */       itemMeta.setLore(lore);
/* 108 */     } catch (Error e) {
/*     */       
/* 110 */       this.disease.getLogger().info("Error setting lore on " + name);
/*     */     } 
/*     */ 
/*     */     
/* 114 */     recipeItem.setItemMeta(itemMeta);
/*     */ 
/*     */ 
/*     */     
/* 118 */     ShapedRecipe bandageRecipe = new ShapedRecipe(key, recipeItem);
/* 119 */     bandageRecipe.shape(new String[] { "ABC", "DEF", "GHI" });
/* 120 */     bandageRecipe.setIngredient('A', Material.getMaterial(m1));
/* 121 */     bandageRecipe.setIngredient('B', Material.getMaterial(m2));
/* 122 */     bandageRecipe.setIngredient('C', Material.getMaterial(m3));
/* 123 */     bandageRecipe.setIngredient('D', Material.getMaterial(m4));
/* 124 */     bandageRecipe.setIngredient('E', Material.getMaterial(m5));
/* 125 */     bandageRecipe.setIngredient('F', Material.getMaterial(m6));
/* 126 */     bandageRecipe.setIngredient('G', Material.getMaterial(m7));
/* 127 */     bandageRecipe.setIngredient('H', Material.getMaterial(m8));
/* 128 */     bandageRecipe.setIngredient('I', Material.getMaterial(m9));
/* 129 */     Bukkit.addRecipe((Recipe)bandageRecipe);
/*     */     
/* 131 */     return recipeItem;
/*     */   }
/*     */ 
/*     */   
/*     */   ItemStack createShapelessCustomRemedy(String name, String desc, String m1, String m2, String m3, String m4, String m5, String m6, String m7, String m8, String m9, String item, int amount) {
/* 136 */     this.disease.getLogger().info("Building remedy " + name + " Shapeless Recipe");
/*     */ 
/*     */     
/*     */     try {
/* 140 */       Bukkit.getLogger().info(ChatColor.BLUE + "Creating custom " + name + " recipe");
/*     */       
/* 142 */       NamespacedKey key = new NamespacedKey("byte" + name.toLowerCase(), name.toLowerCase());
/*     */       
/* 144 */       ItemStack CustomRemedy = new ItemStack(Material.getMaterial(item), amount);
/* 145 */       ItemMeta swb = CustomRemedy.getItemMeta();
/*     */ 
/*     */       
/* 148 */       ArrayList<String> lore = new ArrayList<>();
/* 149 */       lore.add(desc);
/*     */ 
/*     */       
/* 152 */       swb.setDisplayName(ChatColor.GREEN + name);
/* 153 */       swb.setLore(lore);
/*     */       
/* 155 */       CustomRemedy.setItemMeta(swb);
/*     */ 
/*     */       
/* 158 */       ShapelessRecipe customRemedyRecipe = new ShapelessRecipe(key, CustomRemedy);
/*     */       
/* 160 */       if (!m1.equals("AIR")) {
/* 161 */         customRemedyRecipe.addIngredient(Material.getMaterial(m1));
/*     */       }
/* 163 */       if (!m2.equals("AIR")) {
/* 164 */         customRemedyRecipe.addIngredient(Material.getMaterial(m2));
/*     */       }
/* 166 */       if (!m3.equals("AIR")) {
/* 167 */         customRemedyRecipe.addIngredient(Material.getMaterial(m3));
/*     */       }
/* 169 */       if (!m4.equals("AIR")) {
/* 170 */         customRemedyRecipe.addIngredient(Material.getMaterial(m4));
/*     */       }
/* 172 */       if (!m5.equals("AIR")) {
/* 173 */         customRemedyRecipe.addIngredient(Material.getMaterial(m5));
/*     */       }
/* 175 */       if (!m6.equals("AIR")) {
/* 176 */         customRemedyRecipe.addIngredient(Material.getMaterial(m6));
/*     */       }
/* 178 */       if (!m7.equals("AIR")) {
/* 179 */         customRemedyRecipe.addIngredient(Material.getMaterial(m7));
/*     */       }
/* 181 */       if (!m8.equals("AIR")) {
/* 182 */         customRemedyRecipe.addIngredient(Material.getMaterial(m8));
/*     */       }
/* 184 */       if (!m9.equals("AIR")) {
/* 185 */         customRemedyRecipe.addIngredient(Material.getMaterial(m9));
/*     */       }
/*     */       
/* 188 */       Bukkit.addRecipe((Recipe)customRemedyRecipe);
/* 189 */       return CustomRemedy;
/*     */     }
/* 191 */     catch (Exception e) {
/* 192 */       Bukkit.getLogger().info(ChatColor.BLUE + "Error creating custom " + name + " craft recipe");
/* 193 */       Bukkit.getLogger().info(ChatColor.BLUE + "trying to use materials " + m1 + " " + m2 + " " + m3 + " " + m4 + " " + m5 + " " + m6 + " " + m7 + " " + m8 + " " + m9 + " ");
/* 194 */       Bukkit.getLogger().info(ChatColor.BLUE + "Error " + e.toString());
/* 195 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\classes\Remedy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */