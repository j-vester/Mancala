package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalPitTest {

    @Test
    public void aGoalPitStartsWith0stones() {
        PlayingPit pit = new PlayingPit();
        assertEquals(0,pit.getGoalPit(pit.getPlayer()).getStones());
    }

    @Test void secondGoalPitHasInitializedPlayingPitAsNeighbour() {
        PlayingPit pit = new PlayingPit();
        GoalPit goalOther = pit.getGoalPit(pit.getCurrentPlayerObject().getIdlePlayer());
        assertTrue(pit.equals(goalOther.getNeighbour()));
    }

    @Test
    public void goalPitOfPlayer2DoesNotExist() {
        PlayingPit pit = new PlayingPit();
        assertThrows(IllegalArgumentException.class, () -> {pit.getGoalPit(2);});
    }

    @Test
    public void goalPitPlayerCorrespondsToPredecessingPlayingPits() {
        PlayingPit pit = new PlayingPit();
        GoalPit goal = pit.getGoalPit(pit.getPlayer());
        assertEquals(0, goal.getPlayer());
    }

}