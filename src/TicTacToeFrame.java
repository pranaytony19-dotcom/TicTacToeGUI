import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {


    private TicTacToeButton[][] board =
            new TicTacToeButton[3][3];


    private String currentPlayer = "X";


    private int moveCount = 0;


    private JPanel boardPanel;
    private JPanel buttonPanel;
    private JButton quitButton;

    public TicTacToeFrame() {


        setTitle("Tic Tac Toe");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));


        ActionListener boardListener = e -> {

            TicTacToeButton clickedButton =
                    (TicTacToeButton) e.getSource();


            int row = clickedButton.getRow();
            int col = clickedButton.getCol();


            if (clickedButton.isEmpty()) {

                String playerWhoMoved = currentPlayer;


                clickedButton.setState(currentPlayer);


                moveCount++;


                if (moveCount >= 5 && checkWin(playerWhoMoved)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Player " + playerWhoMoved + " wins!",
                            "Winner",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    askToPlayAgain();
                    return;
                }


                if (moveCount >= 7 && checkTie()) {

                    if (moveCount == 9) {

                        JOptionPane.showMessageDialog(
                                this,
                                "The game is a full board tie!",
                                "Tie Game",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "The game is a tie!",
                                "Tie Game",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                    askToPlayAgain();
                    return;
                }


                if (currentPlayer.equals("X")) {
                    currentPlayer = "O";
                } else {
                    currentPlayer = "X";
                }

            } else {


                JOptionPane.showMessageDialog(
                        this,
                        "That square is already occupied.\n" +
                                "Please choose another square.",
                        "Illegal Move",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        };


        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                board[row][col] =
                        new TicTacToeButton(row, col);

                board[row][col].setFont(
                        new Font("Arial", Font.BOLD, 60)
                );


                board[row][col].addActionListener(
                        boardListener
                );

                boardPanel.add(board[row][col]);
            }
        }


        buttonPanel = new JPanel();


        quitButton = new JButton("Quit");


        quitButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to quit?",
                    "Quit Tic Tac Toe",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {

                JOptionPane.showMessageDialog(
                        this,
                        "Thanks for playing!",
                        "Goodbye",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();
                System.exit(0);
            }
        });

        buttonPanel.add(quitButton);


        add(boardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


        setLocationRelativeTo(null);


        setVisible(true);
    }



    private boolean checkWin(String player) {


        for (int row = 0; row < 3; row++) {

            if (board[row][0].getState().equals(player) &&
                    board[row][1].getState().equals(player) &&
                    board[row][2].getState().equals(player)) {

                return true;
            }
        }


        for (int col = 0; col < 3; col++) {

            if (board[0][col].getState().equals(player) &&
                    board[1][col].getState().equals(player) &&
                    board[2][col].getState().equals(player)) {

                return true;
            }
        }


        if (board[0][0].getState().equals(player) &&
                board[1][1].getState().equals(player) &&
                board[2][2].getState().equals(player)) {

            return true;
        }


        if (board[0][2].getState().equals(player) &&
                board[1][1].getState().equals(player) &&
                board[2][0].getState().equals(player)) {

            return true;
        }

        return false;
    }



    private boolean checkTie() {

        if (canStillWin("X")) {
            return false;
        }

        if (canStillWin("O")) {
            return false;
        }

        return true;
    }



    private boolean canStillWin(String player) {


        for (int row = 0; row < 3; row++) {

            if (lineCanStillWin(
                    board[row][0].getState(),
                    board[row][1].getState(),
                    board[row][2].getState(),
                    player)) {

                return true;
            }
        }


        for (int col = 0; col < 3; col++) {

            if (lineCanStillWin(
                    board[0][col].getState(),
                    board[1][col].getState(),
                    board[2][col].getState(),
                    player)) {

                return true;
            }
        }


        if (lineCanStillWin(
                board[0][0].getState(),
                board[1][1].getState(),
                board[2][2].getState(),
                player)) {

            return true;
        }


        if (lineCanStillWin(
                board[0][2].getState(),
                board[1][1].getState(),
                board[2][0].getState(),
                player)) {

            return true;
        }

        return false;
    }



    private boolean lineCanStillWin(
            String first,
            String second,
            String third,
            String player) {

        String opponent;

        if (player.equals("X")) {
            opponent = "O";
        } else {
            opponent = "X";
        }

        return !first.equals(opponent) &&
                !second.equals(opponent) &&
                !third.equals(opponent);
    }



    private void askToPlayAgain() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Would you like to play another game?",
                "Play Again?",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            resetGame();

        } else {

            dispose();
            System.exit(0);
        }
    }



    private void resetGame() {


        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                board[row][col].reset();
            }
        }


        currentPlayer = "X";


        moveCount = 0;
    }
}