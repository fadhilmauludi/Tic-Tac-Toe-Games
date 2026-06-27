import javax.swing.*;
import java.awt.*;

/**
 * Represents the statistics display window for a player in the Tic-Tac-Toe application.
 * Shows the player's game statistics including total score, wins, losses, draws,
 * and total games played, refreshed from the database.
 *
 * @author Fadhil Mauludi
 */
public class StatisticsFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;

    /**
     * Constructs a new StatisticsFrame for the given player.
     * Refreshes the player's data from the database and displays their
     * score, wins, losses, draws, and total games in a styled panel.
     *
     * @param player the {@link Player} whose statistics are to be displayed
     */
    public StatisticsFrame(Player player) {
        this.playerService = new PlayerService();
        // Ambil data terbaru dari database
        Player refreshed = playerService.getPlayerById(player.getId());
        this.currentPlayer = (refreshed != null) ? refreshed : player;

        setTitle("Statistik - " + currentPlayer.getUsername());
        setSize(380, 380);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 60));

        // Judul
        JLabel lblTitle = new JLabel("Statistik Saya", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(100, 200, 255));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Nama pemain
        JLabel lblName = new JLabel("Pemain: " + currentPlayer.getUsername(), SwingConstants.CENTER);
        lblName.setFont(new Font("Arial", Font.BOLD, 15));
        lblName.setForeground(new Color(220, 220, 220));

        // Panel statistik
        JPanel statsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        statsPanel.setBackground(new Color(40, 40, 80));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));

        statsPanel.add(createStatLabel("Total Score", String.valueOf(currentPlayer.getScore()), new Color(255, 215, 0)));
        statsPanel.add(createStatLabel("Menang", String.valueOf(currentPlayer.getWins()), new Color(100, 220, 100)));
        statsPanel.add(createStatLabel("Kalah", String.valueOf(currentPlayer.getLosses()), new Color(255, 100, 100)));
        statsPanel.add(createStatLabel("Seri", String.valueOf(currentPlayer.getDraws()), new Color(255, 200, 100)));

        int totalGames = currentPlayer.getWins() + currentPlayer.getLosses() + currentPlayer.getDraws();
        statsPanel.add(createStatLabel("Total Game", String.valueOf(totalGames), new Color(150, 200, 255)));

        // Tombol tutup
        JButton btnClose = new JButton("Tutup");
        btnClose.setFont(new Font("Arial", Font.BOLD, 13));
        btnClose.setBackground(new Color(60, 120, 200));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClose.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btnClose.addActionListener(e -> this.dispose());

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(30, 30, 60));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 80, 15, 80));
        btnPanel.setLayout(new BorderLayout());
        btnPanel.add(btnClose);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(new Color(30, 30, 60));
        topPanel.add(lblTitle);
        topPanel.add(lblName);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(statsPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Creates a styled panel displaying a statistic as a label-value pair.
     * The label is shown on the left and the value on the right with a custom color.
     *
     * @param label      the descriptive label for the statistic (e.g., "Menang")
     * @param value      the string representation of the statistic value
     * @param valueColor the {@link Color} used to render the value text
     * @return a {@link JPanel} containing the formatted label-value pair
     */
    private JPanel createStatLabel(String label, String value, Color valueColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(50, 50, 100));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl.setForeground(Color.WHITE);

        JLabel val = new JLabel(value, SwingConstants.RIGHT);
        val.setFont(new Font("Arial", Font.BOLD, 16));
        val.setForeground(valueColor);

        panel.add(lbl, BorderLayout.WEST);
        panel.add(val, BorderLayout.EAST);
        return panel;
    }
}
