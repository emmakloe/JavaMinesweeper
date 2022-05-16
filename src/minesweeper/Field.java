package minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class Field {
    private final int numberOfRows;
    private final int numberOfColumns;
    private final int numberOfMines;
    final private ArrayList<ArrayList<Tile>> board;

    public Field(Settings s) {
        numberOfMines = s.getMines();
        numberOfRows = s.getRow();
        numberOfColumns = s.getColumn();
        board = new ArrayList<ArrayList<Tile>>();
        fillTheBoard();
        Neighbors();
    }

    public Tile getTile(int x, int y) {
        return this.board.get(x).get(y);
    }

    public void resetBoard() {
        for (int x = 0; x < this.numberOfRows; x++) {
            for (int y = 0; y < this.numberOfColumns; y++) {
                    this.board.get(x).get(y).setOpen(false);
                    this.board.get(x).get(y).setIsMine(false);
                    this.board.get(x).get(y).setFlag(false);
                    this.board.get(x).get(y).setValue(0);
            }
        }
    }


    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void printBoard() { // for test
        Tile currentTile;

        for (int x = 0; x < this.numberOfRows; x++) {
            for (int y = 0; y < this.numberOfColumns; y++) {
                currentTile = this.board.get(x).get(y);
                if (currentTile.isMine()) {
                    System.out.print('x');
                }
                else System.out.print(currentTile.getValue());
            }
            System.out.print("\n");
        }
    }

    public void printOpen() { // for test
        Tile currentTile;

        for (int x = 0; x < this.numberOfRows; x++) {
            for (int y = 0; y < this.numberOfColumns; y++) {
                currentTile = this.board.get(x).get(y);
                if (currentTile.isOpen()) {
                    System.out.print('O');
                }
                else System.out.print('x');
            }
            System.out.print("\n");
        }
    }


    public void fillTheBoard() {
        for (int x = 0; x < this.numberOfRows; x++) {
            ArrayList<Tile> row = new ArrayList<Tile>();
            for (int y = 0; y < this.numberOfColumns; y++) {
                row.add(new Tile(false, false, false));
            }
            this.board.add(row);
        }
    }

    private void Neighbors() {
        Tile currentTile;

        for (int x = 0; x < this.numberOfRows; x++) {
            for (int y = 0; y < this.numberOfColumns; y++) {
                currentTile = this.board.get(x).get(y);
                // Left corner case
                if (y == 0 & x == 0) {
                    currentTile.addNeighbors(this.board.get(x).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y + 1));
                }

                // Top right corner case
                else if (y == 0 && x == this.numberOfRows - 1) {
                    currentTile.addNeighbors(this.board.get(x).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y + 1));
                }

                // Bottom Left corner case
                else if (y == this.numberOfColumns - 1 && x == 0) {
                    currentTile.addNeighbors(this.board.get(x).get(y - 1));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y - 1));
                }

                // Bottom right corner case
                else if (y == this.numberOfColumns - 1 && x == this.numberOfRows - 1) {
                    currentTile.addNeighbors(this.board.get(x).get(y - 1));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y - 1));
                }

                // Top row edge case
                else if (y == 0) {
                    currentTile.addNeighbors(this.board.get(x).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y));
                }

                // left edge case
                else if (x == 0) {
                    currentTile.addNeighbors(this.board.get(x).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x).get(y - 1));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y - 1));
                }

                // Right row case
                else if (x == this.numberOfRows - 1) {
                    currentTile.addNeighbors(this.board.get(x).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x).get(y - 1));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y - 1));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y + 1));
                }

                // Bottom row case
                else if (y == this.numberOfColumns - 1) {
                    currentTile.addNeighbors(this.board.get(x).get(y - 1));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y - 1));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y - 1));

                }

                // center cases
                else {
                    currentTile.addNeighbors(this.board.get(x + 1).get(y));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y));
                    currentTile.addNeighbors(this.board.get(x).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x).get(y - 1));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y + 1));
                    currentTile.addNeighbors(this.board.get(x + 1).get(y - 1));
                    currentTile.addNeighbors(this.board.get(x - 1).get(y - 1));
                }
            }
        }
    }

    public void placeMines(int x, int y) {
        int counter = 0;
        Random random = new Random();
        while (counter < this.numberOfMines) {
            int RandX = random.nextInt(numberOfColumns);
            int RandY = random.nextInt(numberOfRows);

            if (RandX != y && RandY == x || RandX == y && RandY != x || RandX != y && RandY != x) {
                if (!(this.board.get(RandY).get(RandX).isMine())) {
                    this.board.get(RandY).get(RandX).setIsMine(true);
                    this.board.get(RandY).get(RandX).increaseValue();
                    counter++;
                }
            }
        }
    }

    public void rightClick(Tile tile) {
        if(tile.isFlag()){
            tile.setFlag(false);
        }
        else tile.setFlag(true);
    }


    private void revealAllMines() {
        for (int x = 0; x < this.numberOfRows; x++) {
            for (int y = 0; y < this.numberOfColumns; y++) {
                if(this.board.get(x).get(y).isMine()) {
                    this.board.get(x).get(y).setOpen(true);
                }
            }
        }
    }

    public boolean hasWin() {
        int flaggedMine = 0;
        int normalTile = 0;
        for (int x = 0; x < this.numberOfRows; x++) {
            for (int y = 0; y < this.numberOfColumns; y++) {
                if(this.board.get(x).get(y).isMine() && this.board.get(x).get(y).isFlag() && !this.board.get(x).get(y).isOpen())
                    flaggedMine++;
                if(this.board.get(x).get(y).isOpen() && !this.board.get(x).get(y).isMine() && !this.board.get(x).get(y).isFlag())
                    normalTile++;
            }
        }
        if(flaggedMine == this.numberOfMines && normalTile == (this.numberOfColumns*this.numberOfRows-this.numberOfMines))
            return true;
        else return false;
    }

    public boolean hasLost() {
        int openMines = 0;
        for (int x = 0; x < this.numberOfRows; x++) {
            for (int y = 0; y < this.numberOfColumns; y++) {
                if (this.board.get(x).get(y).isMine() && !this.board.get(x).get(y).isFlag() && this.board.get(x).get(y).isOpen())
                    openMines++;
            }
        }
        if (openMines >= 1)
            return true;
        else return false;
    }


    public void doubleClick(Tile tile) {
        for (int i = 0; i < tile.getNeighbors().size(); i++) {
            tile.getNeighbors().get(i).setOpen(true);
            if(tile.getNeighbors().get(i).isMine()){
                revealAllMines();
            }
            if(tile.getNeighbors().get(i).getValue() == 0){
                for(int j = 0; j < tile.getNeighbors().get(i).getNeighbors().size(); j++)
                leftClick(tile.getNeighbors().get(i).getNeighbors().get(j));
            }
        }
    }



    public void leftClick(Tile tile) {
        if (tile.isOpen() || tile.isFlag()) {
            return;
        }
        if (tile.isMine()) {
            revealAllMines();
        }
        if (tile.getValue() > 0) {
            tile.setOpen(true);
        }
        if (tile.getValue() == 0) {
            tile.setOpen(true);
            for(int i = 0; i < tile.getNeighbors().size(); i++) {
                    leftClick(tile.getNeighbors().get(i));
            }
        }
    }
}

