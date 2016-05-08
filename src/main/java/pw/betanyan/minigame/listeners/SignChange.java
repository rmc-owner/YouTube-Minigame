package pw.betanyan.minigame.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import pw.betanyan.minigame.Minigame;
import pw.betanyan.minigame.game.Arena;

import java.util.ArrayList;
import java.util.List;

public class SignChange implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {

        if (e.getLine(0).equals("[arena]")) {

            Arena arena = Minigame.getInstance().getArenaManager().getArenaByName(e.getLine(1));

            if (arena != null) {

                e.setLine(0, ChatColor.AQUA + arena.getName());
                e.setLine(1, ChatColor.GREEN + arena.getState().toString());
                e.setLine(2, String.valueOf(ChatColor.YELLOW) + arena.getIngame().size() + "/" + arena.getMaxPlayers());

                Minigame.getInstance().getConfig().set("arenas." + arena.getName() + ".sign",
                        Minigame.getInstance().serializeLocation(e.getBlock().getLocation()));

                arena.setSign((Sign) e.getBlock().getState());

                Minigame.getInstance().saveConfig();

            }

        }

    }

}
