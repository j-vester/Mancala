package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KalahaTest {

    @Test
    public void aKalahaStartsWith0stones() {
        Kalaha goal = new Kalaha();
        assertEquals(0,goal.getStones());
    }

    @Test void lastPlayingPitHasInitializedKalahaAsNeighbour() {
        Kalaha goal = new Kalaha();
        PlayingPit lastPit = goal.getPlayingPit(6, goal.getPlayer());
        assertTrue(goal.equals(lastPit.getNeighbour()));
    }

    @Test
    public void KalahaCannotBePlayed() {
        Kalaha goal = new Kalaha();
        assertThrows(UnsupportedOperationException.class, () -> {goal.playPit();});
    }

    @Test
    public void KalahaReceivesOneStoneAfterMove() {
        Kalaha goal = new Kalaha();
        PlayingPit pit = goal.getPlayingPit(3, goal.getPlayer());
        pit.playPit();
        assertEquals(1, goal.getStones());
    }

    @Test
    public void KalahaOfIdlePlayerReceivesNoStones() {
        Kalaha goal = new Kalaha(3,1);
        Kalaha goalOther = goal.getKalaha(goal.getPlayer().getOpponent());
        goal.getPlayingPit(1, goal.getPlayer()).playPit();
        assertEquals(0, goalOther.getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitStonesMoveToKalaha() {
        Kalaha goal = new Kalaha(0,2);
        PlayingPit pit = goal.getPlayingPit(1, goal.getPlayer());
        pit.addStones(1);
        pit.playPit();
        assertEquals(1, goal.getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitOtherSideStonesMoveToGoal() {
        Kalaha goal = new Kalaha(0,2);
        PlayingPit pit = goal.getPlayingPit(1, goal.getPlayer());
        pit.addStones(1);
        goal.getPlayingPit(1, goal.getPlayer().getOpponent()).addStones(1);
        pit.playPit();
        assertEquals(2, goal.getStones());
    }

    @Test
    public void stonesMoveToGoalIfRowIsEmptied() {
        Kalaha goal = new Kalaha(1,6);
        goal.getPlayingPit(1, goal.getPlayer()).emptyRowToKalaha();
        assertEquals(6, goal.getStones());
    }
}