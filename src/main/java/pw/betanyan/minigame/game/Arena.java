package pw.betanyan.minigame.game;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import pw.betanyan.minigame.Minigame;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private Timer timer;

    private String name;

    private GameState state;

    private List<String> ingame;

    private List<Location> spawns;

    private int maxPlayers;

    private Location lobbySpawn;

    private Sign sign;

    public Arena(String name, Location lobbySpawn, List<Location> spawns, int maxPlayers, Sign sign) {
        this.name = name;
        this.maxPlayers = maxPlayers;

        this.spawns = spawns;

        this.lobbySpawn = lobbySpawn;

        this.state = GameState.LOBBY;

        this.sign = sign;

        this.timer = new Timer(this);
        this.ingame = new ArrayList<>();

        Minigame.getInstance().getArenaManager().addArena(this);
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

    public Location getLobbySpawn() {
        return lobbySpawn;
    }

    public void setLobbySpawn(Location lobbySpawn) {
        this.lobbySpawn = lobbySpawn;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public List<Location> getSpawns() {
        return spawns;
    }

    public void setSpawns(List<Location> spawns) {
        this.spawns = spawns;
    }

    public void addSpawn(Location location) {
        spawns.add(location);
    }

    public Sign getSign() {
        return sign;
    }
}
