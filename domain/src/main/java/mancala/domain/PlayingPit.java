package mancala.domain;

import java.lang.IllegalArgumentException;
import java.util.Arrays;
public class PlayingPit extends AbstractPit {
    private int id;
    private static final int[] NR_INIT_STONES = {4,4,4,4,4,4,0,4,4,4,4,4,4,0};
    private static final int MAX_PLAYINGPITS = 6;

    public PlayingPit() {
        this(NR_INIT_STONES);
    }

    public PlayingPit(int[] initialStones) {
        super(initialStones[0], new Player());
        this.id = 1;
        initialStones = Arrays.copyOfRange(initialStones, 1, initialStones.length);
        this.addNeighbour(new PlayingPit(initialStones, this.id+1, this.getPlayer(), this));
    }

    public PlayingPit(int[] initialStones, int id, Player p, PlayingPit firstpit) {
        super(initialStones[0], p);
        this.id = id;
        initialStones = Arrays.copyOfRange(initialStones, 1, initialStones.length);
        if (this.id < MAX_PLAYINGPITS) {
            this.addNeighbour(new PlayingPit(initialStones, id+1, p, firstpit));
        } else {
            this.addNeighbour(new GoalPit(initialStones, p, firstpit));
        }
    }

    @Override
    public boolean rowEmpty() {
        if (this.getStones() <= 0) return this.getNeighbour().rowEmpty();
        return false;
    }

    @Override
    public void playPit() throws UnsupportedOperationException {
        if (!this.getPlayer().isCurrentPlayer()) throw new UnsupportedOperationException("This pit cannot be played by current player.");
        else if (this.getStones() <= 0) throw new UnsupportedOperationException("An empty pit cannot be played.");
        int playedStones = this.emptyPitAndReturnStones();
        this.getNeighbour().passStonesAfterMove(playedStones);
    }

    @Override
    public void passStonesAfterMove(int stones) {
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
    public void passStonesToGoal(int stones) {
        this.getNeighbour().passStonesToGoal(stones);
    }

    @Override
    public PlayingPit getPlayingPit(int id, Player player) throws IllegalArgumentException {
        if (id < 1 || id > MAX_PLAYINGPITS) {
            throw new IllegalArgumentException("This pit does not exist in the game.");
        }
        if (this.id == id && this.getPlayer().equals(player)) {
            return this;
        } else {
            return this.getNeighbour().getPlayingPit(id, player);
        }
    }

    public PlayingPit getOtherSide() {
        int idOther = MAX_PLAYINGPITS + 1 - this.id;
        return this.getPlayingPit(idOther, this.getPlayer().getOpponent());
    }

    @Override
    public void emptyRowToGoalPit() {
        int collectStones = this.emptyPitAndReturnStones();
        this.getNeighbour().passStonesToGoal(collectStones);
        this.getNeighbour().emptyRowToGoalPit();
    }

    private int emptyPitAndReturnStones() {
        int collect = this.getStones();
        this.addStones(-collect);
        return collect;
    }

}