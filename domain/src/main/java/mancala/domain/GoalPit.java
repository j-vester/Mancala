package mancala.domain;

public class GoalPit extends AbstractPit {
    
    public GoalPit(CurrentPlayer cp) {
        super(0,cp);
        cp.switchPlayer();
        if (cp.getCurrentPlayer() != 0 ) { 
            PlayingPit nextPit = new PlayingPit(1,cp);
            this.addNeighbour(nextPit);
        }
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

    @Override
    public void passStonesAfterMove(int stones) {
        if (this.getPlayer() == this.getCurrentPlayer()) {
            this.addStones(1);
            if (stones > 1) this.getNeighbour().passStonesAfterMove(stones-1);
        } else {
            this.getNeighbour().passStonesAfterMove(stones);
        }
    }
}  