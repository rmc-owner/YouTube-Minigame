package pw.betanyan.minigame;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pw.betanyan.minigame.game.ArenaManager;
import pw.betanyan.minigame.listeners.PlayerInteract;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Minigame extends JavaPlugin {

    private ArenaManager arenaManager;

    private File configFile;
    private FileConfiguration config;

    @Override
    public void onEnable() {

        loadConfig();

        registerListeners(new PlayerInteract(this));

    }

    private void loadConfig() {

        if (!getDataFolder().exists()) {

            getDataFolder().mkdir();

        }

        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(configFile);

        if (!config.contains("Path")) {
            config.set("Path", "Value");
        }

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void registerListeners(Listener... listeners) {

        Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));

    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }
}
