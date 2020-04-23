package sample;

import java.sql.Date;

/**
 * PLAYER OBJECT
 */
public class Player {

    private String username;
    private String password;
    private boolean isTurn;
    private int gamesWon;
    private int totalGamesPlayed;
    private int totalGamesWon;
    private int totalGamesLost;
    private int totalGamesTied;
    private double winPercentage;
    private Date lastDatePlayed;

    /**
     * SETTER METHODS
     */
    void setUsername(String playerName) {
        this.username = playerName;
    }

    void setPassword(String playerName) {
        this.password = password;
    }

    void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    void setTotalGamesWon(int totalGamesWon) {
        this.totalGamesWon = totalGamesWon;
    }

    void setTotalGamesLost(int totalGamesLost) { this.totalGamesLost = totalGamesLost; }

    void setTotalGamesTied(int totalGamesTied) { this.totalGamesTied = totalGamesTied; }

    void setWinPercentage(double winLossPercentage) {
        this.winPercentage = winLossPercentage;
    }

    void setLastDatePlayed(Date lastDatePlayed) {
        this.lastDatePlayed = lastDatePlayed;
    }

    void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    /**
     * GETTER METHODS
     */
    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    int getGamesWon() {
        return gamesWon;
    }

    int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    int getTotalGamesWon() { return totalGamesWon; }

    int getTotalGamesLost() { return totalGamesLost; }

    int getTotalGamesTied() { return totalGamesTied; }

    Date getLastDatePlayed() {
        return lastDatePlayed;
    }

    double getWinPercentage() {
        return totalGamesWon / totalGamesPlayed * 100;
    }

    boolean getTurn() {
        return isTurn;
    }

    @Override
    public String toString() {
        return "Name: " + getUsername();
    }

}
