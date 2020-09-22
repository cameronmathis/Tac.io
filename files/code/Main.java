package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static code.ButtonController.*;
import static code.PlayerController.*;
import static code.QuadrantController.*;
import static code.GameController.*;
import static code.PopUpController.*;

public class Main extends Application {
    /**
     * Runs the program
     *
     * @param args args
     */
    public static void main(String... args) {
        Application.launch(args);
    }

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
        primaryStage.setScene(getGameScene()); //sets the scene on the stage
        primaryStage.setAlwaysOnTop(false); //set to false to show popups
        primaryStage.setResizable(false); //makes app able to be resized
        primaryStage.show(); //shows the primaryStage
        numberOfPlayersPopUp(); //ask for number of players

        //initialize all the buttons
        setPauseBtn((Button) getGameScene().lookup("#pause"));
        setUndoBtn((Button) getGameScene().lookup("#undo"));

        //initialize all 9 quadrants
        initializeQuadrants();

        // listen for plays via mouse
        getTopLeft().getPane().setOnMouseClicked(event -> markQuadrant(getTopLeft(), getTopLeft().getIsMarked()));
        getTopCenter().getPane().setOnMouseClicked(event -> markQuadrant(getTopCenter(), getTopCenter().getIsMarked()));
        getTopRight().getPane().setOnMouseClicked(event -> markQuadrant(getTopRight(), getTopRight().getIsMarked()));
        getCenterLeft().getPane().setOnMouseClicked(event -> markQuadrant(getCenterLeft(), getCenterLeft().getIsMarked()));
        getCenter().getPane().setOnMouseClicked(event -> markQuadrant(getCenter(), getCenter().getIsMarked()));
        getCenterRight().getPane().setOnMouseClicked(event -> markQuadrant(getCenterRight(), getCenterRight().getIsMarked()));
        getBottomLeft().getPane().setOnMouseClicked(event -> markQuadrant(getBottomLeft(), getBottomLeft().getIsMarked()));
        getBottomCenter().getPane().setOnMouseClicked(event -> markQuadrant(getBottomCenter(), getBottomCenter().getIsMarked()));
        getBottomRight().getPane().setOnMouseClicked(event -> markQuadrant(getBottomRight(), getBottomRight().getIsMarked()));

        //listen for pause button click via mouse
        getPauseBtn().setOnAction(event -> pause());

        //listen for undo button press via mouse
        getUndoBtn().setOnAction(event -> undo());

        // listen for shortcut keys
        getOpeningScene().setOnKeyReleased(event -> {
            // Pregame shortcut keys
            if (getEnterNumberPopUpShown() && (event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.NUMPAD1)) {
                setNumberOfPlayers(1);
                hidePopUp();
                enterOneNamePopUp();
                getPlayer2().setUsername("Computer");
            } else if (getEnterNumberPopUpShown() && (event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.NUMPAD2)) {
                setNumberOfPlayers(2);
                hidePopUp();
                enterTwoNamesPopUp();
            }
        });

        // Game shortcut keys
        getGameScene().setOnKeyReleased(event -> {
            // listen for plays via number pad
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
            } //close popup shortcuts
            else if (event.getCode() == KeyCode.ENTER && getWonGamePopUpShown()) {
                setGame();
                hidePopUp();
            } else if (event.getCode() == KeyCode.ENTER && getTiedGamePopUpShown()) {
                setGame();
                hidePopUp();
            } //pause game shortcut
            else if (event.getCode() == KeyCode.SPACE && !getPauseBtn().isDisabled()) {
                pause();
            } //unpause game shortcut
            else if (event.getCode() == KeyCode.SPACE && getPauseBtn().isDisabled()) {
                hidePopUp();
            } //restart game from pause menu shortcut
            else if (event.getCode() == KeyCode.BACK_SPACE && getPauseBtn().isDisabled()) {
                primaryStage.setScene(getOpeningScene()); //sets the scene on the stage
                primaryStage.show(); //shows the primaryStage
                hidePopUp();
                numberOfPlayersPopUp();
            } //undo shortcut
            else if (event.getCode() == KeyCode.BACK_SPACE && getPopUp() == null) {
                undo();
            }
        });
    }
}