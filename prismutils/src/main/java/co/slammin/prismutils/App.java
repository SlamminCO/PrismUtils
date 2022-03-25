package co.slammin.prismutils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    private File playerDataFile;
    private FileConfiguration playerData;

    @Override
    public void onEnable() {
        getLogger().info("PrismUtils Enabled");

        createPlayerData();
        saveDefaultConfig();
        getCommand("prism-reload").setExecutor(new CommandReload(this));
        getCommand("apply").setExecutor(new CommandApply(this));
        getCommand("verifyapplication").setExecutor(new CommandVerifyApplication(this));
    }

    public FileConfiguration getPlayerData() {
        return this.playerData;
    }

    public void savePlayerData() {
        try {
            getPlayerData().save(playerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPlayerData() {
        playerDataFile = new File(getDataFolder(), "playerData.yml");
        if (!playerDataFile.exists()) {
            playerDataFile.getParentFile().mkdirs();
            saveResource("playerData.yml", false);
        }

        playerData = new YamlConfiguration();
        try {
            playerData.load(playerDataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("PrismUtils Disabled");
    }
}
