package pw.betanyan.minigame.game;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private Timer timer;

    private String name;

    private GameState state;

    private List<String> ingame;

    private int maxPlayers;

    private Sign sign;

    public Arena(String name, int maxPlayers, Sign sign) {
        this.name = name;
        this.maxPlayers = maxPlayers;

        this.state = GameState.LOBBY;

        this.sign = sign;

        this.timer = new Timer(this);
        this.ingame = new ArrayList<>();
    }

    public Timer getTimer() {
        return timer;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngame() {
        return ingame;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void broadcast(String msg) {
        for (String player : ingame) {
            Bukkit.getPlayer(player).sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[Minigame] &r" + msg));
        }
    }

    public Sign getSign() {
        return sign;
    }
}
