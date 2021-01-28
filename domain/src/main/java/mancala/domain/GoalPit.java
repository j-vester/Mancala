package mancala.domain;

import java.util.Arrays;
public class GoalPit extends AbstractPit {
    
    public GoalPit(int[] initialStones, Player p, PlayingPit firstpit) {
        super(initialStones[0],p);
        initialStones = Arrays.copyOfRange(initialStones, 1, initialStones.length);
        if (p.isCurrentPlayer()) { 
            this.addNeighbour(new PlayingPit(initialStones, 1, p.getOpponent(), firstpit));
        } else {
            this.addNeighbour(firstpit);
        }
    }

    @Override
    public boolean rowEmpty() {
        return true;
    }

    @Override
    public void passStonesAfterMove(int stones) {
        if (this.getPlayer().isCurrentPlayer()) {
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
}  