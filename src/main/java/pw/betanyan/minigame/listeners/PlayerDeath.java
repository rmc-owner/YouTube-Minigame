package pw.betanyan.minigame.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pw.betanyan.minigame.Minigame;
import pw.betanyan.minigame.game.Arena;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player player = event.getEntity();
        Player killer = player.getKiller();

        Arena arena = Minigame.getInstance().getArenaManager().getArenaByPlayer(player.getName());
        if (arena != null) {

            if (killer != null) {
                arena.broadcast("&e" + player.getName() + "&a was slain by &e" + killer.getName());
                arena.getKills().put(killer.getName(),
                        arena.getKills().get(killer.getName()) + 1);
            } else{
                arena.broadcast("&e" + player.getName() + "&a has died.");
            }

        }

    }

}
