package pw.betanyan.minigame.game;

import org.bukkit.scheduler.BukkitRunnable;
import pw.betanyan.minigame.Minigame;

public class Timer extends BukkitRunnable {

    private Arena arena;

    private int lobbyTimeLeft;
    private int gameTimeLeft;
    private int endTimeLeft;

    private boolean paused;

    public Timer(Arena arena) {

        this.arena = arena;

        this.lobbyTimeLeft = 60;
        this.gameTimeLeft = 300;
        this.endTimeLeft = 10;

        this.paused = true;

        this.runTaskTimer(Minigame.getPlugin(Minigame.class), 0, 20);

    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public void run() {

        if (!paused) {

            switch (arena.getState()) {

                case LOBBY:
                    if (lobbyTimeLeft != 0) {
                        lobbyTimeLeft--;
                    } else {
                        arena.setState(GameState.INGAME);
                        //TODO: Send players to spawn, start game, etc
                    }
                    break;
                case INGAME:
                    if (gameTimeLeft != 0) {
                        gameTimeLeft--;
                    } else {
                        arena.setState(GameState.END);
                        //TODO: Send players to spawn, start game, etc
                    }
                    break;
                case END:
                    if (endTimeLeft != 0) {
                        endTimeLeft--;
                    } else {
                        //TODO: End/Restart Game
                    }
                    break;
                default:
                    break;

            }

        }

    }

}
