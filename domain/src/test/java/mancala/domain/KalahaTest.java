package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KalahaTest {

    @Test
    public void aKalahaStartsWith0stones() {
        Kalaha kalaha = new Kalaha();
        assertEquals(0,kalaha.getStones());
    }

    @Test void lastPlayingPitHasInitializedKalahaAsNeighbour() {
        Kalaha kalaha = new Kalaha();
        PlayingPit lastPit = kalaha.getPlayingPit(6, kalaha.getPlayer());
        assertTrue(kalaha.equals(lastPit.getNeighbour()));
    }

    @Test
    public void KalahaCannotBePlayed() {
        Kalaha kalaha = new Kalaha();
        assertThrows(MancalaException.class, () -> {kalaha.playPit();});
    }

    @Test
    public void KalahaReceivesOneStoneAfterMove() {
        Kalaha kalaha = new Kalaha();
        PlayingPit pit = kalaha.getPlayingPit(3, kalaha.getPlayer());
        pit.playPit();
        assertEquals(1, kalaha.getStones());
    }

    @Test
    public void KalahaOfIdlePlayerReceivesNoStones() {
        Kalaha kalaha = new Kalaha(3,1);
        Kalaha kalahaOther = kalaha.getKalaha(kalaha.getPlayer().getOpponent());
        kalaha.getPlayingPit(1, kalaha.getPlayer()).playPit();
        assertEquals(0, kalahaOther.getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitStonesMoveToKalaha() {
        Kalaha kalaha = new Kalaha(0,2);
        PlayingPit pit = kalaha.getPlayingPit(1, kalaha.getPlayer());
        pit.addStones(1);
        pit.playPit();
        assertEquals(1, kalaha.getStones());
    }

    @Test
    public void ifMoveEndsOnEmptyPitOtherSideStonesMoveToKalaha() {
        Kalaha kalaha = new Kalaha(0,2);
        PlayingPit pit = kalaha.getPlayingPit(1, kalaha.getPlayer());
        pit.addStones(1);
        kalaha.getPlayingPit(1, kalaha.getPlayer().getOpponent()).addStones(1);
        pit.playPit();
        assertEquals(2, kalaha.getStones());
    }

    @Test
    public void stonesMoveToKalahaIfRowIsEmptied() {
        Kalaha kalaha = new Kalaha(1,6);
        kalaha.getPlayingPit(1, kalaha.getPlayer()).emptyRowToKalaha();
        assertEquals(6, kalaha.getStones());
    }

    @Test
    public void ifRowIsEmptyAfterPlayAllStonesInOtherRowMoveToKalaha() {
        Kalaha kalaha = new Kalaha(2,2);
        kalaha.getPlayingPit(1, kalaha.getPlayer()).addStones(-2);
        kalaha.getPlayingPit(2, kalaha.getPlayer()).playPit();
        assertEquals(5, kalaha.getKalaha(kalaha.getPlayer().getOpponent()).getStones());
    }

    @Test
    public void checkIfRowOfCorrespondingPlayerIsEmptyFromKalaha() {
        Kalaha kalaha = new Kalaha(0,6);
        kalaha.getPlayingPit(1, kalaha.getPlayer().getOpponent());
        assertTrue(kalaha.isRowEmpty());
    }
}