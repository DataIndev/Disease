/*     */ package com.hmmcrunchy.disease.calculate;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.classes.DDisease;
/*     */ import com.hmmcrunchy.disease.classes.DPlayer;
/*     */ import com.hmmcrunchy.disease.classes.Remedy;
/*     */ import java.util.Random;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*     */ import org.bukkit.inventory.ItemStack;
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
/*     */ public class ConsumeCalculations
/*     */ {
/*     */   private Disease disease;
/*     */   
/*     */   public ConsumeCalculations(Disease diseasePlugin) {
/*  33 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processConsumeEvent(Player p, PlayerItemConsumeEvent event) {
/*  40 */     if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - processing consume event");
/*     */     
/*  42 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*     */     
/*  44 */     if (dPlayer.infection != "none") {
/*  45 */       if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - already infected - returning after non viral item check"); 
/*  46 */       processConsumeNonViral(p, event);
/*     */       
/*     */       return;
/*     */     } 
/*  50 */     for (DDisease dDisease : this.disease.diseases.values()) {
/*     */ 
/*     */       
/*  53 */       if (dDisease.infectionMethod.equalsIgnoreCase("consume")) {
/*     */         
/*  55 */         if (this.disease.debug) this.disease.getLogger().info(dDisease.name + " - has the requirement 'consume' and item type '" + dDisease.infectionMethodType + "'");
/*     */         
/*  57 */         if (event.getItem().getType().name().equalsIgnoreCase(dDisease.infectionMethodType)) {
/*     */ 
/*     */           
/*  60 */           if (this.disease.debug) this.disease.getLogger().info("item type matches disease infection");
/*     */ 
/*     */           
/*  63 */           int chance = dDisease.infectionChance;
/*     */           
/*  65 */           if (chance == 0) {
/*  66 */             if (this.disease.debug) this.disease.getLogger().info("Disease is disabled with zero chance - " + dDisease.name);
/*     */             
/*     */             return;
/*     */           } 
/*  70 */           Random random = new Random();
/*  71 */           int rand = random.nextInt(100);
/*  72 */           if (rand < chance) {
/*  73 */             if (this.disease.debug) this.disease.getLogger().info(rand + " is within " + chance + "%");
/*     */             
/*  75 */             dPlayer.playerInfect(dDisease.name, dDisease.infectionMessage);
/*     */ 
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  85 */     processConsumeNonViral(p, event);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processConsumeNonViral(Player p, PlayerItemConsumeEvent event) {
/*  92 */     if (this.disease.debug) this.disease.getLogger().info(p.getName() + " - processing non viral item consume");
/*     */     
/*  94 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*  95 */     ItemStack item = event.getItem();
/*     */ 
/*     */     
/*  98 */     for (Remedy dRemedy : this.disease.remedies.values()) {
/*     */       
/* 100 */       if (this.disease.debug) this.disease.getLogger().info("checking item consumed - " + item.getType() + " matches the remedy " + dRemedy.name + " item " + dRemedy.item.getType());
/*     */       
/* 102 */       if (dRemedy.item.getType().equals(item.getType())) {
/*     */         
/* 104 */         if (this.disease.debug) this.disease.getLogger().info(dRemedy.name + " item matches the item consumed");
/*     */         
/* 106 */         if (item.getItemMeta().hasDisplayName()) {
/*     */           
/* 108 */           if (this.disease.debug) this.disease.getLogger().info("item has display name - comparing " + item.getItemMeta().getDisplayName() + ", with remedy item name " + ChatColor.GREEN + dRemedy.name);
/*     */           
/* 110 */           if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + dRemedy.name)) {
/*     */             
/* 112 */             if (this.disease.debug) this.disease.getLogger().info("item display name matches remedy");
/*     */             
/* 114 */             if (dRemedy.curedDisease.equalsIgnoreCase(dPlayer.infection)) {
/*     */               
/* 116 */               if (this.disease.debug) this.disease.getLogger().info(dRemedy.name + " - cures the disease '" + dRemedy.curedDisease + "' - player has " + dPlayer.infection); 
/* 117 */               dPlayer.playerCure(false, dRemedy.cureMessage);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\calculate\ConsumeCalculations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */