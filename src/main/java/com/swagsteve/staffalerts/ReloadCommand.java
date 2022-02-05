package com.swagsteve.staffalerts;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.isOp()) {

                StaffAlerts.getInstance().reloadConfig();
                p.sendMessage(Utils.Color("&a&lConfig Successfully Reloaded!"));

            } else {
                p.sendMessage("&c&lYou Don't Have Permission To Use This Command!");
            }
        }
        return false;
    }
}
