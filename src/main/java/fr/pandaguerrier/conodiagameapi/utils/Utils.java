package fr.pandaguerrier.conodiagameapi.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Utils {
  public static FileConfiguration createCustomConfig(String name, JavaPlugin plugin) {
    File file = new File(plugin.getDataFolder(), name);
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      plugin.saveResource(name, false);
    }

    return YamlConfiguration.loadConfiguration(file);
  }
}
