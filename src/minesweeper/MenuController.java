package minesweeper;

import javax.swing.*;
import java.awt.*;

public class MenuController extends JFrame{
        private Settings s;
        private Leaderboard lb;

    public MenuController(){
        lb = new Leaderboard();
        s = new Settings();
        initialize();
    }

    public MenuController(Settings s){
        this.s = s;
        initialize();
    }

    public void initialize(){
        this.setBackground(Color.BLACK);
        JPanel panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        JButton newgame = new JButton(new ImageIcon("res/newgame.png"));
        newgame.setBounds(220, 200, 150, 50);
        newgame.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                new GameController(s, lb);
                dispose();
            }
        });
        JButton settings = new JButton(new ImageIcon("res/settings.png"));
        settings.setBounds(230, 300, 130, 50);
        settings.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                new SettingsController(s);
                dispose();
            }
        });
        JButton leaderboard = new JButton(new ImageIcon("res/leaderboard.png"));
        leaderboard.setBounds(200, 400, 190, 50);
        leaderboard.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                new LeaderController(lb);
                dispose();
            }
        });
        JLabel minesweeper = new JLabel(new ImageIcon("res/minesweeper.png"));


        this.setTitle("Minesweeper");
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        panel.add(minesweeper);
        minesweeper.setBounds(40, 40,500, 90);
        panel.add(newgame);
        panel.add(settings);
        panel.add(leaderboard);

        this.setVisible(true);
    }
}
