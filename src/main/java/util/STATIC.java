package util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class STATIC {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(Plugin pl) {
        plugin = pl;
    }

    private static FileConfiguration lang;

    public static FileConfiguration getLang() {
        return lang;
    }

    public static void setLang(FileConfiguration config) {
        lang = config;
    }

    private static String language = ".en";

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String string) {
        language = string;
    }

    private static boolean started = false;

    public static boolean isStarted() {
        return started;
    }

    public static void setStarted(boolean state) {
        started = state;
    }

    private static BukkitTask exec;
    private static BukkitTask timer;
    private static BukkitTask teleport;

    public static BukkitTask getExec() {
        return exec;
    }

    public static BukkitTask getTimer() {
        return timer;
    }

    public static BukkitTask getTeleport() {
        return teleport;
    }

    public static void setExec(BukkitTask task) {
        exec = task;
    }

    public static void setTimer(BukkitTask task) {
        timer = task;
    }

    public static void setTeleport(BukkitTask task) {
        teleport = task;
    }

    public static String PREFIX = ChatColor.GRAY + "[" + ChatColor.GOLD + "SWITCH" + ChatColor.GRAY + "] " + ChatColor.RESET;

}
