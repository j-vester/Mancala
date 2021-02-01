package mancala.domain;

public abstract class AbstractPit {

    private int stones;
    private AbstractPit neighbour;
    private Player player;

    protected AbstractPit(int initStones, Player p) {
        this.stones = initStones;
        this.player = p;
    }

    public abstract boolean rowEmpty();

    public abstract void playPit();

    protected abstract void passStonesAfterMove(int stones);

    protected abstract void passStonesToGoal(int stones);

    public int getStones() {
        return this.stones;
    }

    public Player getPlayer() {
        return this.player;
    }

    public AbstractPit getNeighbour() {
        return this.neighbour;
    }
    //maak volgende drie abstract
    public PlayingPit getPlayingPit(int id, Player player) {
        return this.getNeighbour().getPlayingPit(id, player);
    }

    public GoalPit getGoalPit(Player player) {
        return this.getNeighbour().getGoalPit(player);
    }
    
    // maak private? 
    public void emptyRowToGoalPit() {}

    protected void addStones(int stones) {
        this.stones += stones;
    }

    protected void addNeighbour(AbstractPit nextPit) {
        this.neighbour = nextPit;
    }

    // method voor get score --> getKalaha setScore --> 
}