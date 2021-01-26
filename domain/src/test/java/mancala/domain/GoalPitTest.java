package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

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

    @Test
    public void goalPitReceivesOneStoneAfterMove() {
        PlayingPit pit = new PlayingPit();
        int expectedStones = pit.getGoalPit(pit.getPlayer()).getStones() + 1;
        pit.getPlayingPit(3, pit.getPlayer()).playPit();
        assertEquals(expectedStones, pit.getGoalPit(pit.getPlayer()).getStones());
    }

    @Test
    public void goalPitOfIdlePlayerReceivesNoStones() {
        int[] startAt = new int[14];
        startAt[0] = 14;
        PlayingPit pit = new PlayingPit(startAt);
        GoalPit idleGoal = pit.getGoalPit(pit.getCurrentPlayerObject().getIdlePlayer());
        int expectedStones = idleGoal.getStones();
        pit.playPit();
        assertEquals(expectedStones, idleGoal.getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitStonesMoveToGoalPit() {
        int[] startAt = new int[14];
        startAt[0] = 1;
        PlayingPit pit = new PlayingPit(startAt);
        pit.playPit();
        assertEquals(1, pit.getGoalPit(pit.getPlayer()).getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitOtherSideStonesMoveToGoal() {
        int[] startAt = new int[14];
        startAt[0] = 1;
        startAt[11] = 1;
        PlayingPit pit = new PlayingPit(startAt);
        pit.playPit();
        assertEquals(2, pit.getGoalPit(pit.getPlayer()).getStones());
    }

    @Test
    public void stonesMoveToGoalIfRowIsEmptied() {
        int[] startAt = new int[14];
        Arrays.fill(startAt, 0, 6, 1);
        PlayingPit pit = new PlayingPit(startAt);
        pit.emptyRowToGoalPit();
        assertEquals(6, pit.getGoalPit(pit.getPlayer()).getStones());
    }
}