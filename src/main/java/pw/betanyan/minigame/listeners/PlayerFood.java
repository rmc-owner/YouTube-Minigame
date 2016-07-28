package pw.betanyan.minigame.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import pw.betanyan.minigame.Minigame;
import pw.betanyan.minigame.game.Arena;

public class PlayerFood implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {

        Player player = (Player) e.getEntity();
        Arena arena = Minigame.getInstance().getArenaManager().getArenaByPlayer(player.getName());
        if (arena != null) {
            e.setCancelled(true);
        }

    }

}
