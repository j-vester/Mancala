package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrentPlayerTest {

    @Test
    public void player2IsInvalid() {
        CurrentPlayer cp = new PlayingPit().getCurrentPlayerObject();
        assertFalse(cp.isValidPlayer(2));
    }

    @Test
    public void currentPlayerIsSwitchedAfterMove() {
        PlayingPit pit = new PlayingPit();
        int playerBefore = pit.getCurrentPlayer();
        pit.playPit();
        int playerAfter = pit.getCurrentPlayer();
        assertTrue(playerBefore != playerAfter);
    }

    @Test
    public void currentPlayerIsNotSwitchedIfMoveEndedInGoalPit() {
        PlayingPit pit = new PlayingPit();
        int playerBefore = pit.getCurrentPlayer();
        pit.getPlayingPit(3, pit.getPlayer()).playPit();
        int playerAfter = pit.getCurrentPlayer();
        assertTrue(playerBefore == playerAfter);
    }

    @Test
    public void playingAnEmptyPitDoesNotChangeTheCurrentPlayer() {
        PlayingPit pit = new PlayingPit();
        pit.playPit();
        int player = pit.getCurrentPlayer();
        pit.playPit();
        assertEquals(player, pit.getCurrentPlayer());
    }

    @Test
    public void playingAPitOfIdlePlayerDoesNotChangeTheCurrentPlayer() {
        PlayingPit pit = new PlayingPit().getOtherSide();
        int player = pit.getCurrentPlayer();
        pit.playPit();
        assertEquals(player, pit.getCurrentPlayer());
    }
}