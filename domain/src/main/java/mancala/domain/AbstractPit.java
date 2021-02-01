package mancala.domain;

public abstract class AbstractPit {

    private int stones;
    private AbstractPit neighbour;
    private Player player;

    protected AbstractPit(int initStones, Player p) {
        this.stones = initStones;
        this.player = p;
    }

    public int getStones() {
        return this.stones;
    }

    public Player getPlayer() {
        return this.player;
    }

    public AbstractPit getNeighbour() {
        return this.neighbour;
    }

    public abstract boolean isRowEmpty();

    public abstract void playPit();

    public abstract PlayingPit getPlayingPit(int id, Player player);

    public abstract Kalaha getKalaha(Player player);
    
    protected abstract void emptyRowToKalaha();

    protected abstract void passStonesAfterMove(int stones);

    protected abstract void passStonesToGoal(int stones);

    protected abstract boolean isEmpty();

    protected void addStones(int stones) {
        this.stones += stones;
    }

    protected void addNeighbour(AbstractPit nextPit) {
        this.neighbour = nextPit;
    }

    // method voor get score --> getKalaha setScore --> 
}