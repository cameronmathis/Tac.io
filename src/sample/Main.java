package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


import static sample.ButtonController.*;
import static sample.PlayerController.*;
import static sample.QuadrantController.*;
import static sample.GameController.*;
import static sample.PopUpController.*;

public class Main extends Application {

    //The GUI interface scene
    @Override
    public void start(Stage primaryStage) throws Exception {

        setPrimaryStage(primaryStage); //sets this primaryStage as 'the' primaryStage
        setOpeningPane(FXMLLoader.load(getClass().getResource("StartScreen.fxml")));
        setGamePane(FXMLLoader.load(getClass().getResource("GameScene.fxml")));

        /**
         * MAIN STAGE CREATED
         * Main Stage and Scene Created and shown
         */
        setOpeningScene(new Scene(getOpeningPane())); //creates a new scene from 'StartScreen.fxml'
        setGameScene(new Scene(getGamePane())); //created a new scene from 'GameScene.fxml'
        primaryStage.setTitle("TicTacToe"); //sets the title of the app
        primaryStage.setScene(getOpeningScene()); //sets the scene on the stage
        primaryStage.setAlwaysOnTop(false); //set to false to show popups
        primaryStage.setResizable(false); //makes app able to be resized
        primaryStage.show(); //shows the primaryStage
        numberOfPlayersPopUp(); //ask for number of players

        /**
         * BUTTON INITIALIZATION
         * Initialize all the buttons
         */
        initializeButtons();

        /**
         * QUADRANT INITIALIZATION
         * Initialize all the quadrants
         */
        initializeQuadrants();

        /**
         * CHECK FOR PLAYS VIA MOUSE
         */
        getTopLeft().getPane().setOnMouseClicked(event -> markQuadrant(getTopLeft(), getTopLeft().getIsMarked()));
        getTopCenter().getPane().setOnMouseClicked(event -> markQuadrant(getTopCenter(), getTopCenter().getIsMarked()));
        getTopRight().getPane().setOnMouseClicked(event -> markQuadrant(getTopRight(), getTopRight().getIsMarked()));
        getCenterLeft().getPane().setOnMouseClicked(event -> markQuadrant(getCenterLeft(), getCenterLeft().getIsMarked()));
        getCenter().getPane().setOnMouseClicked(event -> markQuadrant(getCenter(), getCenter().getIsMarked()));
        getCenterRight().getPane().setOnMouseClicked(event -> markQuadrant(getCenterRight(), getCenterRight().getIsMarked()));
        getBottomLeft().getPane().setOnMouseClicked(event -> markQuadrant(getBottomLeft(), getBottomLeft().getIsMarked()));
        getBottomCenter().getPane().setOnMouseClicked(event -> markQuadrant(getBottomCenter(), getBottomCenter().getIsMarked()));
        getBottomRight().getPane().setOnMouseClicked(event -> markQuadrant(getBottomRight(), getBottomRight().getIsMarked()));

        /**
         * SHORTCUT KEYS
         */
        getOpeningScene().setOnKeyReleased(event -> {
            boolean t1 = getCreateAccountPopUpShown();
            boolean t2 = getCreateAccountErrorPopUpShown();
            // Pregame shortcut keys
            if (getEnterNumberPopUpShown() && (event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.NUMPAD1)) {
                setNumberOfPlayers(1);
                hidePopUp();
                newOrReturningUserPopUp();
                getPlayer2().setUsername("Computer");
            } else if (getEnterNumberPopUpShown() && (event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.NUMPAD2)) {
                setNumberOfPlayers(2);
                hidePopUp();
                enterTwoNamesPopUp();
            } else if ((getCreateAccountPopUpShown() || getAccountLoginPopUpShown()) && event.getCode() == KeyCode.ENTER) {
                if (getCreateAccountPopUpShown()) {
                    TextField username = (TextField) getCreateAccountPopUpPane().lookup("#username");
                    if (username.getText().equals("") || username.getText().contains(" ") || (username.getText().length() > 250)) {
                        hidePopUp();
                        invalidUsernamePopUp();
                        return;
                    } else if (false) {
                        hidePopUp();
                        usernameAlreadyExistPopUp();
                        return;
                    }

                    TextField password1 = (PasswordField) getCreateAccountPopUpPane().lookup("#password1");
                    TextField password2 = (PasswordField) getCreateAccountPopUpPane().lookup("#password2");
                    if (!password1.getText().equals(password2.getText())) {
                        hidePopUp();
                        passwordsDontMatchPopUp();
                        return;
                    } else if (password1.getText().equals("") || password1.getText().contains(" ") || (password1.getText().length() < 7) || (password1.getText().length() > 250)) {
                        hidePopUp();
                        invalidPasswordPopUp();
                        return;
                    }
                    setPlayer1(createPlayer(new Player(), username.getText(), password1.getText()));
                    hidePopUp();
                    startGame();
                } else if (getAccountLoginPopUpShown() && (getNumberOfPlayers() == 1)) {
                    Player tempPlayer = new Player();
                    TextField username = (TextField) getAccountLoginPopUpPane().lookup("#username");
                    TextField password = (PasswordField) getAccountLoginPopUpPane().lookup("#password");
                    if (username.getText().equals("") || (username.getText().length() > 250)) {
                        hidePopUp();
                        usernameDoesNotExistPopUp();
                        return;
                    } else if (false) {
                        hidePopUp();
                        usernameDoesNotExistPopUp();
                        return;
                    } else if (true) {
                        tempPlayer.setUsername(username.getText());
                    }

                    if ((password.getText().length() < 7) || (password.getText().length() > 250)) {
                        hidePopUp();
                        incorrectPasswordPopUp();
                        return;
                    } else if (!password.getText().equals(password.getText())) {
                        hidePopUp();
                        incorrectPasswordPopUp();
                        return;
                    }
                    setPlayer1(importPlayer(tempPlayer, username.getText(), password.getText(), 0, 0));
                    hidePopUp();
                    startGame();
                } else if (getNumberOfPlayers() == 2) {
                    if (!(((TextField) getEnterTwoNamesPopUpPane().lookup("#player1Name")).getText().equals(""))) {
                        TextField name1 = (TextField) getEnterTwoNamesPopUpPane().lookup("#player1Name");
                        getPlayer1().setUsername(name1.getText());
                    } else {
                        getPlayer1().setUsername("player1");
                    }
                    if (!(((TextField) getEnterTwoNamesPopUpPane().lookup("#player2Name")).getText().equals(""))) {
                        TextField name2 = (TextField) getEnterTwoNamesPopUpPane().lookup("#player2Name");
                        getPlayer2().setUsername(name2.getText());
                    } else {
                        getPlayer2().setUsername("player2");
                    }
                    hidePopUp();
                    startGame();
                }
            } else if (getCreateAccountErrorPopUpShown() && event.getCode() == KeyCode.ENTER) {
                hidePopUp();
                createAccountPopUp();
            }
        });

        // Game shortcut keys
        getGameScene().setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.NUMPAD7 && !getPauseBtn().isDisabled() && getPopUp() == null) {
                markQuadrant(getTopLeft(), getTopLeft().getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD8 && !getPauseBtn().isDisabled() && getPopUp() == null) {
                markQuadrant(getTopCenter(), getTopCenter().getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD9 && !getPauseBtn().isDisabled() && getPopUp() == null) {
                markQuadrant(getTopRight(), getTopRight().getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD4 && !getPauseBtn().isDisabled() && getPopUp() == null) {
                markQuadrant(getCenterLeft(), getCenterLeft().getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD5 && !getPauseBtn().isDisabled() && getPopUp() == null) {
                markQuadrant(getCenter(), getCenter().getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD6 && !getPauseBtn().isDisabled() && getPopUp() == null) {
                markQuadrant(getCenterRight(), getCenterRight().getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD1 && !getPauseBtn().isDisabled() && getPopUp() == null) {
                markQuadrant(getBottomLeft(), getBottomLeft().getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD2 && !getPauseBtn().isDisabled() && getPopUp() == null) {
                markQuadrant(getBottomCenter(), getBottomCenter().getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD3 && !getPauseBtn().isDisabled() && getPopUp() == null) {
                markQuadrant(getBottomRight(), getBottomRight().getIsMarked());
            } else if (event.getCode() == KeyCode.ENTER && getWonGamePopUpShown()) {
                setGame();
                hidePopUp();
            } else if (event.getCode() == KeyCode.ENTER && getTiedGamePopUpShown()) {
                setGame();
                hidePopUp();
            } else if (event.getCode() == KeyCode.SPACE && !getPauseBtn().isDisabled()) {
                pause();
            } else if (event.getCode() == KeyCode.SPACE && getPauseBtn().isDisabled()) {
                hidePopUp();
            } else if (event.getCode() == KeyCode.BACK_SPACE && getPauseBtn().isDisabled()) {
                primaryStage.setScene(getOpeningScene()); //sets the scene on the stage
                primaryStage.show(); //shows the primaryStage
                hidePopUp();
                numberOfPlayersPopUp();
            } else if (event.getCode() == KeyCode.BACK_SPACE && getPopUp() == null) {
                undo();
            } else if (event.getCode() == KeyCode.ENTER && getPopUp() != null) {
                hidePopUp();
            }
        });

        /**
         * PAUSE BUTTON
         * Pause the game
         */
        getPauseBtn().setOnAction(event -> pause());

        /**
         * UNDO BUTTON
         * Undo the last move
         */
        getUndoBtn().setOnAction(event -> undo());
    }

    /**
     * Runs the program
     *
     * @param args args
     */
    public static void main(String... args) {
        Application.launch(args);
    }
}