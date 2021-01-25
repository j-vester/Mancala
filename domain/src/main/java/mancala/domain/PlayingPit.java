package mancala.domain;

import java.lang.IllegalArgumentException;
public class PlayingPit extends AbstractPit {
    private int id;
    private static final int NR_INIT_STONES = 4;
    private static final int MAX_PLAYINGPITS = 6;
    private PlayingPit otherSide;

    public PlayingPit() {
        super(NR_INIT_STONES);
        this.id = 1;
        PlayingPit nextPit = new PlayingPit(this.id+1, this.getCurrentPlayerObject());
        this.addNeighbour(nextPit);
        this.getGoalPit(this.getCurrentPlayerObject().getIdlePlayer()).addNeighbour(this);
        int otherId = MAX_PLAYINGPITS+1-this.id;
        this.otherSide = this.getPlayingPit(otherId,this.getCurrentPlayerObject().getIdlePlayer());
        this.otherSide.otherSide = this;
    }

    public PlayingPit(int id, CurrentPlayer cp) {
        super(NR_INIT_STONES, cp);
        this.id = id;
        if (this.id < MAX_PLAYINGPITS) {
            PlayingPit nextPit = new PlayingPit(this.id+1, cp);
            this.addNeighbour(nextPit);
        } else {
            GoalPit nextPit = new GoalPit(cp);
            this.addNeighbour(nextPit);
        }
        if (this.getPlayer() == 0) {
            int otherId = MAX_PLAYINGPITS+1-this.id;
            this.otherSide = this.getPlayingPit(otherId, 1);
            this.otherSide.otherSide = this;
        }
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
}