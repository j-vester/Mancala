package mancala.domain;

public abstract class AbstractPit {

    private int stones;
    private AbstractPit neighbour;

    public AbstractPit() {
        this.stones = 0;
    }

    public AbstractPit(int initStones) {
        this.stones = initStones;
    }

    public int getStones() {
        return this.stones;
    }

    public AbstractPit getNeighbour() {
        return this.neighbour;
    }
}