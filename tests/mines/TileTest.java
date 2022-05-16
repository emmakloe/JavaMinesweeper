package mines;

import minesweeper.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {


    @Test
    void isFlag() {
        Tile tile = new Tile(false, false, false);
        assertFalse(tile.isFlag());
    }

    @Test
    void isMine() {
        Tile tile = new Tile(false, false, false);
        assertFalse(tile.isMine());
    }

    @Test
    void isOpen() {
        Tile tile = new Tile(false, false, false);
        assertFalse(tile.isOpen());
    }

    @Test
    void getValue() {
        Tile tile = new Tile(false, false, false);
        assertEquals(0, tile.getValue());
    }

}