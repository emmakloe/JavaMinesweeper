package minesweeper;

import javax.swing.*;

public class LeaderController{
    private Leaderboard lb;

    public LeaderController(Leaderboard lb){
        this.lb = lb;
        display();
    }


    private void display() {
        if (lb.getScores().size() > 0) {
            String output = "";
            for (int i = 0; i < lb.getScores().size(); i++)
                output += (i + 1) + ") " + lb.getScores().get(i).toString() + "\n";
            JOptionPane.showMessageDialog(null, output);
        }
        else
            JOptionPane.showMessageDialog(null, "There are no scores to display");
    }
}
