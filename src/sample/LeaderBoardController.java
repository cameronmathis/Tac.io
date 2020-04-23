package sample;

import java.sql.*;

public class LeaderBoardController {
    /**
     * UPDATE LEADER BOARD METHOD
     * Updates the leader board file after each game
     */
    static void updateLeaderBoard(Player player) {
        if (player != null) {
            player.setTotalGamesWon(player.getTotalGamesWon() + 1);
            player.setWinPercentage(player.getTotalGamesWon() / player.getTotalGamesPlayed() * 100);
        }
    }

    private static final String DATABASE_URL = "jdbc:mysql://192.168.1.17:3306/TicTacToeDatabase";
    private static final String DATABASE_USERNAME = "TicTacToeUsername";
    private static final String DATABASE_PASSWORD = "TicTacToePassword";
    private static final String INSERT_QUERY = "INSERT INTO HardLeaderBoard user_id, user_password, total_games, total_wins, total_losses, total_ties, last_date_played";

    /**
     * ADD PLAYER TO LEADER BOARD METHOD
     * Adds a player to the leader board
     */
    static void addPlayerToLeaderBoard(Player player) {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, player.getUsername());
            preparedStatement.setString(2, player.getPassword());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, 0);
            preparedStatement.setDate(7, new Date(System.currentTimeMillis()));

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
