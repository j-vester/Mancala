package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayingPitTest {
    
    @Test
    public void aPlayingPitStartsWith4Stones() {
        GoalPit pit = new GoalPit();
        assertEquals(4, pit.getNeighbour().getStones());
    }

    @Test
    public void aPlayerStartsWith6PlayingPits() {
        GoalPit pit = new GoalPit();
        assertEquals(4, pit.getPlayingPit(6, pit.getPlayer()).getStones());
    }

    @Test
    public void playingPitWithId7DoesNotExist() {
        GoalPit pit = new GoalPit();
        assertThrows(IllegalArgumentException.class, () -> {pit.getPlayingPit(7, pit.getPlayer());});
    }

    @Test
    public void pitOfSecondNeigbourHasId3AndSamePlayer() {
        GoalPit pit = new GoalPit();
        PlayingPit secNeighbour = (PlayingPit) pit.getNeighbour().getNeighbour().getNeighbour();
        PlayingPit pitId3ThisSide = pit.getPlayingPit(3, pit.getPlayer().getOpponent());
        assertTrue(secNeighbour.equals(pitId3ThisSide));
    }

    @Test
    public void playerObjectsOfNeighboursAreEqual() {
        GoalPit pit = new GoalPit();
        Player p1 = pit.getNeighbour().getPlayer();
        Player p2 = pit.getNeighbour().getNeighbour().getPlayer();
        assertTrue(p1.equals(p2));
    }

    @Test
    public void otherSideOfFirstPitIsCorrectlyFound() {
        PlayingPit pit = (PlayingPit) new GoalPit().getNeighbour();
        PlayingPit os1 = pit.getOtherSide();
        PlayingPit os2 = pit.getPlayingPit(6, pit.getPlayer().getOpponent());
        assertTrue(os1.equals(os2));
    }

    @Test
    public void playingPitIsEmptyAfterPlay() {
        GoalPit goal = new GoalPit();
        PlayingPit pit = goal.getPlayingPit(1, goal.getPlayer());
        pit.playPit();
        assertEquals(0, pit.getStones());
    }

    @Test
    public void playingAPitOfIdleIsImpossible() {
        PlayingPit pit = (PlayingPit) new GoalPit().getNeighbour();
        assertThrows(UnsupportedOperationException.class, () -> {pit.playPit();});
    }

    @Test
    public void playingAnEmptyPitIsImpossible() {
        GoalPit goal = new GoalPit(0,6);
        PlayingPit pit = goal.getPlayingPit(1, goal.getPlayer());
        assertThrows(UnsupportedOperationException.class, () -> {pit.playPit();});
    }

    @Test
    public void neighbourHasOneStoneExtraAfterPlay() {
        GoalPit goal = new GoalPit();
        PlayingPit pit = goal.getPlayingPit(1, goal.getPlayer());
        pit.playPit();
        assertEquals(5, pit.getNeighbour().getStones());
    }

    @Test
    public void fifthNeighbourHasNoExtraStoneAfterFirstMove() {
        GoalPit goal = new GoalPit();
        PlayingPit pit = goal.getPlayingPit(1, goal.getPlayer());
        pit.playPit();
        assertEquals(4, pit.getPlayingPit(6, pit.getPlayer()).getStones());
    }
    
    //tests voor 1 keer rond gaan, wanneer leeg je, wanneer vul je

    @Test
    public void ifMoveEndsOnEmptyPitOfSamePlayerItIsEmptied() {
        GoalPit goal = new GoalPit(3,1);
        PlayingPit pit = goal.getPlayingPit(1, goal.getPlayer());
        pit.playPit();
        assertEquals(0, pit.getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitOtherSideIsEmptied() {
        GoalPit goal = new GoalPit(3,1);
        PlayingPit pit = goal.getPlayingPit(1, goal.getPlayer());
        pit.playPit();
        assertEquals(0, pit.getOtherSide().getStones());
    }

    @Test
    public void emptyRowIsCorrectlyAssessed() {
        GoalPit goal = new GoalPit(0,6);
        assertTrue(goal.getNeighbour().rowEmpty());
    }

    @Test
    public void nonEmptyRowIsCorrectlyAssessed() {
        GoalPit goal = new GoalPit(1,6);
        assertFalse(goal.getNeighbour().rowEmpty());
    }

    @Test
    public void rowIsEmptyAfterAllStonesMoveToGoalPit() {
        PlayingPit pit = (PlayingPit) new GoalPit(1,6).getNeighbour();
        pit.emptyRowToGoalPit();
        assertTrue(pit.rowEmpty());
    }
}
