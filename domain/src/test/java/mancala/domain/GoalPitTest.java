package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalPitTest {

    @Test
    public void aGoalPitStartsWith0stones() {
        GoalPit pit = new GoalPit();
        assertEquals(0,pit.getStones());
    }
}