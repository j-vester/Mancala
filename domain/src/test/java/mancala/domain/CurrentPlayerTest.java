package mancala.domain;

import org.junit.jupiter.api.Test;

import jdk.jfr.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class CurrentPlayerTest {

    @Test
    public void player2IsInvalid() {
        PlayingPit pit = new PlayingPit();
        CurrentPlayer cp = pit.getCurrentPlayerObject();
        assertFalse(cp.isValidPlayer(2));
    }
}