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
    public void aPlayingPitHasANeighbourPlayingPit() {
        PlayingPit pit = new PlayingPit();
        assertTrue(pit.getNeighbour() instanceof PlayingPit);
    }

    @Test
    public void aPlayingPitsNeighbourHasANeighbourPlayingPit() {
        PlayingPit pit = new PlayingPit();
        assertTrue(pit.getNeighbour().getNeighbour() instanceof PlayingPit);
    } 

    @Test
    public void pitOfSecondNeigbourHasId3AndSamePlayer() {
        PlayingPit pit = new PlayingPit();
        PlayingPit secNeighbour = (PlayingPit) pit.getNeighbour().getNeighbour();
        PlayingPit pitId3Player0 = pit.getPlayingPit(3, 0);
        assertTrue(secNeighbour.equals(pitId3Player0));
    }

    @Test
    public void sixthNeighbourOfPlayingPitIsGoalPit() {
        PlayingPit pit = new PlayingPit().getPlayingPit(6, 0);
        assertTrue(pit.getNeighbour() instanceof GoalPit);
    }

    @Test
    public void currentPlayerObjectsAreEqual() {
        PlayingPit pit = new PlayingPit();
        CurrentPlayer cp1 = pit.getCurrentPlayerObject();
        CurrentPlayer cp2 = pit.getNeighbour().getCurrentPlayerObject();
        assertTrue(cp1.equals(cp2));
    }

    @Test
    public void playerOfInitialPitIs0() {
        PlayingPit pit = new PlayingPit();
        assertEquals(0, pit.getPlayer());
    }

    @Test
    public void playerOfOtherSidePitIs1() {
        PlayingPit pit = new PlayingPit().getOtherSide();
        assertEquals(1, pit.getPlayer());
    }

    @Test
    public void playerOfNeighbouringPlayingPitsIsSame() {
        PlayingPit pit = new PlayingPit();
        PlayingPit neighbour = (PlayingPit) pit.getNeighbour();
        assertEquals(pit.getPlayer(), neighbour.getPlayer());
    }

    @Test
    public void currentPlayerIsPlayer0AfterInitialization() {
        PlayingPit pit = new PlayingPit();
        assertEquals(pit.getPlayer(), pit.getCurrentPlayer());
    }

    @Test
    public void playingPitOfPlayer2DoesNotExist() {
        PlayingPit pit = new PlayingPit();
        assertThrows(IllegalArgumentException.class, () -> {pit.getPlayingPit(1, 2);});
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
        PlayingPit os2 = pit.getPlayingPit(6, 1);
        assertTrue(os1.equals(os2));
    }

    @Test
    public void otherSideOf2ndPlayerisCorrectlyAssigned() {
        PlayingPit pit = new PlayingPit().getPlayingPit(1, 1);
        PlayingPit os1 = pit.getOtherSide();
        PlayingPit os2 = pit.getPlayingPit(6, 0);
        assertTrue(os1.equals(os2));
    }

    @Test
    public void playingPitIsEmptyAfterPlay() {
        PlayingPit pit = new PlayingPit();
        pit.playPit();
        assertEquals(0, pit.getStones());
    }

    @Test
    public void playingAPitOfIdlePlayerDoesNotChangeStonesInPit() {
        PlayingPit pit = new PlayingPit().getOtherSide();
        int expectedStones = pit.getStones();
        pit.playPit();
        assertEquals(expectedStones, pit.getStones());
    }

    @Test
    public void playingAPitOfIdlePlayerDoesNotChangeStonesInItsNeighbour() {
        PlayingPit pit = new PlayingPit().getOtherSide();
        int expectedStones = pit.getNeighbour().getStones();
        pit.playPit();
        assertEquals(expectedStones, pit.getNeighbour().getStones());
    }
    @Test
    public void playingAnEmptyPitDoesNotChangeStonesInItsNeighbour() {
        PlayingPit pit = new PlayingPit();
        pit.playPit();
        int stonesBefore = pit.getNeighbour().getStones();
        pit.playPit();
        assertEquals(stonesBefore, pit.getNeighbour().getStones());
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
    public void ifMoveEndsOnEmptyPitOfSelfItIsEmptied() {
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
        assertEquals(0, pit.getPlayingPit(4, 1).getStones());
    }

    @Test
    public void emptyRowIsCorrectlyAssessed() {
        int[] startAt = new int[14];
        PlayingPit pit = new PlayingPit(startAt);
        assertTrue(pit.rowEmpty());
    }

    @Test
    public void rowIsEmptyAfterAllStonesMoveToGoalPit() {
        PlayingPit pit = new PlayingPit();
        pit.emptyRowToGoalPit();
        assertTrue(pit.rowEmpty());
    }
}
