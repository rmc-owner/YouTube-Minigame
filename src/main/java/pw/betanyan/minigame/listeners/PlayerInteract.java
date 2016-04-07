package pw.betanyan.minigame.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pw.betanyan.minigame.Minigame;

public class PlayerInteract implements Listener {

    private Minigame plugin;

    public PlayerInteract(Minigame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInt(PlayerInteractEvent e) {



    }

}
