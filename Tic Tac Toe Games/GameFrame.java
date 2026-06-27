import javax.swing.*;
import java.awt.*;

/**
 * Represents the main game window for the Tic-Tac-Toe application.
 * <p>
 * This Swing {@link JFrame} renders a 3x3 game board, handles user
 * interaction, delegates move validation and AI logic to {@link GameLogic},
 * and updates player statistics through {@link PlayerService} when a
 * game finishes.
 * </p>
 *
 * @author Fadhil Mauludi
 */

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;
    private boolean gameOver;

    /**
     * Constructs and displays the game frame for the specified player.
     * <p>
     * Sets up the header, 3x3 board grid, and bottom control buttons
     * ("Main Lagi" / restart and "Menu Utama" / back to menu).
     * </p>
     *
     * @param player the {@link Player} who is playing the current game session
     */
    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();
        this.gameOver = false;

        setTitle("Tic-Tac-Toe - Game");
        setSize(420, 520);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 60));

        // Header
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBackground(new Color(30, 30, 60));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));

        JLabel lblTitle = new JLabel("TIC-TAC-TOE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(100, 200, 255));

        lblStatus = new JLabel("Giliran kamu! (X)", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        lblStatus.setForeground(Color.WHITE);

        headerPanel.add(lblTitle);
        headerPanel.add(lblStatus);

        // Papan permainan 3x3
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setBackground(new Color(100, 200, 255));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 36));
            buttons[i].setBackground(new Color(50, 50, 90));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }

        // Tombol bawah
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        bottomPanel.setBackground(new Color(30, 30, 60));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 15, 30));

        JButton btnRestart = new JButton("Main Lagi");
        btnRestart.setFont(new Font("Arial", Font.BOLD, 13));
        btnRestart.setBackground(new Color(60, 180, 100));
        btnRestart.setForeground(Color.WHITE);
        btnRestart.setFocusPainted(false);
        btnRestart.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnMenu = new JButton("Menu Utama");
        btnMenu.setFont(new Font("Arial", Font.BOLD, 13));
        btnMenu.setBackground(new Color(60, 120, 200));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFocusPainted(false);
        btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomPanel.add(btnRestart);
        bottomPanel.add(btnMenu);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);

        btnRestart.addActionListener(e -> restartGame());
        btnMenu.addActionListener(e -> goToMenu());
    }

    /**
     * Handles the human player's move when a board cell is clicked.
     * <p>
     * Places the player's symbol ({@code 'X'}) at the given index, checks for
     * a win or draw, then triggers the computer's response move. If the game
     * is already over, the method returns immediately.
     * </p>
     *
     * @param index the board position (0–8) that the player selected
     */
    private void handlePlayerMove(int index) {
        if (gameOver) return;

        boolean moved = gameLogic.makeMove(index, 'X');
        if (!moved) {
            JOptionPane.showMessageDialog(this, "Sel sudah terisi! Pilih yang lain.", "Move Tidak Valid", JOptionPane.WARNING_MESSAGE);
            return;
        }

        buttons[index].setText("X");
        buttons[index].setForeground(new Color(100, 200, 255));

        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Komputer berpikir...");
        int compIndex = gameLogic.computerMove();
        if (compIndex >= 0) {
            buttons[compIndex].setText("O");
            buttons[compIndex].setForeground(new Color(255, 120, 120));
        }

        if (gameLogic.checkWinner('O')) {
            finishGame("LOSE");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Giliran kamu! (X)");
    }

    /**
     * Ends the current game and updates the player's statistics.
     * <p>
     * Displays a result message on the status label and in a dialog box,
     * then marks the game as over so no further moves can be made.
     * </p>
     *
     * @param result the game outcome — one of {@code "WIN"}, {@code "LOSE"}, or {@code "DRAW"}
     */
    private void finishGame(String result) {
        gameOver = true;

        playerService.updateStatistics(currentPlayer, result);

        String message;
        Color statusColor;
        if (result.equals("WIN")) {
            message = "Yeayy, Kamu MENANG! +10 poin";
            statusColor = new Color(100, 255, 100);
        } else if (result.equals("LOSE")) {
            message = "Yah, Kamu KALAH! Coba lagi!";
            statusColor = new Color(255, 100, 100);
        } else {
            message = "Usahamu tidak sia-sia, permainan SERI! +3 poin";
            statusColor = new Color(255, 200, 100);
        }

        lblStatus.setText(message);
        lblStatus.setForeground(statusColor);

        JOptionPane.showMessageDialog(this, message, "Game Selesai", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Restarts the game by resetting the board, clearing all button labels,
     * and restoring the status label to its initial state.
     */
    private void restartGame() {
        gameLogic.resetBoard();
        gameOver = false;
        for (JButton btn : buttons) {
            btn.setText("");
            btn.setForeground(Color.WHITE);
        }
        lblStatus.setText("Giliran kamu! (X)");
        lblStatus.setForeground(Color.WHITE);
    }

    /**
     * Navigates back to the main menu.
     * <p>
     * Refreshes the current player's data from the data store, opens a new
     * {@link MainMenuFrame}, and disposes of this game frame.
     * </p>
     */
    private void goToMenu() {
        PlayerService ps = new PlayerService();
        Player refreshed = ps.getPlayerById(currentPlayer.getId());
        if (refreshed == null) refreshed = currentPlayer;

        MainMenuFrame menuFrame = new MainMenuFrame(refreshed);
        menuFrame.setVisible(true);
        this.dispose();
    }
}
