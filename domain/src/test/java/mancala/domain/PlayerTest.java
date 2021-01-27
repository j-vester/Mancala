package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    @Test
    public void currentPlayerIsSwitchedAfterMove() {
        PlayingPit pit = new PlayingPit();
        pit.playPit();
        assertFalse(pit.getPlayer().isCurrentPlayer());
    }

    @Test
    public void currentPlayerIsNotSwitchedIfMoveEndedInOwnGoalPit() {
        PlayingPit pit = new PlayingPit();
        pit.getPlayingPit(3, pit.getPlayer()).playPit();
        assertTrue(pit.getPlayer().isCurrentPlayer());
    }

    @Test
    public void currentPlayerIsNotSwitchedAfterPlayingEmptyPit() {
        PlayingPit pit = new PlayingPit(new int[14]);
        pit.playPit();
        assertTrue(pit.getPlayer().isCurrentPlayer());
    }

    @Test
    public void currentPlayerIsNotSwitchedIfWrongPitWasChosenForAMove() {
        PlayingPit pit = new PlayingPit();
        pit.getOtherSide().playPit();
        assertTrue(pit.getPlayer().isCurrentPlayer());
    }
}
