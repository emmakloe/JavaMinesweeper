package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameController extends JFrame{
    private final Field board;
    private int minesLeft;
    private final ArrayList<ArrayList<MyButton>> buttons;
    private boolean firstClick;
    private boolean isFirstClick;
    private int timeElapsed = 0;
    private boolean timerStart = false;
    private Score score;
    private Leaderboard lb;

    public void setTimerStart(boolean start){
        timerStart = start;
    }


    private Timer clock;


    public Field getBoard(){
        return this.board;
    }

    public void decreaseMinesLeft(){
        this.minesLeft--;
    }

    public void increaseMinesLeft(){
        this.minesLeft++;
    }

    public void setFirstClick(boolean firstClick){
        this.firstClick = firstClick;
    }

    public GameController(Settings s, Leaderboard lb){
      board = new Field(s);
      minesLeft = board.getNumberOfMines();
      firstClick = true;
      this.lb = lb;
      buttons = new ArrayList<ArrayList<MyButton>>();
      fillButtons();
      draw();
      popUpWindow();
    }

    private void popUpWindow(){
        JOptionPane name = new JOptionPane();
        String n = JOptionPane.showInputDialog(null, "Enter your name please!",
                "Name", JOptionPane.INFORMATION_MESSAGE);
        if(n != null){
            score = new Score(n, board.getNumberOfMines(), board.getNumberOfColumns(), board.getNumberOfRows());
        }
        else{
            score = new Score("Anonymus", board.getNumberOfMines(), board.getNumberOfColumns(), board.getNumberOfRows());
        }
    }


    private void resetBoard() {
        for (int x = 0; x < this.board.getNumberOfRows(); x++) {
            for (int y = 0; y < this.board.getNumberOfColumns(); y++) {
                this.buttons.get(x).get(y).resetButton();
            }
        }
    }

    private void fillButtons() {
        for (int x = 0; x < this.board.getNumberOfRows(); x++) {
            ArrayList<MyButton> row = new ArrayList<MyButton>();
            for (int y = 0; y < this.board.getNumberOfColumns(); y++) {
                row.add(new MyButton(x, y,this));
            }
            this.buttons.add(row);
        }
    }

    private void actToClick(int x, int y){
        this.board.leftClick(this.board.getTile(x, y));
        for (int i = 0; i < this.board.getNumberOfRows(); i++) {
            for (int j = 0; j < this.board.getNumberOfColumns(); j++) {
                if (this.board.getTile(i, j).isOpen()) {
                    this.buttons.get(i).get(j).reveal();
                    this.board.leftClick(this.board.getTile(i, j));
                }
            }
        }
    }

    public void refreshBoard(int x, int y) {
        if (this.firstClick) {
            this.board.placeMines(x, y);
            actToClick(x,y);
            this.board.printBoard();
            this.board.printOpen();
            this.setFirstClick(false);
        }
        else{
            actToClick(x,y);
            }
        }


        private void drawCenterPanel () {
            JPanel centerPanel = new JPanel();
            GridLayout gl = new GridLayout(board.getNumberOfRows(), board.getNumberOfColumns(), 0, 0);
            centerPanel.setLayout(gl);
            for (int x = 0; x < this.board.getNumberOfRows(); x++) {
                for (int y = 0; y < this.board.getNumberOfColumns(); y++) {
                    JButton temp = buttons.get(x).get(y);
                    centerPanel.add(temp);
                }

            }
            centerPanel.setMaximumSize(new Dimension(board.getNumberOfRows() * 35, board.getNumberOfColumns() * 35));
            centerPanel.setPreferredSize(new Dimension(board.getNumberOfRows() * 35, board.getNumberOfColumns() * 35));
            centerPanel.setBackground(Color.BLACK);
            this.getContentPane().add(centerPanel, BorderLayout.CENTER);

        }

        public void drawBottomPanel(){
            JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(Color.BLACK);
            bottomPanel.setPreferredSize(new Dimension(board.getNumberOfRows() * 35, 60));

            JLabel timer, flag;

            timer = new JLabel();
            timer.setSize(150, 40);
            timer.setText("Time:\n 0");
            timer.setForeground(Color.WHITE);
            bottomPanel.add(timer);
            timer.setBounds(10,board.getNumberOfRows() * 38 + 110, 150, 30 );
            flag = new JLabel();
            flag.setText("Mines left:\n " + minesLeft);
            flag.setForeground(Color.WHITE);
            bottomPanel.add(flag);
            this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
            clock = new Timer(1000, new ActionListener() { // update the time and check for victory every second

                @Override
                public void actionPerformed(ActionEvent e) {
                    timeElapsed++;
                    timer.setText("Time:\n" + timeElapsed);
                    flag.setText("Mines Left:\n" + minesLeft);
                    if(board.hasWin()){
                        clock.stop();
                        score.setTime(timeElapsed);
                        lb.add(score);
                        lb.writeToFile();
                        gameOver("Congrats! You won! :-)");
                    }
                    if (board.hasLost()){
                        clock.stop();
                        gameOver("You lost! :-(");
                    }
                }
            });
            if(timerStart) {
                clock.start();
            }
        }


        public void gameOver(String message){
            timeElapsed=0;
            minesLeft= board.getNumberOfMines();
            timerStart=false;
            Object[] options = {"Play Again", "Exit"};
            int selection = JOptionPane.showOptionDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (selection == JOptionPane.YES_OPTION) {
                //
                resetBoard();
                board.resetBoard();
                firstClick = true;
                drawBottomPanel();
            } else {
                System.exit(0);
            }
        }

        private void draw () {
            this.setTitle("Minesweeper");
            this.setSize(board.getNumberOfColumns() * 38, board.getNumberOfRows() * 38 + 140);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setVisible(true);
            this.setBackground(Color.BLACK);
            drawCenterPanel();
            drawTopPanel();
            drawBottomPanel();
            this.setVisible(true);
        }

        private void drawTopPanel(){
            JPanel topPanel = new JPanel();
            JButton menu = new JButton("Menu");
            menu.setBackground(Color.RED);
            menu.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    dispose();
                    new MenuController();
                }
            });
            JButton newgame = new JButton("Restart");
            newgame.setBackground(Color.RED);
            newgame.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    resetBoard();
                    board.resetBoard();
                    timeElapsed=0;
                    clock.stop();
                    minesLeft= board.getNumberOfMines();
                    firstClick = true;
                }
            });
            topPanel.add(newgame);
            topPanel.add(menu);
            topPanel.setMaximumSize(new Dimension(board.getNumberOfRows() * 35, 80));
            topPanel.setPreferredSize(new Dimension(board.getNumberOfRows() * 35, 80));
            topPanel.setBackground(Color.BLACK);
            this.getContentPane().add(topPanel, BorderLayout.NORTH);

            this.setVisible(true);
        }

    public boolean isFirstClick() {
        return firstClick;
    }
}
