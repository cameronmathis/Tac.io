package sample;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PLAYER OBJECT
 */
public class Player {

    private String username;
    private String password;
    private boolean isTurn;
    private int gamesWon;
    private int gamesPlayed;
    private int totalGamesWon;
    private double winPercentage;
    private Date lastDatePlayed;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * SETTER METHODS
     */
    void setUsername(String playerName) {
        this.username = playerName;
    }

    void setPassword(String playerName) {
        this.password = password;
    }

    void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    void setTotalGamesWon(int totalGamesWon) {
        this.totalGamesWon = totalGamesWon;
    }

    void setWinPercentage(double winLossPercentage) {
        this.winPercentage = winLossPercentage;
    }

    void setLastDatePlayed(Date lastDatePlayed) {
        this.lastDatePlayed = lastDatePlayed;
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

    boolean getTurn() {
        return isTurn;
    }

    int getGamesWon() {
        return gamesWon;
    }

    int getGamesPlayed() {
        return gamesPlayed;
    }

    int getTotalGamesWon() {
        return totalGamesWon;
    }

    double getWinPercentage() {
        return totalGamesWon / gamesPlayed * 100;
    }

    Date getLastDatePlayed() {
        return lastDatePlayed;
    }

    @Override
    public String toString() {
        return "Name: " + getUsername();
    }

}
