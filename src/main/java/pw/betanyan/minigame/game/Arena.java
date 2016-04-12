package pw.betanyan.minigame.game;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private Timer timer;

    private String name;

    private GameState state;

    private List<String> ingame;

    private int maxPlayers;

    public Arena(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;

        this.state = GameState.LOBBY;

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

}
