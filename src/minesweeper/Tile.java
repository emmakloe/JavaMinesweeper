package minesweeper;

import java.util.ArrayList;

public class Tile {
    private boolean isOpen;
    private boolean isMine;
    private int value;
    private boolean isFlag;
    private ArrayList<Tile> neighbors;

    public ArrayList<Tile> getNeighbors() {
        return neighbors;
    }

    public void addNeighbors(Tile tile){
        this.neighbors.add(tile);
    }

    public boolean isFlag() {
        return isFlag;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getValue() {
        return value;
    }

    public void setFlag(boolean isFlag){
        this.isFlag = isFlag;
    }

    public void setOpen(boolean isOpen){
        this.isOpen = isOpen;
    }

    // main constructor
    public Tile(boolean isMine, boolean isOpen, ArrayList<Tile> neighbors) {
        this.isOpen = isOpen;
        this.isMine = isMine;
        this.neighbors = neighbors;
        value = 0;
    }

    // Convince constructor
    public Tile(boolean isOpen, boolean isMine, boolean isFlag) {
        this.isOpen = isOpen;
        this.isMine = isMine;
        this.isFlag = isFlag;
        this.neighbors = new ArrayList<Tile>();
        value = 0;
    }

    // makes a cell as a mine
    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }


    public void setValue(int value){
        this.value = value;
    }


    public void increaseValue(){
        for(int i = 0; i < this.neighbors.size(); i++){
            this.neighbors.get(i).value += 1;
        }
    }
}
