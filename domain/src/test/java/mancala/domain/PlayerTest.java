package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    @Test
    public void currentPlayerIsSwitchedAfterMove() {
        GoalPit goal = new GoalPit();
        goal.getPlayingPit(1, goal.getPlayer()).playPit();
        assertFalse(goal.getPlayer().isCurrentPlayer());
    }

    @Test
    public void currentPlayerIsNotSwitchedIfMoveEndedInOwnGoalPit() {
        GoalPit goal = new GoalPit();
        goal.getPlayingPit(3, goal.getPlayer()).playPit();
        assertTrue(goal.getPlayer().isCurrentPlayer());
    }
}
