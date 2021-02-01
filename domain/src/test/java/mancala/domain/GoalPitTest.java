package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalPitTest {

    @Test
    public void aGoalPitStartsWith0stones() {
        GoalPit goal = new GoalPit();
        assertEquals(0,goal.getStones());
    }

    @Test void lastPlayingPitHasInitializedGoalPitAsNeighbour() {
        GoalPit goal = new GoalPit();
        PlayingPit lastPit = goal.getPlayingPit(6, goal.getPlayer());
        assertTrue(goal.equals(lastPit.getNeighbour()));
    }

    @Test
    public void goalPitCannotBePlayed() {
        GoalPit goal = new GoalPit();
        assertThrows(UnsupportedOperationException.class, () -> {goal.playPit();});
    }

    @Test
    public void goalPitReceivesOneStoneAfterMove() {
        GoalPit goal = new GoalPit();
        PlayingPit pit = goal.getPlayingPit(3, goal.getPlayer());
        pit.playPit();
        assertEquals(1, goal.getStones());
    }

    @Test
    public void goalPitOfIdlePlayerReceivesNoStones() {
        GoalPit goal = new GoalPit(3,1);
        GoalPit goalOther = goal.getGoalPit(goal.getPlayer().getOpponent());
        goal.getPlayingPit(1, goal.getPlayer()).playPit();
        assertEquals(0, goalOther.getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitStonesMoveToGoalPit() {
        GoalPit goal = new GoalPit(0,2);
        PlayingPit pit = goal.getPlayingPit(1, goal.getPlayer());
        pit.addStones(1);
        pit.playPit();
        assertEquals(1, goal.getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitOtherSideStonesMoveToGoal() {
        GoalPit goal = new GoalPit(0,2);
        PlayingPit pit = goal.getPlayingPit(1, goal.getPlayer());
        pit.addStones(1);
        goal.getPlayingPit(1, goal.getPlayer().getOpponent()).addStones(1);
        pit.playPit();
        assertEquals(2, goal.getStones());
    }

    @Test
    public void stonesMoveToGoalIfRowIsEmptied() {
        GoalPit goal = new GoalPit(1,6);
        goal.getPlayingPit(1, goal.getPlayer()).emptyRowToGoalPit();
        assertEquals(6, goal.getStones());
    }
}