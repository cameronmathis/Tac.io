package code;

import javafx.scene.control.Button;

import static code.PlayerController.*;
import static code.PopUpController.*;
import static code.QuadrantController.*;

public class ButtonController {
    // Variables
    private static Button pauseBtn;
    private static Button undoBtn;
    private static boolean ableToUndo = false;

    /**
     * SETTER METHODS
     */
    static void setPauseBtn(Button button) {
        pauseBtn = button;
    }

    static void setUndoBtn(Button button) {
        undoBtn = button;
    }

    static void setAbleToUndo(boolean bool) {
        ableToUndo = bool;
    }

    /**
     * GETTER METHODS
     */
    static Button getPauseBtn() {
        return pauseBtn;
    }

    static Button getUndoBtn() {
        return undoBtn;
    }


    /**
     * PAUSE METHOD
     * Pause the game
     */
    static void pause() {
        pauseBtn.setDisable(true);
        undoBtn.setDisable(true);
        pausedPopUp();
    }

    /**
     * UNDO METHOD
     * Undo the last move
     */
    static void undo() {
        if (getNumberOfPlayers() != 2) {
            return;
        }
        if (ableToUndo && getNumberOfPlayers() == 2) {
            getCurrentQuadrant().setIsMarked(false);
            setCurrentQuadrant(getPreviousQuadrant());
            setPreviousQuadrant(getPreviousPreviousQuadrant());
            if (getPlayer1().getTurn()) {
                getPlayer1().setTurn(false);
                getPlayer2().setTurn(true);
            } else if (getPlayer2().getTurn()) {
                getPlayer2().setTurn(false);
                getPlayer1().setTurn(true);
            }
        } else if (ableToUndo && getNumberOfPlayers() == 1) {
            if (getPlayer1().getTurn()) {
                getCurrentQuadrant().setIsMarked(false);
                setCurrentQuadrant(getPreviousPreviousQuadrant());
                getPreviousQuadrant().setIsMarked(false);
                setPreviousQuadrant(null);
                setPreviousPreviousQuadrant(null);
            } else if (PlayerController.getPlayer2().getTurn()) {
                getCurrentQuadrant().setIsMarked(false);
                setCurrentQuadrant(getPreviousQuadrant());
                setPreviousQuadrant(null);
                setPreviousPreviousQuadrant(null);
                PlayerController.getPlayer2().setTurn(false);
                getPlayer1().setTurn(true);
            }
        } else if (getCurrentQuadrant() == null) {
            pauseBtn.setDisable(true);
            undoBtn.setDisable(true);
            undoStartPopUp();
        } else if (getPreviousQuadrant() == null) {
            pauseBtn.setDisable(true);
            undoBtn.setDisable(true);
            undoTwicePopUp();
        }

        ableToUndo = false;
    }
}
