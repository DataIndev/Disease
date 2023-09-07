/*    */ package com.hmmcrunchy.disease.calculate;
/*    */ 
/*    */ import com.hmmcrunchy.disease.Disease;
/*    */ import com.hmmcrunchy.disease.classes.DDisease;
/*    */ import com.hmmcrunchy.disease.classes.DPlayer;
/*    */ import java.util.Random;
/*    */ import org.bukkit.block.Biome;
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
/*    */ public class BiomeCalculations
/*    */ {
/*    */   private Disease disease;
/*    */   
/*    */   public BiomeCalculations(Disease diseasePlugin) {
/* 26 */     this.disease = diseasePlugin;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processBiomes(Player p) {
/* 33 */     if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - processing current biomes");
/*    */     
/* 35 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/* 36 */     Biome playerBiome = p.getLocation().getBlock().getBiome();
/* 37 */     if (this.disease.debug) this.disease.getLogger().info("player is in biome " + playerBiome.name());
/*    */     
/* 39 */     if (!dPlayer.infection.equalsIgnoreCase("none")) {
/* 40 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - already infected - returning");
/*    */       
/*    */       return;
/*    */     } 
/* 44 */     for (DDisease dDisease : this.disease.diseases.values()) {
/*    */ 
/*    */       
/* 47 */       if (dDisease.infectionMethod.equalsIgnoreCase("biome")) {
/*    */         
/* 49 */         if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " - has the requirement 'biome' and biome type '" + dDisease.infectionMethodType + "'");
/*    */         
/* 51 */         if (dDisease.infectionMethodType.equalsIgnoreCase(playerBiome.name())) {
/*    */           
/* 53 */           if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " infection biome matches the players current biome");
/*    */           
/* 55 */           int chance = dDisease.infectionChance;
/*    */           
/* 57 */           if (chance == 0) {
/* 58 */             if (this.disease.debug) this.disease.getLogger().info("Disease is disabled with zero chance - " + dDisease.name);
/*    */             
/*    */             return;
/*    */           } 
/* 62 */           Random random = new Random();
/* 63 */           int i = random.nextInt(100) + ((Integer)dPlayer.dImmunity.get(dDisease.name)).intValue();
/* 64 */           if (i < chance) {
/* 65 */             if (this.disease.debug) this.disease.getLogger().info(i + " is within " + chance + "%");
/*    */             
/* 67 */             dPlayer.playerInfect(dDisease.name, "");
/*    */             return;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\calculate\BiomeCalculations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */