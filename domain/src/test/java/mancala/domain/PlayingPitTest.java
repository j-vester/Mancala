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
    public void aPlayingPitHasANeighbour() {
        PlayingPit pit = new PlayingPit();
        assertNotNull(pit.getNeighbour());
    }
}
