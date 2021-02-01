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

    public void turnOver() {
        this.currentPlayer = false;
        this.opponent.turn();
    }

    private void turn() {
        this.currentPlayer = true;
    }
}
