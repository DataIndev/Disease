/*    */ package com.hmmcrunchy.disease.effects;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
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
/*    */ public class UseVaccine
/*    */ {
/*    */   int useSelfVaccine(Player p, int immunity, int vaccineImmunityIncrease, String vaccineCureMessage, String diseaseName) {
/* 23 */     p.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " " + vaccineCureMessage + " " + diseaseName);
/*    */ 
/*    */     
/* 26 */     int immunityIncrease = immunity + vaccineImmunityIncrease;
/* 27 */     if (immunityIncrease > 100) {
/* 28 */       immunityIncrease = 100;
/*    */     }
/*    */ 
/*    */     
/* 32 */     p.getWorld().playEffect(p.getEyeLocation(), Effect.ENDER_SIGNAL, 1);
/* 33 */     p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1);
/* 34 */     p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 2);
/*    */ 
/*    */     
/* 37 */     ItemStack itemStack = p.getItemInHand();
/* 38 */     int amount = itemStack.getAmount();
/* 39 */     if (amount == 1) {
/*    */       
/* 41 */       p.setItemInHand(null);
/*    */     } else {
/*    */       
/* 44 */       itemStack.setAmount(amount - 1);
/* 45 */       p.setItemInHand(itemStack);
/*    */     } 
/*    */     
/* 48 */     return immunityIncrease;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   int useOthersVaccine(Player p, Player pc, int immunity, int vaccineImmunityIncrease, String vaccineCureMessage, String diseaseName) {
/* 57 */     pc.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " " + vaccineCureMessage + " " + diseaseName);
/* 58 */     p.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " You have vaccinated " + pc.getName());
/*    */ 
/*    */     
/* 61 */     int immunityIncrease = immunity + vaccineImmunityIncrease;
/* 62 */     if (immunityIncrease > 100) {
/* 63 */       immunityIncrease = 100;
/*    */     }
/*    */ 
/*    */     
/* 67 */     pc.getWorld().playEffect(p.getEyeLocation(), Effect.ENDER_SIGNAL, 1);
/* 68 */     pc.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1);
/* 69 */     pc.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 2);
/*    */ 
/*    */     
/* 72 */     ItemStack itemStack = p.getItemInHand();
/* 73 */     int amount = itemStack.getAmount();
/* 74 */     if (amount == 1) {
/*    */       
/* 76 */       p.setItemInHand(null);
/*    */     } else {
/*    */       
/* 79 */       itemStack.setAmount(amount - 1);
/* 80 */       p.setItemInHand(itemStack);
/*    */     } 
/*    */     
/* 83 */     return immunityIncrease;
/*    */   }
/*    */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\effects\UseVaccine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */