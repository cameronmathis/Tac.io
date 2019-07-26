package sample;

/**
 * PLAYER OBJECT
 */
public class Player {

    private String playerName;
    private int gamesWon;
    private boolean isTurn;

    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public String getPlayerName() {
        return playerName;
    }

    public void setGamesWon(int gamesWon) { this.gamesWon = gamesWon; }

    public int getGamesWon() { return gamesWon; }

    public void setTurn(boolean isTurn) { this.isTurn = isTurn; }

    public boolean getTurn() { return isTurn; }

    @Override
    public String toString() {
        return "Name: " + getPlayerName();
    }

}
