package pw.betanyan.minigame.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import pw.betanyan.minigame.Minigame;
import pw.betanyan.minigame.game.Arena;
import pw.betanyan.minigame.game.GameState;

public class PlayerDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        if(event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();
            Arena arena = Minigame.getInstance().getArenaManager().getArenaByPlayer(player.getName());
            if (arena != null) {
                if (arena.getState() != GameState.INGAME) {
                    event.setCancelled(true);
                }
            }

        }

    }

}
