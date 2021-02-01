package mancala.domain;

public class PlayingPit extends AbstractPit {
    private int id;
    private int pitsPerPlayer;

    public PlayingPit(int nrStones, int nrPits, int id, Player p, Kalaha firstpit) {
        super(nrStones, p);
        this.id = id;
        this.pitsPerPlayer = nrPits;
        if (id < nrPits) {
            this.addNeighbour(new PlayingPit(nrStones, nrPits, id+1, p, firstpit));
        } else if (p.getOpponent().isCurrentPlayer()) {
            this.addNeighbour(new Kalaha(nrStones, nrPits, p, firstpit));
        } else {
            this.addNeighbour(firstpit);
        }
    }
    
    @Override
    public boolean isRowEmpty() {
        if (this.id != 1) return this.getPlayingPit(1, this.getPlayer()).isRowEmpty();
        if (this.getStones() <= 0) return this.getNeighbour().isEmpty();
        return false;
    }

    @Override
    public void playPit() throws MancalaException {
        if (!this.getPlayer().isCurrentPlayer()) throw new MancalaException("This pit cannot be played by current player.");
        if (this.getStones() <= 0) throw new MancalaException("An empty pit cannot be played.");
        int playedStones = this.emptyPitAndReturnStones();
        this.getNeighbour().passStonesAfterMove(playedStones);
    }

    @Override
    public PlayingPit getPlayingPit(int id, Player player) throws IllegalArgumentException {
        if (id < 1 || id > this.pitsPerPlayer) {
            throw new IllegalArgumentException("This pit does not exist in the game.");
        }
        if (this.id == id && this.getPlayer().equals(player)) {
            return this;
        } else {
            return this.getNeighbour().getPlayingPit(id, player);
        }
    }

    public PlayingPit getOtherSide() {
        int idOther = this.pitsPerPlayer + 1 - this.id;
        return this.getPlayingPit(idOther, this.getPlayer().getOpponent());
    }

    @Override
    public Kalaha getKalaha(Player player) {
        return this.getNeighbour().getKalaha(player);
    }

    @Override
    protected void emptyRowToKalaha() {
        int collectStones = this.emptyPitAndReturnStones();
        this.getNeighbour().passStonesToGoal(collectStones);
        this.getNeighbour().emptyRowToKalaha();
    }

    @Override
    protected void passStonesAfterMove(int stones) {
        this.addStones(1);
        if (stones > 1) {
            this.getNeighbour().passStonesAfterMove(stones-1);
        } else {
            if (this.getStones() == 1) {
                int collectStones = this.emptyPitAndReturnStones() + this.getOtherSide().emptyPitAndReturnStones();
                this.getNeighbour().passStonesToGoal(collectStones);
            }
            if (this.isRowEmpty()) {
                this.getPlayingPit(1, this.getPlayer().getOpponent()).emptyRowToKalaha();
                this.getPlayer().endGame();
            } else if (this.getOtherSide().isRowEmpty()) {
                this.getPlayingPit(1, this.getPlayer()).emptyRowToKalaha();
                this.getPlayer().endGame();
            } else {
                this.getPlayer().switchTurns();
            }
        }
    }

    @Override
    protected void passStonesToGoal(int stones) {
        this.getNeighbour().passStonesToGoal(stones);
    }

    @Override
    protected boolean isEmpty() {
        if (this.getStones() <= 0) return this.getNeighbour().isEmpty();
        return false;
    }

    private int emptyPitAndReturnStones() {
        int collect = this.getStones();
        this.addStones(-collect);
        return collect;
    }

}