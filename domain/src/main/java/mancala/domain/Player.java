package mancala.domain;

public class Player {
    private Player opponent;
    private boolean currentPlayer;

    public Player() {
        this.currentPlayer = true;
        this.opponent = new Player(this);
    }

    public Player(Player opp) {
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
