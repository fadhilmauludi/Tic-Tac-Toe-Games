import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private PlayerService playerService;

    public LoginFrame() {
        playerService = new PlayerService();
        setTitle("Tic-Tac-Toe - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 60));

        // Panel judul
        JLabel lblTitle = new JLabel("TIC-TAC-TOE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(new Color(100, 200, 255));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Panel form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(30, 30, 60));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));

        txtUsername = new JTextField(18);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));

        txtPassword = new JPasswordField(18);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));

        btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(100, 200, 255));
        btnLogin.setForeground(new Color(30, 30, 60));
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblUsername, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblPassword, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(btnLogin, gbc);

        mainPanel.add(lblTitle, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        JLabel lblInfo = new JLabel("Default: student1 / 12345", SwingConstants.CENTER);
        lblInfo.setForeground(new Color(150, 150, 150));
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        lblInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(lblInfo, BorderLayout.SOUTH);

        add(mainPanel);

        // Event handling tombol login
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // Tekan Enter juga bisa login
        txtPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan password tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Player player = playerService.login(username, password);

        if (player != null) {
            JOptionPane.showMessageDialog(this, "Login berhasil! Selamat datang, " + player.getUsername() + "!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            MainMenuFrame menuFrame = new MainMenuFrame(player);
            menuFrame.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
        }
    }
}
