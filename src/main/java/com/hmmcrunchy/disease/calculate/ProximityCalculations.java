/*    */ package com.hmmcrunchy.disease.calculate;
/*    */ 
/*    */ import com.hmmcrunchy.disease.Disease;
/*    */ import com.hmmcrunchy.disease.classes.DDisease;
/*    */ import com.hmmcrunchy.disease.classes.DPlayer;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProximityCalculations
/*    */ {
/*    */   private Disease disease;
/*    */   
/*    */   public ProximityCalculations(Disease diseasePlugin) {
/* 32 */     this.disease = diseasePlugin;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processProximity(Player p) {
/* 39 */     if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - processing proximity");
/*    */     
/* 41 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*    */     
/* 43 */     if (dPlayer.infection != "none") {
/* 44 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - already infected - returning");
/*    */       
/*    */       return;
/*    */     } 
/* 48 */     for (DDisease dDisease : this.disease.diseases.values()) {
/*    */ 
/*    */       
/* 51 */       if (dDisease.infectionMethod.equalsIgnoreCase("proximity")) {
/*    */         
/* 53 */         if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " - has the requirement 'proximity' and mob type '" + dDisease.infectionMethodType + "'");
/*    */ 
/*    */         
/* 56 */         List<Entity> nearby = p.getNearbyEntities(5.0D, 5.0D, 5.0D);
/*    */         
/* 58 */         if (nearby == null) {
/*    */           
/* 60 */           if (this.disease.debug) this.disease.getLogger().info("No animal near " + p.getName());
/*    */           
/*    */           continue;
/*    */         } 
/* 64 */         int i = 0;
/*    */         
/* 66 */         for (Entity entity : nearby) {
/*    */ 
/*    */           
/* 69 */           if (entity.getType() == EntityType.valueOf(dDisease.infectionMethodType.toUpperCase()))
/*    */           {
/* 71 */             i++;
/*    */           }
/*    */         } 
/*    */         
/* 75 */         if (i > 5) {
/*    */           
/* 77 */           if (this.disease.debug) this.disease.getLogger().info(i + "animals near near" + p.getName());
/*    */ 
/*    */           
/* 80 */           int chance = dDisease.infectionChance;
/*    */           
/* 82 */           if (chance == 0) {
/* 83 */             if (this.disease.debug) this.disease.getLogger().info("Disease is disabled with zero chance - " + dDisease.name);
/*    */             
/*    */             return;
/*    */           } 
/* 87 */           Random random = new Random();
/* 88 */           int rand = random.nextInt(100) + ((Integer)dPlayer.dImmunity.get(dDisease.name)).intValue();
/* 89 */           if (rand < chance) {
/* 90 */             if (this.disease.debug) this.disease.getLogger().info(rand + " is within " + chance + "%");
/*    */             
/* 92 */             dPlayer.playerInfect(dDisease.name, dDisease.infectionMessage);
/*    */             return;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\calculate\ProximityCalculations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */