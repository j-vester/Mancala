package mancala.domain;

import java.lang.IllegalArgumentException;
public class PlayingPit extends AbstractPit {
    private int id;
    private int pitsPerPlayer;

    public PlayingPit(int nrStones, int nrPits, int id, Player p, GoalPit firstpit) {
        super(nrStones, p);
        this.id = id;
        this.pitsPerPlayer = nrPits;
        if (id < nrPits) {
            this.addNeighbour(new PlayingPit(nrStones, nrPits, id+1, p, firstpit));
        } else if (p.getOpponent().isCurrentPlayer()) {
            this.addNeighbour(new GoalPit(nrStones, nrPits, p, firstpit));
        } else {
            this.addNeighbour(firstpit);
        }
    }

    // maak alleen mogelijk deze op de eerste pit uit te voeren
    @Override
    public boolean rowEmpty() {
        if (this.getStones() <= 0) return this.getNeighbour().rowEmpty();
        return false;
    }

    // Maak eigen Exception class
    @Override
    public void playPit() throws UnsupportedOperationException {
        if (!this.getPlayer().isCurrentPlayer()) throw new UnsupportedOperationException("This pit cannot be played by current player.");
        else if (this.getStones() <= 0) throw new UnsupportedOperationException("An empty pit cannot be played.");
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
    public void emptyRowToGoalPit() {
        int collectStones = this.emptyPitAndReturnStones();
        this.getNeighbour().passStonesToGoal(collectStones);
        this.getNeighbour().emptyRowToGoalPit();
    }

    @Override
    protected void passStonesAfterMove(int stones) {
        this.addStones(1);
        if (stones > 1) {
            this.getNeighbour().passStonesAfterMove(stones-1);
        } else {
            this.getPlayer().turnOver();
            if (this.getStones() == 1) {
                int collectStones = this.emptyPitAndReturnStones() + this.getOtherSide().emptyPitAndReturnStones();
                this.getNeighbour().passStonesToGoal(collectStones);
            }
        }
    }

    @Override
    protected void passStonesToGoal(int stones) {
        this.getNeighbour().passStonesToGoal(stones);
    }

    private int emptyPitAndReturnStones() {
        int collect = this.getStones();
        this.addStones(-collect);
        return collect;
    }

}