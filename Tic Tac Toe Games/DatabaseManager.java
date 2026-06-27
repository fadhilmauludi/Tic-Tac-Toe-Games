import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the database connection for the Tic Tac Toe game application.
 * Provides a centralized method to obtain a MySQL database connection
 * using JDBC.
 *
 * @author Fadhil Mauludi
 */

public class DatabaseManager {
    // TODO: Sesuaikan dengan konfigurasi MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/game_project";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Isi password MySQL di sini

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return a {@link Connection} object representing the database connection
     * @throws SQLException if a database access error occurs or the URL is invalid
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
