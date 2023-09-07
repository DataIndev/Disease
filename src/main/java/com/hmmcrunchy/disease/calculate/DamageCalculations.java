/*     */ package com.hmmcrunchy.disease.calculate;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.classes.DDisease;
/*     */ import com.hmmcrunchy.disease.classes.DPlayer;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
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
/*     */ public class DamageCalculations
/*     */ {
/*     */   private Disease disease;
/*     */   
/*     */   public DamageCalculations(Disease diseasePlugin) {
/*  33 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processEntityDamageEvent(Player p, EntityDamageEvent event) {
/*  40 */     if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - processing entity environment damage");
/*     */     
/*  42 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*     */ 
/*     */     
/*  45 */     if (this.disease.citizensEnabled) {
/*  46 */       if (this.disease.debug) Bukkit.getLogger().info("check " + event.getEntity().getName() + " is npc");
/*     */       
/*  48 */       if (event.getEntity().hasMetadata("NPC")) {
/*  49 */         if (this.disease.debug) Bukkit.getLogger().info("player hit npc");
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  55 */     if (!dPlayer.infection.equalsIgnoreCase("none")) {
/*  56 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - already infected - returning");
/*     */       
/*     */       return;
/*     */     } 
/*  60 */     for (DDisease dDisease : this.disease.diseases.values()) {
/*     */ 
/*     */       
/*  63 */       if (dDisease.infectionMethod.equalsIgnoreCase("environmentDamage")) {
/*     */         
/*  65 */         if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " - has the requirement 'damage' and damage type '" + dDisease.infectionMethodType + "'");
/*     */         
/*  67 */         if (event.getCause().name().equalsIgnoreCase(dDisease.infectionMethodType)) {
/*     */ 
/*     */           
/*  70 */           if (this.disease.debug) this.disease.getLogger().info("Damage type matches disease");
/*     */ 
/*     */           
/*  73 */           int chance = dDisease.infectionChance;
/*     */           
/*  75 */           if (chance == 0) {
/*  76 */             if (this.disease.debug) this.disease.getLogger().info("Disease is disabled with zero chance - " + dDisease.name);
/*     */             
/*     */             return;
/*     */           } 
/*  80 */           Random random = new Random();
/*  81 */           int rand = random.nextInt(100);
/*  82 */           if (rand < chance) {
/*  83 */             if (this.disease.debug) this.disease.getLogger().info(rand + " is within " + chance + "%");
/*     */             
/*  85 */             dPlayer.playerInfect(dDisease.name, dDisease.infectionMessage);
/*     */             return;
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
/*     */   public void processEntityDamageByEntityEvent(Player p, EntityDamageByEntityEvent event) {
/* 100 */     if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - processing entity damage by entity damage");
/*     */     
/* 102 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/* 103 */     Entity damager = event.getDamager();
/*     */     
/* 105 */     if (this.disease.citizensEnabled == true)
/* 106 */     { if (this.disease.debug) Bukkit.getLogger().info("check " + event.getEntity().getName() + " is npc");
/*     */       
/* 108 */       if (event.getEntity().hasMetadata("NPC")) {
/* 109 */         if (this.disease.debug) Bukkit.getLogger().info("player hit npc");
/*     */ 
/*     */ 
/*     */         
/*     */         return;
/*     */       }  }
/* 115 */     else if (this.disease.debug) { Bukkit.getLogger().info("citizens not enabled"); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     if (damager instanceof Player) {
/* 121 */       processPlayerDamagePlayer(p, event);
/*     */     }
/*     */     
/* 124 */     if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
/* 125 */       processArrow(p, event);
/*     */     }
/*     */     
/* 128 */     if (!dPlayer.infection.equalsIgnoreCase("none")) {
/* 129 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - already infected - returning");
/*     */       
/*     */       return;
/*     */     } 
/* 133 */     for (DDisease dDisease : this.disease.diseases.values()) {
/*     */ 
/*     */       
/* 136 */       if (dDisease.infectionMethod.equalsIgnoreCase("mobDamage")) {
/*     */         
/* 138 */         if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " - has the requirement 'damage' and mob type '" + dDisease.infectionMethodType + "'");
/*     */         
/* 140 */         if (this.disease.citizensEnabled && 
/* 141 */           event.getEntity().hasMetadata("NPC")) {
/* 142 */           if (this.disease.debug) this.disease.getLogger().info("player hit npc");
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/* 147 */         if (this.disease.debug) this.disease.getLogger().info("player hit by " + damager.getType().name());
/*     */ 
/*     */         
/* 150 */         if (damager.getType().name().equalsIgnoreCase(dDisease.infectionMethodType)) {
/*     */ 
/*     */           
/* 153 */           if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " damager matches " + damager.getType().name());
/*     */           
/* 155 */           int chance = dDisease.infectionChance;
/*     */           
/* 157 */           if (chance == 0) {
/* 158 */             if (this.disease.debug) this.disease.getLogger().info("Disease is disabled with zero chance - " + dDisease.name);
/*     */             
/*     */             return;
/*     */           } 
/* 162 */           Random random = new Random();
/* 163 */           int rand = random.nextInt(100);
/* 164 */           if (rand < chance) {
/* 165 */             if (this.disease.debug) this.disease.getLogger().info(rand + " is within " + chance + "%");
/*     */             
/* 167 */             dPlayer.playerInfect(dDisease.name, dDisease.infectionMessage);
/*     */             return;
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
/*     */   void processPlayerDamagePlayer(Player p, EntityDamageByEntityEvent event) {
/* 182 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*     */     
/* 184 */     Player damager = (Player)event.getDamager();
/* 185 */     Material item = damager.getItemInHand().getType();
/*     */     
/* 187 */     for (DDisease dDisease : this.disease.diseases.values()) {
/*     */ 
/*     */ 
/*     */       
/* 191 */       if (dDisease.infectionMethod.equalsIgnoreCase("playerDamage")) {
/*     */         
/* 193 */         if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " - has the requirement 'damage' and infection method type '" + dDisease.infectionMethodType + "'");
/*     */         
/* 195 */         if (this.disease.citizensEnabled && 
/* 196 */           event.getEntity().hasMetadata("NPC")) {
/* 197 */           if (this.disease.debug) this.disease.getLogger().info("player hit npc");
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/* 202 */         if (this.disease.debug) this.disease.getLogger().info("player hit by " + damager.getName());
/*     */ 
/*     */         
/* 205 */         if (item.name().equalsIgnoreCase(dDisease.infectionMethodType)) {
/*     */ 
/*     */           
/* 208 */           if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " damaging item matches " + item.name());
/*     */           
/* 210 */           int chance = dDisease.infectionChance;
/*     */           
/* 212 */           if (chance == 0) {
/* 213 */             if (this.disease.debug) this.disease.getLogger().info("Disease is disabled with zero chance - " + dDisease.name);
/*     */             
/*     */             return;
/*     */           } 
/* 217 */           Random random = new Random();
/* 218 */           int rand = random.nextInt(100);
/* 219 */           if (rand < chance) {
/* 220 */             if (this.disease.debug) this.disease.getLogger().info(rand + " is within " + chance + "%");
/*     */             
/* 222 */             dPlayer.playerInfect(dDisease.name, dDisease.infectionMessage);
/*     */             return;
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
/*     */   void processArrow(Player p, EntityDamageByEntityEvent event) {
/* 238 */     if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
/*     */       
/* 240 */       DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*     */       
/* 242 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " damaged by projectile " + event.getCause().name()); 
/* 243 */       if (event.getCause().name().contains(" Arrow")) {
/*     */         
/* 245 */         Entity arrow = event.getEntity();
/* 246 */         if (this.disease.debug) this.disease.getLogger().info(p.getName() + " damaged by arrow - " + arrow.getCustomName());
/*     */         
/* 248 */         for (DDisease dDisease : this.disease.diseases.values()) {
/*     */ 
/*     */           
/* 251 */           if (dDisease.arrowEnabled == true) {
/*     */             
/* 253 */             if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " - has the ability to infect via arrow");
/*     */             
/* 255 */             if (arrow.getCustomName().equalsIgnoreCase(ChatColor.GREEN + dDisease.name + " Arrow")) {
/*     */ 
/*     */               
/* 258 */               if (this.disease.debug) this.disease.getLogger().info("arrow matches disease arrow type");
/*     */               
/* 260 */               int chance = dDisease.infectionChance;
/*     */               
/* 262 */               if (chance == 0) {
/* 263 */                 if (this.disease.debug) this.disease.getLogger().info("Disease is disabled with zero chance - " + dDisease.name);
/*     */                 
/*     */                 return;
/*     */               } 
/* 267 */               Random random = new Random();
/* 268 */               int rand = random.nextInt(100);
/*     */ 
/*     */               
/* 271 */               if (rand < chance) {
/* 272 */                 if (this.disease.debug) this.disease.getLogger().info(rand + " is within " + chance + "%");
/*     */                 
/* 274 */                 dPlayer.playerInfect(dDisease.name, dDisease.infectionMessage);
/*     */                 return;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\calculate\DamageCalculations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */