package pw.betanyan.minigame.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pw.betanyan.minigame.Minigame;
import pw.betanyan.minigame.game.Arena;

public class PlayerInteract implements Listener {

    private Minigame plugin;

    public PlayerInteract(Minigame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInt(PlayerInteractEvent e) {

        FileConfiguration config = plugin.getConfig();

        for (String arena : config.getConfigurationSection("arenas").getKeys(false)) {

            if (config.getStringList("arenas." + arena + ".signs").contains(plugin.serializeLocation(e.getClickedBlock().getLocation()))) {

                Arena arenaObj = plugin.getArenaManager().getArenaByName(arena);

                arenaObj.getIngame().add(e.getPlayer().getName());
                arenaObj.broadcast("&a" +  e.getPlayer().getName() + " has joined the game! " + arenaObj.getIngame().size() + "/" + arenaObj.getMaxPlayers());
                arenaObj.getSign().setLine(2, String.valueOf(ChatColor.YELLOW) + arenaObj.getIngame().size() + "/" + arenaObj.getMaxPlayers());

            }

        }

    }

}
