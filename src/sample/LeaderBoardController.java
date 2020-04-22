package sample;

public class LeaderBoardController {
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
