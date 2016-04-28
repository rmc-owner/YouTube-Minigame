package pw.betanyan.minigame;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pw.betanyan.minigame.game.Arena;
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

    private void loadArenas() {

        for (String arena : config.getConfigurationSection("arenas").getKeys(false)) {

            new Arena(arena, config.getInt("arenas." + arena + ".maxplayers"),
                    config.getString("sign") == "NOEXIST" ? null :
                            (Sign) unserializeLocation(config.getString("sign")).getWorld()
                                    .getBlockAt(unserializeLocation(config.getString("sign"))).getState());

        }

    }

    private void registerListeners(Listener... listeners) {

        Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));

    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public String serializeLocation(Location loc) {
        return loc.getWorld().getName() + "=" + loc.getX() + "=" + loc.getY() + "=" + loc.getZ() + "=" + loc.getPitch() + "=" + loc.getYaw();
    }

    public Location unserializeLocation(String loc) {
        String[] split = loc.split("=");
        Location location = new Location(getServer().getWorld(split[0]),
                Double.parseDouble(split[1]), Double.parseDouble(split[2]),
                Double.parseDouble(split[3]));

        location.setPitch(Float.parseFloat(split[4]));
        location.setYaw(Float.parseFloat(split[5]));

        return location;
    }

    public String chatColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }

    public static Minigame getInstance() {
        return instance;
    }


}
