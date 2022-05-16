package minesweeper;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyButton extends JButton implements MouseListener {
    private final GameController game;
    private final int row, column;
    private boolean isOpen;
    private boolean leftPressed;
    private boolean rightPressed;
    private final Icon egy = new ImageIcon("res/egy.jpg");
    private final Icon ketto = new ImageIcon("res/ketto.jpg");
    private final Icon harom = new ImageIcon("res/harom.jpg");
    private final Icon negy = new ImageIcon("res/negy.jpg");
    private final Icon ot = new ImageIcon("res/ot.jpg");
    private final Icon hat = new ImageIcon("res/hat.jpg");
    private final Icon het = new ImageIcon("res/het.jpg");
    private final Icon nyolc = new ImageIcon("res/nyolc.jpg");
    private final Icon flag = new ImageIcon("res/jeloltakna.jpg");
    private final Icon akna = new ImageIcon("res/pirosakna.jpg");
    private final Icon ures = new ImageIcon("res/ures.jpg");
    private final Icon fedett = new ImageIcon("res/fedettmezo.jpg");

    public MyButton(int row, int column, GameController game){
        this.row = row;
        this.column = column;
        this.game = game;
        this.isOpen = false;
        addMouseListener(this);
        this.setEnabled(true);
        this.setIcon(fedett);
        this.leftPressed = false;
        this.rightPressed = false;
    }

    public void resetButton(){
        this.isOpen = false;
        this.setIcon(fedett);
    }


    public void reveal(){
        if(this.isOpen) return;
        if(game.getBoard().getTile(this.row, this.column).isMine() && !game.getBoard().getTile(this.row, this.column).isFlag()) {
            this.isOpen = true;
            this.setIcon(akna);
            //fedettaknat és simát is beállítani
        }
        if(!game.getBoard().getTile(this.row, this.column).isMine() && !game.getBoard().getTile(this.row, this.column).isFlag()) {
            this.isOpen = true;
            switch (this.game.getBoard().getTile(this.row, this.column).getValue()) {
                case 1:
                    this.setIcon(egy);
                    break;
                case 2:
                    this.setIcon(ketto);
                    break;
                case 3:
                    this.setIcon(harom);
                    break;
                case 4:
                    this.setIcon(negy);
                    break;
                case 5:
                    this.setIcon(ot);
                case 6:
                    this.setIcon(hat);
                    break;
                case 7:
                    this.setIcon(het);
                    break;
                case 8:
                    this.setIcon(nyolc);
                    break;
                default:
                    this.setIcon(ures);
                    break;
            }
        }
        if(this.game.getBoard().getTile(this.row,this.column).isFlag()){
            this.setIcon(flag);
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton (e))
        {
            if(this.game.isFirstClick()) {
                this.game.refreshBoard(this.row, this.column);
                this.game.setFirstClick(false);
                this.game.setFirstClick(false);
                this.game.setTimerStart(true);
                this.game.drawBottomPanel();
            }
            else{
                this.reveal();
                game.refreshBoard(this.row, this.column);
            }
        }
        if (SwingUtilities.isRightMouseButton (e)){
            this.game.getBoard().rightClick(this.game.getBoard().getTile(this.row, this.column));
            if(!this.game.getBoard().getTile(this.row,this.column).isFlag()){
                this.setIcon(fedett);
                this.game.increaseMinesLeft();
            }
            else {
                this.reveal();
                this.game.decreaseMinesLeft();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
            if(SwingUtilities.isLeftMouseButton(e)){
                leftPressed = true;
            }
            if(SwingUtilities.isRightMouseButton(e)){
                rightPressed = true;
            }
        if(leftPressed && rightPressed){
            this.game.getBoard().doubleClick(this.game.getBoard().getTile(this.row, this.column));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)){
            leftPressed = false;
        }
        if(SwingUtilities.isRightMouseButton(e)){
            rightPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
