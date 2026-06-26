import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {
    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame() {
        playerService = new PlayerService();
        setTitle("Top 5 Pemain Terbaik");
        setSize(480, 340);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 60));

        // Judul
        JLabel lblTitle = new JLabel("🏆 Top 5 Pemain Terbaik", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(255, 215, 0));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 15, 0));

        // Tabel
        String[] columns = {"Rank", "Username", "Menang", "Kalah", "Seri", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        // Ambil data Top 5 dari database
        ArrayList<Player> topPlayers = playerService.getTopFiveScorers();
        int rank = 1;
        for (Player p : topPlayers) {
            String rankStr;
            if (rank == 1) rankStr = "🥇 1";
            else if (rank == 2) rankStr = "🥈 2";
            else if (rank == 3) rankStr = "🥉 3";
            else rankStr = "   " + rank;

            model.addRow(new Object[]{
                rankStr,
                p.getUsername(),
                p.getWins(),
                p.getLosses(),
                p.getDraws(),
                p.getScore()
            });
            rank++;
        }

        table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(60, 120, 200));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBackground(new Color(50, 50, 90));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(80, 80, 120));
        table.setSelectionBackground(new Color(100, 150, 255));
        table.setShowVerticalLines(true);

        // Center-align semua kolom
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set lebar kolom
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(65);
        table.getColumnModel().getColumn(3).setPreferredWidth(65);
        table.getColumnModel().getColumn(4).setPreferredWidth(55);
        table.getColumnModel().getColumn(5).setPreferredWidth(65);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        scrollPane.getViewport().setBackground(new Color(50, 50, 90));

        // Tombol tutup
        JButton btnClose = new JButton("Tutup");
        btnClose.setFont(new Font("Arial", Font.BOLD, 13));
        btnClose.setBackground(new Color(60, 120, 200));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClose.addActionListener(e -> this.dispose());

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(30, 30, 60));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 120, 15, 120));
        btnPanel.setLayout(new BorderLayout());
        btnPanel.add(btnClose);

        mainPanel.add(lblTitle, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
