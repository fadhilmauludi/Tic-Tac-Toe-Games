import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;
    private boolean gameOver;

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

        JButton btnRestart = new JButton("🔄 Main Lagi");
        btnRestart.setFont(new Font("Arial", Font.BOLD, 13));
        btnRestart.setBackground(new Color(60, 180, 100));
        btnRestart.setForeground(Color.WHITE);
        btnRestart.setFocusPainted(false);
        btnRestart.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnMenu = new JButton("🏠 Menu Utama");
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

    private void handlePlayerMove(int index) {
        if (gameOver) return;

        // Gerakan pemain (X)
        boolean moved = gameLogic.makeMove(index, 'X');
        if (!moved) {
            JOptionPane.showMessageDialog(this, "Sel sudah terisi! Pilih yang lain.", "Move Tidak Valid", JOptionPane.WARNING_MESSAGE);
            return;
        }

        buttons[index].setText("X");
        buttons[index].setForeground(new Color(100, 200, 255));

        // Cek apakah pemain menang
        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }

        // Cek draw
        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        // Giliran komputer (O)
        lblStatus.setText("Komputer berpikir...");
        int compIndex = gameLogic.computerMove();
        if (compIndex >= 0) {
            buttons[compIndex].setText("O");
            buttons[compIndex].setForeground(new Color(255, 120, 120));
        }

        // Cek apakah komputer menang
        if (gameLogic.checkWinner('O')) {
            finishGame("LOSE");
            return;
        }

        // Cek draw lagi setelah komputer move
        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Giliran kamu! (X)");
    }

    private void finishGame(String result) {
        gameOver = true;

        // Update statistik di database
        playerService.updateStatistics(currentPlayer, result);

        String message;
        Color statusColor;
        if (result.equals("WIN")) {
            message = "🎉 Kamu MENANG! +10 poin";
            statusColor = new Color(100, 255, 100);
        } else if (result.equals("LOSE")) {
            message = "😞 Kamu KALAH! Coba lagi!";
            statusColor = new Color(255, 100, 100);
        } else {
            message = "🤝 SERI! +3 poin";
            statusColor = new Color(255, 200, 100);
        }

        lblStatus.setText(message);
        lblStatus.setForeground(statusColor);

        JOptionPane.showMessageDialog(this, message, "Game Selesai", JOptionPane.INFORMATION_MESSAGE);
    }

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

    private void goToMenu() {
        // Refresh data player dari database sebelum ke menu
        PlayerService ps = new PlayerService();
        Player refreshed = ps.getPlayerById(currentPlayer.getId());
        if (refreshed == null) refreshed = currentPlayer;

        MainMenuFrame menuFrame = new MainMenuFrame(refreshed);
        menuFrame.setVisible(true);
        this.dispose();
    }
}
