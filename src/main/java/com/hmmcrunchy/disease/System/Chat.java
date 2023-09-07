/*     */ package com.hmmcrunchy.disease.System;
/*     */ 
/*     */ import com.hmmcrunchy.disease.Disease;
/*     */ import com.hmmcrunchy.disease.classes.DPlayer;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
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
/*     */ 
/*     */ public class Chat
/*     */ {
/*     */   private Disease disease;
/*     */   
/*     */   public Chat(Disease diseasePlugin) {
/*  29 */     this.disease = diseasePlugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chat(CommandSender sender, Command cmd, String label, String[] args) {
/*  37 */     if (this.disease.debug) this.disease.getLogger().info("run command for " + sender.getName());
/*     */ 
/*     */     
/*  40 */     if (cmd.getName().equalsIgnoreCase("disease")) {
/*     */       
/*  42 */       if (args.length > 0) {
/*     */ 
/*     */         
/*  45 */         if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase(null)) {
/*     */           
/*  47 */           if (sender instanceof Player) {
/*  48 */             this.disease.messaging.sendHelpMessage(sender);
/*     */           } else {
/*  50 */             this.disease.messaging.sendConsoleHelpMessage(sender);
/*     */           }
/*     */         
/*  53 */         } else if (args[0].equalsIgnoreCase("check")) {
/*     */           
/*  55 */           if (sender instanceof Player) {
/*  56 */             this.disease.messaging.checkHealth(sender, "player");
/*     */ 
/*     */           
/*     */           }
/*  60 */           else if (args.length == 2 && sender.hasPermission("disease.diagnose")) {
/*  61 */             Player ap = this.disease.getServer().getPlayer(args[1]);
/*  62 */             if (ap instanceof Player) {
/*  63 */               this.disease.messaging.checkHealth(sender, args[1]);
/*     */             } else {
/*     */               
/*  66 */               sender.sendMessage("Not a valid player");
/*     */             } 
/*     */           } else {
/*     */             
/*  70 */             sender.sendMessage("Use /disease check <playername>");
/*     */           
/*     */           }
/*     */         
/*     */         }
/*  75 */         else if (args[0].equalsIgnoreCase("immunity")) {
/*     */           
/*  77 */           this.disease.messaging.sendImmunity(sender);
/*     */         }
/*  79 */         else if (args[0].equalsIgnoreCase("debug")) {
/*     */ 
/*     */           
/*  82 */           if (sender.hasPermission("disease.debug"))
/*     */           {
/*     */             
/*  85 */             if (this.disease.debug == true)
/*     */             {
/*  87 */               this.disease.debug = false;
/*  88 */               this.disease.getLogger().info("Debugger disabled");
/*  89 */               sender.sendMessage("Debugger disabled");
/*     */             }
/*     */             else
/*     */             {
/*  93 */               this.disease.debug = true;
/*  94 */               this.disease.getLogger().info("Debugger enabled");
/*  95 */               sender.sendMessage("Debugger enabled");
/*     */             }
/*     */           
/*     */           }
/*     */         }
/* 100 */         else if (args[0].equalsIgnoreCase("reload")) {
/*     */ 
/*     */           
/* 103 */           if (sender.hasPermission("disease.reload"))
/*     */           {
/*     */             
/* 106 */             this.disease.reloadConfig();
/* 107 */             this.disease.ds.loadConfigData();
/*     */           }
/*     */         
/*     */         }
/* 111 */         else if (args[0].equalsIgnoreCase("list")) {
/*     */           
/* 113 */           if (sender instanceof Player) {
/* 114 */             this.disease.messaging.sendDiseaseList(sender);
/*     */           } else {
/*     */             
/* 117 */             this.disease.messaging.sendConsoleDiseaseList(sender);
/*     */           }
/*     */         
/*     */         }
/* 121 */         else if (args[0].equalsIgnoreCase("remedy")) {
/*     */           
/* 123 */           if (sender.hasPermission("disease.remedy")) {
/*     */             
/* 125 */             if (args.length == 2) {
/*     */               
/* 127 */               if (args[1].equalsIgnoreCase("list")) {
/*     */                 
/* 129 */                 if (sender instanceof Player) {
/* 130 */                   this.disease.messaging.sendRemedyList(sender);
/*     */                 } else {
/*     */                   
/* 133 */                   this.disease.messaging.sendConsoleRemedyList(sender);
/*     */                 }
/*     */               
/*     */               } else {
/*     */                 
/* 138 */                 this.disease.messaging.sendRemedyDetail(sender, args[1]);
/*     */               }
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 144 */               sender.sendMessage("Use /disease remedy list or /disease remedy <RemedyName>");
/*     */             } 
/*     */             
/* 147 */             if (args.length == 4) {
/*     */ 
/*     */               
/* 150 */               if (args[1].equalsIgnoreCase("give"))
/*     */               {
/* 152 */                 if (sender.hasPermission("disease.give"))
/*     */                 {
/*     */                   
/* 155 */                   Player ap = this.disease.getServer().getPlayer(args[2]);
/*     */                   
/* 157 */                   if (ap instanceof Player) {
/*     */                     
/* 159 */                     if (this.disease.diseases.containsKey(args[3]))
/*     */                     {
/*     */                       
/* 162 */                       ((DPlayer)this.disease.dPlayers.get(ap.getUniqueId().toString())).playerGiveItem(args[3], args[3], Material.POTION);
/* 163 */                       ap.sendMessage(ChatColor.GREEN + this.disease.messaging.givenPotionMessage + args[3]);
/* 164 */                       sender.sendMessage(ChatColor.GREEN + ap.getName() + " " + this.disease.messaging.hasBeenGivenPotionMessage + args[3]);
/*     */                     
/*     */                     }
/*     */                     else
/*     */                     {
/* 169 */                       sender.sendMessage("Not a recognised remedy");
/*     */                     }
/*     */                   
/*     */                   } else {
/*     */                     
/* 174 */                     sender.sendMessage(ChatColor.GREEN + "Not a Valid player");
/*     */                   
/*     */                   }
/*     */                 
/*     */                 }
/*     */               
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 184 */               sender.sendMessage("Use /disease remedy give <player> <remedy> ");
/*     */             } 
/*     */           } else {
/*     */             
/* 188 */             sender.sendMessage("You do not have permission for this command");
/*     */           }
/*     */         
/*     */         }
/* 192 */         else if (args[0].equalsIgnoreCase("info")) {
/*     */ 
/*     */           
/* 195 */           if (sender.hasPermission("disease.info")) {
/*     */ 
/*     */             
/* 198 */             if (args.length == 2) {
/*     */ 
/*     */               
/* 201 */               this.disease.messaging.sendDiseaseDetail(sender, args[1]);
/*     */             } else {
/*     */               
/* 204 */               sender.sendMessage("Use /disease info <disease>");
/*     */             } 
/*     */           } else {
/*     */             
/* 208 */             sender.sendMessage("You do not have permission for this command");
/*     */           }
/*     */         
/*     */         }
/* 212 */         else if (args[0].equalsIgnoreCase("infect")) {
/*     */ 
/*     */           
/* 215 */           if (args.length == 3) {
/*     */             
/* 217 */             if (sender.hasPermission("disease.infect"))
/*     */             {
/*     */               
/* 220 */               Player ap = this.disease.getServer().getPlayer(args[1]);
/*     */               
/* 222 */               if (ap instanceof Player)
/*     */               {
/* 224 */                 String diseaseName = args[2];
/* 225 */                 if (this.disease.diseases.containsKey(args[2]))
/*     */                 {
/*     */                   
/* 228 */                   DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(ap.getUniqueId().toString());
/* 229 */                   dPlayer.infection = args[2];
/* 230 */                   ap.sendMessage(ChatColor.GREEN + "[Disease]" + ChatColor.WHITE + " " + this.disease.messaging.infectedByCommandMessage + " " + diseaseName);
/* 231 */                   this.disease.getLogger().info(ap.getName() + " has been infected with " + diseaseName + " by " + sender.getName());
/* 232 */                   sender.sendMessage(ap.getName() + " has been infected with " + diseaseName);
/*     */                 }
/*     */                 else
/*     */                 {
/* 236 */                   sender.sendMessage("not a valid disease type");
/*     */                 
/*     */                 }
/*     */               
/*     */               }
/*     */               else
/*     */               {
/* 243 */                 sender.sendMessage("not a valid player");
/*     */               }
/*     */             
/*     */             }
/*     */             else
/*     */             {
/* 249 */               sender.sendMessage("You do not have permission for this ");
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 254 */             sender.sendMessage("Use /disease infect <player> <disease>");
/*     */           }
/*     */         
/* 257 */         } else if (args[0].equalsIgnoreCase("cure")) {
/*     */ 
/*     */           
/* 260 */           if (args.length == 2) {
/*     */ 
/*     */ 
/*     */             
/* 264 */             if (sender.hasPermission("disease.cure.others")) {
/*     */ 
/*     */               
/* 267 */               Player ap = this.disease.getServer().getPlayer(args[1]);
/*     */               
/* 269 */               if (ap instanceof Player) {
/*     */ 
/*     */                 
/* 272 */                 DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(ap.getUniqueId().toString());
/* 273 */                 dPlayer.playerCure(false, this.disease.messaging.cureFromCommandMessage);
/*     */               
/*     */               }
/*     */               else {
/*     */ 
/*     */                 
/* 279 */                 sender.sendMessage("not a valid player");
/*     */               } 
/*     */             } else {
/*     */               
/* 283 */               sender.sendMessage("You do not have permission for this command");
/*     */             }
/*     */           
/*     */           }
/* 287 */           else if (args.length == 1) {
/*     */ 
/*     */ 
/*     */             
/* 291 */             if (sender.hasPermission("disease.cure")) {
/*     */ 
/*     */ 
/*     */               
/* 295 */               if (sender instanceof Player) {
/*     */ 
/*     */                 
/* 298 */                 Player ap = (Player)sender;
/* 299 */                 DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(ap.getUniqueId().toString());
/* 300 */                 dPlayer.playerCure(false, this.disease.messaging.cureFromCommandMessage);
/*     */               }
/*     */               else {
/*     */                 
/* 304 */                 sender.sendMessage("this command must be run by a player");
/*     */               } 
/*     */             } else {
/*     */               
/* 308 */               sender.sendMessage("You do not have permission for this command");
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 313 */             sender.sendMessage("Use /disease cure");
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 321 */       else if (sender instanceof Player) {
/* 322 */         if (this.disease.debug) this.disease.getLogger().info("no args sending Gui to " + sender.getName());
/*     */         
/* 324 */         Player player = (Player)sender;
/* 325 */         this.disease.gui.openBook(player);
/*     */       } else {
/* 327 */         this.disease.messaging.sendConsoleHelpMessage(sender);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 334 */     else if (cmd.getName().equalsIgnoreCase("health")) {
/*     */ 
/*     */       
/* 337 */       if (sender instanceof Player) {
/* 338 */         this.disease.messaging.checkHealth(sender, "player");
/*     */       
/*     */       }
/* 341 */       else if (args.length == 1) {
/*     */         
/* 343 */         Player ap = this.disease.getServer().getPlayer(args[0]);
/* 344 */         if (ap instanceof Player) {
/*     */           
/* 346 */           this.disease.messaging.checkHealth(sender, args[0]);
/*     */         } else {
/*     */           
/* 349 */           sender.sendMessage("Not a valid player");
/*     */         } 
/*     */       } else {
/*     */         
/* 353 */         sender.sendMessage("Use /health <playername>");
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 358 */     else if (cmd.getName().equalsIgnoreCase("temp")) {
/*     */       
/* 360 */       if (this.disease.ds.useTemp == true) {
/*     */ 
/*     */ 
/*     */         
/* 364 */         if (args.length == 1) {
/*     */ 
/*     */           
/* 367 */           if (args[0].equals("details"))
/*     */           {
/*     */             
/* 370 */             sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 371 */             sender.sendMessage(ChatColor.GREEN + "Player Temperature Details");
/* 372 */             sender.sendMessage(ChatColor.YELLOW + "→ Region temperature: " + (int)this.disease.ct.locationTemp((Player)sender) + "C / " + this.disease.messaging.getFahrenheit((int)this.disease.ct.locationTemp((Player)sender)) + "F");
/* 373 */             sender.sendMessage(ChatColor.YELLOW + "→ Armour: " + this.disease.ct.calculateArmourHeatValue((Player)sender) + "C / " + this.disease.messaging.getFahrenheit(this.disease.ct.calculateArmourHeatValue((Player)sender)) + "F");
/* 374 */             sender.sendMessage(ChatColor.YELLOW + "→ Heat Sources held: " + this.disease.ct.detectHeldHeat((Player)sender) + "C / " + this.disease.messaging.getFahrenheit(this.disease.ct.detectHeldHeat((Player)sender)) + "F");
/* 375 */             sender.sendMessage(ChatColor.YELLOW + "→ Heat Sources: " + this.disease.ct.detectHeat((Player)sender) + "C / " + this.disease.messaging.getFahrenheit(this.disease.ct.detectHeat((Player)sender)) + "F");
/* 376 */             sender.sendMessage(ChatColor.YELLOW + "→ Water Heat Loss: -" + this.disease.ct.isInWater((Player)sender) + "C / " + this.disease.messaging.getFahrenheit(this.disease.ct.isInWater((Player)sender)) + "F");
/* 377 */             sender.sendMessage(ChatColor.YELLOW + "→ Storm : -" + this.disease.ct.isRaining((Player)sender) + "C / " + this.disease.messaging.getFahrenheit(this.disease.ct.isRaining((Player)sender)) + "F");
/* 378 */             sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 379 */             sender.sendMessage(ChatColor.YELLOW + "→ Ambient heat: " + (int)this.disease.ct.calcHeat((Player)sender, true) + "C / " + this.disease.messaging.getFahrenheit((int)this.disease.ct.locationTemp((Player)sender)) + "F");
/* 380 */             sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */           
/*     */           }
/*     */           else
/*     */           {
/* 385 */             Player p = this.disease.getServer().getPlayer(args[0]);
/* 386 */             if (p instanceof Player) {
/*     */ 
/*     */               
/* 389 */               DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(p.getUniqueId().toString());
/*     */               
/* 391 */               sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 392 */               sender.sendMessage(ChatColor.GREEN + " Player Temperature");
/*     */ 
/*     */               
/* 395 */               double bodyTemp = (Math.round(dPlayer.temp * 100.0D) / 100L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 401 */               if (bodyTemp >= 38.0D) {
/* 402 */                 sender.sendMessage(ChatColor.YELLOW + "→ Body temperature: " + ChatColor.RED + bodyTemp + "C / " + this.disease.messaging.getFahrenheit(bodyTemp) + "F");
/* 403 */               } else if (bodyTemp <= 34.0D) {
/* 404 */                 sender.sendMessage(ChatColor.YELLOW + "→ Body temperature: " + ChatColor.BLUE + bodyTemp + "C / " + this.disease.messaging.getFahrenheit(bodyTemp) + "F");
/*     */               } else {
/* 406 */                 sender.sendMessage(ChatColor.YELLOW + "→ Body temperature: " + ChatColor.GREEN + bodyTemp + "C / " + this.disease.messaging.getFahrenheit(bodyTemp) + "F");
/*     */               } 
/*     */               
/* 409 */               double localTemp = this.disease.ct.calcHeat(p, true);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 414 */               if (localTemp >= 30.0D) {
/* 415 */                 sender.sendMessage(ChatColor.YELLOW + "→ Ambient temperature: " + ChatColor.RED + localTemp + "C / " + this.disease.messaging.getFahrenheit(localTemp) + "F");
/* 416 */               } else if (localTemp <= 5.0D) {
/* 417 */                 sender.sendMessage(ChatColor.YELLOW + "→ Ambient temperature: " + ChatColor.BLUE + localTemp + "C / " + this.disease.messaging.getFahrenheit(localTemp) + "F");
/*     */               } else {
/* 419 */                 sender.sendMessage(ChatColor.YELLOW + "→ Ambient temperature: " + ChatColor.GREEN + localTemp + "C / " + this.disease.messaging.getFahrenheit(localTemp) + "F");
/*     */               } 
/*     */               
/* 422 */               sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */             } else {
/*     */               
/* 425 */               sender.sendMessage(ChatColor.GREEN + "Not a proper player");
/*     */             }
/*     */           
/*     */           }
/*     */         
/* 430 */         } else if (args.length == 0) {
/*     */           
/* 432 */           if (sender instanceof Player) {
/*     */             
/* 434 */             DPlayer dPlayer = (DPlayer)this.disease.dPlayers.get(((Player)sender).getUniqueId().toString());
/* 435 */             sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/* 436 */             sender.sendMessage(ChatColor.GREEN + "Player Temperature ");
/*     */             
/* 438 */             double bodyTemp = (Math.round(dPlayer.temp * 100.0D) / 100L);
/*     */             
/* 440 */             if (bodyTemp >= 38.0D) {
/* 441 */               sender.sendMessage(ChatColor.YELLOW + "→ Body temperature: " + ChatColor.RED + bodyTemp + "C / " + this.disease.messaging.getFahrenheit(bodyTemp) + "F");
/* 442 */             } else if (bodyTemp <= 34.0D) {
/* 443 */               sender.sendMessage(ChatColor.YELLOW + "→ Body temperature: " + ChatColor.BLUE + bodyTemp + "C / " + this.disease.messaging.getFahrenheit(bodyTemp) + "F");
/*     */             } else {
/* 445 */               sender.sendMessage(ChatColor.YELLOW + "→ Body temperature: " + ChatColor.GREEN + bodyTemp + "C / " + this.disease.messaging.getFahrenheit(bodyTemp) + "F");
/*     */             } 
/*     */             
/* 448 */             double localTemp = this.disease.ct.calcHeat((Player)sender, true);
/* 449 */             if (localTemp >= 30.0D) {
/* 450 */               sender.sendMessage(ChatColor.YELLOW + "→ Ambient temperature: " + ChatColor.RED + localTemp + "C / " + this.disease.messaging.getFahrenheit(localTemp) + "F");
/* 451 */             } else if (localTemp <= 5.0D) {
/* 452 */               sender.sendMessage(ChatColor.YELLOW + "→ Ambient temperature: " + ChatColor.BLUE + localTemp + "C / " + this.disease.messaging.getFahrenheit(localTemp) + "F");
/*     */             } else {
/* 454 */               sender.sendMessage(ChatColor.YELLOW + "→ Ambient temperature: " + ChatColor.GREEN + localTemp + "C / " + this.disease.messaging.getFahrenheit(localTemp) + "F");
/*     */             } 
/*     */             
/* 457 */             sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                        ");
/*     */           } else {
/*     */             
/* 460 */             sender.sendMessage(ChatColor.GREEN + "Must be a player to use this command");
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 465 */           sender.sendMessage(ChatColor.GREEN + " Use /temp or /temp details");
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 470 */         sender.sendMessage(ChatColor.GREEN + " Disease temperature module not enabled");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 476 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\System\Chat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */