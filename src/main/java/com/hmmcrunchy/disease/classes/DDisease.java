/*     */ package com.hmmcrunchy.disease.classes;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DDisease
/*     */ {
/*     */   private Disease disease;
/*     */   public String name;
/*     */   public String description;
/*     */   public boolean infectious;
/*     */   public String infectionMethod;
/*     */   public String infectionMethodType;
/*     */   public int infectionChance;
/*     */   public String infectionMessage;
/*     */   public String prerequisiteDisease;
/*     */   public int sicknessIncrease;
/*     */   public String cure;
/*     */   public boolean vialEnabled;
/*     */   public boolean arrowEnabled;
/*     */   List<String> activeBiomes;
/*     */   public LinkedHashMap<String, Integer> sickActions;
/*     */   public LinkedHashMap<String, Integer> sickEffects;
/*     */   public LinkedHashMap<String, Integer> sickSounds;
/*     */   
/*     */   public DDisease(Disease diseasePlugin) {
/*  51 */     this.sickActions = new LinkedHashMap<>();
/*  52 */     this.sickEffects = new LinkedHashMap<>();
/*  53 */     this.sickSounds = new LinkedHashMap<>();
/*     */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean activateDisease(String dDisease) throws IOException {
/*  60 */     File file = new File(this.disease.diseaseData + File.separator + dDisease + ".yml");
/*     */ 
/*     */     
/*  63 */     if (!file.exists()) {
/*     */       
/*  65 */       this.disease.getLogger().info("Disease " + dDisease + " does not have a configuration file - SKIPPING ");
/*  66 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  70 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
/*  71 */     this.disease.getLogger().info("Loading disease " + dDisease);
/*     */ 
/*     */     
/*  74 */     this.name = yamlConfiguration.getString("name");
/*  75 */     this.description = yamlConfiguration.getString("description");
/*     */     
/*  77 */     this.infectious = yamlConfiguration.getBoolean("infectious");
/*  78 */     this.infectionMethod = yamlConfiguration.getString("infectionMethod");
/*  79 */     this.infectionMethodType = yamlConfiguration.getString("infectionMethodType");
/*  80 */     this.infectionChance = yamlConfiguration.getInt("infectionChance");
/*  81 */     this.infectionMessage = yamlConfiguration.getString("infectionMessage");
/*  82 */     this.prerequisiteDisease = yamlConfiguration.getString("prerequisiteDisease");
/*     */     
/*  84 */     this.sicknessIncrease = yamlConfiguration.getInt("sicknessIncrease");
/*  85 */     this.cure = yamlConfiguration.getString("cure");
/*     */     
/*  87 */     this.arrowEnabled = yamlConfiguration.getBoolean("arrowEnabled");
/*  88 */     this.vialEnabled = yamlConfiguration.getBoolean("vialEnabled");
/*     */ 
/*     */     
/*  91 */     List<String> lstSickActions = yamlConfiguration.getStringList("enabledSickActions");
/*  92 */     for (String action : lstSickActions) {
/*  93 */       if (this.disease.debug) this.disease.getLogger().info(ChatColor.GREEN + "Disease action: " + action); 
/*  94 */       this.sickActions.put(action, Integer.valueOf(yamlConfiguration.getInt("sickActions." + action)));
/*     */     } 
/*     */ 
/*     */     
/*  98 */     List<String> lstSickEffects = yamlConfiguration.getStringList("enabledSickEffects");
/*  99 */     for (String effect : lstSickEffects) {
/* 100 */       if (this.disease.debug) this.disease.getLogger().info(ChatColor.GREEN + "Disease effect: " + effect); 
/* 101 */       this.sickEffects.put(effect, Integer.valueOf(yamlConfiguration.getInt("sickEffects." + effect)));
/*     */     } 
/*     */ 
/*     */     
/* 105 */     List<String> lstSickSounds = yamlConfiguration.getStringList("enabledSickSounds");
/* 106 */     for (String sound : lstSickSounds) {
/* 107 */       if (this.disease.debug) this.disease.getLogger().info(ChatColor.GREEN + "Disease sound: " + sound); 
/* 108 */       this.sickSounds.put(sound, Integer.valueOf(yamlConfiguration.getInt("sickSounds." + sound)));
/*     */     } 
/*     */ 
/*     */     
/* 112 */     this.activeBiomes = yamlConfiguration.getStringList("biomes");
/*     */     
/* 114 */     this.disease.getLogger().info("Loaded disease " + dDisease);
/*     */     
/* 116 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\classes\DDisease.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */