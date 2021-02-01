package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayingPitTest {
    
    @Test
    public void aPlayingPitStartsWith4Stones() {
        Kalaha kalaha = new Kalaha();
        assertEquals(4, kalaha.getNeighbour().getStones());
    }

    @Test
    public void aPlayerStartsWith6PlayingPits() {
        Kalaha kalaha = new Kalaha();
        assertEquals(4, kalaha.getPlayingPit(6, kalaha.getPlayer()).getStones());
    }

    @Test
    public void playingPitWithId7DoesNotExist() {
        Kalaha kalaha = new Kalaha();
        assertThrows(IllegalArgumentException.class, () -> {kalaha.getPlayingPit(7, kalaha.getPlayer());});
    }

    @Test
    public void pitOfSecondNeigbourHasId3AndSamePlayer() {
        Kalaha kalaha = new Kalaha();
        PlayingPit secNeighbour = (PlayingPit) kalaha.getNeighbour().getNeighbour().getNeighbour();
        PlayingPit pitId3ThisSide = kalaha.getPlayingPit(3, kalaha.getPlayer().getOpponent());
        assertTrue(secNeighbour.equals(pitId3ThisSide));
    }

    @Test
    public void playerObjectsOfNeighboursAreEqual() {
        Kalaha kalaha = new Kalaha();
        Player p1 = kalaha.getNeighbour().getPlayer();
        Player p2 = kalaha.getNeighbour().getNeighbour().getPlayer();
        assertTrue(p1.equals(p2));
    }

    @Test
    public void otherSideOfFirstPitIsCorrectlyFound() {
        PlayingPit pit = (PlayingPit) new Kalaha().getNeighbour();
        PlayingPit os1 = pit.getOtherSide();
        PlayingPit os2 = pit.getPlayingPit(6, pit.getPlayer().getOpponent());
        assertTrue(os1.equals(os2));
    }

    @Test
    public void playingPitIsEmptyAfterPlay() {
        Kalaha kalaha = new Kalaha();
        PlayingPit pit = kalaha.getPlayingPit(1, kalaha.getPlayer());
        pit.playPit();
        assertEquals(0, pit.getStones());
    }

    @Test
    public void playingAPitOfIdleIsImpossible() {
        PlayingPit pit = (PlayingPit) new Kalaha().getNeighbour();
        assertThrows(MancalaException.class, () -> {pit.playPit();});
    }

    @Test
    public void playingAnEmptyPitIsImpossible() {
        Kalaha kalaha = new Kalaha(0,6);
        PlayingPit pit = kalaha.getPlayingPit(1, kalaha.getPlayer());
        assertThrows(MancalaException.class, () -> {pit.playPit();});
    }

    @Test
    public void neighbourHasOneStoneExtraAfterPlay() {
        Kalaha kalaha = new Kalaha();
        PlayingPit pit = kalaha.getPlayingPit(1, kalaha.getPlayer());
        pit.playPit();
        assertEquals(5, pit.getNeighbour().getStones());
    }

    @Test
    public void fifthNeighbourHasNoExtraStoneAfterFirstMove() {
        Kalaha kalaha = new Kalaha();
        PlayingPit pit = kalaha.getPlayingPit(1, kalaha.getPlayer());
        pit.playPit();
        assertEquals(4, pit.getPlayingPit(6, pit.getPlayer()).getStones());
    }
    
    //tests voor 1 keer rond gaan, wanneer leeg je, wanneer vul je

    @Test
    public void ifMoveEndsOnEmptyPitOfSamePlayerItIsEmptied() {
        Kalaha kalaha = new Kalaha(3,1);
        PlayingPit pit = kalaha.getPlayingPit(1, kalaha.getPlayer());
        pit.playPit();
        assertEquals(0, pit.getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitOtherSideIsEmptied() {
        Kalaha kalaha = new Kalaha(3,1);
        PlayingPit pit = kalaha.getPlayingPit(1, kalaha.getPlayer());
        pit.playPit();
        assertEquals(0, pit.getOtherSide().getStones());
    }

    @Test
    public void emptyRowIsCorrectlyAssessed() {
        Kalaha kalaha = new Kalaha(0,6);
        assertTrue(kalaha.getNeighbour().isRowEmpty());
    }

    @Test
    public void emptyRowIsAlwaysAssesedFromFirstPit() {
        Kalaha kalaha = new Kalaha(0,6);
        kalaha.getPlayingPit(1, kalaha.getPlayer()).addStones(1);;
        assertFalse(kalaha.getPlayingPit(2, kalaha.getPlayer()).isRowEmpty());
    }

    @Test
    public void nonEmptyRowIsCorrectlyAssessed() {
        Kalaha kalaha = new Kalaha(1,6);
        assertFalse(kalaha.getNeighbour().isRowEmpty());
    }

    @Test
    public void rowIsEmptyAfterAllStonesMoveToKalaha() {
        PlayingPit pit = (PlayingPit) new Kalaha(1,6).getNeighbour();
        pit.emptyRowToKalaha();
        assertTrue(pit.isRowEmpty());
    }
}
