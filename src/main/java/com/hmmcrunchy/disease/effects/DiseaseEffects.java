/*     */ package com.hmmcrunchy.disease.effects;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.classes.DDisease;
/*     */ import com.hmmcrunchy.disease.classes.DPlayer;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DiseaseEffects
/*     */ {
/*     */   Disease disease;
/*     */   
/*     */   public DiseaseEffects(Disease plugin) {
/*  40 */     this.disease = plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ProcessDisease(Player p, int timeCheck) {
/*  47 */     if (this.disease.debug) this.disease.getLogger().info("processing disease for " + p.getName());
/*     */     
/*  49 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*     */     
/*  51 */     if (dPlayer.infection.equalsIgnoreCase("none")) {
/*     */       
/*  53 */       if (this.disease.debug) this.disease.getLogger().info("no disease - returning");
/*     */       
/*     */       return;
/*     */     } 
/*  57 */     int sickness = dPlayer.sickness;
/*  58 */     DDisease dDisease = (DDisease)this.disease.diseases.get(dPlayer.infection);
/*  59 */     if (this.disease.debug) this.disease.getLogger().info(p.getName() + "is infected with " + dDisease.name + ", with sickeness level of " + sickness);
/*     */     
/*  61 */     for (String action : dDisease.sickActions.keySet()) {
/*     */       
/*  63 */       if (this.disease.debug) this.disease.getLogger().info("process action " + action);
/*     */       
/*  65 */       if (((Integer)dDisease.sickActions.get(action)).intValue() < sickness) {
/*     */ 
/*     */         
/*  68 */         if (action.equalsIgnoreCase("cough")) {
/*     */           
/*  70 */           if (this.disease.debug) this.disease.getLogger().info("has cough"); 
/*  71 */           if (dDisease.infectious == true)
/*  72 */             radiusInfect(p, dPlayer, dDisease.name, dDisease.infectionChance); 
/*     */           continue;
/*     */         } 
/*  75 */         if (action.equalsIgnoreCase("sneeze")) {
/*     */           
/*  77 */           if (this.disease.debug) this.disease.getLogger().info("has sneeze"); 
/*  78 */           p.getWorld().spawnParticle(Particle.SNEEZE, p.getLocation(), 2, -1.0D, 1.0D, 0.0D);
/*  79 */           if (dDisease.infectious == true)
/*  80 */             radiusInfect(p, dPlayer, dDisease.name, dDisease.infectionChance); 
/*     */           continue;
/*     */         } 
/*  83 */         if (action.equalsIgnoreCase("lashOut")) {
/*     */           
/*  85 */           if (this.disease.debug) this.disease.getLogger().info("has lash out"); 
/*  86 */           radiusDamage(p, dPlayer, dDisease.name, dDisease.infectionChance); continue;
/*     */         } 
/*  88 */         if (action.contains("damage")) {
/*     */           
/*  90 */           if (this.disease.debug) this.disease.getLogger().info(" has damage " + action); 
/*  91 */           int intDamage = Integer.parseInt(action.split("-")[1]);
/*  92 */           p.damage(intDamage);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  99 */     for (String sound : dDisease.sickSounds.keySet()) {
/*     */       
/* 101 */       if (this.disease.debug) this.disease.getLogger().info("process sound " + sound + " - target " + dDisease.sickSounds.get(sound) + " - current " + sickness); 
/* 102 */       if (((Integer)dDisease.sickSounds.get(sound)).intValue() < sickness) {
/*     */         
/* 104 */         if (sound.equalsIgnoreCase("burp")) {
/*     */           
/* 106 */           if (this.disease.debug) this.disease.getLogger().info("burp"); 
/* 107 */           p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 4.0F, 2.0F); continue;
/*     */         } 
/* 109 */         if (sound.equalsIgnoreCase("heavyBreath")) {
/*     */           
/* 111 */           if (this.disease.debug) this.disease.getLogger().info("heavy breath"); 
/* 112 */           p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1.0F, 1.0F); continue;
/*     */         } 
/* 114 */         if (sound.equalsIgnoreCase("sneeze")) {
/*     */           
/* 116 */           if (this.disease.debug) this.disease.getLogger().info("sneeze"); 
/* 117 */           p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CAT_HISS, 1.0F, 1.0F); continue;
/*     */         } 
/* 119 */         if (sound.equalsIgnoreCase("choke")) {
/*     */           
/* 121 */           if (this.disease.debug) this.disease.getLogger().info("choke"); 
/* 122 */           p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP_SMALL, 4.0F, 2.0F); continue;
/*     */         } 
/* 124 */         if (sound.equalsIgnoreCase("cough")) {
/*     */           
/* 126 */           if (this.disease.debug) this.disease.getLogger().info("cough"); 
/* 127 */           p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LLAMA_ANGRY, 4.0F, 2.0F); continue;
/*     */         } 
/* 129 */         if (sound.equalsIgnoreCase("crunch")) {
/*     */           
/* 131 */           if (this.disease.debug) this.disease.getLogger().info("crunch"); 
/* 132 */           p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_HURT, 1.0F, 1.0F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     for (String effect : dDisease.sickEffects.keySet()) {
/*     */       
/* 142 */       if (this.disease.debug) this.disease.getLogger().info("process effect " + effect); 
/* 143 */       if (((Integer)dDisease.sickEffects.get(effect)).intValue() < sickness) {
/*     */         
/* 145 */         if (effect.equalsIgnoreCase("vomit")) {
/*     */           
/* 147 */           if (this.disease.debug) this.disease.getLogger().info("vomit"); 
/* 148 */           p.getWorld().playSound(p.getLocation(), Sound.ENTITY_DROWNED_SWIM, 4.0F, 2.0F);
/* 149 */           p.getWorld().playEffect(p.getEyeLocation(), Effect.DRAGON_BREATH, 2); continue;
/*     */         } 
/* 151 */         if (effect.equalsIgnoreCase("sweat")) {
/*     */           
/* 153 */           if (this.disease.debug) this.disease.getLogger().info("sweat"); 
/* 154 */           p.getWorld().spawnParticle(Particle.DRIP_WATER, p.getEyeLocation(), 5, -1.0D, 1.0D, 0.0D); continue;
/*     */         } 
/* 156 */         if (effect.equalsIgnoreCase("poop")) {
/*     */           
/* 158 */           if (this.disease.debug) this.disease.getLogger().info("poop"); 
/* 159 */           p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation(), 6, 0.0D, 1.0D, 0.0D); continue;
/*     */         } 
/* 161 */         if (effect.equalsIgnoreCase("bleedBody")) {
/*     */           
/* 163 */           if (this.disease.debug) this.disease.getLogger().info("bleed body"); 
/* 164 */           p.getWorld().spawnParticle(Particle.DRIP_LAVA, p.getLocation(), 6, 0.0D, 1.0D, 0.0D); continue;
/*     */         } 
/* 166 */         if (effect.equalsIgnoreCase("bleedHead")) {
/*     */           
/* 168 */           if (this.disease.debug) this.disease.getLogger().info("bleed head"); 
/* 169 */           p.getWorld().spawnParticle(Particle.DRIP_LAVA, p.getEyeLocation(), 6, 0.0D, 1.0D, 0.0D); continue;
/*     */         } 
/* 171 */         if (effect.equalsIgnoreCase("blindness")) {
/*     */           
/* 173 */           if (this.disease.debug) this.disease.getLogger().info("blindness"); 
/* 174 */           p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, timeCheck, 2)); continue;
/*     */         } 
/* 176 */         if (effect.equalsIgnoreCase("slowness")) {
/*     */           
/* 178 */           if (this.disease.debug) this.disease.getLogger().info("slowness"); 
/* 179 */           p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, timeCheck, 2)); continue;
/*     */         } 
/* 181 */         if (effect.equalsIgnoreCase("confusion")) {
/*     */           
/* 183 */           if (this.disease.debug) this.disease.getLogger().info("confusion"); 
/* 184 */           p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, timeCheck, 2));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     int sickIncreased = sickness + dDisease.sicknessIncrease;
/* 194 */     if (sickIncreased > 100)
/*     */     {
/* 196 */       sickIncreased = 100;
/*     */     }
/*     */ 
/*     */     
/* 200 */     dPlayer.sickness = sickIncreased;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void radiusInfect(Player p, DPlayer dp, String diseaseInfect, int chance) {
/* 207 */     List<Entity> nearby = p.getNearbyEntities(5.0D, 5.0D, 5.0D);
/*     */     
/* 209 */     if (nearby != null)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 215 */       for (Entity entity : nearby) {
/*     */ 
/*     */         
/* 218 */         if (entity instanceof Player) {
/*     */           
/* 220 */           Random random = new Random();
/* 221 */           int i = random.nextInt(100);
/*     */           
/* 223 */           if (i < chance)
/*     */           {
/* 225 */             dp.playerInfect(diseaseInfect, this.disease.messaging.infectedByRadiusProximity);
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
/*     */   void radiusDamage(Player p, DPlayer dPlayer, String diseaseType, int chance) {
/* 240 */     List<Entity> nearby = p.getNearbyEntities(5.0D, 5.0D, 5.0D);
/*     */     
/* 242 */     if (nearby != null)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 248 */       for (Entity entity : nearby) {
/*     */ 
/*     */         
/* 251 */         if (entity instanceof Player) {
/*     */           
/* 253 */           Player b = (Player)entity;
/* 254 */           Random random = new Random();
/* 255 */           int i = random.nextInt(100);
/*     */           
/* 257 */           if (i < chance) {
/*     */             
/* 259 */             b.damage(2.0D);
/* 260 */             dPlayer.playerInfect(diseaseType, this.disease.messaging.infectedByRadiusDamage);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\effects\DiseaseEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */