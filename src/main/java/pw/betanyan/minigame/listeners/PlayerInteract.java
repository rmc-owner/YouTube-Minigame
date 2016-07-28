package pw.betanyan.minigame.listeners;

import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
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
    public void onInt(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (event.getClickedBlock().getState() instanceof Sign) {

                FileConfiguration config = plugin.getConfig();

                for (String arena : config.getConfigurationSection("arenas").getKeys(false)) {

                    if (config.getString("arenas." + arena + ".sign").equals(plugin.serializeLocation(event.getClickedBlock().getLocation()))) {

                        Arena arenaObj = plugin.getArenaManager().getArenaByName(arena);

                        if (arenaObj.getSign() != null) {

                            arenaObj.joinPlayer(event.getPlayer());

                        }

                    }

                }

            }

        }

    }

}
