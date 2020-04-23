package sample;

public class LeaderBoardController {
    /**
     * ADD PLAYER TO LEADER BOARD METHOD
     * Adds a player to the leader board
     */
    static void addPlayerToLeaderBoard(Player player) {
        if (player != null) {
            //
        }
    }

    /**
     * UPDATE LEADER BOARD METHOD
     * Updates the leader board file after each game
     */
    static void updateLeaderBoard(Player player) {
        if (player != null) {
            player.setTotalGamesWon(player.getTotalGamesWon() + 1);
            player.setWinPercentage(player.getTotalGamesWon() / player.getGamesPlayed() * 100);
        }
    }
}
