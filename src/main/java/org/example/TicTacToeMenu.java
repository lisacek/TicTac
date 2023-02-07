package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeMenu extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private int wins;
    private int losses;

    private JLabel stats;

    public TicTacToeMenu() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuBar.add(menu);

        //add stats text
        stats = new JLabel("Wins: " + wins + " Losses: " + losses);
        stats.setForeground(Color.BLUE);
        stats.setFont(new Font("Arial", Font.BOLD, 20));
        add(stats, BorderLayout.NORTH);

        //create update

        menuItem = new JMenuItem("New Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        JButton startButton = new JButton("Start Game!");
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to start the Tic-Tac-Toe game
                TicTacToe game = new TicTacToe(3, TicTacToeMenu.this);
                game.setVisible(true);
            }
        });
        panel.add(startButton);

        add(panel, BorderLayout.CENTER);

        setJMenuBar(menuBar);

        setVisible(true);
    }

    public void updateScore(boolean win) {
        if (win) {
            wins++;
        } else {
            losses++;
        }
        stats.setText("Wins: " + wins + " Losses: " + losses);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Game")) {
            // Open a new Tic-Tac-Toe game
            TicTacToe game = new TicTacToe(3, this);
            game.setVisible(true);
        } else if (e.getActionCommand().equals("Exit")) {
            // Exit the program
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        TicTacToeMenu menu = new TicTacToeMenu();
    }
}