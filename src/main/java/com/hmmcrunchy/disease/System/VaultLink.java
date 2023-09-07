/*    */ package com.hmmcrunchy.disease.System;
/*    */ 
/*    */ import com.hmmcrunchy.disease.Disease;
/*    */ import net.milkbowl.vault.Vault;
/*    */ import net.milkbowl.vault.economy.Economy;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.plugin.RegisteredServiceProvider;
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
/*    */ public class VaultLink
/*    */ {
/* 20 */   public static Economy economy = null;
/*    */   
/*    */   private Disease disease;
/*    */ 
/*    */   
/*    */   public VaultLink(Disease diseasePlugin, Vault vault) {
/* 26 */     this.disease = diseasePlugin;
/* 27 */     setupEconomy();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean setupEconomy() {
/* 33 */     RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
/* 34 */     if (economyProvider != null) {
/* 35 */       this.disease.econ = (Economy)economyProvider.getProvider();
/*    */     }
/*    */     
/* 38 */     return (economy != null);
/*    */   }
/*    */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\VaultLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */