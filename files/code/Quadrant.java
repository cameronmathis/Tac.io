package code;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

public class Quadrant {
    //Variables
    private Pane pane;
    private boolean isMarked;
    private Player playerPlayed;

    /**
     * SETTER METHODS
     */
    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setIsMarked(boolean isMarked) {
        this.isMarked = isMarked;
        if (isMarked == false) {
            this.setPlayerPlayed(null);
            Object[] temp = this.getPane().getChildren().toArray();
            File imageFile = new File("images/BlankImage.png");
            Image blankImage = new Image(imageFile.toURI().toString());
            ImageView quadrantImageView = (ImageView) temp[0];
            quadrantImageView.setImage(blankImage);
        }
    }

    public void setPlayerPlayed(Player playerPlayed) {
        this.playerPlayed = playerPlayed;
    }

    /**
     * GETTER METHODS
     */
    public Pane getPane() {
        return pane;
    }

    public boolean getIsMarked() {
        return isMarked;
    }

    public Player getPlayerPlayed() {
        return playerPlayed;
    }
}
