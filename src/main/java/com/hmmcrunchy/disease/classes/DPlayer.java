/*     */ package com.hmmcrunchy.disease.classes;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DPlayer
/*     */ {
/*     */   private Disease disease;
/*     */   public UUID uuid;
/*     */   public String name;
/*     */   Player bukkitPlayer;
/*     */   public double temp;
/*     */   public double normalTemp;
/*     */   public String infection;
/*     */   public int sickness;
/*     */   public LinkedHashMap<String, Integer> dImmunity;
/*     */   
/*     */   public DPlayer(Disease diseasePlugin) {
/*  55 */     this.dImmunity = new LinkedHashMap<>();
/*     */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void activatePlayer(Player p) throws IOException {
/*  61 */     File file = new File(this.disease.playerData + File.separator + p.getUniqueId() + ".yml");
/*     */ 
/*     */     
/*  64 */     if (!file.exists()) {
/*     */       
/*  66 */       file.createNewFile();
/*  67 */       YamlConfiguration player = YamlConfiguration.loadConfiguration(file);
/*     */       
/*  69 */       player.set("name", p.getName());
/*  70 */       player.set("temp", Integer.valueOf(37));
/*  71 */       player.set("normalTemp", Integer.valueOf(37));
/*  72 */       player.set("infection", "none");
/*  73 */       player.set("sickness", Integer.valueOf(0));
/*  74 */       for (String disease : this.disease.diseases.keySet())
/*     */       {
/*  76 */         player.set("immunity." + disease, Integer.valueOf(0));
/*     */       }
/*     */ 
/*     */       
/*  80 */       player.save(file);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  85 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
/*     */     
/*  87 */     this.name = p.getName();
/*  88 */     this.temp = yamlConfiguration.getDouble("temp");
/*  89 */     this.normalTemp = yamlConfiguration.getDouble("normalTemp");
/*  90 */     this.sickness = yamlConfiguration.getInt("sickness");
/*     */     
/*  92 */     if (this.disease.debug) this.disease.getLogger().info("getting disease infections for player " + p.getName() + ":");
/*     */     
/*  94 */     this.infection = yamlConfiguration.getString("infection");
/*     */     
/*  96 */     if (this.infection.equalsIgnoreCase("none"))
/*     */     
/*  98 */     { if (this.disease.debug) this.disease.getLogger().info(p.getName() + " is not infected with any disease");
/*     */       
/*     */        }
/*     */     
/* 102 */     else if (this.disease.debug) { this.disease.getLogger().info(p.getName() + " infected with " + this.infection + " rate = " + this.sickness); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     if (!this.disease.diseases.containsKey(this.infection) && !this.infection.equalsIgnoreCase("none")) {
/* 108 */       this.disease.getLogger().info(p.getName() + " has a disease that no longer exists - removing " + this.infection);
/* 109 */       this.infection = "none";
/* 110 */       this.sickness = 0;
/*     */     } 
/*     */     
/* 113 */     if (this.disease.debug) {
/* 114 */       this.disease.getLogger().info("getting immunity details for player " + p.getName() + ":");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 120 */       for (String immunity : yamlConfiguration.getConfigurationSection("immunity").getKeys(false)) {
/* 121 */         if (this.disease.debug) this.disease.getLogger().info(ChatColor.GREEN + "Player Immunity: " + immunity + " at value " + yamlConfiguration.getInt("immunity." + immunity)); 
/* 122 */         this.dImmunity.put(immunity, Integer.valueOf(yamlConfiguration.getInt("immunity." + immunity)));
/*     */       } 
/* 124 */     } catch (Exception e) {
/* 125 */       this.disease.getLogger().info("no immunity details for player " + p.getName() + " - SKIPPING");
/*     */     } 
/*     */     
/* 128 */     this.bukkitPlayer = p;
/* 129 */     this.disease.getLogger().info("Loaded player " + p.getName());
/*     */     
/* 131 */     if (this.infection.equalsIgnoreCase("none"))
/*     */     {
/* 133 */       playerCure(true, "");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void savePlayer() {
/* 142 */     File file = new File(this.disease.playerData + File.separator + this.bukkitPlayer.getUniqueId() + ".yml");
/* 143 */     YamlConfiguration player = YamlConfiguration.loadConfiguration(file);
/*     */ 
/*     */     
/* 146 */     player.set("name", this.name);
/* 147 */     player.set("temp", Double.valueOf(this.temp));
/* 148 */     player.set("normalTemp", Double.valueOf(this.normalTemp));
/*     */     
/* 150 */     player.set("infection", this.infection);
/* 151 */     player.set("sickness", Integer.valueOf(this.sickness));
/*     */ 
/*     */     
/* 154 */     for (String diseaseImmunity : this.dImmunity.keySet()) {
/* 155 */       player.set("immunity." + diseaseImmunity, this.dImmunity.get(diseaseImmunity));
/*     */     }
/*     */     
/*     */     try {
/* 159 */       player.save(file);
/* 160 */     } catch (IOException ex) {
/* 161 */       Logger.getLogger(DDisease.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 163 */     this.disease.getLogger().info("Saving Disease details for " + this.bukkitPlayer.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void playerInfect(String diseaseType, String infectionMessage) {
/* 169 */     Player p = this.bukkitPlayer;
/* 170 */     DDisease dDisease = (DDisease)this.disease.diseases.get(diseaseType);
/*     */     
/* 172 */     if (this.infection.equalsIgnoreCase("none")) {
/*     */ 
/*     */       
/* 175 */       this.disease.getLogger().info("infecting player " + this.name + " with " + diseaseType);
/* 176 */       this.infection = diseaseType;
/*     */       
/* 178 */       this.disease.de.ProcessDisease(p, this.disease.ds.timeBetweenChecks);
/*     */       
/* 180 */       if (infectionMessage.equalsIgnoreCase("")) {
/* 181 */         infectionMessage = dDisease.infectionMessage;
/*     */       }
/*     */       
/* 184 */       if (this.disease.ds.actionBarEnabled) {
/* 185 */         this.disease.messaging.actionBar(p, ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " " + infectionMessage);
/*     */       } else {
/*     */         
/* 188 */         p.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " " + infectionMessage);
/*     */       } 
/*     */       
/* 191 */       if (this.disease.byteScoreboardEnabled)
/*     */       {
/* 193 */         this.disease.dp.sbsLink.sendScore(p);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void playerCure(boolean silent, String cureMessage) {
/* 200 */     Player p = this.bukkitPlayer;
/*     */     
/* 202 */     this.disease.getLogger().info("Curing player " + p.getName());
/*     */ 
/*     */ 
/*     */     
/* 206 */     if (!this.infection.equalsIgnoreCase("none"))
/*     */     {
/* 208 */       if (this.dImmunity.get(this.infection) != null) {
/* 209 */         this.dImmunity.put(this.infection, Integer.valueOf(((Integer)this.dImmunity.get(this.infection)).intValue() + this.disease.ds.immunityGainOnHeal));
/*     */       } else {
/*     */         
/* 212 */         this.dImmunity.put(this.infection, Integer.valueOf(this.disease.ds.immunityGainOnHeal));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 217 */     this.infection = "none";
/* 218 */     this.sickness = 0;
/* 219 */     this.temp = 37.0D;
/*     */     
/* 221 */     if (!silent) {
/*     */       
/* 223 */       if (this.disease.ds.actionBarEnabled) {
/* 224 */         this.disease.messaging.actionBar(p, ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " " + cureMessage);
/*     */       } else {
/*     */         
/* 227 */         p.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " " + cureMessage);
/*     */       } 
/* 229 */       p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 2, 0.0D, 1.0D, 0.0D);
/* 230 */       p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 2, 1.0D, 1.0D, 0.0D);
/* 231 */       p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 2, 0.0D, 1.0D, 1.0D);
/* 232 */       p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 2, -1.0D, 1.0D, 0.0D);
/* 233 */       p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 2, 0.0D, 1.0D, 1.0D);
/* 234 */       p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 2, 0.0D, 1.0D, -1.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void playerGiveItem(String name, String loreString, Material mat) {
/* 241 */     Player p = this.bukkitPlayer;
/* 242 */     ItemStack item = new ItemStack(mat, 1);
/* 243 */     ItemMeta itemMeta = item.getItemMeta();
/*     */ 
/*     */ 
/*     */     
/* 247 */     itemMeta.setDisplayName(ChatColor.GREEN + name);
/* 248 */     ArrayList<String> lore = new ArrayList<>();
/* 249 */     lore.add(loreString);
/* 250 */     itemMeta.setLore(lore);
/*     */     
/* 252 */     item.setItemMeta(itemMeta);
/*     */     
/* 254 */     p.getInventory().addItem(new ItemStack[] { item });
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\classes\DPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */