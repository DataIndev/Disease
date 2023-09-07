/*     */ package com.hmmcrunchy.disease.System;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.calculate.CalculateTemperature;
/*     */ import com.hmmcrunchy.disease.classes.DDisease;
/*     */ import com.hmmcrunchy.disease.classes.Remedy;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DiseaseSettings
/*     */ {
/*     */   private Disease disease;
/*     */   public boolean byteScoreboardEnabled;
/*     */   public boolean scoreboardStatsEnabled;
/*     */   public boolean worldGuardEnabled;
/*     */   public boolean actionBarEnabled;
/*     */   public boolean cureOnDeath;
/*     */   public boolean dropItemOnCough;
/*     */   public boolean useTemp;
/*     */   
/*     */   public DiseaseSettings(Disease diseasePlugin) {
/*  30 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */   
/*     */   public int doctorFee;
/*     */   
/*     */   public int deathFine;
/*     */   
/*     */   public int tempHospitalReduce;
/*     */   
/*     */   public int sickHospitalReduce;
/*     */   
/*     */   public int warm;
/*     */   
/*     */   public int hot;
/*     */   
/*     */   public int fever;
/*     */   
/*     */   public int cold;
/*     */   
/*     */   public int chilled;
/*     */   
/*     */   public int hypothermic;
/*     */   
/*     */   public boolean tempCentigrade;
/*     */   
/*     */   public int unwell;
/*     */   
/*     */   public int queasy;
/*     */   
/*     */   public int sick;
/*     */   
/*     */   public int immunityDecreaseOnInfection;
/*     */   
/*     */   public int immunityGainOnHeal;
/*     */   
/*     */   public int vaccineImmunityIncrease;
/*     */   
/*     */   public int immunityDecreaseOnDeath;
/*     */   
/*     */   public int tempCureReduction;
/*     */   
/*     */   public int tempCureChance;
/*     */   
/*     */   List<String> chokeItems;
/*     */   List<String> diseasesEnabled;
/*     */   List<String> remediesEnabled;
/*     */   public List<String> worlds;
/*     */   public int timeBetweenChecks;
/*     */   
/*     */   public void loadConfigData() {
/*  81 */     this.timeBetweenChecks = this.disease.getConfig().getInt("timeBetweenChecks");
/*     */ 
/*     */     
/*  84 */     this.useTemp = this.disease.getConfig().getBoolean("useTemp");
/*  85 */     if (this.useTemp) {
/*  86 */       this.disease.getLogger().info("Temperature module loaded");
/*     */     }
/*     */ 
/*     */     
/*  90 */     this.worlds = this.disease.getConfig().getStringList("worlds");
/*  91 */     for (String world : this.worlds) {
/*  92 */       this.disease.getLogger().info(ChatColor.GREEN + "Adding allowed world: " + world);
/*     */     }
/*     */ 
/*     */     
/*  96 */     this.diseasesEnabled = this.disease.getConfig().getStringList("diseasesEnabled");
/*  97 */     for (String diseaseEnabled : this.diseasesEnabled) {
/*     */       
/*  99 */       this.disease.getLogger().info(ChatColor.GREEN + "Adding disease: " + diseaseEnabled);
/*     */       try {
/* 101 */         importDisease(diseaseEnabled);
/* 102 */       } catch (IOException ex) {
/* 103 */         Logger.getLogger(DiseaseSettings.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 109 */     this.remediesEnabled = this.disease.getConfig().getStringList("remediesEnabled");
/* 110 */     for (String remedyEnabled : this.remediesEnabled) {
/*     */       
/* 112 */       this.disease.getLogger().info(ChatColor.GREEN + "Adding remedy: " + remedyEnabled);
/*     */       try {
/* 114 */         importRemedy(remedyEnabled);
/* 115 */       } catch (IOException ex) {
/* 116 */         Logger.getLogger(DiseaseSettings.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 122 */     this.chokeItems = this.disease.getConfig().getStringList("chokeItems");
/* 123 */     this.disease.getLogger().info("Loading choking items: " + this.chokeItems);
/*     */ 
/*     */     
/* 126 */     this.byteScoreboardEnabled = this.disease.getConfig().getBoolean("byteScoreboardEnabled");
/* 127 */     this.scoreboardStatsEnabled = this.disease.getConfig().getBoolean("useScoreboardStats");
/* 128 */     this.worldGuardEnabled = this.disease.getConfig().getBoolean("useWorldGuard");
/* 129 */     this.actionBarEnabled = this.disease.getConfig().getBoolean("actionBarEnabled");
/*     */ 
/*     */     
/* 132 */     if (this.byteScoreboardEnabled) {
/* 133 */       this.disease.getLogger().info(ChatColor.GREEN + "BYTE scoreboard Enabled");
/*     */     } else {
/*     */       
/* 136 */       this.disease.getLogger().info(ChatColor.GREEN + "BYTE scoreboard disabled in config");
/*     */     } 
/*     */     
/* 139 */     Messaging messaging = this.disease.messaging;
/* 140 */     ItemFactory itemF = this.disease.itemF;
/* 141 */     YamlConfiguration languageFile = this.disease.languageFile;
/*     */     
/* 143 */     this.cureOnDeath = this.disease.getConfig().getBoolean("cureOnDeath");
/* 144 */     this.dropItemOnCough = this.disease.getConfig().getBoolean("cureOnDeath");
/*     */     
/* 146 */     this.doctorFee = this.disease.getConfig().getInt("doctorFee");
/* 147 */     this.deathFine = this.disease.getConfig().getInt("deathFine");
/*     */ 
/*     */     
/* 150 */     this.tempHospitalReduce = this.disease.getConfig().getInt("tempHospitalReduce");
/* 151 */     this.sickHospitalReduce = this.disease.getConfig().getInt("sickHospitalReduce");
/* 152 */     messaging.hospitalCureMessage = this.disease.languageFile.getString("hospitalCureMessage");
/*     */ 
/*     */ 
/*     */     
/* 156 */     this.tempCentigrade = this.disease.getConfig().getBoolean("tempCentigrade");
/*     */ 
/*     */ 
/*     */     
/* 160 */     messaging.warmMessage = this.disease.languageFile.getString("warmMessage");
/* 161 */     messaging.hotMessage = this.disease.languageFile.getString("hotMessage");
/* 162 */     messaging.feverMessage = this.disease.languageFile.getString("feverMessage");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     messaging.coldMessage = languageFile.getString("coldMessage");
/* 168 */     messaging.chilledMessage = languageFile.getString("chilledMessage");
/* 169 */     messaging.hypothermicMessage = languageFile.getString("hypothermicMessage");
/*     */ 
/*     */     
/* 172 */     messaging.tempIsColdMessage = languageFile.getString("tempIsColdMessage");
/* 173 */     messaging.tempIsFreezingMessage = languageFile.getString("tempIsFreezingMessage");
/* 174 */     messaging.tempIsHotMessage = languageFile.getString("tempIsHotMessage");
/* 175 */     messaging.tempIsBoilingMessage = languageFile.getString("tempIsBoilingMessage");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     this.unwell = this.disease.getConfig().getInt("unwell");
/* 181 */     this.queasy = this.disease.getConfig().getInt("queasy");
/* 182 */     this.sick = this.disease.getConfig().getInt("sick");
/*     */     
/* 184 */     messaging.unwellMessage = languageFile.getString("unwellMessage");
/* 185 */     messaging.queasyMessage = languageFile.getString("queasyMessage");
/* 186 */     messaging.sickMessage = languageFile.getString("sickMessage");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     this.immunityDecreaseOnInfection = this.disease.getConfig().getInt("immunityDecreaseOnInfection");
/* 192 */     this.immunityGainOnHeal = this.disease.getConfig().getInt("immunityGainOnHeal");
/* 193 */     this.vaccineImmunityIncrease = this.disease.getConfig().getInt("vaccineImmunityIncrease");
/* 194 */     this.immunityDecreaseOnDeath = this.disease.getConfig().getInt("immunityDecreaseOnDeath");
/*     */ 
/*     */     
/* 197 */     this.tempCureReduction = this.disease.getConfig().getInt("tempCureReduction");
/* 198 */     this.tempCureChance = this.disease.getConfig().getInt("tempCureChance");
/*     */ 
/*     */ 
/*     */     
/* 202 */     messaging.hasBeenGivenPotionMessage = languageFile.getString("hasBeenGivenPotionMessage");
/* 203 */     messaging.givenPotionMessage = languageFile.getString("givenPotionMessage");
/*     */     
/* 205 */     messaging.cureFromCommandMessage = languageFile.getString("cureFromCommandMessage");
/* 206 */     messaging.infectedByCommandMessage = languageFile.getString("infectedByCommandMessage");
/* 207 */     messaging.infectedByRadiusDamage = languageFile.getString("infectedByRadiusDamage");
/* 208 */     messaging.infectedByRadiusProximity = languageFile.getString("infectedByRadiusProximity");
/*     */ 
/*     */     
/* 211 */     CalculateTemperature ct = this.disease.ct;
/*     */     
/* 213 */     ct.heatradius = this.disease.getConfig().getInt("heatRadius");
/*     */     
/* 215 */     ct.fireHeat = this.disease.getConfig().getInt("fireHeat");
/* 216 */     ct.torchHeat = this.disease.getConfig().getInt("torchHeat");
/* 217 */     ct.lavaHeat = this.disease.getConfig().getInt("lavaHeat");
/* 218 */     ct.campFireHeat = this.disease.getConfig().getInt("campFireHeat");
/* 219 */     ct.furnaceHeat = this.disease.getConfig().getInt("furnaceHeat");
/* 220 */     ct.blastFurnaceHeat = this.disease.getConfig().getInt("blastFurnaceHeat");
/* 221 */     ct.redstoneTorchHeat = this.disease.getConfig().getInt("redstonetorchHeat");
/* 222 */     ct.jackolanternHeat = this.disease.getConfig().getInt("jackolanternHeat");
/*     */     
/* 224 */     ct.jackolanternHeatHeld = this.disease.getConfig().getInt("jackolanternHeatHeld");
/* 225 */     ct.torchHeatHeld = this.disease.getConfig().getInt("torchHeatHeld");
/* 226 */     ct.lavaBucketHeatHeld = this.disease.getConfig().getInt("lavaBucketHeatHeld");
/* 227 */     ct.redstoneTorchHeatHeld = this.disease.getConfig().getInt("redstoneTorchHeatHeld");
/*     */ 
/*     */     
/* 230 */     itemF.createDiseaseVial("Disease Vial", "vial for thrown disease");
/* 231 */     itemF.createSyringe("Syringe", "syringe for extracting disease and injecting vaccine");
/* 232 */     itemF.createVaccine("Vaccine", "vaccine to cure disease");
/* 233 */     itemF.createDiseaseArrow("Disease Arrow", "arrow for shot disease");
/* 234 */     itemF.createColdWater(languageFile.getString("coldWaterName"));
/* 235 */     itemF.createHotMilk(languageFile.getString("hotMilkName"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void importDisease(String enabledDisease) throws IOException {
/* 243 */     DDisease newDisease = new DDisease(this.disease);
/*     */     
/* 245 */     if (newDisease.activateDisease(enabledDisease) == true) {
/* 246 */       this.disease.diseases.put(enabledDisease, newDisease);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void importRemedy(String enabledRemedy) throws IOException {
/* 253 */     Remedy newRemedy = new Remedy(this.disease);
/*     */     
/* 255 */     if (newRemedy.activateRemedy(enabledRemedy) == true)
/* 256 */       this.disease.remedies.put(enabledRemedy, newRemedy); 
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\DiseaseSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */