package mancala.domain;

public class CurrentPlayer {
    private int player;
    private static final int[] PLAYERS = {1,0};

    public CurrentPlayer() {
        this.player = PLAYERS[1];
    }

    public void switchPlayer() {
        this.player = PLAYERS[this.player];
    }

    public int getCurrentPlayer() {
        return this.player;
    }

    public int getIdlePlayer() {
        return PLAYERS[this.player];
    }
    
    public boolean isValidPlayer(int player) {
        for (int i : PLAYERS) {
            if (i == player) {
                return true;
            }
        }
        return false;
    }
}