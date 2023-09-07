/*     */ package com.hmmcrunchy.disease.System;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.classes.DPlayer;
/*     */ import com.sk89q.worldedit.bukkit.BukkitAdapter;
/*     */ import com.sk89q.worldguard.WorldGuard;
/*     */ import com.sk89q.worldguard.protection.ApplicableRegionSet;
/*     */ import com.sk89q.worldguard.protection.flags.Flags;
/*     */ import com.sk89q.worldguard.protection.flags.StateFlag;
/*     */ import com.sk89q.worldguard.protection.regions.RegionContainer;
/*     */ import com.sk89q.worldguard.protection.regions.RegionQuery;
/*     */ import java.util.Random;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
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
/*     */ public class DiseaseRegions
/*     */ {
/*     */   private Disease disease;
/*     */   
/*     */   public DiseaseRegions(Disease diseasePlugin) {
/*  34 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ProcessRegion(Player p) {
/*  41 */     int val = 0;
/*     */     
/*  43 */     if (this.disease.debug) this.disease.getLogger().info(p.getName() + " Processing regions");
/*     */     
/*  45 */     if (this.disease.worldGuardEnabled == true)
/*     */     
/*  47 */     { if (this.disease.debug) this.disease.getLogger().info("worldguard enabled");
/*     */       
/*  49 */       RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
/*  50 */       RegionQuery query = container.createQuery();
/*     */ 
/*     */       
/*  53 */       ApplicableRegionSet arSet = query.getApplicableRegions(BukkitAdapter.adapt(p.getLocation()));
/*     */ 
/*     */       
/*  56 */       if (!arSet.testState(null, new StateFlag[] { Flags.LIGHTNING }))
/*     */       
/*     */       { 
/*  59 */         if (this.disease.debug) this.disease.getLogger().info(p.getName() + " in lightning cure region ");
/*     */         
/*  61 */         lightningCure(p); }
/*     */       
/*  63 */       else if (!arSet.testState(null, new StateFlag[] { Flags.LEAF_DECAY }))
/*     */       
/*  65 */       { if (this.disease.debug) this.disease.getLogger().info(p.getName() + " in hospital region "); 
/*  66 */         leafdecayHospital(p); }
/*     */       
/*  68 */       else if (!arSet.testState(null, new StateFlag[] { Flags.FROSTED_ICE_MELT }))
/*     */       
/*  70 */       { if (this.disease.debug) this.disease.getLogger().info(p.getName() + " in rat region "); 
/*  71 */         frostedIceMeltRats(p);
/*     */         
/*     */          }
/*     */       
/*  75 */       else if (this.disease.debug) { this.disease.getLogger().info(p.getName() + " not in disease region ");
/*     */          }
/*     */ 
/*     */       
/*     */        }
/*     */     
/*  81 */     else if (this.disease.debug) { this.disease.getLogger().info("worldguard disabled"); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void lightningCure(Player p) {
/*  90 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*     */ 
/*     */     
/*  93 */     if (!dPlayer.infection.equalsIgnoreCase("none"))
/*     */     {
/*  95 */       dPlayer.playerCure(true, "");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void leafdecayHospital(Player p) {
/* 102 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*     */     
/* 104 */     if (!dPlayer.infection.equalsIgnoreCase("None")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 110 */       if (this.disease.debug) {
/* 111 */         this.disease.getLogger().info(p.getName() + " being cured by hospital " + this.disease.ds.sickHospitalReduce);
/*     */       }
/*     */ 
/*     */       
/* 115 */       double temp = dPlayer.temp;
/* 116 */       int sick = dPlayer.sickness;
/*     */       
/* 118 */       temp -= this.disease.ds.sickHospitalReduce;
/* 119 */       if (temp < 0.0D) {
/* 120 */         temp = 0.0D;
/*     */       }
/*     */ 
/*     */       
/* 124 */       sick -= this.disease.ds.sickHospitalReduce;
/*     */       
/* 126 */       dPlayer.temp = temp;
/* 127 */       dPlayer.sickness = sick;
/*     */       
/* 129 */       if (sick <= 0) {
/* 130 */         sick = 0;
/* 131 */         dPlayer.playerCure(false, this.disease.messaging.hospitalCureMessage);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void frostedIceMeltRats(Player p) {
/*     */     int chance;
/* 142 */     if (p.getWorld().getTime() > 0L && p.getWorld().getTime() < 12300L) {
/*     */       
/* 144 */       if (this.disease.debug) this.disease.getLogger().info("is day"); 
/* 145 */       chance = 10;
/*     */     } else {
/*     */       
/* 148 */       if (this.disease.debug) this.disease.getLogger().info("is night"); 
/* 149 */       chance = 40;
/*     */     } 
/*     */     
/* 152 */     Random random = new Random();
/* 153 */     int rand = random.nextInt(100);
/* 154 */     if (rand < chance) {
/* 155 */       if (this.disease.debug) this.disease.getLogger().info("Rat chance is over 80% - creating rat");
/*     */       
/* 157 */       Entity rat = p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
/* 158 */       rat.setCustomName("Rat");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\DiseaseRegions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */