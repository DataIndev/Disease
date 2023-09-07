package com.hmmcrunchy.disease;

import com.hmmcrunchy.disease.System.Chat;
import com.hmmcrunchy.disease.System.CreateVaccine;
import com.hmmcrunchy.disease.System.DetectPlugins;
import com.hmmcrunchy.disease.System.DiseaseData;
import com.hmmcrunchy.disease.System.DiseaseRegions;
import com.hmmcrunchy.disease.System.DiseaseSettings;
import com.hmmcrunchy.disease.System.GUI;
import com.hmmcrunchy.disease.System.ItemFactory;
import com.hmmcrunchy.disease.System.Messaging;
import com.hmmcrunchy.disease.calculate.ArmourValues;
import com.hmmcrunchy.disease.calculate.BiomeCalculations;
import com.hmmcrunchy.disease.calculate.CalculateTemperature;
import com.hmmcrunchy.disease.calculate.ConsumeCalculations;
import com.hmmcrunchy.disease.calculate.DamageCalculations;
import com.hmmcrunchy.disease.calculate.InteractCalculations;
import com.hmmcrunchy.disease.calculate.InventoryProcess;
import com.hmmcrunchy.disease.calculate.ProximityCalculations;
import com.hmmcrunchy.disease.classes.DDisease;
import com.hmmcrunchy.disease.classes.DPlayer;
import com.hmmcrunchy.disease.classes.Remedy;
import com.hmmcrunchy.disease.classes.Vaccine;
import com.hmmcrunchy.disease.classes.Vial;
import com.hmmcrunchy.disease.effects.DiseaseEffects;
import com.hmmcrunchy.disease.effects.UseVaccine;
import com.hmmcrunchy.disease.effects.UseVials;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Disease
extends JavaPlugin
implements Listener {
    /*  92 */ DiseaseData dd = new DiseaseData(this);
    /*  93 */   public DiseaseSettings ds = new DiseaseSettings(this);
    /*  94 */   public DetectPlugins dp = new DetectPlugins(this);
    /*  95 */   public DiseaseEffects de = new DiseaseEffects(this);
    /*  96 */   public CalculateTemperature ct = new CalculateTemperature(this);
    /*  97 */   public ItemFactory itemF = new ItemFactory(this);
    /*  98 */ CreateVaccine cv = new CreateVaccine(this);
    /*  99 */ ArmourValues av = new ArmourValues();
    /* 100 */ UseVaccine uv = new UseVaccine();
    /* 101 */ UseVials uvi = new UseVials();
    /* 102 */ InventoryProcess ip = new InventoryProcess();
    /* 103 */   public Chat chat = new Chat(this);
    /* 104 */   public Messaging messaging = new Messaging(this);
    /* 105 */   public ProximityCalculations proxCalc = new ProximityCalculations(this);
    /* 106 */   public BiomeCalculations biomeCalc = new BiomeCalculations(this);
    /* 107 */   public DamageCalculations damCalc = new DamageCalculations(this);
    /* 108 */   public ConsumeCalculations conCalc = new ConsumeCalculations(this);
    /* 109 */   public InteractCalculations interCalc = new InteractCalculations(this);
    /* 110 */   public DiseaseRegions diseaseRegions = new DiseaseRegions(this);
    /* 111 */   public GUI gui = new GUI(this);

    /* 113 */ String folderDir = getDataFolder() + "";
    /* 114 */   public File folder = new File(this.folderDir);
    /* 115 */   public File playerData = new File(this.folderDir + File.separator + "players");
    /* 116 */   public File diseaseData = new File(this.folderDir + File.separator + "diseases");
    /* 117 */   public File remedyData = new File(this.folderDir + File.separator + "remedies");
    /* 118 */   public File configFile = new File(this.folderDir + File.separator + "config.yml");
    /* 119 */   public File LanguageFile = new File(this.folderDir + File.separator + "language.yml");
    /* 120 */   public File ESLanguageFile = new File(this.folderDir + File.separator + "language - ES.yml");

    public YamlConfiguration languageFile;

    FileConfiguration config;

    public boolean byteScoreboardEnabled;

    public boolean scoreboardStatsEnabled;

    public boolean worldGuardEnabled;
    public boolean vaultEnabled;
    public boolean actionBarEnabled;
    public boolean citizensEnabled;
    public boolean debug = false;
    /* 135 */   public Plugin scoreboardStats = getServer().getPluginManager().getPlugin("ScoreboardStats");
    /* 136 */   public Plugin citizens = getServer().getPluginManager().getPlugin("Citizens");
    /* 137 */   public Plugin vault = getServer().getPluginManager().getPlugin("Vault");
    /* 138 */   public Plugin Vampire = getServer().getPluginManager().getPlugin("Vampire");
    /* 139 */   public Plugin worldGuard = getServer().getPluginManager().getPlugin("WorldGuard");
    /* 140 */   public Economy econ = null;

    /* 142 */   public LinkedHashMap<String, DDisease> diseases = new LinkedHashMap<>();
    /* 143 */   public LinkedHashMap<String, DPlayer> dPlayers = new LinkedHashMap<>();
    /* 144 */   public LinkedHashMap<String, Remedy> remedies = new LinkedHashMap<>();
    /* 145 */   public LinkedHashMap<String, Vaccine> vaccines = new LinkedHashMap<>();
    /* 146 */   public LinkedHashMap<String, Vial> vials = new LinkedHashMap<>();


    public void onEnable() {

        this.dd.CheckFolders();
        this.dp.checkPlugins();
        this.ds.loadConfigData();
        for (Player p : getServer().getOnlinePlayers()) {

            String uuid = p.getUniqueId().toString();
            if (this.dPlayers.containsKey(uuid)) {
                continue;
            }

            DPlayer dPlayer = new DPlayer(this);
            try {
                dPlayer.activatePlayer(p);
            } catch (IOException ex) {
                Logger.getLogger(Disease.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
            this.dPlayers.put(uuid, dPlayer);
        }

        getLogger().info(ChatColor.RED + "Thanks for using the BYTE Disease plugin - www.byte.org.uk!");
        getServer().getPluginManager().registerEvents(this, (Plugin) this);

        getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) this, new Runnable() {

            public void run() {
                for (Player p : Disease.this.getServer().getOnlinePlayers()) {
                    if (Disease.this.ds.worlds.contains(p.getWorld().getName())) {
                        if (p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE)) {
                            DPlayer dPlayer = Disease.this.dPlayers.get(p.getUniqueId().toString());
                            if (Disease.this.ds.useTemp == true) {
                                if (Disease.this.debug) Disease.this.getLogger().info(p.getName() + " - getting temp");
                                Disease.this.ct.calcHeat(p, false);
                            }

                            if (dPlayer.infection.equalsIgnoreCase("none")) {
                                if (Disease.this.debug) Disease.this.getLogger().info(p.getName() + " not infected - checking for infections chances");
                                Disease.this.proxCalc.processProximity(p);
                                Disease.this.biomeCalc.processBiomes(p);
                            } else {
                                if (Disease.this.debug) Disease.this.getLogger().info(p.getName() + " already infected - process player infections");
                                Disease.this.de.ProcessDisease(p, Disease.this.ds.timeBetweenChecks);
                            }
                            if (Disease.this.debug) Disease.this.getLogger().info(p.getName() + " checking WG regions");
                            Disease.this.diseaseRegions.ProcessRegion(p);
                        }
                    }
                }
            }
        },100,60);
    }


    public void onDisable() {
        /* 244 */
        getLogger().info("Saving players to file");

        /* 246 */
        for (Player p : getServer().getOnlinePlayers()) {
            /* 248 */
            ((DPlayer) this.dPlayers.get(p.getUniqueId().toString())).savePlayer();
        }

        getLogger().info("Byeeee Thanks for using BYTE Disease plugin - www.byte.org.uk!");

        getServer().getScheduler().cancelTasks((Plugin) this);
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void playerlogin(PlayerJoinEvent event) throws IOException {
        /* 261 */
        Player p = event.getPlayer();
        /* 262 */
        if (this.dPlayers.containsKey(p.getUniqueId().toString())) {
            /* 263 */
            ((DPlayer) this.dPlayers.get(p.getUniqueId().toString())).activatePlayer(p);
        } else {

            /* 266 */
            DPlayer dp = new DPlayer(this);
            /* 267 */
            dp.activatePlayer(p);
            /* 268 */
            this.dPlayers.put(p.getUniqueId().toString(), dp);
        }
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void playerLeave(PlayerQuitEvent event) throws IOException {
        /* 276 */
        Player p = event.getPlayer();

        /* 278 */
        ((DPlayer) this.dPlayers.get(p.getUniqueId().toString())).savePlayer();
        /* 279 */
        this.dPlayers.remove(p.getUniqueId().toString());
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        /* 285 */
        if (this.debug) getLogger().info("init command for " + sender.getName());

        /* 287 */
        this.chat.chat(sender, cmd, label, args);

        /* 289 */
        return true;
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void playerDamage(EntityDamageEvent e) {
        Player p;
        /* 297 */
        if (this.debug) getLogger().info("entity damage event");



        /* 301 */
        if (isAnNpc(e.getEntity())) {
            return;
        }


        /* 306 */
        if (e.getEntity() instanceof Player) {
            /* 307 */
            p = (Player) e.getEntity();
        } else {
            return;
        }


        /* 313 */
        if (this.ds.worlds.contains(p.getWorld().getName())) {

            /* 316 */
            if (p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE)) {
                /* 318 */
                this.damCalc.processEntityDamageEvent(p, e);
            }
        }
    }


    @EventHandler(priority = EventPriority.MONITOR)
    void playerHitEvent(EntityDamageByEntityEvent e) {
        Player p;
        /* 329 */
        if (this.debug) getLogger().info("entity damage by entity event");



        /* 333 */
        if (isAnNpc(e.getEntity())) {
            return;
        }



        /* 339 */
        if (e.getEntity() instanceof Player) {
            /* 340 */
            p = (Player) e.getEntity();
        } else {
            return;
        }


        /* 346 */
        if (this.ds.worlds.contains(p.getWorld().getName())) {

            /* 349 */
            if (p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE)) {
                /* 351 */
                this.damCalc.processEntityDamageByEntityEvent(p, e);
            }
        }
    }


    @EventHandler(priority = EventPriority.MONITOR)
    void playerInteract(PlayerInteractEvent e) {
        Player p;
        /* 363 */
        if (this.debug) getLogger().info("player interact event");



        /* 367 */
        if (isAnNpc((Entity) e.getPlayer())) {
            return;
        }


        /* 372 */
        if (e.getPlayer() instanceof Player) {
            /* 373 */
            if (this.debug) getLogger().info(e.getPlayer().getName() + " player interacted with");
            /* 374 */
            p = e.getPlayer();
        } else {
            /* 376 */
            if (this.debug) getLogger().info("not a player - returning");


            return;
        }
        /* 381 */
        if (this.ds.worlds.contains(p.getWorld().getName())) {

            /* 383 */
            if (this.debug) getLogger().info("player in world");

            /* 385 */
            if (p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE)) {

                /* 387 */
                if (this.debug) getLogger().info("player in gm survival or adventure");
                /* 388 */
                this.interCalc.processPlayerInteraction(p, e);
            }
        }
    }


    @EventHandler(priority = EventPriority.MONITOR)
    void playerHelpOthers(PlayerInteractEntityEvent e) {
        Player p;
        /* 400 */
        if (this.debug) getLogger().info("player interact entity event");



        /* 404 */
        if (isAnNpc((Entity) e.getPlayer())) {
            return;
        }


        /* 409 */
        if (e.getPlayer() instanceof Player) {

            /* 411 */
            if (this.debug) getLogger().info(e.getPlayer().getName() + " player interacted with");
            /* 412 */
            p = e.getPlayer();
        } else {
            return;
        }


        /* 418 */
        if (this.ds.worlds.contains(p.getWorld().getName())) {

            /* 420 */
            if (this.debug) getLogger().info("player in world");

            /* 422 */
            if (p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE)) {

                /* 424 */
                if (this.debug) getLogger().info("player in gm survival or adventure");
                /* 425 */
                this.interCalc.processPlayerInteractEntity(p, e);
            }
        }
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void playerConsume(PlayerItemConsumeEvent event) {
        /* 436 */
        if (this.debug) getLogger().info("player consume event");

        /* 438 */
        this.conCalc.processConsumeEvent(event.getPlayer(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    void playerCraft(PrepareItemCraftEvent event) {
        /* 447 */
        this.cv.createVaccine(event, this.debug);
    }


    @EventHandler(priority = EventPriority.MONITOR)
    void playerArrowShoot(EntityShootBowEvent e) {
        /* 454 */
        if (e.getEntity() instanceof Player && e.getProjectile() instanceof Arrow) {

            /* 456 */
            Player p = (Player) e.getEntity();
            /* 457 */
            Arrow arrow = (Arrow) e.getProjectile();

            /* 459 */
            arrow.setCustomName(this.ip.getArrowName(p, Boolean.valueOf(this.debug)));
        }
    }


    @EventHandler(priority = EventPriority.MONITOR)
    void potionSplash(PotionSplashEvent event) {
        /* 469 */
        this.uvi.VialSplash(event, this, this.debug);
    }


    public int getSickness(Player p) {
        /* 477 */
        int sick = 0;

        /* 479 */
        DPlayer player = this.dPlayers.get(p.getUniqueId().toString());
        /* 480 */
        sick = player.sickness;

        /* 482 */
        return sick;
    }

    public int getTemperature(Player p) {
        /* 486 */
        int temp = 0;

        /* 488 */
        DPlayer player = this.dPlayers.get(p.getUniqueId().toString());
        /* 489 */
        temp = (int) player.temp;

        /* 491 */
        return temp;
    }

    public String getDisease(Player p) {
        /* 495 */
        String dis = "";

        /* 497 */
        DPlayer player = this.dPlayers.get(p.getUniqueId().toString());
        /* 498 */
        dis = player.infection;

        /* 500 */
        return dis;
    }


    @EventHandler(priority = EventPriority.HIGH)
    void inventoryClick(InventoryClickEvent event) {
        /* 506 */
        Player player = (Player) event.getWhoClicked();
        /* 507 */
        InventoryView inventory = event.getView();
        /* 508 */
        Boolean shift = Boolean.valueOf(event.isShiftClick());

        /* 510 */
        if (this.debug) getLogger().info(player.getName() + " inventory click " + inventory.getTitle());
        /* 511 */
        String invName = inventory.getTitle();

        /* 513 */
        if (invName.equals("Disease - Menu") || invName.equals("Diseases") || invName.equals("Remedies") || invName.equals("Temperature") || invName.equals("Immunity") || invName.equals("Health")) {

            /* 515 */
            if (this.debug) getLogger().info(player.getName() + " clicked disease menu inventory");
            /* 516 */
            this.gui.inventoryClick(event);
        }
    }


    boolean isAnNpc(Entity entity) {
        /* 524 */
        if (this.citizensEnabled &&
        /* 525 */       entity.hasMetadata("NPC")) {
            /* 526 */
            if (this.debug) getLogger().info("NPC Detected");

            /* 528 */
            return true;
        }

        /* 531 */
        return false;
    }
}


/* Location:              D:\Escritorio\Disease-4.2.2.jar!\com\hmmcrunchy\disease\Disease.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */