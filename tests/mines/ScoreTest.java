package mines;

import minesweeper.Score;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @org.junit.jupiter.api.Test
    void getPoint() {
        Score score= new Score("Juli", 1000, 5, 5);
        assertEquals(40, score.getPoint());
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        Score score= new Score("Juli", 1000, 5, 5);
        Score score1= new Score("Emese", 100, 5, 5);
        assertFalse(score1.compareTo(score));
    }


}