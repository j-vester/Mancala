package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    @Test
    public void currentPlayerIsSwitchedAfterMove() {
        Kalaha kalaha = new Kalaha();
        kalaha.getPlayingPit(1, kalaha.getPlayer()).playPit();
        assertFalse(kalaha.getPlayer().isCurrentPlayer());
    }

    @Test
    public void currentPlayerIsSwitchedAfterMoveEndsOnOtherSide() {
        Kalaha kalaha = new Kalaha();
        kalaha.getPlayingPit(6, kalaha.getPlayer()).playPit();
        assertFalse(kalaha.getPlayer().isCurrentPlayer());
    }

    @Test
    public void currentPlayerIsSwitchedAfterMoveEndsOnEmptyPit() {
        Kalaha kalaha = new Kalaha(1,3);
        PlayingPit pit = kalaha.getPlayingPit(1, kalaha.getPlayer());
        pit.getNeighbour().addStones(-1);
        pit.playPit();
        assertFalse(pit.getPlayer().isCurrentPlayer());
    }
    
    @Test
    public void currentPlayerIsNotSwitchedIfMoveEndedInOwnKalaha() {
        Kalaha kalaha = new Kalaha();
        kalaha.getPlayingPit(3, kalaha.getPlayer()).playPit();
        assertTrue(kalaha.getPlayer().isCurrentPlayer());
    }

    @Test
    public void lastPlayerIsNoLongerActiveAtEndOfGame() {
        Kalaha kalaha = new Kalaha(1,1);
        kalaha.getPlayingPit(1, kalaha.getPlayer()).playPit();
        assertFalse(kalaha.getPlayer().isCurrentPlayer());
    }

    @Test
    public void otherPlayerIsNoLongerActiveAtEndOfGame() {
        Kalaha kalaha = new Kalaha(1,1);
        kalaha.getPlayingPit(1, kalaha.getPlayer()).playPit();
        assertFalse(kalaha.getPlayer().getOpponent().isCurrentPlayer());
    }

    @Test
    public void lastCannotBeReactivatedOnceGameEnded() {
        Kalaha kalaha = new Kalaha(1,1);
        kalaha.getPlayingPit(1, kalaha.getPlayer()).playPit();
        kalaha.getPlayer().switchTurns();
        assertFalse(kalaha.getPlayer().isCurrentPlayer());
    }

    @Test
    public void otherPlayerCannotBeReactivatedOnceGameEnded() {
        Kalaha kalaha = new Kalaha(1,1);
        kalaha.getPlayingPit(1, kalaha.getPlayer()).playPit();
        kalaha.getPlayer().switchTurns();
        assertFalse(kalaha.getPlayer().getOpponent().isCurrentPlayer());
    }
}
