package mancala.domain;

import java.util.Arrays;
public class GoalPit extends AbstractPit {
    
    public GoalPit(int[] initialStones, CurrentPlayer cp) {
        super(initialStones[0],cp);
        cp.switchPlayer();
        initialStones = Arrays.copyOfRange(initialStones, 1, initialStones.length);
        if (cp.getCurrentPlayer() != 0 ) { 
            PlayingPit nextPit = new PlayingPit(initialStones,1,cp);
            this.addNeighbour(nextPit);
        }
    }

    @Override
    public boolean rowEmpty() {
        return true;
    }

    @Override
    public void passStonesAfterMove(int stones) {
        if (this.getPlayer() == this.getCurrentPlayer()) {
            this.addStones(1);
            if (stones > 1) this.getNeighbour().passStonesAfterMove(stones-1);
        } else {
            this.getNeighbour().passStonesAfterMove(stones);
        }
    }

    @Override
    public void passStonesToGoal(int stones) {
        this.addStones(stones);
    }
    
    @Override
    public GoalPit getGoalPit(int player) throws IllegalArgumentException {
        if (!this.getCurrentPlayerObject().isValidPlayer(player)) {
            throw new IllegalArgumentException("This pit does not exist in the game");
        }
        if (this.getPlayer() == player){
            return this;
        } else {
            return this.getNeighbour().getGoalPit(player);
        }
    }
}  