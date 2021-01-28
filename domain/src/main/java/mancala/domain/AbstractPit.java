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

    public abstract void passStonesAfterMove(int stones);

    public abstract void passStonesToGoal(int stones);

    public abstract void playPit();

    public int getStones() {
        return this.stones;
    }

    public Player getPlayer() {
        return this.player;
    }

    public AbstractPit getNeighbour() {
        return this.neighbour;
    }

    public PlayingPit getPlayingPit(int id, Player player) {
        return this.getNeighbour().getPlayingPit(id, player);
    }

    public GoalPit getGoalPit(Player player) {
        return this.getNeighbour().getGoalPit(player);
    }

    public void emptyRowToGoalPit() {}

    protected void addStones(int stones) {
        this.stones += stones;
    }

    protected void addNeighbour(AbstractPit nextPit) {
        this.neighbour = nextPit;
    }
}