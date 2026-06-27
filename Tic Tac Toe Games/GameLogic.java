import java.util.ArrayList;
import java.util.Random;

/**
 * Provides the core game logic for a Tic-Tac-Toe game.
 * <p>
 * This class manages the 3x3 game board, validates moves, checks for
 * win/draw conditions, and implements a simple AI strategy for the
 * computer opponent.
 * </p>
 *
 * @author Fadhil Mauludi
 */

public class GameLogic {
    private char[] board;
    private Random random;

    /**
     * Constructs a new {@code GameLogic} instance.
     * Initializes the 3x3 board and resets all cells to empty.
     */
    public GameLogic() {
        board = new char[9];
        random = new Random();
        resetBoard();
    }

    /**
     * Resets the game board by setting all cells to the empty character ({@code ' '}).
     */
    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    /**
     * Attempts to place a symbol on the board at the specified index.
     *
     * @param index  the board position (0–8) where the symbol should be placed
     * @param symbol the character to place ({@code 'X'} or {@code 'O'})
     * @return {@code true} if the move was successfully made;
     *         {@code false} if the index is out of range or the cell is already occupied
     */
    public boolean makeMove(int index, char symbol) {
        if (index < 0 || index >= 9) return false;
        if (board[index] != ' ') return false;
        board[index] = symbol;
        return true;
    }

    /**
     * Checks whether the specified symbol has achieved a winning combination.
     * <p>
     * A win occurs when three matching symbols appear in any row, column,
     * or diagonal on the board.
     * </p>
     *
     * @param symbol the character to check for a win ({@code 'X'} or {@code 'O'})
     * @return {@code true} if the symbol has a winning combination; {@code false} otherwise
     */
    public boolean checkWinner(char symbol) {
        int[][] patterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int[] p : patterns) {
            if (board[p[0]] == symbol && board[p[1]] == symbol && board[p[2]] == symbol) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether the game has ended in a draw.
     * A draw occurs when every cell on the board is occupied and no winner exists.
     *
     * @return {@code true} if all cells are filled; {@code false} if at least one cell is empty
     */
    public boolean isDraw() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    /**
     * Calculates and executes the computer's next move using a simple AI strategy.
     * <p>
     * The strategy follows this priority order:
     * <ol>
     *   <li>Win — complete a winning combination for {@code 'O'} if possible.</li>
     *   <li>Block — prevent the player ({@code 'X'}) from winning.</li>
     *   <li>Center — take the center cell (index 4) if available.</li>
     *   <li>Random — choose a random empty cell.</li>
     * </ol>
     * </p>
     *
     * @return the board index (0–8) where {@code 'O'} was placed,
     *         or {@code -1} if no empty cell is available
     */
    public int computerMove() {
        // 1. Coba menang
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = 'O';
                if (checkWinner('O')) return i;
                board[i] = ' ';
            }
        }
        // 2. Blokir pemain
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = 'X';
                if (checkWinner('X')) {
                    board[i] = 'O';
                    return i;
                }
                board[i] = ' ';
            }
        }
        // 3. Ambil tengah jika kosong
        if (board[4] == ' ') {
            board[4] = 'O';
            return 4;
        }
        // 4. Random dari sel kosong
        ArrayList<Integer> empty = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') empty.add(i);
        }
        if (empty.isEmpty()) return -1;
        int chosen = empty.get(random.nextInt(empty.size()));
        board[chosen] = 'O';
        return chosen;
    }

    /**
     * Returns a reference to the current game board.
     *
     * @return a {@code char} array of length 9 representing the board state
     */
    public char[] getBoard() { return board; }
}
