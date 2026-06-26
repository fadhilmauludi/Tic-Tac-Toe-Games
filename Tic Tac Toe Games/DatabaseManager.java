import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // TODO: Sesuaikan dengan konfigurasi MySQL kamu
    private static final String URL = "jdbc:mysql://localhost:3306/game_project";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Isi password MySQL kamu di sini

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
