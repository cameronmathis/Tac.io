package sample;

import java.util.ArrayList;
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
     *
     * @param player
     * @param fields
     */
    static void createPlayer(Player player, String[] fields) {
        player.setUsername(fields[0]);
        player.setPassword(fields[1]);
        player.setGamesPlayed(Integer.parseInt(fields[2]));
        player.setTotalGamesWon(Integer.parseInt(fields[3]));
        player.setWinPercentage(Double.parseDouble(fields[4]));
    }
}
