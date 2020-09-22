package code;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

import static code.ButtonController.*;
import static code.ComputerOpponentController.*;
import static code.PlayerController.*;
import static code.PopUpController.*;

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
     * CHECK MARKED METHOD
     * Check if square is playable and responds accordingly
     */
    static boolean markQuadrant(Quadrant quadrant, boolean isMarked) {
        boolean start = currentQuadrant == null;

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
                File imageFile1 = new File("files/images/TicTacToeSingleX.png");
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
                        quadrant = getQuadrantToMark(quadrant);
                        Object[] temp2 = quadrant.getPane().getChildren().toArray();
                        File imageFile2 = new File("files/images/TicTacToeSingleO.png");
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
                File imageFile = new File("files/images/TicTacToeSingleO.png");
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
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
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
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
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
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
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
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
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
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
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
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
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
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
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
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
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
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    getPauseBtn().setDisable(true);
                    getUndoBtn().setDisable(true);
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
}
