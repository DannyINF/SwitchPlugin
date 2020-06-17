package com.github.moorhuhnhd.switchplugin;

import commands.CommandStart;
import commands.CommandStop;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import util.STATIC;

import java.io.File;

public class SwitchPlugin extends JavaPlugin {

    FileConfiguration config = getConfig();

    FileConfiguration lang = YamlConfiguration.loadConfiguration(this.getTextResource("lang.yml"));

    private File langfile = null;

    @Override
    public void onEnable() {
        STATIC.setPlugin(this);
        this.saveDefaultConfig();
        if (langfile == null) {
            langfile = new File(getDataFolder(), "lang.yml");
        }
        if (!langfile.exists()) {
            this.saveResource("lang.yml", false);
        }
        STATIC.setLang(lang);
        STATIC.setLanguage("." + config.getString("language"));

        getLogger().info("SWITCH has been enabled.");

        initCommands();

    }

    @Override
    public void onDisable() {
        getLogger().info("SWITCH has been disabled.");
    }

    private void initCommands() {
        this.getCommand("switchstart").setExecutor(new CommandStart());
        this.getCommand("switchstop").setExecutor(new CommandStop());
    }
}