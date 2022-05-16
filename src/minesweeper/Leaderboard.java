package minesweeper;

import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    private ArrayList<Score> scores;

    public Leaderboard(){

            ObjectInputStream input;
            try {
                input = new ObjectInputStream(new FileInputStream("scores.dat"));
                scores = (ArrayList<Score>) input.readObject();
            } catch (IOException e) { // there is no file, just make a new ArrayList
                scores = new ArrayList<Score>(10);
            } catch (ClassNotFoundException e) {
                System.err.println("Incorrect file");
                System.exit(1);
            }
    }


    public ArrayList getScores(){
        return scores;
    }

    public void add(Score score) {

        if (scores.size() == 0)
            scores.add(score);
        else {
            int index = 0;
            for (int i = 0; i < scores.size(); i++) {
                index = i;
                if (!scores.get(i).compareTo(score)) {
                    if (i == scores.size() - 1) // this is the last iteration, so set index to be the last element
                        index++;
                    continue; // move to next iteration
                }
                else
                    break; // insert score at index i
            }
            if (index < 10)
                scores.add(index, score);
        }

        // write to the file
        writeToFile();
    }


    public void writeToFile(){
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("scores.dat"));
            output.writeObject(scores);
            output.close();
        } catch (IOException e) {
            System.err.println("Cannot open/write to the file!");
            e.printStackTrace(); // so you can see what went wrong
            System.exit(1);
        }
    }
}
