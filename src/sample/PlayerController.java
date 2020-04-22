package sample;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {
    // Variables
    static Player player1 = new Player();
    static Player player2 = new Player();
    static List<Player> playerList = new ArrayList<>();
    static int numberOfPlayers;
    static Player firstMovePlayer;

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
