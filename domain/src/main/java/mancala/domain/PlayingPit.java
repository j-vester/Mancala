package mancala.domain;

import java.lang.IllegalArgumentException;
public class PlayingPit extends AbstractPit {
    private int id;
    private static final int MAX_PLAYINGPITS = 6;

    public PlayingPit() {
        super(4);
        this.id = 1;
        PlayingPit nextPit = new PlayingPit(this.id+1, this.getCurrentPlayerObject());
        this.addNeighbour(nextPit);
        this.getGoalPit(this.getCurrentPlayerObject().getIdlePlayer()).addNeighbour(this);
    }

    public PlayingPit(int id, CurrentPlayer cp) {
        super(4, cp);
        this.id = id;
        if (this.id < MAX_PLAYINGPITS) {
            PlayingPit nextPit = new PlayingPit(this.id+1, cp);
            this.addNeighbour(nextPit);
        } else {
            GoalPit nextPit = new GoalPit(cp);
            this.addNeighbour(nextPit);
        }
    }

    @Override
    public AbstractPit getPlayingPit(int id, int player) throws IllegalArgumentException {
        if (id < 1 || id > MAX_PLAYINGPITS || !this.getCurrentPlayerObject().isValidPlayer(player)) {
            throw new IllegalArgumentException("This pit does not exist in the game");
        }
        if (this.id == id && this.getPlayer() == player) {
            return this;
        } else {
            return this.getNeighbour().getPlayingPit(id, player);
        }
    }
}