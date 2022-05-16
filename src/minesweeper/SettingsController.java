package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class SettingsController extends JFrame {
    private Settings sett;
    private JPanel panel;

    public SettingsController(Settings s){
        sett = s;
        this.setTitle("Minesweeper");
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        initialize();
    }

    public void initialize() {
        this.setBackground(Color.BLACK);
        //this.setLayout(null);
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        JButton beginner = new JButton("Beginner");
        beginner.setBounds(38, 180, 150, 50);
        beginner.setBackground(Color.RED);
        beginner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sett.setColumn(8);
                sett.setMines(10);
                sett.setRow(8);
                new MenuController(sett);
                dispose();
                //setVisible(false);
            }
        });
        JButton intermediate = new JButton("Intermediate");
        intermediate.setBounds(208, 180, 150, 50);
        intermediate.setBackground(Color.RED);
        intermediate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sett.setColumn(32);
                sett.setMines(40);
                sett.setRow(16);
                new MenuController(sett);
                dispose();
            }
        });
        JButton expert = new JButton("Expert");
        expert.setBounds(378, 180, 150, 50);
        expert.setBackground(Color.RED);
        expert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sett.setColumn(32);
                sett.setMines(99);
                sett.setRow(16);
                new MenuController(sett);
                dispose();
            }
        });
        JButton custom = new JButton("Custom");
        custom.setBounds(208, 250, 150, 50);
        custom.setBackground(Color.RED);
        custom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                String text = JOptionPane.showInputDialog("Enter the number of rows, columns, and mines, separated by spaces");
                if (text == null)
                    return;
                Scanner input = new Scanner(text);
                sett.setRow(Integer.parseInt(input.next()));
                sett.setColumn(Integer.parseInt(input.next()));
                sett.setMines(Integer.parseInt(input.next()));
                input.close();
            }
        });
        JButton menu = new JButton("Back to Menu");
        menu.setBounds(140, 500, 300, 50);
        menu.setBackground(Color.RED);
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                new MenuController(sett);
                dispose();
            }
        });



        JLabel settings = new JLabel(new ImageIcon("res/settings_big.png"));
        //minesweeper.setBounds(100, 50,300, 50);


         /*   JLabel row = new JLabel("Rows:");
            row.setBounds(120, 350, 90, 30);
            JLabel column = new JLabel("Columns:");
            column.setBounds(120, 400, 90, 30);
            JLabel mine = new JLabel("Mines:");
            mine.setBounds(120, 450, 90, 30);
            JTextField r = new JTextField();
            r.setBounds(350, 350, 90, 30);
            JTextField c = new JTextField();
            c.setBounds(350, 400, 90, 30);
            JTextField m = new JTextField();
            m.setBounds(350, 450, 90, 30);
            String x = r.getText();
            String y = c.getText();
            String z = m.getText();
            //if( x != null && y != null && z != null){
                try{
                    int rows = Integer.parseInt(x);
                    int columns = Integer.parseInt(y);
                    int mines = Integer.parseInt(z);
                    if(rows > 25 || columns > 45 || mines > 1123){
                        JOptionPane.showMessageDialog(null, "You are over the limit!");
                    }
                }
                catch(NumberFormatException n){
                    JOptionPane.showMessageDialog(null, "Numbers, please!");
                }
            //}
            panel.add(row);
            panel.add(column);
            panel.add(mine);
            panel.add(r);
            panel.add(c);
            panel.add(m);*/

        this.setTitle("Minesweeper");
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        panel.add(settings);
        settings.setBounds(40, 40,500, 90);
        panel.add(beginner);
        panel.add(intermediate);
        panel.add(expert);
        panel.add(custom);
        panel.add(menu);
        //drawCustom();

        this.setVisible(true);
    }

    private void drawCustom(){
        JLabel row = new JLabel("Rows:");
        row.setBounds(60, 300, 90, 30);
        JLabel column = new JLabel("Columns:");
        column.setBounds(60, 350, 90, 30);
        JLabel mine = new JLabel("Mines:");
        mine.setBounds(60, 400, 90, 30);
        JTextField r = new JTextField();
        r.setBounds(190, 300, 90, 30);
        JTextField c = new JTextField();
        c.setBounds(190, 350, 90, 30);
        JTextField m = new JTextField();
        m.setBounds(190, 400, 90, 30);
        String x = r.getText();
        String y = c.getText();
        String z = m.getText();
        if( x != null && y != null && z != null){
            try{
                int rows = Integer.parseInt(x);
                int columns = Integer.parseInt(y);
                int mines = Integer.parseInt(z);
            }
            catch(NumberFormatException n){
                //
            }
        }
        panel.add(row);
        panel.add(column);
        panel.add(mine);
        panel.add(r);
        panel.add(c);
        panel.add(m);
    }



}
