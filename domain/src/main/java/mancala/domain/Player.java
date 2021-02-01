package mancala.domain;

public class Player {
    private Player opponent;
    private boolean currentPlayer;
    // private int score?

    public Player() {
        this.currentPlayer = true;
        this.opponent = new Player(this);
    }

    private Player(Player opp) {
        this.currentPlayer = false;
        this.opponent = opp;
    }

    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public void switchTurns() {
        if (this.currentPlayer) this.turnIsOver();
        else this.getOpponent().turnIsOver();
    }

    public void endGame() {
        if (this.currentPlayer) this.currentPlayer = false;
        else this.getOpponent().endGame();
    }

    private void turnIsOver() {
        this.currentPlayer = false;
        this.opponent.turn();
    }

    private void turn() {
        this.currentPlayer = true;
    }
}
