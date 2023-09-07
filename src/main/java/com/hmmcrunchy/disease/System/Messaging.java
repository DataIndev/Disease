/*     */ package com.hmmcrunchy.disease.System;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.classes.DDisease;
/*     */ import com.hmmcrunchy.disease.classes.DPlayer;
/*     */ import com.hmmcrunchy.disease.classes.Remedy;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Messaging
/*     */ {
/*     */   Disease disease;
/*     */   String warmMessage;
/*     */   String hotMessage;
/*     */   String feverMessage;
/*     */   String coldMessage;
/*     */   String chilledMessage;
/*     */   String hypothermicMessage;
/*     */   String tempIsColdMessage;
/*     */   String tempIsFreezingMessage;
/*     */   String tempIsHotMessage;
/*     */   
/*     */   public Messaging(Disease plugin) {
/*  33 */     this.disease = plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   String tempIsBoilingMessage;
/*     */ 
/*     */   
/*     */   String unwellMessage;
/*     */ 
/*     */   
/*     */   String queasyMessage;
/*     */ 
/*     */   
/*     */   String sickMessage;
/*     */ 
/*     */   
/*     */   String hasBeenGivenPotionMessage;
/*     */ 
/*     */   
/*     */   String givenPotionMessage;
/*     */   
/*     */   public String infectedByCommandMessage;
/*     */   
/*     */   public String infectedByRadiusDamage;
/*     */   
/*     */   public String infectedByRadiusProximity;
/*     */   
/*     */   public String cureFromCommandMessage;
/*     */   
/*     */   public String hospitalCureMessage;
/*     */ 
/*     */   
/*     */   public void actionBar(Player player, String message) {
/*  67 */     player.sendTitle(" ", message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void sendHelpMessage(CommandSender sender) {
/*  73 */     if (this.disease.debug) this.disease.getLogger().info("send help messge to " + sender.getName());
/*     */ 
/*     */     
/*  76 */     sender.sendMessage("");
/*  77 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*  78 */     sender.sendMessage(ChatColor.GREEN + "Disease");
/*     */     
/*  80 */     TextComponent health = new TextComponent(ChatColor.YELLOW + "/health - show health stats");
/*  81 */     if (sender.getName().equals(sender.getName())) {
/*  82 */       health.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/disease check "));
/*  83 */       health.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to check your health")).create()));
/*     */     } 
/*  85 */     sender.spigot().sendMessage((BaseComponent)health);
/*     */     
/*  87 */     TextComponent list = new TextComponent(ChatColor.YELLOW + "/disease list - show all diseases");
/*  88 */     if (sender.getName().equals(sender.getName())) {
/*  89 */       list.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/disease list"));
/*  90 */       list.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to list all diseases")).create()));
/*     */     } 
/*  92 */     sender.spigot().sendMessage((BaseComponent)list);
/*     */     
/*  94 */     TextComponent info = new TextComponent(ChatColor.YELLOW + "/disease info <disease> - show disease details");
/*  95 */     if (sender.getName().equals(sender.getName())) {
/*  96 */       info.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/disease info "));
/*  97 */       info.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to see a diseases info")).create()));
/*     */     } 
/*  99 */     sender.spigot().sendMessage((BaseComponent)info);
/*     */     
/* 101 */     TextComponent remedyList = new TextComponent(ChatColor.YELLOW + "/disease remedy list - show remedies");
/* 102 */     if (sender.getName().equals(sender.getName())) {
/* 103 */       remedyList.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/disease remedy list "));
/* 104 */       remedyList.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to show a list of remedies")).create()));
/*     */     } 
/* 106 */     sender.spigot().sendMessage((BaseComponent)remedyList);
/*     */     
/* 108 */     TextComponent remedyInfo = new TextComponent(ChatColor.YELLOW + "/disease remedy <remedyName> - show remedy details");
/* 109 */     if (sender.getName().equals(sender.getName())) {
/* 110 */       remedyInfo.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/disease remedy "));
/* 111 */       remedyInfo.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to show remedy info")).create()));
/*     */     } 
/* 113 */     sender.spigot().sendMessage((BaseComponent)remedyInfo);
/*     */     
/* 115 */     TextComponent immunity = new TextComponent(ChatColor.YELLOW + "/disease immunity - show your immunity details");
/* 116 */     if (sender.getName().equals(sender.getName())) {
/* 117 */       immunity.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/disease immunity "));
/* 118 */       immunity.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to show your immunity info")).create()));
/*     */     } 
/* 120 */     sender.spigot().sendMessage((BaseComponent)immunity);
/*     */     
/* 122 */     if (this.disease.ds.useTemp) {
/* 123 */       TextComponent temp = new TextComponent(ChatColor.YELLOW + "/temp - show body heat and ambient temperature");
/* 124 */       if (sender.getName().equals(sender.getName())) {
/* 125 */         temp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/temp"));
/* 126 */         temp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to show your temperature info")).create()));
/*     */       } 
/* 128 */       sender.spigot().sendMessage((BaseComponent)temp);
/*     */       
/* 130 */       TextComponent tempDetails = new TextComponent(ChatColor.YELLOW + "/temp details - show temperature details ");
/* 131 */       if (sender.getName().equals(sender.getName())) {
/* 132 */         tempDetails.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/temp details"));
/* 133 */         tempDetails.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to show your temperature details")).create()));
/*     */       } 
/* 135 */       sender.spigot().sendMessage((BaseComponent)tempDetails);
/*     */     } 
/*     */ 
/*     */     
/* 139 */     if (sender.hasPermission("disease.give")) {
/*     */       
/* 141 */       TextComponent give = new TextComponent(ChatColor.YELLOW + "/disease give <player> <remedy> - give a player an item cure");
/* 142 */       if (sender.getName().equals(sender.getName())) {
/* 143 */         give.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/disease give "));
/* 144 */         give.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to give a player a cure")).create()));
/*     */       } 
/* 146 */       sender.spigot().sendMessage((BaseComponent)give);
/*     */     } 
/*     */     
/* 149 */     if (sender.hasPermission("disease.infect") || sender.hasPermission("disease.cure")) {
/*     */ 
/*     */       
/* 152 */       TextComponent infect = new TextComponent(ChatColor.YELLOW + "/disease infect <player> <illness> - infect player");
/* 153 */       if (sender.getName().equals(sender.getName())) {
/* 154 */         infect.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/disease infect "));
/* 155 */         infect.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to give a player a disease")).create()));
/*     */       } 
/* 157 */       sender.spigot().sendMessage((BaseComponent)infect);
/*     */       
/* 159 */       TextComponent cure = new TextComponent(ChatColor.YELLOW + "/disease cure <player> - heal player from all illness");
/* 160 */       if (sender.getName().equals(sender.getName())) {
/* 161 */         cure.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/disease cure "));
/* 162 */         cure.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click to cure a player")).create()));
/*     */       } 
/* 164 */       sender.spigot().sendMessage((BaseComponent)cure);
/*     */     } 
/*     */ 
/*     */     
/* 168 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */   }
/*     */ 
/*     */   
/*     */   void sendConsoleHelpMessage(CommandSender sender) {
/* 173 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 174 */     sender.sendMessage(ChatColor.GREEN + "Disease");
/* 175 */     sender.sendMessage("");
/* 176 */     sender.sendMessage(ChatColor.GREEN + "→ disease list - show all diseases");
/* 177 */     sender.sendMessage(ChatColor.GREEN + "→ disease info <disease> - show disease details");
/* 178 */     sender.sendMessage(ChatColor.GREEN + "→ disease remedy list - show remedies");
/* 179 */     sender.sendMessage(ChatColor.GREEN + "→ disease remedy <remedyName> - show remedy details");
/* 180 */     sender.sendMessage(ChatColor.GREEN + "→ disease give <player> <remedy> - give a player an item cure");
/* 181 */     sender.sendMessage(ChatColor.GREEN + "→ disease give <player> <remedy> - give a player an item cure");
/* 182 */     sender.sendMessage(ChatColor.GREEN + "→ disease infect <player> <illness> - infect player");
/* 183 */     sender.sendMessage(ChatColor.GREEN + "→ disease cure <player> - heal player from all illness");
/* 184 */     sender.sendMessage("");
/* 185 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void sendImmunity(CommandSender sender) {
/* 191 */     if (sender instanceof Player) {
/*     */       
/* 193 */       DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(((Player)sender).getUniqueId().toString());
/*     */       
/* 195 */       sender.sendMessage("");
/* 196 */       sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 197 */       sender.sendMessage(ChatColor.GREEN + "Immunity");
/*     */       
/* 199 */       for (String dImmunity : dPlayer.dImmunity.keySet())
/*     */       {
/* 201 */         sender.sendMessage(ChatColor.YELLOW + "→ " + dImmunity + ": " + dPlayer.dImmunity.get(dImmunity));
/*     */       }
/*     */ 
/*     */       
/* 205 */       sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void sendDiseaseList(CommandSender cs) {
/* 213 */     Player sender = (Player)cs;
/*     */     
/* 215 */     sender.sendMessage("");
/*     */     
/* 217 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 218 */     sender.sendMessage(ChatColor.GREEN + "Disease List");
/* 219 */     sender.sendMessage("");
/*     */     
/* 221 */     for (String dDisease : this.disease.diseases.keySet()) {
/*     */       
/* 223 */       TextComponent diseaseComponent = new TextComponent(ChatColor.YELLOW + "→ " + dDisease);
/* 224 */       if (sender.getName().equals(sender.getName())) {
/* 225 */         diseaseComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/disease info " + dDisease));
/* 226 */         diseaseComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click for more info")).create()));
/*     */       } 
/* 228 */       sender.spigot().sendMessage((BaseComponent)diseaseComponent);
/*     */     } 
/*     */ 
/*     */     
/* 232 */     sender.sendMessage("");
/* 233 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 234 */     sender.sendMessage(ChatColor.YELLOW + "Use /disease info <diseaseName>, or ");
/* 235 */     sender.sendMessage(ChatColor.YELLOW + "click above for disease details & cures");
/* 236 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void sendConsoleDiseaseList(CommandSender sender) {
/* 243 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 244 */     sender.sendMessage(ChatColor.GREEN + "Disease List");
/*     */     
/* 246 */     for (String dDisease : this.disease.diseases.keySet()) {
/*     */       
/* 248 */       TextComponent diseaseComponent = new TextComponent(ChatColor.YELLOW + "→ " + dDisease);
/* 249 */       if (sender.getName().equals(sender.getName())) {
/* 250 */         diseaseComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/disease info " + dDisease));
/* 251 */         diseaseComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click for more info")).create()));
/*     */       } 
/* 253 */       sender.spigot().sendMessage((BaseComponent)diseaseComponent);
/*     */     } 
/*     */     
/* 256 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void sendRemedyList(CommandSender cs) {
/* 262 */     Player sender = (Player)cs;
/*     */     
/* 264 */     sender.sendMessage("");
/*     */     
/* 266 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 267 */     sender.sendMessage(ChatColor.GREEN + "Remedy List");
/* 268 */     sender.sendMessage("");
/*     */     
/* 270 */     for (String remedy : this.disease.remedies.keySet()) {
/*     */       
/* 272 */       TextComponent remedyComponent = new TextComponent(ChatColor.YELLOW + remedy);
/* 273 */       if (sender.getName().equals(sender.getName())) {
/* 274 */         remedyComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/disease remedy " + remedy));
/* 275 */         remedyComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click for more info")).create()));
/*     */       } 
/* 277 */       sender.spigot().sendMessage((BaseComponent)remedyComponent);
/*     */     } 
/*     */ 
/*     */     
/* 281 */     sender.sendMessage("");
/* 282 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 283 */     sender.sendMessage(ChatColor.YELLOW + "Use /disease remedy <remedyname>, or ");
/* 284 */     sender.sendMessage(ChatColor.YELLOW + "click above for remedy details & recipes");
/* 285 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void sendConsoleRemedyList(CommandSender sender) {
/* 291 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 292 */     sender.sendMessage(ChatColor.GREEN + "Remedy List");
/*     */     
/* 294 */     for (String remedy : this.disease.remedies.keySet())
/*     */     {
/* 296 */       sender.sendMessage(ChatColor.GREEN + "→ " + remedy);
/*     */     }
/*     */ 
/*     */     
/* 300 */     sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void sendRemedyDetail(CommandSender sender, String remedy) {
/* 308 */     sender.sendMessage("");
/*     */     
/* 310 */     if (this.disease.remedies.containsKey(remedy)) {
/*     */ 
/*     */       
/* 313 */       Remedy dRemedy = (Remedy)this.disease.remedies.get(remedy);
/*     */       
/* 315 */       sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 316 */       sender.sendMessage(ChatColor.GREEN + "Remedy Info");
/* 317 */       sender.sendMessage(ChatColor.YELLOW + "→ Name       : " + dRemedy.name);
/* 318 */       sender.sendMessage(ChatColor.YELLOW + "→ Description: " + dRemedy.description);
/* 319 */       sender.sendMessage(ChatColor.YELLOW + "→ Crafting   : " + dRemedy.craftDescription);
/* 320 */       sender.sendMessage("");
/*     */       
/* 322 */       TextComponent remedyComponent = new TextComponent(ChatColor.YELLOW + "→ Cures      : " + dRemedy.curedDisease);
/* 323 */       if (sender.getName().equals(sender.getName())) {
/* 324 */         remedyComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/disease info " + dRemedy.curedDisease));
/* 325 */         remedyComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click for info on " + dRemedy.curedDisease)).create()));
/*     */       } 
/* 327 */       sender.spigot().sendMessage((BaseComponent)remedyComponent);
/*     */       
/* 329 */       sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */     }
/*     */     else {
/*     */       
/* 333 */       sender.sendMessage("Not a recognised remedy");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void sendDiseaseDetail(CommandSender sender, String diseaseName) {
/* 344 */     sender.sendMessage("");
/*     */     
/* 346 */     if (this.disease.diseases.containsKey(diseaseName)) {
/*     */       
/* 348 */       DDisease dDisease = (DDisease)this.disease.diseases.get(diseaseName);
/*     */       
/* 350 */       sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 351 */       sender.sendMessage(ChatColor.GREEN + "Disease Info");
/* 352 */       sender.sendMessage(ChatColor.YELLOW + "→ Name       : " + dDisease.name);
/* 353 */       sender.sendMessage(ChatColor.YELLOW + "→ Description: " + dDisease.description);
/* 354 */       sender.sendMessage(ChatColor.YELLOW + "→ Caused by  : " + dDisease.infectionMethod + " - " + dDisease.infectionMethodType);
/* 355 */       sender.sendMessage(ChatColor.YELLOW + "→ Effects    : " + dDisease.sickActions);
/* 356 */       sender.sendMessage(ChatColor.YELLOW + "→ Contagious : " + dDisease.infectious);
/*     */       
/* 358 */       TextComponent diseaseCureName = new TextComponent(ChatColor.YELLOW + "Cure       : " + dDisease.cure);
/* 359 */       if (sender.getName().equals(sender.getName())) {
/* 360 */         diseaseCureName.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/disease remedy " + dDisease.cure));
/* 361 */         diseaseCureName.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click for info on " + dDisease.cure)).create()));
/*     */       } 
/* 363 */       sender.spigot().sendMessage((BaseComponent)diseaseCureName);
/*     */       
/* 365 */       sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 370 */       sender.sendMessage("Not a valid disease type use /disease list");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void checkHealth(CommandSender cs, String object) {
/*     */     Player sender;
/*     */     String diseaseType;
/* 380 */     if (object.equalsIgnoreCase("player")) {
/* 381 */       sender = (Player)cs;
/*     */     } else {
/*     */       
/* 384 */       sender = this.disease.getServer().getPlayer(object);
/*     */     } 
/*     */     
/* 387 */     if (this.disease.debug) this.disease.getLogger().info("checking health for " + sender.getName());
/*     */ 
/*     */     
/* 390 */     DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(sender.getUniqueId().toString());
/*     */     
/* 392 */     if (this.disease.debug) this.disease.getLogger().info(dPlayer.name + " detected with " + dPlayer.infection);
/*     */ 
/*     */     
/* 395 */     DDisease dDisease = null;
/*     */     
/* 397 */     if (!dPlayer.infection.equalsIgnoreCase("none")) {
/* 398 */       dDisease = (DDisease)this.disease.diseases.get(dPlayer.infection);
/* 399 */       diseaseType = dDisease.name;
/*     */     } else {
/*     */       
/* 402 */       diseaseType = "none";
/*     */     } 
/*     */     
/* 405 */     double bodyTemp = (Math.round(dPlayer.temp * 100.0D) / 100L);
/*     */     
/* 407 */     cs.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 408 */     cs.sendMessage(ChatColor.GREEN + "Health Stats");
/* 409 */     cs.sendMessage(ChatColor.YELLOW + "→ Temperature:    " + bodyTemp + "C / " + getFahrenheit(bodyTemp) + "F");
/* 410 */     cs.sendMessage(ChatColor.YELLOW + "→ Sickness:   " + dPlayer.sickness);
/* 411 */     cs.sendMessage(ChatColor.GREEN + "Diagnosis");
/*     */     
/* 413 */     if (!diseaseType.equalsIgnoreCase("none")) {
/*     */ 
/*     */ 
/*     */       
/* 417 */       TextComponent currentDisease = new TextComponent(ChatColor.YELLOW + "→ " + diseaseType);
/* 418 */       if (sender.getName().equals(sender.getName())) {
/* 419 */         currentDisease.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/disease info " + diseaseType));
/* 420 */         currentDisease.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click for info on " + diseaseType)).create()));
/*     */       } 
/* 422 */       cs.spigot().sendMessage((BaseComponent)currentDisease);
/* 423 */       cs.sendMessage(ChatColor.YELLOW + "→ Infectious: " + dDisease.infectious);
/*     */     }
/*     */     else {
/*     */       
/* 427 */       cs.sendMessage(ChatColor.YELLOW + "→ In Fine Health  ");
/*     */     } 
/*     */ 
/*     */     
/* 431 */     cs.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getFahrenheit(double centigrade) {
/* 438 */     double fahrenheit = centigrade * 9.0D / 5.0D + 32.0D;
/* 439 */     return fahrenheit;
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\Messaging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */