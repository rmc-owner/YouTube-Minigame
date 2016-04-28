package pw.betanyan.minigame;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pw.betanyan.minigame.game.ArenaManager;
import pw.betanyan.minigame.listeners.PlayerInteract;
import pw.betanyan.minigame.listeners.SignChange;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Minigame extends JavaPlugin {

    private static Minigame instance;

    private ArenaManager arenaManager;

    private File configFile;
    private FileConfiguration config;

    @Override
    public void onEnable() {

        arenaManager = new ArenaManager();

        loadConfig();

        registerListeners(new PlayerInteract(this), new SignChange());

        instance = this;

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

        if (!config.contains("arenas")) {
            config.set("arenas", "");
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

    public String serializeLocation(Location loc) {
        return loc.getWorld() + "=" + loc.getX() + "=" + loc.getY() + "=" + loc.getZ() + "=" + loc.getPitch() + "=" + loc.getYaw();
    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }

    public static Minigame getInstance() {
        return instance;
    }
}
