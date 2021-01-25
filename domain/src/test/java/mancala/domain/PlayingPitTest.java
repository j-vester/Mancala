package mancala.domain;

import org.junit.jupiter.api.Test;

import jdk.jfr.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class PlayingPitTest {
    
    @Test
    public void aPlayingPitStartsWith4Stones() {
        PlayingPit pit = new PlayingPit();
        assertEquals(4, pit.getStones());
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
    public void sixthNeighbourOfPlayingPitIsGoalPit() {
        PlayingPit pit = new PlayingPit();
        assertTrue(pit.getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour() instanceof GoalPit);
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
    public void currentPlayerIsPlayer0AfterInitialization() {
        PlayingPit pit = new PlayingPit();
        assertTrue(pit.getPlayer() == pit.getCurrentPlayerObject().getCurrentPlayer());
    }

    @Test
    public void pitOfSecondNeigbourHasId3AndSamePlayer() {
        PlayingPit pit = new PlayingPit();
        PlayingPit secNeighbour = (PlayingPit) pit.getNeighbour().getNeighbour();
        PlayingPit pitId3Player0 = pit.getPlayingPit(3, 0);
        assertTrue(secNeighbour.equals(pitId3Player0));
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
}
