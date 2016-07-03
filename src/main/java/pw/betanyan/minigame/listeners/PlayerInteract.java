package pw.betanyan.minigame.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {

                FileConfiguration config = plugin.getConfig();

                for (String arena : config.getConfigurationSection("arenas").getKeys(false)) {

                    if (config.getString("arenas." + arena + ".sign").equals(plugin.serializeLocation(e.getClickedBlock().getLocation()))) {

                        Arena arenaObj = plugin.getArenaManager().getArenaByName(arena);

                        if (arenaObj.getSign() != null) {

                            arenaObj.joinPlayer(e.getPlayer());

                        }

                    }

                }

            }

        }

    }

}
