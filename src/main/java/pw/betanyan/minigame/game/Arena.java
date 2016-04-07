package pw.betanyan.minigame.game;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private Timer timer;

    private String name;

    private List<String> ingame;

    private int maxPlayers;

    public Arena(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;

        this.timer = new Timer();
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

}
