package pw.betanyan.minigame.game;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
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

    public void updateSign() {

        getSign().setLine(0, ChatColor.AQUA + getName());
        getSign().setLine(1, ChatColor.GREEN + getState().toString());
        getSign().setLine(2, String.valueOf(ChatColor.YELLOW) + getIngame().size() + "/" + getMaxPlayers());

        getSign().update(true);

    }
    
    public void joinPlayer(Player player) {

        if (getIngame().size() < maxPlayers) {

            if (!getIngame().contains(player.getName())) {

                if (getState() == GameState.LOBBY) {

                    getIngame().add(player.getName());
                    broadcast("&a" + player.getName() + " has joined the game! " + getIngame().size() + "/" + getMaxPlayers());

                    player.teleport(getLobbySpawn());

                    if (getIngame().size() == getMaxPlayers() / 2) {

                        getTimer().setPaused(false);

                    }

                    updateSign();

                } else {

                    player.sendMessage(ChatColor.RED + "The game already started!");

                }

            } else {

                player.sendMessage(ChatColor.RED + "You are already in this game!");

            }

        } else {

            player.sendMessage(ChatColor.RED + "There is already enough people!");

        }
        
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
