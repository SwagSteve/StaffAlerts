package com.swagsteve.staffalerts;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffAlerts extends JavaPlugin implements Listener {

    public static String joinmsg;
    public static String leavemsg;

    //Instance
    private static StaffAlerts instance;
    public static StaffAlerts getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {

        //Instance
        instance = this;

        //Enable MSG
        System.out.println("[SA] Staff Alerts Enabled!");

        //Config
        this.getConfig().options().copyDefaults();
        this.getConfig().addDefault("Messages.JoinAlertMessage", "&a[ADMIN]&r&b&l %player% &r&aJoined The Game");
        this.getConfig().addDefault("Messages.LeaveAlertMessage", "&c[ADMIN]&r&b&l %player% &r&aHas Left The Game");
        saveDefaultConfig();

        //Commands
        this.getCommand("sa-reload").setExecutor(new ReloadCommand());

        //Events
        this.getServer().getPluginManager().registerEvents(this,this);

    }

    @EventHandler(priority = EventPriority.HIGH)
    public static void onJoin(PlayerJoinEvent e) {

        //Config Variable
        joinmsg = instance.getConfig().getString("Messages.JoinAlertMessage");

        if (e.getPlayer().isOp()) {
            Player p = e.getPlayer();

            e.setJoinMessage(Utils.Color(joinmsg.replace("%player%", p.getName())));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public static void onLeave(PlayerQuitEvent e) {

        //Config Variable
        leavemsg = instance.getConfig().getString("Messages.LeaveAlertMessage");

        if (e.getPlayer().isOp()) {
            Player p = e.getPlayer();
            e.setQuitMessage(Utils.Color(leavemsg.replace("%player%", p.getName())));
        }
    }

    @Override
    public void onDisable() {

        //Disable MSG
        System.out.println("[SA] Staff Alerts Disabled!");

        saveConfig();

    }
}
