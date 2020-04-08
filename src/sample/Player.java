package sample;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * PLAYER OBJECT
 */
public class Player {

    private String username;
    private String password;
    private int gamesWon;
    private boolean isTurn;
    private int gamesPlayed;
    private int totalGamesWon;
    private double winPercentage;
    private LocalDate lastDatePlayed;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public void setUsername(String playerName) { this.username = playerName; }

    public String getUsername() {
        return username;
    }

    public void setPassword(String playerName) { this.password = password; }

    public String getPassword() {
        return password;
    }

    public void setGamesWon(int gamesWon) { this.gamesWon = gamesWon; }

    public int getGamesWon() { return gamesWon; }

    public void setTurn(boolean isTurn) { this.isTurn = isTurn; }

    public boolean getTurn() { return isTurn; }

    public void setGamesPlayed(int gamesPlayed) { this.gamesPlayed = gamesPlayed; }

    public int getGamesPlayed() { return gamesPlayed; }

    public void setTotalGamesWon(int totalGamesWon) { this.totalGamesWon = totalGamesWon; }

    public int getTotalGamesWon() { return totalGamesWon; }

    public void setWinPercentage(double winLossPercentage) { this.winPercentage = winLossPercentage; }

    public double getWinPercentage() { return totalGamesWon/gamesPlayed * 100; }

    public void setLastDatePlayed(LocalDate lastDatePlayed) { this.lastDatePlayed = lastDatePlayed; }

    public LocalDate getLastDatePlayed() { return lastDatePlayed; }

    @Override
    public String toString() {
        return "Name: " + getUsername();
    }

}
