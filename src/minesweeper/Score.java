package minesweeper;

import java.io.Serializable;

public class Score implements Serializable {
    private String name;
    private int time;
    private double point;

    public Score(String name, int mines, int columns, int rows){
        this.name = name;
        this.point = (mines / (columns*rows));
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getPoint(){
        return point;
    }

    public int getTime(){
        return time;
    }

    public boolean compareTo(Score other) {

        if (point < other.getPoint())
            return false;
        else if (point > other.getPoint())
            return true;
        else if (point == other.getPoint())
            if (time > other.getTime())
                return true;
            else if (time < other.getTime())
                return false;
        return false;
    }

    @Override
    public String toString() {

        if (time > 60) {
            return String.format("%-12s with %d minutes and %d seconds", name, time / 60, time % 60);
        }
        return String.format("%-12s with in %d seconds", name, time);
    }
}
