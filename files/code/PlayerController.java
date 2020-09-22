package code;

import java.sql.Date;

public class PlayerController {
    // Variables
    private static Player player1 = new Player();
    private static Player player2 = new Player();
        private static int numberOfPlayers;
    private static Player firstMovePlayer;
    private static Date now = new Date(System.currentTimeMillis());

    /**
     * SETTER METHODS
     */

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

    static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    static Player getFirstMovePlayer() {
        return firstMovePlayer;
    }

}
