import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;
        setTitle("Tic-Tac-Toe - Main Menu");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 60));

        // Header
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBackground(new Color(30, 30, 60));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JLabel lblTitle = new JLabel("TIC-TAC-TOE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setForeground(new Color(100, 200, 255));

        JLabel lblWelcome = new JLabel("Halo, " + currentPlayer.getUsername() + "! 👤", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 14));
        lblWelcome.setForeground(new Color(200, 200, 200));

        headerPanel.add(lblTitle);
        headerPanel.add(lblWelcome);

        // Panel tombol
        JPanel btnPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        btnPanel.setBackground(new Color(30, 30, 60));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton btnStartGame = createMenuButton("🎮  Mulai Game");
        JButton btnStatistics = createMenuButton("📊  Statistik Saya");
        JButton btnTopScorers = createMenuButton("🏆  Top 5 Pemain");
        JButton btnExit = createMenuButton("🚪  Keluar");
        btnExit.setBackground(new Color(200, 80, 80));

        btnPanel.add(btnStartGame);
        btnPanel.add(btnStatistics);
        btnPanel.add(btnTopScorers);
        btnPanel.add(btnExit);

        // Info score
        JLabel lblScore = new JLabel("Score kamu: " + currentPlayer.getScore() + " poin", SwingConstants.CENTER);
        lblScore.setFont(new Font("Arial", Font.ITALIC, 12));
        lblScore.setForeground(new Color(150, 220, 150));
        lblScore.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(btnPanel, BorderLayout.CENTER);
        mainPanel.add(lblScore, BorderLayout.SOUTH);

        add(mainPanel);

        // Event handling
        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            this.dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statsFrame = new StatisticsFrame(currentPlayer);
            statsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin mau keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(60, 120, 200));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return btn;
    }
}
