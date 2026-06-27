import javax.swing.SwingUtilities;

/**
 * Entry point class for the Tic Tac Toe game application.
 * Initializes the GUI by launching the login frame on the Swing event dispatch thread.
 * 
 * @author Fadhil Mauludi
 */

public class Main {
    /**
     * Main method that serves as the application entry point.
     * Uses {@link javax.swing.SwingUtilities#invokeLater(Runnable)} to ensure
     * the GUI is created and displayed on the Event Dispatch Thread.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}
