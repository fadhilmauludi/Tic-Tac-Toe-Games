/**
 * Represents a player in the Tic Tac Toe game.
 * This model class stores player information including identity,
 * game statistics (wins, losses, draws), and overall score.
 *
 * @author Fadhil Mauludi
 */
public class Player {
    private int id;
    private String username;
    private int wins;
    private int losses;
    private int draws;
    private int score;

    /**
     * Constructs a new Player with the specified attributes.
     *
     * @param id       the unique identifier of the player
     * @param username the player's username
     * @param wins     the total number of wins
     * @param losses   the total number of losses
     * @param draws    the total number of draws
     * @param score    the player's overall score
     */
    public Player(int id, String username, int wins, int losses, int draws, int score) {
        this.id = id;
        this.username = username;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.score = score;
    }

    /**
     * Returns the unique identifier of this player.
     *
     * @return the player's ID
     */
    public int getId() { return id; }

    /**
     * Returns the username of this player.
     *
     * @return the player's username
     */
    public String getUsername() { return username; }

    /**
     * Returns the total number of wins for this player.
     *
     * @return the number of wins
     */
    public int getWins() { return wins; }

    /**
     * Returns the total number of losses for this player.
     *
     * @return the number of losses
     */
    public int getLosses() { return losses; }

    /**
     * Returns the total number of draws for this player.
     *
     * @return the number of draws
     */
    public int getDraws() { return draws; }

    /**
     * Returns the overall score of this player.
     *
     * @return the player's score
     */
    public int getScore() { return score; }

    /**
     * Sets the total number of wins for this player.
     *
     * @param wins the number of wins to set
     */
    public void setWins(int wins) { this.wins = wins; }

    /**
     * Sets the total number of losses for this player.
     *
     * @param losses the number of losses to set
     */
    public void setLosses(int losses) { this.losses = losses; }

    /**
     * Sets the total number of draws for this player.
     *
     * @param draws the number of draws to set
     */
    public void setDraws(int draws) { this.draws = draws; }

    /**
     * Sets the overall score for this player.
     *
     * @param score the score to set
     */
    public void setScore(int score) { this.score = score; }
}
