package sample;

import javafx.scene.control.Button;

import static sample.PlayerController.*;
import static sample.PopUpController.*;
import static sample.QuadrantController.*;

public class ButtonController {
    // Variables
    private static Button pauseBtn;
    private static Button undoBtn;
    private static boolean ableToUndo = false;

    /**
     * SETTER METHODS
     */
    static void setPauseBtn(Button b) {
        pauseBtn = b;
    }

    static void setUndoBtn(Button b) {
        undoBtn = b;
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

    static boolean getAbleToUndo() { return ableToUndo; }

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
     * HIDE POPUP METHOD
     * Hides current PopUp
     */
    static void hidePopUp() {
        try {
            getPopUp().hide();
            setPopUp(null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
