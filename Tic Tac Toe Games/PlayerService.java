import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Service class that handles player-related database operations for the Tic Tac Toe game.
 * Provides methods for player authentication, retrieval, statistics updates,
 * and leaderboard queries.
 *
 * @author Fadhil Mauludi
 * @version 1.0
 */
public class PlayerService {

    /**
     * Authenticates a player by verifying the username and password against the database.
     *
     * @param username the player's username
     * @param password the player's password
     * @return the {@link Player} object if authentication is successful, or {@code null} if
     *         the credentials are invalid or an error occurs
     */
    // Login: cek username dan password dari database
    public Player login(String username, String password) {
        String sql = "SELECT * FROM players WHERE username = ? AND password = ?";
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");
                int score = rs.getInt("score");
                return new Player(id, uname, wins, losses, draws, score);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves the latest player data from the database by player ID.
     * Useful for refreshing player statistics after a game.
     *
     * @param id the unique identifier of the player to retrieve
     * @return the {@link Player} object matching the given ID, or {@code null} if
     *         no player is found or an error occurs
     */
    // Ambil data player terbaru dari database (untuk refresh statistik)
    public Player getPlayerById(int id) {
        String sql = "SELECT * FROM players WHERE id = ?";
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Player(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getInt("wins"),
                    rs.getInt("losses"),
                    rs.getInt("draws"),
                    rs.getInt("score")
                );
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("GetPlayer error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Updates the player's game statistics in the database after a match.
     * The score is adjusted based on the result: +10 for a win, +3 for a draw,
     * and +0 for a loss. The corresponding win/loss/draw counter is also incremented.
     *
     * @param player the {@link Player} whose statistics are to be updated
     * @param result the game result; expected values are "WIN", "LOSE", or "DRAW" (case-insensitive)
     */
    // Update statistik pemain setelah game selesai
    public void updateStatistics(Player player, String result) {
        int additionalScore = 0;
        String sql = "";

        if (result.equalsIgnoreCase("WIN")) {
            additionalScore = 10;
            sql = "UPDATE players SET wins = wins + 1, score = score + ? WHERE id = ?";
        } else if (result.equalsIgnoreCase("LOSE")) {
            additionalScore = 0;
            sql = "UPDATE players SET losses = losses + 1, score = score + ? WHERE id = ?";
        } else if (result.equalsIgnoreCase("DRAW")) {
            additionalScore = 3;
            sql = "UPDATE players SET draws = draws + 1, score = score + ? WHERE id = ?";
        }

        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, additionalScore);
            stmt.setInt(2, player.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Update statistics error: " + e.getMessage());
        }
    }

    /**
     * Retrieves the top five players sorted by highest score, with wins as a tiebreaker.
     *
     * @return an {@link ArrayList} of the top five {@link Player} objects ordered by
     *         score descending, then wins descending; returns an empty list if an error occurs
     */
    // Ambil Top 5 pemain berdasarkan score tertinggi
    public ArrayList<Player> getTopFiveScorers() {
        ArrayList<Player> list = new ArrayList<>();
        String sql = "SELECT * FROM players ORDER BY score DESC, wins DESC LIMIT 5";
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Player(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getInt("wins"),
                    rs.getInt("losses"),
                    rs.getInt("draws"),
                    rs.getInt("score")
                ));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Top 5 error: " + e.getMessage());
        }
        return list;
    }
}
