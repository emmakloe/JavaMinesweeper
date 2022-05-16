package mines;

import minesweeper.Field;
import minesweeper.Settings;
import minesweeper.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {


    @Test
    void getNumberOfColumns() {
        Settings s = new Settings();
        Field f = new Field(s);
        assertEquals(8, f.getNumberOfColumns());
    }

    @Test
    void getNumberOfRows() {
        Settings s = new Settings();
        Field f = new Field(s);
        assertEquals(8, f.getNumberOfRows());
    }

    @Test
    void getNumberOfMines() {
        Settings s = new Settings();
        Field f = new Field(s);
        assertEquals(10, f.getNumberOfMines());
    }

    @Test
    void placeMines() {
        Settings s = new Settings();
        Field f = new Field(s);
        f.fillTheBoard();
        f.placeMines(0,0);

    }

    @Test
    void rightClick() {
        Settings s = new Settings();
        Field f = new Field(s);
        Tile t = new Tile(false,false,false);
        t.setFlag(true);
        f.rightClick(t);
        assertFalse(t.isFlag());
    }

    @Test
    void hasWin() {
        Settings s = new Settings();
        Field f = new Field(s);
        f.fillTheBoard();
        f.placeMines(0,0);
        for (int x = 0; x < f.getNumberOfRows(); x++) {
            for (int y = 0; y < f.getNumberOfColumns(); y++) {
                if(f.getTile(x,y).isMine())
                    f.getTile(x,y).setFlag(true);
                if(!f.getTile(x,y).isMine() && !f.getTile(x,y).isFlag())
                    f.getTile(x,y).setOpen(true);
            }
        }
        assertTrue(f.hasWin());
    }


    @Test
    void hasLost() {
        Settings s = new Settings();
        Field f = new Field(s);
        f.fillTheBoard();
        f.placeMines(0,0);
        for (int x = 0; x < f.getNumberOfRows(); x++) {
            for (int y = 0; y < f.getNumberOfColumns(); y++) {
                if(f.getTile(x,y).isMine())
                    f.getTile(x,y).setOpen(true);
            }
        }
        assertTrue(f.hasLost());
    }

}