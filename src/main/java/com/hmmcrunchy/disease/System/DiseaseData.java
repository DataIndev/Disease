/*     */ package com.hmmcrunchy.disease.System;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DiseaseData
/*     */ {
/*     */   private Disease disease;
/*     */   
/*     */   public DiseaseData(Disease diseasePlugin) {
/*  39 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int createFolders(File folder) {
/*  47 */     if (!folder.exists()) {
/*     */       
/*  49 */       folder.mkdir();
/*  50 */       return 1;
/*     */     } 
/*     */     
/*  53 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveToFile(File playerFolder, String player, String thirst) {
/*  60 */     BufferedWriter bw = null;
/*     */     try {
/*  62 */       String path = playerFolder + File.separator + player + ".yml";
/*  63 */       File file = new File(path);
/*  64 */       if (!file.exists()) {
/*  65 */         file.createNewFile();
/*     */       }
/*  67 */       bw = new BufferedWriter(new FileWriter(path));
/*  68 */       bw.write(thirst);
/*  69 */       bw.close();
/*     */     }
/*  71 */     catch (IOException ex) {
/*  72 */       Logger.getLogger(DiseaseData.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } finally {
/*     */       try {
/*  75 */         bw.close();
/*  76 */       } catch (IOException ex) {
/*  77 */         Logger.getLogger(DiseaseData.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createLanguageFile(Plugin plugin) {
/*  88 */     File dataFolder = new File(plugin.getDataFolder().toString());
/*     */     
/*  90 */     File file = new File(dataFolder + File.separator + "language.yml");
/*  91 */     File esFile = new File(dataFolder + File.separator + "language - ES.yml");
/*     */     
/*  93 */     if (!dataFolder.exists()) {
/*  94 */       Bukkit.getLogger().info("Trying to create plugin folder.");
/*     */       try {
/*  96 */         boolean success = (new File(plugin.getDataFolder().toString())).mkdir();
/*  97 */         if (success)
/*     */         {
/*  99 */           Bukkit.getLogger().info("Sucessfully created plugin folder.");
/*     */         
/*     */         }
/*     */       }
/* 103 */       catch (Exception e) {
/*     */         
/* 105 */         Bukkit.getLogger().info("Failed to create plugin folder.");
/*     */         
/* 107 */         Bukkit.getLogger().info(e.getMessage());
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (dataFolder.exists() && !file.exists()) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 119 */         InputStream templateIn = plugin.getResource("language.yml");
/* 120 */         OutputStream outStream = new FileOutputStream(file);
/* 121 */         int read = 0;
/* 122 */         byte[] bytes = new byte[1024];
/*     */         
/* 124 */         while ((read = templateIn.read(bytes)) != -1)
/*     */         {
/* 126 */           outStream.write(bytes, 0, read);
/*     */         }
/*     */         
/* 129 */         templateIn.close();
/* 130 */         outStream.flush();
/* 131 */         outStream.close();
/*     */ 
/*     */ 
/*     */         
/* 135 */         InputStream templateEs = plugin.getResource("language - ES.yml");
/* 136 */         OutputStream outStreamEs = new FileOutputStream(esFile);
/* 137 */         int readEs = 0;
/* 138 */         byte[] bytesEs = new byte[1024];
/*     */         
/* 140 */         while ((read = templateEs.read(bytesEs)) != -1)
/*     */         {
/* 142 */           outStreamEs.write(bytesEs, 0, readEs);
/*     */         }
/*     */         
/* 145 */         templateIn.close();
/* 146 */         outStream.flush();
/* 147 */         outStream.close();
/*     */       }
/* 149 */       catch (Exception e) {
/* 150 */         Bukkit.getLogger().info("language file creation error");
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDiseaseFiles(Plugin plugin) {
/* 159 */     List<String> diseaseList = new ArrayList<>();
/* 160 */     diseaseList.clear();
/* 161 */     diseaseList.add("BrokenLeg");
/* 162 */     diseaseList.add("ChickenPox");
/* 163 */     diseaseList.add("Plague");
/* 164 */     diseaseList.add("SwampFever");
/* 165 */     diseaseList.add("Cholera");
/* 166 */     diseaseList.add("DiseaseTemplate");
/*     */     
/* 168 */     File dataFolder = new File(plugin.getDataFolder().toString() + File.separator + "diseases");
/*     */     
/* 170 */     for (String diseaseName : diseaseList) {
/*     */ 
/*     */       
/* 173 */       File file = new File(dataFolder + File.separator + diseaseName + ".yml");
/*     */       
/* 175 */       if (!file.exists()) {
/*     */         
/*     */         try {
/*     */ 
/*     */ 
/*     */           
/* 181 */           InputStream templateIn = plugin.getResource(diseaseName + ".yml");
/* 182 */           OutputStream outStream = new FileOutputStream(file);
/* 183 */           int read = 0;
/* 184 */           byte[] bytes = new byte[1024];
/*     */           
/* 186 */           while ((read = templateIn.read(bytes)) != -1)
/*     */           {
/* 188 */             outStream.write(bytes, 0, read);
/*     */           }
/*     */           
/* 191 */           templateIn.close();
/* 192 */           outStream.flush();
/* 193 */           outStream.close();
/*     */         
/*     */         }
/* 196 */         catch (Exception e) {
/* 197 */           Bukkit.getLogger().info(diseaseName + " file creation error");
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createRemedyFiles(Plugin plugin) {
/* 208 */     List<String> remedyList = new ArrayList<>();
/* 209 */     remedyList.clear();
/* 210 */     remedyList.add("Splint");
/* 211 */     remedyList.add("WormwoodPotion");
/* 212 */     remedyList.add("MudbanePotion");
/* 213 */     remedyList.add("HenstoothPotion");
/* 214 */     remedyList.add("BrightWater");
/*     */     
/* 216 */     File dataFolder = new File(plugin.getDataFolder().toString() + File.separator + "remedies");
/*     */     
/* 218 */     for (String remedyName : remedyList) {
/*     */ 
/*     */       
/* 221 */       File file = new File(dataFolder + File.separator + remedyName + ".yml");
/*     */       
/* 223 */       if (!file.exists()) {
/*     */         
/*     */         try {
/*     */ 
/*     */ 
/*     */           
/* 229 */           InputStream templateIn = plugin.getResource(remedyName + ".yml");
/* 230 */           OutputStream outStream = new FileOutputStream(file);
/* 231 */           int read = 0;
/* 232 */           byte[] bytes = new byte[1024];
/*     */           
/* 234 */           while ((read = templateIn.read(bytes)) != -1)
/*     */           {
/* 236 */             outStream.write(bytes, 0, read);
/*     */           }
/*     */           
/* 239 */           templateIn.close();
/* 240 */           outStream.flush();
/* 241 */           outStream.close();
/*     */         
/*     */         }
/* 244 */         catch (Exception e) {
/* 245 */           Bukkit.getLogger().info(remedyName + " file creation error");
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void CheckFolders() {
/* 256 */     File folder = this.disease.folder;
/* 257 */     File playerData = this.disease.playerData;
/* 258 */     File diseaseData = this.disease.diseaseData;
/* 259 */     File remedyData = this.disease.remedyData;
/* 260 */     File configFile = this.disease.configFile;
/* 261 */     File LanguageFile = this.disease.LanguageFile;
/*     */ 
/*     */     
/* 264 */     if (!folder.exists()) {
/*     */ 
/*     */       
/* 267 */       Bukkit.getLogger().info("First time run - Creating files");
/* 268 */       int i = 0;
/*     */       
/* 270 */       i = createFolders(folder);
/* 271 */       if (i == 1) {
/* 272 */         Bukkit.getLogger().info("Disease Folder created");
/*     */       }
/*     */       
/* 275 */       i = createFolders(playerData);
/* 276 */       if (i == 1) {
/* 277 */         Bukkit.getLogger().info("Player Data folder Created");
/*     */       }
/*     */       
/* 280 */       i = createFolders(diseaseData);
/* 281 */       if (i == 1) {
/* 282 */         Bukkit.getLogger().info("Disease Data folder Created");
/* 283 */         createDiseaseFiles((Plugin)this.disease);
/*     */       } 
/*     */       
/* 286 */       i = createFolders(remedyData);
/* 287 */       if (i == 1) {
/* 288 */         Bukkit.getLogger().info("Remedy Data folder Created");
/* 289 */         createRemedyFiles((Plugin)this.disease);
/*     */       } 
/*     */       
/* 292 */       Bukkit.getLogger().info("Plugin Loading");
/*     */     } else {
/*     */       
/* 295 */       Bukkit.getLogger().info("Plugin Loading");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     if (!configFile.exists()) {
/*     */       
/* 303 */       this.disease.saveDefaultConfig();
/* 304 */       Bukkit.getLogger().info("Creating new config file");
/*     */     } else {
/*     */       
/* 307 */       Bukkit.getLogger().info("Loading configuration file");
/*     */     } 
/*     */     
/* 310 */     if (!LanguageFile.exists()) {
/*     */       
/* 312 */       Bukkit.getLogger().info("Creating new language file");
/* 313 */       createLanguageFile((Plugin)this.disease);
/*     */       
/* 315 */       Bukkit.getLogger().info("Loading Language file");
/* 316 */       this.disease.languageFile = YamlConfiguration.loadConfiguration(LanguageFile);
/*     */       
/*     */       try {
/* 319 */         Thread.sleep(4000L);
/* 320 */       } catch (InterruptedException ex) {
/* 321 */         Logger.getLogger(Disease.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } else {
/*     */       
/* 325 */       Bukkit.getLogger().info("Loading Language file");
/* 326 */       this.disease.languageFile = YamlConfiguration.loadConfiguration(LanguageFile);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\DiseaseData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */