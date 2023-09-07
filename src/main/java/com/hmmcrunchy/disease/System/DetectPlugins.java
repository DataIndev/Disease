/*     */ package com.hmmcrunchy.disease.System;
/*     */ 
/*     */ import com.github.games647.scoreboardstats.ScoreboardStats;
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import net.milkbowl.vault.Vault;
/*     */ import org.bukkit.Bukkit;
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
/*     */ public class DetectPlugins
/*     */ {
/*     */   private Disease disease;
/*     */   public SbsLink sbsLink;
/*     */   
/*     */   public DetectPlugins(Disease diseasePlugin) {
/*  29 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkPlugins() {
/*  37 */     this.disease.scoreboardStatsEnabled = this.disease.getConfig().getBoolean("useScoreboardStats");
/*  38 */     this.disease.worldGuardEnabled = this.disease.getConfig().getBoolean("useWorldGuard");
/*     */ 
/*     */ 
/*     */     
/*  42 */     if (this.disease.scoreboardStats != null) {
/*  43 */       this.disease.getLogger().info("Scoreboard Stats Found");
/*     */       
/*  45 */       if (this.disease.getConfig().getString("useScoreboardStats").equals("false"))
/*     */       {
/*  47 */         this.disease.scoreboardStatsEnabled = false;
/*  48 */         this.disease.getLogger().info("Scoreboard Stats disabled in config");
/*     */       }
/*  50 */       else if (this.disease.getConfig().getString("useScoreboardStats").equals("true"))
/*     */       {
/*  52 */         this.disease.scoreboardStatsEnabled = true;
/*     */         
/*  54 */         ScoreboardStats sbs = (ScoreboardStats)Bukkit.getPluginManager().getPlugin("ScoreboardStats");
/*     */ 
/*     */         
/*  57 */         this.disease.getLogger().info("Scoreboard Stats Enabled");
/*  58 */         this.sbsLink = new SbsLink(this.disease, sbs);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  63 */       this.disease.scoreboardStatsEnabled = false;
/*  64 */       this.disease.getLogger().info("Scoreboard Stats not Found");
/*     */     } 
/*     */ 
/*     */     
/*  68 */     if (this.disease.citizens != null) {
/*  69 */       this.disease.getLogger().info("Citizens plugin Found");
/*     */ 
/*     */       
/*  72 */       this.disease.citizensEnabled = true;
/*  73 */       this.disease.getLogger().info("Hooking into citizens");
/*     */     }
/*     */     else {
/*     */       
/*  77 */       this.disease.citizensEnabled = false;
/*  78 */       this.disease.getLogger().info("Citizens plugin not Found");
/*     */     } 
/*     */ 
/*     */     
/*  82 */     if (this.disease.vault != null) {
/*  83 */       this.disease.getLogger().info("Vault plugin Found");
/*     */ 
/*     */       
/*  86 */       this.disease.vaultEnabled = true;
/*     */       
/*  88 */       Vault vault = (Vault)Bukkit.getPluginManager().getPlugin("Vault");
/*     */ 
/*     */       
/*  91 */       this.disease.getLogger().info("Vault Enabled");
/*  92 */       VaultLink vaultLink = new VaultLink(this.disease, vault);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  97 */       this.disease.vaultEnabled = false;
/*  98 */       this.disease.getLogger().info("Vault plugin not Found");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (this.disease.worldGuardEnabled == true) {
/*     */ 
/*     */       
/* 106 */       if (this.disease.getServer().getPluginManager().getPlugin("WorldGuard") != null) {
/*     */         
/* 108 */         this.disease.getLogger().info("World Guard Enabled");
/*     */       } else {
/*     */         
/* 111 */         this.disease.worldGuardEnabled = false;
/* 112 */         this.disease.getLogger().info("Error - World Guard plugin not found");
/*     */       } 
/*     */     } else {
/* 115 */       this.disease.getLogger().info("World Guard disabled in config");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\DetectPlugins.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */