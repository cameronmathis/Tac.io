package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.Random;

import static sample.ButtonController.*;
import static sample.PlayerController.*;
import static sample.PopUpController.*;

public class QuadrantController {
    // Variables
    private static Quadrant topLeft;
    private static Quadrant topCenter;
    private static Quadrant topRight;
    private static Quadrant centerLeft;
    private static Quadrant center;
    private static Quadrant centerRight;
    private static Quadrant bottomLeft;
    private static Quadrant bottomCenter;
    private static Quadrant bottomRight;
    private static Quadrant currentQuadrant;
    private static Quadrant previousQuadrant;
    private static Quadrant previousPreviousQuadrant;
    private static boolean gameOver;
    private static int counter;

    /**
     * SETTER METHODS
     */
    static void setTopLeft(Quadrant q) {
        topLeft = q;
    }

    static void setTopCenter(Quadrant q) {
        topCenter = q;
    }

    static void setTopRight(Quadrant q) {
        topRight = q;
    }

    static void setCenterLeft(Quadrant q) { centerLeft = q; }

    static void setCenter(Quadrant q) {
        center = q;
    }

    static void setCenterRight(Quadrant q) {
        centerRight = q;
    }

    static void setBottomLeft(Quadrant q) {
        bottomLeft = q;
    }

    static void setBottomCenter(Quadrant q) {
        bottomCenter = q;
    }

    static void setBottomRight(Quadrant q) {
        bottomRight = q;
    }

    static void setCurrentQuadrant(Quadrant q) {
        currentQuadrant = q;
    }

    static void setPreviousQuadrant(Quadrant q) {
        previousQuadrant = q;
    }

    static void setPreviousPreviousQuadrant(Quadrant q) {
        previousPreviousQuadrant = q;
    }

    static void setGameOver(boolean bool) {
        gameOver = bool;
    }


    /**
     * GETTER METHODS
     */
    static Quadrant getTopLeft() {
        return topLeft;
    }

    static Quadrant getTopCenter() {
        return topCenter;
    }

    static Quadrant getTopRight() {
        return topRight;
    }

    static Quadrant getCenterLeft() {
        return centerLeft;
    }

    static Quadrant getCenter() {
        return center;
    }

    static Quadrant getCenterRight() {
        return centerRight;
    }

    static Quadrant getBottomLeft() {
        return bottomLeft;
    }

    static Quadrant getBottomCenter() {
        return bottomCenter;
    }

    static Quadrant getBottomRight() {
        return bottomRight;
    }

    static Quadrant getCurrentQuadrant() {
        return currentQuadrant;
    }

    static Quadrant getPreviousQuadrant() {
        return previousQuadrant;
    }

    static Quadrant getPreviousPreviousQuadrant() {
        return previousPreviousQuadrant;
    }

    static boolean getGameOver() {
        return gameOver;
    }

    /**
     * INITIALIZE QUADRANTS METHOD
     * Initialize all the quadrants
     */
    static void initializeQuadrants() {
        topLeft = new Quadrant();
        topLeft.setPane((Pane) getGameScene().lookup("#topLeftPane"));
        topLeft.setIsMarked(false);
        topCenter = new Quadrant();
        topCenter.setPane((Pane) getGameScene().lookup("#topCenterPane"));
        topCenter.setIsMarked(false);
        topRight = new Quadrant();
        topRight.setPane((Pane) getGameScene().lookup("#topRightPane"));
        topRight.setIsMarked(false);
        centerLeft = new Quadrant();
        centerLeft.setPane((Pane) getGameScene().lookup("#centerLeftPane"));
        centerLeft.setIsMarked(false);
        center = new Quadrant();
        center.setPane((Pane) getGameScene().lookup("#centerPane"));
        center.setIsMarked(false);
        centerRight = new Quadrant();
        centerRight.setPane((Pane) getGameScene().lookup("#centerRightPane"));
        centerRight.setIsMarked(false);
        bottomLeft = new Quadrant();
        bottomLeft.setPane((Pane) getGameScene().lookup("#bottomLeftPane"));
        bottomLeft.setIsMarked(false);
        bottomCenter = new Quadrant();
        bottomCenter.setPane((Pane) getGameScene().lookup("#bottomCenterPane"));
        bottomCenter.setIsMarked(false);
        bottomRight = new Quadrant();
        bottomRight.setPane((Pane) getGameScene().lookup("#bottomRightPane"));
        bottomRight.setIsMarked(false);
    }

    /**
     * CHECK IF WON METHOD
     * Check if player won
     */
    static boolean checkIfWon(Quadrant quadrant, Player player) {
        if (quadrant.getPane().getId().equals("topLeftPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("topCenterPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("topRightPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("centerLeftPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("centerPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("centerRightPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("bottomLeftPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("bottomCenterPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("bottomRightPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * CHECK IF TIE METHOD
     * Check if players tied
     */
    static void checkIfTie() {
        if (topLeft.getIsMarked() && topCenter.getIsMarked() && topRight.getIsMarked() && centerLeft.getIsMarked() &&
                center.getIsMarked() && centerRight.getIsMarked() && bottomLeft.getIsMarked() && bottomCenter.getIsMarked() &&
                bottomRight.getIsMarked()) {
            getPauseBtn().setDisable(true);
            getUndoBtn().setDisable(true);
            tiePopUp();
            gameOver = true;
        }
    }

    /**
     * GET QUADRANT TO MARK METHOD
     * Find the best quadrant to mark
     */
    static Quadrant getQuadrantToMark(Quadrant quadrant) {
        previousPreviousQuadrant = previousQuadrant;
        previousQuadrant = quadrant;

        //Primary Offensive Moves
        if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
        } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        }

        //Defensive Moves
        switch (quadrant.getPane().getId()) {
            case "topLeftPane":
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "topCenterPane":
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "topRightPane":
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "centerLeftPane":
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "centerPane":
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                break;
            case "centerRightPane":
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "bottomLeftPane":
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "bottomCenterPane":
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "bottomRightPane":
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
        }

        //Secondary Offensive Moves
        if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (topCenter.getPlayerPlayed() == null && topRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topCenter;
                                case 1:
                                    return topRight;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (centerLeft.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return centerLeft;
                                case 1:
                                    return bottomLeft;
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return bottomRight;
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (topLeft.getPlayerPlayed() == null && topRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topLeft;
                                case 1:
                                    return topRight;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (center.getPlayerPlayed() == null && bottomCenter.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return bottomCenter;
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (topCenter.getPlayerPlayed() == null && topLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topCenter;
                                case 1:
                                    return topLeft;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (centerRight.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return centerRight;
                                case 1:
                                    return bottomRight;
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return bottomLeft;
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (topLeft.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return bottomLeft;
                                case 1:
                                    return topLeft;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (center.getPlayerPlayed() == null && centerRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return centerRight;
                                case 1:
                                    return center;
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            boolean case3 = true;
            while (case0 || case1 || case2 || case3) {
                Random rand = new Random();
                int n = rand.nextInt(4);

                switch (n) {
                    case 0:
                        if (topLeft.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return bottomRight;
                                case 1:
                                    return topLeft;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (topCenter.getPlayerPlayed() == null && bottomCenter.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topCenter;
                                case 1:
                                    return bottomCenter;
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (topRight.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topRight;
                                case 1:
                                    return bottomLeft;
                            }
                        } else {
                            case2 = false;
                        }
                    case 3:
                        if (centerLeft.getPlayerPlayed() == null && centerRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return centerRight;
                                case 1:
                                    return centerLeft;
                            }
                        } else {
                            case3 = false;
                        }
                }
            }
        } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (topRight.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topRight;
                                case 1:
                                    return bottomRight;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (center.getPlayerPlayed() == null && centerLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return centerLeft;
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (centerLeft.getPlayerPlayed() == null && topLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return centerLeft;
                                case 1:
                                    return topLeft;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (bottomCenter.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return bottomRight;
                                case 1:
                                    return bottomCenter;
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null && topRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return topRight;
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        } else if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (bottomLeft.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return bottomLeft;
                                case 1:
                                    return bottomRight;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (center.getPlayerPlayed() == null && topCenter.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topCenter;
                                case 1:
                                    return center;
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (centerRight.getPlayerPlayed() == null && topRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topRight;
                                case 1:
                                    return centerRight;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (bottomCenter.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return bottomCenter;
                                case 1:
                                    return bottomLeft;
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null && topLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return topLeft;
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        }

        //Random Moves
        while (true) {
            Random rand = new Random();
            int n = rand.nextInt(9);

            switch (n) {
                case 0:
                    if (!center.getIsMarked()) {
                        return center;
                    } else {
                        break;
                    }
                case 1:
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    } else {
                        break;
                    }
                case 2:
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    } else {
                        break;
                    }
                case 3:
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    } else {
                        break;
                    }
                case 4:
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    } else {
                        break;
                    }
                case 5:
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    } else {
                        break;
                    }
                case 6:
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    } else {
                        break;
                    }
                case 7:
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    } else {
                        break;
                    }
                case 8:
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    } else {
                        break;
                    }
            }
        }
    }

    /**
     * CHECK MARKED METHOD
     * Check if square is playable and responds accordingly
     */
    static boolean markQuadrant(Quadrant quadrant, boolean isMarked) {
        boolean start;
        start = currentQuadrant == null;

        if ((getNumberOfPlayers() == 2 && isMarked) || (getPlayer1().getTurn() && isMarked)) {
            if (!previousQuadrant.equals(quadrant)) {
                counter = 0;
            }
            counter++;
            if (counter >= 3) {
                counter = 0;
                getPauseBtn().setDisable(true);
                getUndoBtn().setDisable(true);
                isAlreadyMarkedPopUp();
            }
        } else {
            if (!start) {
                previousPreviousQuadrant = previousQuadrant;
                previousQuadrant = currentQuadrant;
            }
            currentQuadrant = quadrant;
            setAbleToUndo(true);
            counter = 0;
            if (!gameOver && !getPauseBtn().isDisabled() && getPlayer1().getTurn()) {
                Object[] temp1 = quadrant.getPane().getChildren().toArray();
                File imageFile1 = new File("src/sample/images/TicTacToeSingleX.png");
                Image imageO1 = new Image(imageFile1.toURI().toString());
                ImageView quadrantImageView1 = (ImageView) temp1[0];
                quadrantImageView1.setImage(imageO1);
                quadrant.setIsMarked(true);
                quadrant.setPlayerPlayed(getPlayer1());
                getPlayer1().setTurn(false);
                getPlayer2().setTurn(true);
                if (!checkIfWon(quadrant, getPlayer1())) {
                    checkIfTie();
                    if (!gameOver && !getPauseBtn().isDisabled() && getNumberOfPlayers() == 1 && getPlayer2().getTurn()) {
                        quadrant = QuadrantController.getQuadrantToMark(quadrant);
                        Object[] temp2 = quadrant.getPane().getChildren().toArray();
                        File imageFile2 = new File("src/sample/images/TicTacToeSingleO.png");
                        Image imageO2 = new Image(imageFile2.toURI().toString());
                        ImageView quadrantImageView2 = (ImageView) temp2[0];
                        quadrantImageView2.setImage(imageO2);
                        quadrant.setIsMarked(true);
                        quadrant.setPlayerPlayed(getPlayer2());
                        QuadrantController.currentQuadrant = quadrant;
                        getPlayer2().setTurn(false);
                        getPlayer1().setTurn(true);
                        if (!QuadrantController.checkIfWon(quadrant, getPlayer2())) {
                            QuadrantController.checkIfTie();
                        }
                    }
                }
            } else if (!gameOver && !getPauseBtn().isDisabled() && getNumberOfPlayers() == 2 && getPlayer2().getTurn()) {
                Object[] temp = quadrant.getPane().getChildren().toArray();
                File imageFile = new File("src/sample/images/TicTacToeSingleO.png");
                Image imageO = new Image(imageFile.toURI().toString());
                ImageView quadrantImageView = (ImageView) temp[0];
                quadrantImageView.setImage(imageO);
                quadrant.setIsMarked(true);
                quadrant.setPlayerPlayed(getPlayer2());
                getPlayer2().setTurn(false);
                getPlayer1().setTurn(true);
                if (!QuadrantController.checkIfWon(quadrant, getPlayer2())) {
                    QuadrantController.checkIfTie();
                }
            }
        }

        return true;
    }
}
