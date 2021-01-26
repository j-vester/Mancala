package mancala.domain;

public abstract class AbstractPit {

    private int stones;
    private int player;
    private AbstractPit neighbour;
    private CurrentPlayer cp;

    protected AbstractPit(int initStones, CurrentPlayer cp) {
        this.stones = initStones;
        this.player = cp.getCurrentPlayer();;
        this.cp = cp;
    }

    protected AbstractPit(int initStones) {
        this(initStones, new CurrentPlayer());
    }

    protected AbstractPit() {
        this(0, new CurrentPlayer());
    }

    public abstract boolean rowEmpty();

    public abstract void passStonesAfterMove(int stones);

    public abstract void passStonesToGoal(int stones);

    public PlayingPit getPlayingPit(int id, int player) throws IllegalArgumentException {
        if (!this.getCurrentPlayerObject().isValidPlayer(player)) {
            throw new IllegalArgumentException("This pit does not exist in the game");
        }
        return this.getNeighbour().getPlayingPit(id, player);
    }

    public GoalPit getGoalPit(int player) throws IllegalArgumentException {
        if (!this.getCurrentPlayerObject().isValidPlayer(player)) {
            throw new IllegalArgumentException("This pit does not exist in the game");
        }
        return this.getNeighbour().getGoalPit(player);
    }

    public int getCurrentPlayer() {
        return cp.getCurrentPlayer();
    }

    public int getStones() {
        return this.stones;
    }

    protected void addNeighbour(AbstractPit nextPit) {
        this.neighbour = nextPit;
    }

    protected int getPlayer() {
        return this.player;
    }
    
    protected CurrentPlayer getCurrentPlayerObject() {
        return cp;
    }

    protected int emptyPitAndReturnStones() {
        int collect = this.stones;
        this.stones = 0;
        return collect;
    }

    public AbstractPit getNeighbour() {
        return this.neighbour;
    }

    protected void addStones(int stones) {
        this.stones += stones;
    }
}