package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

public class Quadrant {

    private Pane pane;
    private boolean isMarked;
    private Player playerPlayed;

    public void setPane(Pane pane) { this.pane = pane; }

    public Pane getPane() { return pane; }

    public void setIsMarked(boolean isMarked) {
        this.isMarked = isMarked;
        if (isMarked == false) {
            this.setPlayerPlayed(null);
            Object[] temp = this.getPane().getChildren().toArray();
            File imageFile = new File("src/sample/images/BlankImage.png");
            Image blankImage = new Image(imageFile.toURI().toString());
            ImageView quadrantImageView = (ImageView) temp[0];
            quadrantImageView.setImage(blankImage);
        }
    }

    public boolean getIsMarked() { return isMarked; }

    public void setPlayerPlayed(Player playerPlayed) { this.playerPlayed = playerPlayed; }

    public Player getPlayerPlayed() { return playerPlayed; }
}