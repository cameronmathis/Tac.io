package sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerController {
    // Variables
    private static Player player1 = new Player();
    private static Player player2 = new Player();
    private static List<Player> playerList = new ArrayList<>();
    private static int numberOfPlayers;
    private static Player firstMovePlayer;

    /**
     * SETTER METHODS
     */
    static void setPlayer1(Player p) {
        player1 = p;
    }

    static void setPlayer2(Player p) {
        player2 = p;
    }

    static void setNumberOfPlayers(int n) {
        numberOfPlayers = n;
    }

    static void setFirstMovePlayer(Player p) {
        firstMovePlayer = p;
    }

    /**
     * GETTER METHODS
     */
    static Player getPlayer1() {
        return player1;
    }

    static Player getPlayer2() {
        return player2;
    }

    static List<Player> getPlayerList() {
        return playerList;
    }

    static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    static Player getFirstMovePlayer() {
        return firstMovePlayer;
    }

    /**
     * CREATE PLAYER METHOD
     * Method to create a player that is not already on the leader board
     */
    static void createPlayer(Player player, String username, String password) {
        player.setUsername(username);
        player.setPassword(password);
        player.setGamesPlayed(0);
        player.setTotalGamesWon(0);
        player.setWinPercentage(0);
        player.setLastDatePlayed(new Date());
    }
}
