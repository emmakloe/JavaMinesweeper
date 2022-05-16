package mines;

import minesweeper.Settings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {


    @Test
    void getRow() {
        Settings s = new Settings();
        assertEquals(8, s.getRow());
    }

    @Test
    void getColumn() {
        Settings s = new Settings();
        assertEquals(8, s.getColumn());
    }

    @Test
    void getMines() {
        Settings s = new Settings();
        assertEquals(10, s.getMines());
    }
}