package sample;

import javafx.scene.control.Button;

import static sample.Main.*;
import static sample.PopUpController.*;
import static sample.QuadrantController.*;

public class ButtonController {
    // Variables
    static Button pauseBtn;
    static Button undoBtn;
    static boolean ableToUndo = false;

    /**
     * PAUSE METHOD
     * Pause the game
     */
    static void pause() {
        paused = true;
        pauseBtn.setDisable(true);
        undoBtn.setDisable(true);
        pausedPopUp();
    }

    /**
     * RESUME METHOD
     * Resumes the game
     */
    static void resume() {
        try {
            paused = false;
            PopUp.hide();
            PopUp = null;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * UNDO METHOD
     * Undo the last move
     */
    static void undo() {
        if (ableToUndo && PlayerController.numberOfPlayers == 2) {
            currentQuadrant.setIsMarked(false);
            currentQuadrant = previousQuadrant;
            previousQuadrant = previousPreviousQuadrant;
            if ( PlayerController.player1.getTurn()) {
                PlayerController.player1.setTurn(false);
                PlayerController.player2.setTurn(true);
            } else if ( PlayerController.player2.getTurn()) {
                PlayerController.player2.setTurn(false);
                PlayerController.player1.setTurn(true);
            }
        } else if (ableToUndo && PlayerController.numberOfPlayers == 1) {
            if ( PlayerController.player1.getTurn()) {
                currentQuadrant.setIsMarked(false);
                currentQuadrant = previousPreviousQuadrant;
                previousQuadrant.setIsMarked(false);
                previousQuadrant = null;
                previousPreviousQuadrant = null;
            } else if ( PlayerController.player2.getTurn()) {
                currentQuadrant.setIsMarked(false);
                currentQuadrant = previousQuadrant;
                previousQuadrant = null;
                previousPreviousQuadrant = null;
                PlayerController.player2.setTurn(false);
                PlayerController.player1.setTurn(true);
            }
        } else if (currentQuadrant == null) {
            pauseBtn.setDisable(true);
            undoBtn.setDisable(true);
            undoStartPopUp();
        } else if (previousQuadrant == null) {
            pauseBtn.setDisable(true);
            undoBtn.setDisable(true);
            undoTwicePopUp();
        }

        ableToUndo = false;
    }
}
