package com.swagsteve.staffalerts;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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
        this.getConfig().addDefault("Messages.JoinAlertMessage", "");
        this.getConfig().addDefault("Messages.LeaveAlertMessage", "");
        saveDefaultConfig();

        //Config Variables
        joinmsg = this.getConfig().getString("Messages.JoinAlertMessage");
        leavemsg = this.getConfig().getString("Messages.LeaveAlertMessage");

        //Commands
        this.getCommand("sa-reload").setExecutor(new ReloadCommand());

    }

    @EventHandler
    public static void onJoin(PlayerJoinEvent e) {

        Bukkit.getScheduler().scheduleSyncDelayedTask(StaffAlerts.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (e.getPlayer().isOp()) {

                    Player p = e.getPlayer();

                    e.setJoinMessage(Utils.Color(joinmsg.replace("%player%", p.getName())));
                }
            }
        },20L);
    }

    @EventHandler
    public static void onLeave(PlayerQuitEvent e) {

        Bukkit.getScheduler().scheduleSyncDelayedTask(StaffAlerts.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (e.getPlayer().isOp()) {

                    Player p = e.getPlayer();

                    e.setQuitMessage(Utils.Color(leavemsg.replace("%player%", p.getName())));
                }
            }
        },20L);
    }

    @Override
    public void onDisable() {

        //Disable MSG
        System.out.println("[SA] Staff Alerts Disabled!");

        saveConfig();

    }
}
