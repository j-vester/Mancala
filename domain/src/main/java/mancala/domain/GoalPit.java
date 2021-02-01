package mancala.domain;

// Kalaha ipv GoalPit
public class GoalPit extends AbstractPit {

    private static final int USUAL_INIT_STONES = 4;
    private static final int USUAL_MAX_PITS = 6;

    public GoalPit() {
        this(USUAL_INIT_STONES, USUAL_MAX_PITS);
    }

    public GoalPit(int nrStones, int nrPits) {
        super(0, new Player());
        this.addNeighbour(new PlayingPit(nrStones, nrPits, 1, this.getPlayer().getOpponent(), this));
    }
    
    public GoalPit(int nrStones, int nrPits, Player p, GoalPit firstpit) {
        super(0,p);
        this.addNeighbour(new PlayingPit(nrStones, nrPits, 1, this.getPlayer().getOpponent(), firstpit));
    }

    @Override
    public boolean rowEmpty() {
        return true;
    }
    
    @Override
    public GoalPit getGoalPit(Player player) {
        if (this.getPlayer().equals(player)){
            return this;
        } else {
            return this.getNeighbour().getGoalPit(player);
        }
    }

    @Override
    public void playPit() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("A goal pit cannot be played.");
    }

    @Override
    protected void passStonesAfterMove(int stones) {
        if (this.getPlayer().isCurrentPlayer()) {
            this.addStones(1);
            if (stones > 1) this.getNeighbour().passStonesAfterMove(stones-1);
        } else {
            this.getNeighbour().passStonesAfterMove(stones);
        }
    }

    @Override
    protected void passStonesToGoal(int stones) {
        this.addStones(stones);
    }
}  