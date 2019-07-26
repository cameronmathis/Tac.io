package sample;

/**
 * PLAYER OBJECT
 */
public class Player {

    private String playerName;
    private int gamesWon;
    private double MinY;
    private double MaxX;
    private double MaxY;

    public void setPLayerName(String imageName) {
        playerName = imageName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setGamesWon(int gamesWon) { this.gamesWon = gamesWon; }

    public int getGamesWon() { return gamesWon; }

    @Override
    public String toString() {
        return "Name: " + getPlayerName();
    }

}
