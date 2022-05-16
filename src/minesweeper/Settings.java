package minesweeper;

public class Settings {
    private int row;
    private int column;
    private int mines;

    public Settings(){      //default-ként beginner pálya beállítva
        row = 8;
        column = 8;
        mines = 10;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column){
        this.column = column;
    }

    public void setMines(int mines){
        this.mines = mines;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public int getMines(){
        return mines;
    }
}

