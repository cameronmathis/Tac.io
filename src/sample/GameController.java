package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

import static sample.ButtonController.*;
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
        for (Player p : playerList) {
            if (player1Found && player2Found) {
                break;
            } else if (p.getUsername().equals( player1.getUsername())) {
                player1 = p;
                player1Found = true;
            } else if (p.getUsername().equals( player2.getUsername())) {
                player2 = p;
                player2Found = true;
            }
        }

        if (player1Found == false) {
            player1.setGamesPlayed(0);
            player1.setTotalGamesWon(0);
            player1.setWinPercentage(0);
            playerList.add( player1)
            ;
        }
        if (player2Found == false) {
            player2.setGamesPlayed(0);
            player2.setTotalGamesWon(0);
            player2.setWinPercentage(0);
            playerList.add( player2);
        }
        setGame();
        pauseBtn.setDisable(false);
        undoBtn.setDisable(false);
        primaryStage.setScene(gameScene); //sets the scene on the stage
        primaryStage.show();
    }

    /**
     * SET GAME METHOD
     * Sets the board for a new game
     */
    static void setGame() {
        if (firstMovePlayer == null) {
            player1.setTurn(true);
            player2.setTurn(false);
            firstMovePlayer =  player1;
        } else if (firstMovePlayer.equals( player1)) {
            player1.setTurn(false);
            player2.setTurn(true);
            firstMovePlayer =  player2;
        } else if (firstMovePlayer.equals( player2)) {
            player1.setTurn(true);
            player2.setTurn(false);
            firstMovePlayer =  player1;
        }

        pauseBtn.setDisable(false);
        undoBtn.setDisable(false);
        wonGamePopUpShown = false;
        tiedGamePopUpShown = false;

        topLeft = new Quadrant();
        topLeft.setPane((Pane) gameScene.lookup("#topLeftPane"));
        topLeft.setIsMarked(false);
        topCenter = new Quadrant();
        topCenter.setPane((Pane) gameScene.lookup("#topCenterPane"));
        topCenter.setIsMarked(false);
        topRight = new Quadrant();
        topRight.setPane((Pane) gameScene.lookup("#topRightPane"));
        topRight.setIsMarked(false);
        centerLeft = new Quadrant();
        centerLeft.setPane((Pane) gameScene.lookup("#centerLeftPane"));
        centerLeft.setIsMarked(false);
        center = new Quadrant();
        center.setPane((Pane) gameScene.lookup("#centerPane"));
        center.setIsMarked(false);
        centerRight = new Quadrant();
        centerRight.setPane((Pane) gameScene.lookup("#centerRightPane"));
        centerRight.setIsMarked(false);
        bottomLeft = new Quadrant();
        bottomLeft.setPane((Pane) gameScene.lookup("#bottomLeftPane"));
        bottomLeft.setIsMarked(false);
        bottomCenter = new Quadrant();
        bottomCenter.setPane((Pane) gameScene.lookup("#bottomCenterPane"));
        bottomCenter.setIsMarked(false);
        bottomRight = new Quadrant();
        bottomRight.setPane((Pane) gameScene.lookup("#bottomRightPane"));
        bottomRight.setIsMarked(false);

        gameOver = false;

        player1.setGamesPlayed( player1.getGamesPlayed() + 1);
        player2.setGamesPlayed( player2.getGamesPlayed() + 1);

        if (firstMovePlayer.equals( player2) && numberOfPlayers == 1) {
            Quadrant quadrant = getQuadrantToMark(topLeft);
            Object[] temp = quadrant.getPane().getChildren().toArray();
            File imageFile = new File("src/sample/images/TicTacToeSingleO.png");
            Image imageO = new Image(imageFile.toURI().toString());
            ImageView quadrantImageView = (ImageView) temp[0];
            quadrantImageView.setImage(imageO);
            quadrant.setIsMarked(true);
            quadrant.setPlayerPlayed( player2);
            currentQuadrant = quadrant;
            player2.setTurn(false);
            player1.setTurn(true);
        }
    }
}
