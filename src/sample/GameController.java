package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

import static sample.ButtonController.*;
import static sample.ComputerController.*;
import static sample.PlayerController.*;
import static sample.PopUpController.*;
import static sample.QuadrantController.*;

public class GameController {

    /**
     * START GAME METHOD
     * Starts the game
     */
    static void startGame() {
        boolean player1Found = false;
        boolean player2Found = false;
        for (Player p : getPlayerList()) {
            if (player1Found && player2Found) {
                break;
            } else if (p.getUsername().equals(getPlayer1().getUsername())) {
                setPlayer1(p);
                player1Found = true;
            } else if (p.getUsername().equals(getPlayer2().getUsername())) {
                setPlayer2(p);
                player2Found = true;
            }
        }

        if (player1Found == false) {
            getPlayer1().setTotalGamesPlayed(0);
            getPlayer1().setTotalGamesWon(0);
            getPlayer1().setWinPercentage(0);
            getPlayerList().add(getPlayer1())
            ;
        }
        if (player2Found == false) {
            getPlayer2().setTotalGamesPlayed(0);
            getPlayer2().setTotalGamesWon(0);
            getPlayer2().setWinPercentage(0);
            getPlayerList().add(getPlayer2());
        }
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

        getPlayer1().setTotalGamesPlayed(getPlayer1().getTotalGamesPlayed() + 1);
        getPlayer2().setTotalGamesPlayed(getPlayer2().getTotalGamesPlayed() + 1);

        if (getFirstMovePlayer().equals(getPlayer2()) && getNumberOfPlayers() == 1) {
            Quadrant quadrant = randomMove();
            Object[] temp = quadrant.getPane().getChildren().toArray();
            File imageFile = new File("src/sample/images/TicTacToeSingleO.png");
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
