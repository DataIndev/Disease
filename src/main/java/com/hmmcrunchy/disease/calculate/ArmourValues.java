/*    */ package com.hmmcrunchy.disease.calculate;
/*    */ 
/*    */ import org.bukkit.Material;
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
/*    */ public class ArmourValues
/*    */ {
/*    */   int calculatArmourValue(Player p) {
/* 20 */     int armourVal = 0;
/*    */     
/* 22 */     ItemStack helm = p.getInventory().getHelmet();
/* 23 */     ItemStack chestPlate = p.getInventory().getChestplate();
/* 24 */     ItemStack leggings = p.getInventory().getLeggings();
/* 25 */     ItemStack boots = p.getInventory().getBoots();
/*    */ 
/*    */     
/* 28 */     if (helm.getType() == Material.LEATHER_HELMET) {
/* 29 */       armourVal++;
/* 30 */     } else if (helm.getType() == Material.IRON_HELMET) {
/* 31 */       armourVal += 2;
/* 32 */     } else if (helm.getType() == Material.NETHERITE_HELMET) {
/* 33 */       armourVal += 2;
/* 34 */     } else if (helm.getType() == Material.GOLDEN_HELMET) {
/* 35 */       armourVal += 3;
/* 36 */     } else if (helm.getType() == Material.CHAINMAIL_HELMET) {
/* 37 */       armourVal += 2;
/* 38 */     } else if (helm.getType() == Material.DIAMOND_HELMET) {
/* 39 */       armourVal += 4;
/*    */     } 
/*    */ 
/*    */     
/* 43 */     if (chestPlate.getType() == Material.LEATHER_CHESTPLATE) {
/* 44 */       armourVal += 3;
/* 45 */     } else if (chestPlate.getType() == Material.IRON_CHESTPLATE) {
/* 46 */       armourVal += 5;
/* 47 */     } else if (chestPlate.getType() == Material.NETHERITE_CHESTPLATE) {
/* 48 */       armourVal += 5;
/* 49 */     } else if (chestPlate.getType() == Material.GOLDEN_CHESTPLATE) {
/* 50 */       armourVal += 5;
/* 51 */     } else if (chestPlate.getType() == Material.CHAINMAIL_CHESTPLATE) {
/* 52 */       armourVal += 4;
/* 53 */     } else if (chestPlate.getType() == Material.DIAMOND_CHESTPLATE) {
/* 54 */       armourVal += 6;
/*    */     } 
/*    */ 
/*    */     
/* 58 */     if (boots.getType() == Material.LEATHER_BOOTS) {
/* 59 */       armourVal++;
/* 60 */     } else if (boots.getType() == Material.IRON_BOOTS) {
/* 61 */       armourVal += 3;
/* 62 */     } else if (boots.getType() == Material.NETHERITE_BOOTS) {
/* 63 */       armourVal += 3;
/* 64 */     } else if (boots.getType() == Material.GOLDEN_BOOTS) {
/* 65 */       armourVal += 2;
/* 66 */     } else if (boots.getType() == Material.CHAINMAIL_BOOTS) {
/* 67 */       armourVal += 2;
/* 68 */     } else if (boots.getType() == Material.DIAMOND_BOOTS) {
/* 69 */       armourVal += 3;
/*    */     } 
/*    */ 
/*    */     
/* 73 */     if (leggings.getType() == Material.LEATHER_LEGGINGS) {
/* 74 */       armourVal += 2;
/* 75 */     } else if (leggings.getType() == Material.IRON_LEGGINGS) {
/* 76 */       armourVal += 3;
/* 77 */     } else if (leggings.getType() == Material.NETHERITE_LEGGINGS) {
/* 78 */       armourVal += 3;
/* 79 */     } else if (leggings.getType() == Material.GOLDEN_LEGGINGS) {
/* 80 */       armourVal += 3;
/* 81 */     } else if (leggings.getType() == Material.CHAINMAIL_LEGGINGS) {
/* 82 */       armourVal += 2;
/* 83 */     } else if (leggings.getType() == Material.DIAMOND_LEGGINGS) {
/* 84 */       armourVal += 4;
/*    */     } 
/*    */     
/* 87 */     return armourVal;
/*    */   }
/*    */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\calculate\ArmourValues.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */