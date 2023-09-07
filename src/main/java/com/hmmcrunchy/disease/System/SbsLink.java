/*    */ package com.hmmcrunchy.disease.System;
/*    */ 
/*    */ import com.github.games647.scoreboardstats.ScoreboardStats;
/*    */ import com.github.games647.scoreboardstats.variables.ReplaceEvent;
/*    */ import com.github.games647.scoreboardstats.variables.UnsupportedPluginException;
/*    */ import com.github.games647.scoreboardstats.variables.VariableReplacer;
/*    */ import com.hmmcrunchy.disease.Disease;
/*    */ import com.hmmcrunchy.disease.classes.DPlayer;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scoreboard.DisplaySlot;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Score;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ import org.bukkit.scoreboard.ScoreboardManager;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SbsLink
/*    */   implements VariableReplacer
/*    */ {
/*    */   private Disease disease;
/*    */   
/*    */   public SbsLink(Disease diseasePlugin, ScoreboardStats sbs) {
/* 45 */     Bukkit.getLogger().info("registering " + diseasePlugin);
/*    */     
/* 47 */     if (sbs == null)
/*    */     {
/* 49 */       throw new UnsupportedPluginException("Couldn't find Scoreboard plugin");
/*    */     }
/*    */     
/* 52 */     this.disease = diseasePlugin;
/*    */ 
/*    */ 
/*    */     
/* 56 */     ScoreboardStats scoreboardstats = (ScoreboardStats)Disease.getPlugin(ScoreboardStats.class);
/* 57 */     scoreboardstats.getReplaceManager().register(this, (Plugin)this.disease, new String[] { "variable" });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onReplace(Player player, String var, ReplaceEvent replaceEvent) {
/* 64 */     replaceEvent.setScore(321);
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendScore(Player player) {
/* 69 */     if (player.getScoreboard() == null);
/*    */ 
/*    */     
/* 72 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(player.getUniqueId().toString());
/* 73 */     ScoreboardManager sbm = Bukkit.getScoreboardManager();
/* 74 */     Scoreboard board = sbm.getNewScoreboard();
/* 75 */     Objective o = board.registerNewObjective("Disease", "Dummy");
/* 76 */     o.setDisplaySlot(DisplaySlot.SIDEBAR);
/* 77 */     int temp = (int)dPlayer.temp;
/* 78 */     int sick = dPlayer.sickness;
/*    */     
/* 80 */     o.setDisplayName(ChatColor.DARK_GREEN + "  o0 Health 0o  ");
/* 81 */     Score templevel = o.getScore(ChatColor.GREEN + "Temperature:");
/* 82 */     templevel.setScore(temp);
/* 83 */     Score sicklevel = o.getScore(ChatColor.GREEN + "Sickness   :");
/* 84 */     sicklevel.setScore(sick);
/*    */ 
/*    */     
/* 87 */     player.setScoreboard(board);
/*    */   }
/*    */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\SbsLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */