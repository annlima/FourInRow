import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class Tablero extends JFrame {
    private static final String UNAVAILABLE = "U";
    private static final String AVAILABLE = "A";
    private static final String PLAYER_ONE = "X";
    private static final String PLAYER_TWO = "O";
    private boolean gameOver = false;
    boolean playAgainstComputer = false;
    private JButton[][] matrizTablero = new JButton[7][7];
    public int turn = 1, lastCol, lastRow;

    public Tablero(boolean playAgainstComputer) {
        this.playAgainstComputer = playAgainstComputer;
        setupUI();
    }

    private void setupUI() {
        Container container = getContentPane();
        container.setLayout(new GridLayout(7, 7));
        ButtonHandler handler = new ButtonHandler();

        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                String text = (row == 6) ? AVAILABLE : UNAVAILABLE;
                Color bgColor = (row == 6) ? null : Color.BLACK;

                matrizTablero[row][column] = new JButton(text);
                matrizTablero[row][column].setBackground(bgColor);
                matrizTablero[row][column].addActionListener(handler);

                container.add(matrizTablero[row][column]);
            }
        }

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void checkWinner(int row, int col, String player) {
        int count;

        // Vertical (column)
        count = 0;
        for (int i = 0; i < 7; i++) {
            count = (matrizTablero[i][col].getText().equals(player)) ? count + 1 : 0;
            if (count == 4) {
                announceWinner(player);
                return;
            }
        }

        // Horizontal (row)
        count = 0;
        for (int j = 0; j < 7; j++) {
            count = (matrizTablero[row][j].getText().equals(player)) ? count + 1 : 0;
            if (count == 4) {
                announceWinner(player);
                return;
            }
        }

        // Check diagonals
        for (int[] direction : new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}) {
            count = 0;
            for (int i = row, j = col; i >= 0 && i < 7 && j >= 0 && j < 7; i += direction[0], j += direction[1]) {
                count = (matrizTablero[i][j].getText().equals(player)) ? count + 1 : 0;
                if (count == 4) {
                    announceWinner(player);
                    return;
                }
            }
        }
    }
    private void computerMove() {
        Random rand = new Random();
        int col;
        do {
            col = rand.nextInt(7); // Random column between 0 and 6
        } while (!AVAILABLE.equals(matrizTablero[6][col].getText()));

        play(6, col);
        turn = 1; // Set it to human's turn again after the computer plays
    }


    private void announceWinner(String player) {
        String winnerMessage;
        if (PLAYER_ONE.equals(player)) {
            winnerMessage = "Player 1 has connected four";
        } else if (playAgainstComputer && PLAYER_TWO.equals(player)) {
            winnerMessage = "La computadora ha ganado";
        } else {
            winnerMessage = "Player 2 has connected four";
        }

        int result = JOptionPane.showConfirmDialog(null, winnerMessage + ". Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);  // Close the game
        }
    }


    private void restartGame() {
        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                if (row == 6) {
                    matrizTablero[row][column].setText(AVAILABLE);
                    matrizTablero[row][column].setBackground(null);
                } else {
                    matrizTablero[row][column].setText(UNAVAILABLE);
                    matrizTablero[row][column].setBackground(Color.BLACK);
                }
                matrizTablero[row][column].setEnabled(true);
            }
        }
        turn = 1;
    }

    private void play(int row, int col) {
        JButton button = matrizTablero[row][col];
        if (AVAILABLE.equals(button.getText())) {
            String playerSymbol = (turn == 1) ? PLAYER_ONE : PLAYER_TWO;
            Color playerColor = (turn == 1) ? Color.YELLOW : Color.RED;

            button.setBackground(playerColor);
            button.setText(playerSymbol);
            button.setEnabled(false);

            if (row > 0) {
                JButton aboveButton = matrizTablero[row - 1][col];
                aboveButton.setText(AVAILABLE);
                aboveButton.setBackground(null);
            }

            checkWinner(row, col, playerSymbol);

            turn = (turn == 1) ? 2 : 1;  // Change turn here, only when a valid move has been made.

            if (playAgainstComputer && turn == 2) {
                computerMove();
            }
        } else if (UNAVAILABLE.equals(button.getText())) {
            JOptionPane.showMessageDialog(null, "This cell is unavailable, please click on a button with the letter 'A'");
        }
    }


    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (!gameOver) {  // Only process button clicks if the game is not over.
                JButton clickedButton = (JButton) event.getSource();
                for (int row = 0; row < 7; row++) {
                    for (int column = 0; column < 7; column++) {
                        if (clickedButton == matrizTablero[row][column]) {
                            play(row, column);
                            return;
                        }
                    }
                }
            }
        }
    }
}
