package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static sample.ButtonController.*;
import static sample.PlayerController.*;
import static sample.QuadrantController.*;
import static sample.GameController.*;
import static sample.PopUpController.*;

public class Main extends Application {

    //Variables
    static boolean paused;

    //The GUI interface scene
    @Override
    public void start(Stage primaryStage) throws Exception {

        PopUpController.primaryStage = primaryStage; //sets this primaryStage as 'the' primaryStage
        openingPane = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        gamePane = FXMLLoader.load(getClass().getResource("GameScene.fxml"));

        /**
         * MAIN STAGE CREATED
         * Main Stage and Scene Created and shown
         */
        openingScene = new Scene(openingPane); //creates a new scene from 'StartScreen.fxml'
        gameScene = new Scene(gamePane); //created a new scene from 'GameScreen.fxml'
        primaryStage.setTitle("TicTacToe"); //sets the title of the app
        primaryStage.setScene(openingScene); //sets the scene on the stage
        primaryStage.setAlwaysOnTop(false); //set to false to show popups
        primaryStage.setResizable(false); //makes app able to be resized
        primaryStage.show(); //shows the primaryStage
        numberOfPlayersPopUp(); //ask for number of players

        //initialize both player games one
        player1.setGamesWon(0);
        player2.setGamesWon(0);

        /**
         * BUTTON INITIALIZATION
         * Initialize all the buttons
         */
        pauseBtn = (Button) gameScene.lookup("#pause");
        undoBtn = (Button) gameScene.lookup("#undo");

        //initialize each quadrant
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

        /**
         * SHORTCUT KEYS
         */
        paused = false;

        openingScene.setOnKeyReleased(event -> {
            if ((event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.NUMPAD1) && enterNumberPopUpShown) {
                numberOfPlayers = 1;
                resume();
                enterNumberPopUpShown = false;
                newOrReturningUserPopUp();
                player2.setUsername("Computer");
            } else if ((event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.NUMPAD2) && enterNumberPopUpShown) {
                numberOfPlayers = 2;
                resume();
                enterNumberPopUpShown = false;
                enterTwoNamesPopUp();
            } else if (event.getCode() == KeyCode.ENTER && enterNamePopUpShown) {
                if (numberOfPlayers == 1) {
                    if (!(((TextField) accountLoginPopUpPane.lookup("#username")).getText().equals(""))) {
                        TextField username = (TextField) accountLoginPopUpPane.lookup("#username");
                        player1.setUsername(username.getText());
                    } else {
                        player1.setUsername("player1");
                    }
                    resume();
                    startGame();
                    enterNamePopUpShown = false;
                    pauseBtn.setDisable(false);
                    undoBtn.setDisable(false);
                } else if (numberOfPlayers == 2) {
                    TextField t1 = ((TextField) enterTwoNamesPopUpPane.lookup("#player1Name"));
                    TextField t2 = ((TextField) enterTwoNamesPopUpPane.lookup("#player2Name"));
                    if (t1.isFocused()) {
                        t2.requestFocus();
                    } else {
                        if (!(((TextField) enterTwoNamesPopUpPane.lookup("#player1Name")).getText().equals(""))) {
                            TextField name1 = (TextField) enterTwoNamesPopUpPane.lookup("#player1Name");
                            player1.setUsername(name1.getText());
                        } else {
                            player1.setUsername("player1");
                        }
                        if (!(((TextField) enterTwoNamesPopUpPane.lookup("#player2Name")).getText().equals(""))) {
                            TextField name2 = (TextField) enterTwoNamesPopUpPane.lookup("#player2Name");
                            player2.setUsername(name2.getText());
                        } else {
                            player2.setUsername("player2");
                        }
                        resume();
                        startGame();
                        enterNamePopUpShown = false;
                        pauseBtn.setDisable(false);
                        undoBtn.setDisable(false);
                    }
                }
            }
        });

        gameScene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.NUMPAD7 && !paused && PopUp == null) {
                markQuadrant(topLeft, topLeft.getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD8 && !paused && PopUp == null) {
                markQuadrant(topCenter, topCenter.getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD9 && !paused && PopUp == null) {
                markQuadrant(topRight, topRight.getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD4 && !paused && PopUp == null) {
                markQuadrant(centerLeft, centerLeft.getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD5 && !paused && PopUp == null) {
                markQuadrant(center, center.getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD6 && !paused && PopUp == null) {
                markQuadrant(centerRight, centerRight.getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD1 && !paused && PopUp == null) {
                markQuadrant(bottomLeft, bottomLeft.getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD2 && !paused && PopUp == null) {
                markQuadrant(bottomCenter, bottomCenter.getIsMarked());
            } else if (event.getCode() == KeyCode.NUMPAD3 && !paused && PopUp == null) {
                markQuadrant(bottomRight, bottomRight.getIsMarked());
            } else if (event.getCode() == KeyCode.ENTER && wonGamePopUpShown) {
                setGame();
                resume();
                wonGamePopUpShown = false;
                pauseBtn.setDisable(false);
                undoBtn.setDisable(false);
            } else if (event.getCode() == KeyCode.ENTER && tiedGamePopUpShown) {
                setGame();
                resume();
                wonGamePopUpShown = false;
                pauseBtn.setDisable(false);
                undoBtn.setDisable(false);
            } else if (event.getCode() == KeyCode.SPACE && !paused) {
                pause();
            } else if (event.getCode() == KeyCode.SPACE && paused) {
                resume();
                pauseBtn.setDisable(false);
                undoBtn.setDisable(false);
            } else if (event.getCode() == KeyCode.BACK_SPACE && paused) {
                primaryStage.setScene(openingScene); //sets the scene on the stage
                primaryStage.show(); //shows the primaryStage
                resume();
                numberOfPlayersPopUp();
            } else if (event.getCode() == KeyCode.BACK_SPACE && PopUp == null) {
                undo();
            } else if (event.getCode() == KeyCode.ENTER && PopUp != null) {
                resume();
                pauseBtn.setDisable(false);
                undoBtn.setDisable(false);
            }
        });

        /**
         * PAUSE BUTTON
         * Pause the game
         */
        pauseBtn.setOnAction(event -> pause());

        /**
         * UNDO BUTTON
         * Undo the last move
         */
        undoBtn.setOnAction(event -> undo());

        /**
         * CHECK FOR PLAYS
         */
        topLeft.getPane().setOnMouseClicked(event -> markQuadrant(topLeft, topLeft.getIsMarked()));
        topCenter.getPane().setOnMouseClicked(event -> markQuadrant(topCenter, topCenter.getIsMarked()));
        topRight.getPane().setOnMouseClicked(event -> markQuadrant(topRight, topRight.getIsMarked()));
        centerLeft.getPane().setOnMouseClicked(event -> markQuadrant(centerLeft, centerLeft.getIsMarked()));
        center.getPane().setOnMouseClicked(event -> markQuadrant(center, center.getIsMarked()));
        centerRight.getPane().setOnMouseClicked(event -> markQuadrant(centerRight, centerRight.getIsMarked()));
        bottomLeft.getPane().setOnMouseClicked(event -> markQuadrant(bottomLeft, bottomLeft.getIsMarked()));
        bottomCenter.getPane().setOnMouseClicked(event -> markQuadrant(bottomCenter, bottomCenter.getIsMarked()));
        bottomRight.getPane().setOnMouseClicked(event -> markQuadrant(bottomRight, bottomRight.getIsMarked()));
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