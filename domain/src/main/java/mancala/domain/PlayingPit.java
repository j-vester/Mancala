package mancala.domain;

import java.lang.IllegalArgumentException;
import java.util.Arrays;
public class PlayingPit extends AbstractPit {
    private int id;
    private static final int[] NR_INIT_STONES = {4,4,4,4,4,4,0,4,4,4,4,4,4,0};
    private static final int MAX_PLAYINGPITS = 6;
    private PlayingPit otherSide;

    public PlayingPit() {
        this(NR_INIT_STONES);
    }

    public PlayingPit(int[] initialStones) {
        super(initialStones[0]);
        this.id = 1;
        initialStones = Arrays.copyOfRange(initialStones, 1, initialStones.length);
        PlayingPit nextPit = new PlayingPit(initialStones, this.id+1, this.getCurrentPlayerObject());
        this.addNeighbour(nextPit);
        this.getGoalPit(this.getCurrentPlayerObject().getIdlePlayer()).addNeighbour(this);
        int otherId = MAX_PLAYINGPITS+1-this.id;
        this.otherSide = this.getPlayingPit(otherId,this.getCurrentPlayerObject().getIdlePlayer());
        this.otherSide.otherSide = this;
    }

    public PlayingPit(int[] initialStones, int id, CurrentPlayer cp) {
        super(initialStones[0], cp);
        this.id = id;
        initialStones = Arrays.copyOfRange(initialStones, 1, initialStones.length);
        if (this.id < MAX_PLAYINGPITS) {
            PlayingPit nextPit = new PlayingPit(initialStones, this.id+1, cp);
            this.addNeighbour(nextPit);
        } else {
            GoalPit nextPit = new GoalPit(initialStones, cp);
            this.addNeighbour(nextPit);
        }
        if (this.getPlayer() == 0) {
            int otherId = MAX_PLAYINGPITS+1-this.id;
            this.otherSide = this.getPlayingPit(otherId, 1);
            this.otherSide.otherSide = this;
        }
    }

    @Override
    public boolean rowEmpty() {
        return this.getNeighbour().rowEmpty();
    }

    @Override
    public void passStonesAfterMove(int stones) {
        this.addStones(1);
        if (stones > 1) {
            this.getNeighbour().passStonesAfterMove(stones-1);
        } else {
            this.getCurrentPlayerObject().switchPlayer();
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
    public PlayingPit getPlayingPit(int id, int player) throws IllegalArgumentException {
        if (id < 1 || id > MAX_PLAYINGPITS || !this.getCurrentPlayerObject().isValidPlayer(player)) {
            throw new IllegalArgumentException("This pit does not exist in the game");
        }
        if (this.id == id && this.getPlayer() == player) {
            return this;
        } else {
            return this.getNeighbour().getPlayingPit(id, player);
        }
    }

    public PlayingPit getOtherSide() {
        return this.otherSide;
    }

    public void playPit() {
        if (this.getPlayer() == this.getCurrentPlayer() && this.getStones() > 0) {
            int playedStones = this.emptyPitAndReturnStones();
            this.getNeighbour().passStonesAfterMove(playedStones);
        }
    }

    public void emptyRowToGoalPit() {
        int collectStones = this.emptyPitAndReturnStones();
        this.getNeighbour().passStonesToGoal(collectStones);
        if (this.getNeighbour() instanceof PlayingPit) {
            PlayingPit neighbour = (PlayingPit) this.getNeighbour();
            neighbour.emptyRowToGoalPit();
        }
    }

}