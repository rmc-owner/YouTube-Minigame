package pw.betanyan.minigame.listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import pw.betanyan.minigame.Minigame;
import pw.betanyan.minigame.game.Arena;

public class SignChange implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent event) {

        if (event.getLine(0).equals("[arena]")) {

            Arena arena = Minigame.getInstance().getArenaManager().getArenaByName(event.getLine(1));

            if (arena != null) {

                event.setLine(0, ChatColor.AQUA + arena.getName());
                event.setLine(1, ChatColor.GREEN + arena.getState().toString());
                event.setLine(2, String.valueOf(ChatColor.YELLOW) + arena.getIngame().size() + "/" + arena.getMaxPlayers());

                Minigame.getInstance().getConfig().set("arenas." + arena.getName() + ".sign",
                        Minigame.getInstance().serializeLocation(event.getBlock().getLocation()));

                arena.setSign((Sign) event.getBlock().getState());

                Minigame.getInstance().saveConfig();

            }

        }

    }

}
