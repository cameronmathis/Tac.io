package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //Variables
    private Stage primaryStage;
    private AnchorPane openingPane;
    private Scene openingScene;
    private AnchorPane gamePane;
    private Scene gameScene;
    private boolean paused;
    private Popup pausedPopUp;

    //The GUI interface scene
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage; //sets this primaryStage as 'the' primaryStage
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
        primaryStage.setMinWidth(600); //set the minimum width
        primaryStage.setMinHeight(500); //set the minimum height
        primaryStage.setResizable(true); //makes app able to be resized
        primaryStage.show(); //shows the primaryStage

        /**
         * BUTTON INITIALIZATION
         * Initialize all the buttons
         */
        Button startBtn = (Button) openingScene.lookup("#start"); //calls the browse button from the fxml file

        /**
         * IMAGE LAYER GROUP
         * New group for the image layer
         * (used so you can snapshot the current photo and crop
         */
        paused = false;

        openingScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                start();
            }
        });

        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE && !paused) {
                pause();
            } else if (event.getCode() == KeyCode.ENTER && paused) {
                resume();
            }
        });

        Pane pane = (Pane) openingScene.lookup("#textPane");
        openingScene.widthProperty().addListener((obs, oldVal, newVal) -> {
            pane.setLayoutY(newVal.intValue());
        });
        openingScene.heightProperty().addListener((obs, oldVal, newVal) -> {
            pane.setLayoutX(newVal.intValue());
        });

        /**
         * BEGIN BUTTON
         * Start the game
         */
        startBtn.setOnAction(event -> start());
    }

    /**
     * START METHOD
     * Starts the game
     */
    private void start() {
        primaryStage.setScene(gameScene); //sets the scene on the stage
        primaryStage.show(); //shows the primaryStage
    }

    /**
     * PAUSE METHOD
     * Pause the game
     */
    private void pause() {
        paused = true;
        pausedPopUp();
    }

    /**
     * START METHOD
     * Starts the game
     */
    private void resume() {
        try {
            paused = false;
            pausedPopUp.hide();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Paused POPUP
     * PopUp for when the game is paused
     */
    private void pausedPopUp() {
        pausedPopUp = new Popup(); //creates new popup

        TitledPane pausedPupUpPane = null; //calls popup menu created in 'chooseZipErrorPopUp.fxml' file

        try {
            pausedPupUpPane = FXMLLoader.load(getClass().getResource("pausedPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        pausedPopUp.getContent().add(pausedPupUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        pausedPopUp.show(primaryStage);

        Button resumeBtn = (Button) pausedPupUpPane.lookup("#resume");
        resumeBtn.setOnAction(browseDismissEvent -> resume());

        Button exitBtn = (Button) pausedPupUpPane.lookup("#exit");
        exitBtn.setOnAction(browseDismissEvent -> {
            try {
                System.exit(0);
                pausedPopUp.hide();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    /**
     * Runs the program
     *
     * @param args
     */
    public static void main(String... args) {
        Application.launch(args);
    }
}