package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //Variables
    private Stage primaryStage;
    private SplitPane pane;
    private Scene scene;
    private boolean started;
    private boolean paused;

    //The GUI interface scene
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage; //sets this primaryStage as 'the' primaryStage
        pane = FXMLLoader.load(getClass().getResource("GUI.fxml")); //calls the TitledPane from the fxml file

        /**
         * MAIN STAGE CREATED
         * Main Stage and Scene Created and shown
         */
        scene = new Scene(pane); //creates a new scene from the TitlesPane created in 'GUI.fxml'
        primaryStage.setTitle("TicTacToe"); //sets the title of the app
        primaryStage.setScene(scene); //sets the scene on the stage
        primaryStage.show(); //shows the primaryStage
        primaryStage.setAlwaysOnTop(false); //set to false to show popups
        primaryStage.setMinWidth(400); //set the minimum width
        primaryStage.setMinHeight(400); //set the minimum height
        primaryStage.setResizable(true); //makes app able to be resized

        /**
         * BUTTON INITIALIZATION
         * Initialize all the buttons
         */
        Button startBtn = (Button) scene.lookup("#begin"); //calls the browse button from the fxml file

        /**
         * IMAGE LAYER GROUP
         * New group for the image layer
         * (used so you can snapshot the current photo and crop
         */
        started = false;
        paused = false;

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && !started) {
                start();
            }
            if (event.getCode() == KeyCode.ENTER && paused) {
                resume();
            }
        });

        Pane pane = (Pane) scene.lookup("#textPane");
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            pane.setLayoutY(newVal.intValue());
        });
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
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

    }

    /**
     * START METHOD
     * Starts the game
     */
    private void resume() {

    }

    /**
     * START METHOD
     * Starts the game
     */
    private void endGame() {

    }

    /**
     * Paused POPUP
     * PopUp for when the game is paused
     */
    private void pausedPopUp() {
        Popup pausedPopUp = new Popup(); //creates new popup

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
        resumeBtn.setOnAction(browseDismissEvent -> {
            try {
                resume();
                pausedPopUp.hide();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        Button exitBtn = (Button) pausedPupUpPane.lookup("#exit");
        resumeBtn.setOnAction(browseDismissEvent -> {
            try {
                endGame();
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