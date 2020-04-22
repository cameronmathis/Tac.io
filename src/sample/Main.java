package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        setGameScene(new Scene(getGamePane())); //created a new scene from 'GameScreen.fxml'
        primaryStage.setTitle("TicTacToe"); //sets the title of the app
        primaryStage.setScene(getOpeningScene()); //sets the scene on the stage
        primaryStage.setAlwaysOnTop(false); //set to false to show popups
        primaryStage.setResizable(false); //makes app able to be resized
        primaryStage.show(); //shows the primaryStage
        numberOfPlayersPopUp(); //ask for number of players

        //initialize both player games one this during this session
        getPlayer1().setGamesWon(0);
        getPlayer2().setGamesWon(0);

        /**
         * BUTTON INITIALIZATION
         * Initialize all the buttons
         */
        setPauseBtn((Button) getGameScene().lookup("#pause"));
        setUndoBtn((Button) getGameScene().lookup("#undo"));

        initializeQuadrants();

        /**
         * CHECK FOR PLAYS
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

            if ((event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.NUMPAD1) && getEnterNumberPopUpShown()) {
                setNumberOfPlayers(1);
                resume();
                setEnterNumberPopUpShown(false);
                newOrReturningUserPopUp();
                getPlayer2().setUsername("Computer");
            } else if ((event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.NUMPAD2) && getEnterNumberPopUpShown()) {
                setNumberOfPlayers(2);
                resume();
                setEnterNumberPopUpShown(false);
                enterTwoNamesPopUp();
            } else if (event.getCode() == KeyCode.ENTER && getEnterNamePopUpShown()) {
                if (getNumberOfPlayers() == 1) {
                    if (!(((TextField) getAccountLoginPopUpPane().lookup("#username")).getText().equals(""))) {
                        TextField username = (TextField) getAccountLoginPopUpPane().lookup("#username");
                        getPlayer1().setUsername(username.getText());
                    } else {
                        getPlayer1().setUsername("player1");
                    }
                    resume();
                    startGame();
                    setEnterNamePopUpShown(false);
                    getPauseBtn().setDisable(false);
                    getUndoBtn().setDisable(false);
                } else if (getNumberOfPlayers() == 2) {
                    TextField t1 = ((TextField) getEnterTwoNamesPopUpPane().lookup("#player1Name"));
                    TextField t2 = ((TextField) getEnterTwoNamesPopUpPane().lookup("#player2Name"));
                    if (t1.isFocused()) {
                        t2.requestFocus();
                    } else {
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
                        resume();
                        startGame();
                        setEnterNamePopUpShown(false);
                        getPauseBtn().setDisable(false);
                        getUndoBtn().setDisable(false);
                    }
                }
            }
        });

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
                resume();
                setWonGamePopUpShown(false);
                getPauseBtn().setDisable(false);
                getUndoBtn().setDisable(false);
            } else if (event.getCode() == KeyCode.ENTER && getTiedGamePopUpShown()) {
                setGame();
                resume();
                setWonGamePopUpShown(false);
                getPauseBtn().setDisable(false);
                getUndoBtn().setDisable(false);
            } else if (event.getCode() == KeyCode.SPACE && !getPauseBtn().isDisabled()) {
                pause();
            } else if (event.getCode() == KeyCode.SPACE && getPauseBtn().isDisabled()) {
                resume();
                getPauseBtn().setDisable(false);
                getUndoBtn().setDisable(false);
            } else if (event.getCode() == KeyCode.BACK_SPACE && getPauseBtn().isDisabled()) {
                primaryStage.setScene(getOpeningScene()); //sets the scene on the stage
                primaryStage.show(); //shows the primaryStage
                resume();
                numberOfPlayersPopUp();
            } else if (event.getCode() == KeyCode.BACK_SPACE && getPopUp() == null) {
                undo();
            } else if (event.getCode() == KeyCode.ENTER && getPopUp() != null) {
                resume();
                getPauseBtn().setDisable(false);
                getUndoBtn().setDisable(false);
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