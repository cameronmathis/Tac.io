package code;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

import static code.ButtonController.*;
import static code.ComputerOpponentController.*;
import static code.PlayerController.*;
import static code.PopUpController.*;
import static code.QuadrantController.*;

public class GameController {
    /**
     * START GAME METHOD
     * Starts the game
     */
    static void startGame() {
        setGame();
        getPauseBtn().setDisable(false);
        getUndoBtn().setDisable(false);
        getPrimaryStage().setScene(getGameScene()); //sets the scene on the stage
        getPrimaryStage().show();
    }

    /**
     * SET GAME METHOD
     * Sets the board for a new game
     */
    static void setGame() {
        if (getFirstMovePlayer() == null) {
            getPlayer1().setTurn(true);
            getPlayer2().setTurn(false);
            setFirstMovePlayer(getPlayer1());
        } else if (getFirstMovePlayer().equals(getPlayer1())) {
            getPlayer1().setTurn(false);
            getPlayer2().setTurn(true);
            setFirstMovePlayer(getPlayer2());
        } else if (getFirstMovePlayer().equals(getPlayer2())) {
            getPlayer1().setTurn(true);
            getPlayer2().setTurn(false);
            setFirstMovePlayer(getPlayer1());
        }

        hidePopUp();
        getPauseBtn().setDisable(false);
        getUndoBtn().setDisable(false);

        initializeQuadrants();

        setGameOver(false);

        if (getFirstMovePlayer().equals(getPlayer2()) && getNumberOfPlayers() == 1) {
            Quadrant quadrant = randomMove();
            Object[] temp = quadrant.getPane().getChildren().toArray();
            File imageFile = new File("files/images/TicTacToeSingleO.png");
            Image imageO = new Image(imageFile.toURI().toString());
            ImageView quadrantImageView = (ImageView) temp[0];
            quadrantImageView.setImage(imageO);
            quadrant.setIsMarked(true);
            quadrant.setPlayerPlayed(getPlayer2());
            setCurrentQuadrant(quadrant);
            getPlayer2().setTurn(false);
            getPlayer1().setTurn(true);
        }
    }
}
