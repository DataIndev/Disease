/*    */ package com.hmmcrunchy.disease.effects;
/*    */ 
/*    */ import com.hmmcrunchy.disease.Disease;
/*    */ import com.hmmcrunchy.disease.classes.DDisease;
/*    */ import com.hmmcrunchy.disease.classes.DPlayer;
/*    */ import java.util.Random;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.ThrownPotion;
/*    */ import org.bukkit.event.entity.PotionSplashEvent;
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
/*    */ public class UseVials
/*    */ {
/*    */   public void VialSplash(PotionSplashEvent event, Disease disease, boolean debug) {
/* 31 */     for (LivingEntity entity : event.getAffectedEntities()) {
/*    */       
/* 33 */       if (entity instanceof Player) {
/*    */         
/* 35 */         Player p = (Player)entity;
/* 36 */         DPlayer dPlayer = (DPlayer)disease.dPlayers.get(p.getUniqueId().toString());
/*    */         
/* 38 */         if (disease.debug) disease.getLogger().info(p.getName() + " damaged by splashpotion");
/*    */         
/* 40 */         ThrownPotion thrownPotion = event.getEntity();
/* 41 */         if (disease.debug) disease.getLogger().info(p.getName() + " damaged by vial - " + thrownPotion.getCustomName());
/*    */         
/* 43 */         for (DDisease dDisease : disease.diseases.values()) {
/*    */ 
/*    */           
/* 46 */           if (dDisease.vialEnabled == true) {
/*    */             
/* 48 */             if (disease.debug) disease.getLogger().info(dDisease.name + " - has the ability to infect via vial");
/*    */             
/* 50 */             if (thrownPotion.getCustomName().equalsIgnoreCase(ChatColor.GREEN + dDisease.name + " Vial")) {
/*    */ 
/*    */               
/* 53 */               if (disease.debug) disease.getLogger().info("arrow matches disease vial type");
/*    */               
/* 55 */               int chance = dDisease.infectionChance;
/*    */               
/* 57 */               if (chance == 0) {
/* 58 */                 if (disease.debug) disease.getLogger().info("Disease is disabled with zero chance - " + dDisease.name);
/*    */                 
/*    */                 return;
/*    */               } 
/* 62 */               Random random = new Random();
/* 63 */               int rand = random.nextInt(100);
/* 64 */               if (rand < chance) {
/* 65 */                 if (disease.debug) disease.getLogger().info(rand + " is within " + chance + "%");
/*    */                 
/* 67 */                 dPlayer.playerInfect(dDisease.name, dDisease.infectionMessage);
/*    */                 return;
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\effects\UseVials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */