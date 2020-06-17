package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import util.STATIC;

public class CommandStop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (STATIC.isStarted()) {
            STATIC.setStarted(false);
            Bukkit.getServer().broadcastMessage(STATIC.PREFIX + ChatColor.RED + STATIC.getLang().getString("stop.success" + STATIC.getLanguage()));
            STATIC.getExec().cancel();
            try {
                STATIC.getTimer().cancel();
            } catch (Exception ignored) {}
        } else
            sender.sendMessage(STATIC.PREFIX + ChatColor.RED + STATIC.getLang().getString("stop.error" + STATIC.getLanguage()));
        return true;
    }
}
