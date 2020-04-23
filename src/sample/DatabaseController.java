package sample;

import java.sql.*;

public class DatabaseController {
    // Variables
    private static final String DATABASE_URL = "jdbc:mysql://192.168.1.17:3306/TicTacToeDatabase";
    private static final String DATABASE_USERNAME = "TicTacToeUser";
    private static final String DATABASE_PASSWORD = "TicTacToePassword";
    private static String leaderBoard;
    private static String userField;
    private static String insertAccount = "INSERT INTO Accounts user_id, user_password, last_date_player";
    private static String importAccount = "SELECT " + userField + " FROM Accounts WHERE user_id = ";
    private static String updateAccount = "UPDATE Accounts SET last_date_played = " + new Date(System.currentTimeMillis()) + " WHERE user_id = ";
    private static String removeAccount = "DELETE FROM Accounts WHERE user_id = ";
    private static String insertToLeaderBoardDatabase = "INSERT INTO " + leaderBoard + " user_id, user_password, total_games, total_wins, total_losses, total_ties";
    private static String importFromLeaderBoardDatabase = "SELECT " + userField + " FROM " + leaderBoard + " WHERE user_id = ";
    private static String updateLeaderBoardDatabase = "SELECT " + userField + " FROM " + leaderBoard + " WHERE user_id = ";
    private static String removeFromLeaderBoardDatabase = "DELETE FROM " + leaderBoard + " WHERE user_id = ";

    /**
     * ADD ACCOUNT TO ACCOUNT DATABASE METHOD
     * Adds an account to the account database
     */
    static void insertAccountToDatabase(Player player) {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(insertAccount)) {
            preparedStatement.setString(1, player.getUsername());
            preparedStatement.setString(2, player.getPassword());
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    /**
     * IMPORT ACCOUNT FROM DATABASE METHOD
     * Imports an account from the database
     */
    static void importAccountFromDatabase(Player player) {
        String[] accountFields = new String[3];
        accountFields[0] = "user_id";
        accountFields[1] = "user_password";
        accountFields[2] = "last_date_played";

        for (int i = 0; i < 3; i++) {
            userField = accountFields[i];
            // Step 1: Establishing a Connection and
            // try-with-resource statement will auto close the connection.
            try (Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

                 // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(importAccount + player.getUsername())) {

                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                // player.setUsername(preparedStatement.executeUpdate());
            } catch (SQLException e) {
                // print SQL exception information
                printSQLException(e);
            }
        }
    }

    /**
     * UPDATE ACCOUNT IN DATABASE METHOD
     * Update an account in the database
     */
    static void updateAccountInDatabase(Player player) {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(updateAccount + player.getUsername())) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    /**
     * REMOVE PLAYER FROM ACCOUNT DATABASE METHOD
     * Removes an account from the account database
     */
    static void removeAccountFromDatabase(Player player) {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(removeAccount + player.getUsername())) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }

        String[] leaderBoards = new String[4];
        leaderBoards[0] = "HardLeaderBoard";
        leaderBoards[1] = "MediumLeaderBoard";
        leaderBoards[2] = "EasyLeaderBoard";
        leaderBoards[3] = "BeginnerLeaderBoard";
        for (int i = 0; i < 4; i++) {
            removeFromLeaderBoardDatabase(player, leaderBoards[i]);
        }
    }

    /**
     * ADD PLAYER TO LEADER BOARD METHOD
     * Adds an account to the leader board
     */
    static void insertPlayerToLeaderBoardDatabase(Player player, String table) {
        leaderBoard = table;

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(insertToLeaderBoardDatabase)) {
            preparedStatement.setString(1, player.getUsername());
            preparedStatement.setString(2, player.getPassword());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, 0);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    /**
     * IMPORT FROM LEADER BOARD METHOD
     * Imports an account info from the leader board
     */
    static void importFromLeaderBoardDatabase(Player player, String table) {
        leaderBoard = table;
        String[] accountFields = new String[6];
        accountFields[0] = "user_id";
        accountFields[1] = "user_password";
        accountFields[2] = "total_games";
        accountFields[3] = "total_wins";
        accountFields[4] = "total_losses";
        accountFields[5] = "total_ties";

        for (int i = 0; i < 6; i++) {
            userField = accountFields[i];
            // Step 1: Establishing a Connection and
            // try-with-resource statement will auto close the connection.
            try (Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

                 // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(importFromLeaderBoardDatabase + player.getUsername())) {

                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                // player.setUsername(preparedStatement.executeUpdate());
            } catch (SQLException e) {
                // print SQL exception information
                printSQLException(e);
            }
        }
    }

    /**
     * UPDATE LEADER BOARD METHOD
     * Updates the leader board after each game
     */
    static void updateLeaderBoardDatabase(Player player, String table, String field) {
        leaderBoard = table;
        userField = field;

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(updateLeaderBoardDatabase + player.getUsername())) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    /**
     * REMOVE FROM LEADER BOARD METHOD
     * Removes an account from the leaderboard
     */
    static void removeFromLeaderBoardDatabase(Player player, String table) {
        leaderBoard = table;

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(removeFromLeaderBoardDatabase + player.getUsername())) {

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
