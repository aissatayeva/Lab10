import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            clearBoard();
            String currentPlayer = "X";
            int moveCount = 0;
            boolean gameWon = false;

            while (moveCount < ROWS * COLS && !gameWon) {
                display();
                int row = SafeInput.getRangedInt(scanner, "Enter row", 1, 3) - 1;
                int col = SafeInput.getRangedInt(scanner, "Enter column", 1, 3) - 1;

                if (!isValidMove(row, col)) {
                    System.out.println("Invalid move! Cell already taken.");
                    continue;
                }

                board[row][col] = currentPlayer;
                moveCount++;

                if (moveCount >= 5) { 
                    gameWon = isWin(currentPlayer);
                    if (gameWon) {
                        display();
                        System.out.println("Player " + currentPlayer + " wins!");
                    } else if (isTie()) {
                        display();
                        System.out.println("It's a tie!");
                        break;
                    }
                }

                currentPlayer = currentPlayer.equals("X") ? "O" : "X"; 
            }

            playAgain = SafeInput.getYNConfirm(scanner, "Play again?");
        } while (playAgain);

        scanner.close();
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("Current board:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(" " + board[i][j]);
                if (j < COLS - 1) System.out.print(" |");
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println("---|---|---");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
               (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
