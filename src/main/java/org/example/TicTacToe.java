package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {

    private int size;
    private JButton buttons[][];
    private boolean turn = true;

    private TicTacToeMenu menu;

    public TicTacToe(int size, TicTacToeMenu menu) {
        this.menu = menu;
        this.size = size;
        buttons = new JButton[size][size];

        setLayout(new GridLayout(size, size));
        setTitle("Tic Tac Toe");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 48));
                buttons[i][j].addActionListener(new ButtonListener(i, j));
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonListener implements ActionListener {

        private int row;
        private int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                if (turn) {
                    button.setText("X");
                    button.setForeground(Color.RED);
                } else {
                    button.setText("O");
                    button.setForeground(Color.BLUE);
                }
                turn = !turn;
                checkForWin();
            }
        }
    }

    private void checkForWin() {
        int size = Math.min(buttons.length, 5);

        // Check rows
        for (int i = 0; i < size; i++) {
            int rowCount = 0;
            int colCount = 0;
            for (int j = 0; j < size; j++) {
                if (buttons[i][j].getText().equals("X")) {
                    rowCount++;
                }
                if (buttons[j][i].getText().equals("X")) {
                    colCount++;
                }
            }
            if (rowCount == size || colCount == size) {
                JOptionPane.showMessageDialog(this, "X wins!");
                dispose();
                menu.updateScore(false);
            }
        }

        // Check columns
        for (int i = 0; i < size; i++) {
            int rowCount = 0;
            int colCount = 0;
            for (int j = 0; j < size; j++) {
                if (buttons[i][j].getText().equals("O")) {
                    rowCount++;
                }
                if (buttons[j][i].getText().equals("O")) {
                    colCount++;
                }
            }
            if (rowCount == size || colCount == size) {
                JOptionPane.showMessageDialog(this, "O wins!");
                dispose();
                menu.updateScore(true);
            }
        }

        // Check diagonals
        int diagCount1 = 0;
        int diagCount2 = 0;
        for (int i = 0; i < size; i++) {
            if (buttons[i][i].getText().equals("X")) {
                diagCount1++;
            }
            if (buttons[i][size - i - 1].getText().equals("X")) {
                diagCount2++;
            }
        }
        if (diagCount1 == size || diagCount2 == size) {
            JOptionPane.showMessageDialog(this, "X wins!");
            dispose();
            menu.updateScore(false);
        }
        diagCount1 = 0;
        diagCount2 = 0;
        for (int i = 0; i < size; i++) {
            if (buttons[i][i].getText().equals("O")) {
                diagCount1++;
            }
            if (buttons[i][size - i - 1].getText().equals("O")) {
                diagCount2++;
            }
        }
        if (diagCount1 == size || diagCount2 == size) {
            JOptionPane.showMessageDialog(this, "O wins!");
            dispose();
            menu.updateScore(true);
        }

        // Check for draw
        int filledCount = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!buttons[i][j].getText().equals("")) {
                    filledCount++;
                }
            }
        }
        if (filledCount == size * size) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            dispose();
            menu.updateScore(true);
        }
    }
}
