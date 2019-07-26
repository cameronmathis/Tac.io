package sample;

import javafx.scene.layout.Pane;

public class Quadrant {

    private Pane pane;
    private boolean isMarked;
    private Player playerPlayed;

    public void setPane(Pane pane) { this.pane = pane; }

    public Pane getPane() { return pane; }

    public void setIsMarked(boolean isMarked) { this.isMarked = isMarked; }

    public boolean getIsMarked() { return isMarked; }

    public void setPlayerPlayed(Player playerPlayed) { this.playerPlayed = playerPlayed; }

    public Player getPlayerPlayed() { return playerPlayed; }
}
