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
    public void currentPlayerIsNotSwitchedIfMoveEndedInOwnKalaha() {
        Kalaha kalaha = new Kalaha();
        kalaha.getPlayingPit(3, kalaha.getPlayer()).playPit();
        assertTrue(kalaha.getPlayer().isCurrentPlayer());
    }
}
