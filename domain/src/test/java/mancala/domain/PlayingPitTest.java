package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayingPitTest {
    
    @Test
    public void aPlayingPitStartsWith4Stones() {
        PlayingPit pit = new PlayingPit();
        assertEquals(4, pit.getStones());
    }

    @Test
    public void constructorWithInitialStonesArrayWorksProperly() {
        int[] startAt = new int[14];
        startAt[12] = 1;
        PlayingPit pit = new PlayingPit(startAt).getOtherSide();
        assertEquals(1, pit.getStones());
    }

    @Test
    public void pitOfSecondNeigbourHasId3AndSamePlayer() {
        PlayingPit pit = new PlayingPit();
        PlayingPit secNeighbour = (PlayingPit) pit.getNeighbour().getNeighbour();
        PlayingPit pitId3Player0 = pit.getPlayingPit(3, pit.getPlayer());
        assertTrue(secNeighbour.equals(pitId3Player0));
    }

    @Test
    public void playerObjectsOfNeighboursAreEqual() {
        PlayingPit pit = new PlayingPit();
        Player p1 = pit.getPlayer();
        Player p2 = pit.getNeighbour().getPlayer();
        assertTrue(p1.equals(p2));
    }

    @Test
    public void playingPitWithId7DoesNotExist() {
        PlayingPit pit = new PlayingPit();
        assertThrows(IllegalArgumentException.class, () -> {pit.getPlayingPit(7, pit.getPlayer());});
    }

    @Test
    public void otherSideOfFirstPitIsCorrectlyAssigned() {
        PlayingPit pit = new PlayingPit();
        PlayingPit os1 = pit.getOtherSide();
        PlayingPit os2 = pit.getPlayingPit(6, pit.getPlayer().getOpponent());
        assertTrue(os1.equals(os2));
    }

    @Test
    public void playingPitIsEmptyAfterPlay() {
        PlayingPit pit = new PlayingPit();
        pit.playPit();
        assertEquals(0, pit.getStones());
    }

    @Test
    public void playingAPitOfIdleIsImpossible() {
        PlayingPit pit = new PlayingPit().getOtherSide();
        assertThrows(UnsupportedOperationException.class, () -> {pit.playPit();});
    }

    @Test
    public void playingAnEmptyPitIsImpossible() {
        PlayingPit pit = new PlayingPit(new int[14]);
        assertThrows(UnsupportedOperationException.class, () -> {pit.playPit();});
    }

    @Test
    public void neighbourHasOneStoneExtraAfterPlay() {
        PlayingPit pit = new PlayingPit();
        int expectedStones = pit.getNeighbour().getStones() + 1;
        pit.playPit();
        assertEquals(expectedStones, pit.getNeighbour().getStones());
    }

    @Test
    public void fifthNeighbourHasNoExtraStoneAfterFirstMove() {
        PlayingPit pit = new PlayingPit();
        int expectedStones = pit.getPlayingPit(6, pit.getPlayer()).getStones();
        pit.playPit();
        assertEquals(expectedStones, pit.getPlayingPit(6, pit.getPlayer()).getStones());
    }
    
    @Test
    public void ifMoveEndsOnEmptyPitOfSamePlayerItIsEmptied() {
        int[] startAt = new int[14];
        startAt[0] = 1;
        PlayingPit pit = new PlayingPit(startAt);
        pit.playPit();
        assertEquals(0, pit.getNeighbour().getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitOtherSideIsEmptied() {
        int[] startAt = new int[14];
        startAt[0] = 1;
        startAt[11] = 1;
        PlayingPit pit = new PlayingPit(startAt);
        pit.playPit();
        assertEquals(0, pit.getPlayingPit(4, pit.getPlayer().getOpponent()).getStones());
    }

    @Test
    public void emptyRowIsCorrectlyAssessed() {
        int[] startAt = new int[14];
        PlayingPit pit = new PlayingPit(startAt);
        assertTrue(pit.rowEmpty());
    }

    @Test
    public void nonEmptyRowIsCorrectlyAssessed() {
        PlayingPit pit = new PlayingPit();
        assertFalse(pit.rowEmpty());
    }

    @Test
    public void rowIsEmptyAfterAllStonesMoveToGoalPit() {
        PlayingPit pit = new PlayingPit();
        pit.emptyRowToGoalPit();
        assertTrue(pit.rowEmpty());
    }
}
