package mancala.domain;

// Kalaha ipv Kalaha
public class Kalaha extends AbstractPit {

    private static final int USUAL_INIT_STONES = 4;
    private static final int USUAL_MAX_PITS = 6;

    public Kalaha() {
        this(USUAL_INIT_STONES, USUAL_MAX_PITS);
    }

    public Kalaha(int nrStones, int nrPits) {
        super(0, new Player());
        this.addNeighbour(new PlayingPit(nrStones, nrPits, 1, this.getPlayer().getOpponent(), this));
    }
    
    public Kalaha(int nrStones, int nrPits, Player p, Kalaha firstpit) {
        super(0,p);
        this.addNeighbour(new PlayingPit(nrStones, nrPits, 1, this.getPlayer().getOpponent(), firstpit));
    }

    @Override
    public boolean isRowEmpty() {
        return this.getPlayingPit(1, this.getPlayer()).isRowEmpty();
    }

    @Override
    public  PlayingPit getPlayingPit(int id, Player player) {
        return this.getNeighbour().getPlayingPit(id, player);
    }
    
    @Override
    public Kalaha getKalaha(Player player) {
        if (this.getPlayer().equals(player)){
            return this;
        } else {
            return this.getNeighbour().getKalaha(player);
        }
    }

    @Override
    public void playPit() throws MancalaException {
        throw new MancalaException("A goal pit cannot be played.");
    }

    @Override
    public void emptyRowToKalaha() {}

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

    @Override
    protected boolean isEmpty() {
        return true;
    }
}  