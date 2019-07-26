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
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {

    //Variables
    private Stage primaryStage;
    private AnchorPane openingPane;
    private Scene openingScene;
    private AnchorPane gamePane;
    private Scene gameScene;
    private boolean paused;
    private Popup PopUp;
    private int counter;

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

        //ensure the window proportions remain the same
        primaryStage.isFullScreen();
//        Pane pane = (Pane) openingScene.lookup("#openingPane");
//        openingScene.widthProperty().addListener((obs, oldVal, newVal) -> {
//            pane.setLayoutY(newVal.intValue());
//        });
//        openingScene.heightProperty().addListener((obs, oldVal, newVal) -> {
//            pane.setLayoutX(newVal.intValue() + 100);
//        });

        //initialize each quadrant
        Pane topLeft = (Pane) gameScene.lookup("#topLeftPane");
        Pane topCenter = (Pane) gameScene.lookup("#topCenterPane");
        Pane topRight = (Pane) gameScene.lookup("#topRightPane");
        Pane centerLeft = (Pane) gameScene.lookup("#centerLeftPane");
        Pane center = (Pane) gameScene.lookup("#centerPane");
        Pane centerRight = (Pane) gameScene.lookup("#centerRightPane");
        Pane bottomLeft = (Pane) gameScene.lookup("#bottomLeftPane");
        Pane bottomCenter = (Pane) gameScene.lookup("#bottomCenterPane");
        Pane bottomRight = (Pane) gameScene.lookup("#bottomRightPane");

        //initialize boolean values for if quadrant has been marked
        AtomicBoolean topLeftMarked = new AtomicBoolean(false);
        AtomicBoolean topCenterMarked = new AtomicBoolean(false);
        AtomicBoolean topRightMarked = new AtomicBoolean(false);
        AtomicBoolean centerLeftMarked = new AtomicBoolean(false);
        AtomicBoolean centerMarked = new AtomicBoolean(false);
        AtomicBoolean centerRightMarked = new AtomicBoolean(false);
        AtomicBoolean bottomLeftMarked = new AtomicBoolean(false);
        AtomicBoolean bottomCenterMarked = new AtomicBoolean(false);
        AtomicBoolean bottomRightMarked = new AtomicBoolean(false);

        /**
         * BUTTON INITIALIZATION
         * Initialize all the buttons
         */
        Button startBtn = (Button) openingScene.lookup("#start");
        Button pauseBtn = (Button) gameScene.lookup("#pause");

        /**
         * SHORTCUT KEYS
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
            } else if (event.getCode() == KeyCode.ENTER && PopUp != null) {
                resume();
            }
        });

        /**
         * BEGIN BUTTON
         * Start the game
         */
        startBtn.setOnAction(event -> start());

        /**
         * PAUSE BUTTON
         * Pause the game
         */
        pauseBtn.setOnAction(event -> pause());

        /**
         * CHECK FOR PLAYS
         */
        topLeft.setOnMouseClicked(event -> {
            topLeftMarked.set(markBox(topLeft, topLeftMarked.get()));
        });
        topCenter.setOnMouseClicked(event -> {
            topCenterMarked.set(markBox(topCenter, topCenterMarked.get()));
        });
        topRight.setOnMouseClicked(event -> {
            topRightMarked.set(markBox(topRight, topRightMarked.get()));
        });
        centerLeft.setOnMouseClicked(event -> {
            centerLeftMarked.set(markBox(centerLeft, centerLeftMarked.get()));
        });
        center.setOnMouseClicked(event -> {
            centerMarked.set(markBox(center, centerMarked.get()));
        });
        centerRight.setOnMouseClicked(event -> {
            centerRightMarked.set(markBox(centerRight, centerRightMarked.get()));
        });
        bottomLeft.setOnMouseClicked(event -> {
            bottomLeftMarked.set(markBox(bottomLeft, bottomLeftMarked.get()));
        });
        bottomCenter.setOnMouseClicked(event -> {
            bottomCenterMarked.set(markBox(bottomCenter, bottomCenterMarked.get()));
        });
        bottomRight.setOnMouseClicked(event -> {
            bottomRightMarked.set(markBox(bottomRight, bottomRightMarked.get()));
        });
    }

    /**
     * START METHOD
     * Starts the game
     */
    private void start() {
        boolean fullScreen = primaryStage.isFullScreen();
        primaryStage.setScene(gameScene); //sets the scene on the stage
        if (fullScreen) {
            primaryStage.setFullScreen(true);
        } else {
            primaryStage.setFullScreen(false);
        }
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
     * RESUME METHOD
     * Resumes the game
     */
    private void resume() {
        try {
            paused = false;
            PopUp.hide();
            PopUp = null;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * PAUSES POPUP
     * PopUp for when the game is paused
     */
    private void pausedPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane pausedPupUpPane = null; //calls popup menu created in 'chooseZipErrorPopUp.fxml' file

        try {
            pausedPupUpPane = FXMLLoader.load(getClass().getResource("pausedPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(pausedPupUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button resumeBtn = (Button) pausedPupUpPane.lookup("#resume");
        resumeBtn.setOnAction(browseDismissEvent -> resume());

        Button exitBtn = (Button) pausedPupUpPane.lookup("#exit");
        exitBtn.setOnAction(event -> {
            try {
                System.exit(0);
                PopUp.hide();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    /**
     * CHECK MARKED METHOD
     * Check if square is playable and responds accordingly
     */
    private boolean markBox(Pane boxPane, boolean isMarked) {
        if (isMarked) {
            counter++;
            if (counter >= 3) {
                counter = 0;
                isAlreadyMarkedPopUp();
            }
        } else {
            counter = 0;
            boxPane.setStyle("-fx-background-color: #ffffff");
        }

        return true;
    }

    /**
     * Paused POPUP
     * PopUp for when the game is paused
     */
    private void isAlreadyMarkedPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane isAlreadyMarkedPopUpPane = null; //calls popup menu created in 'isAlreadyMarkedPopUp.fxml' file

        try {
            isAlreadyMarkedPopUpPane = FXMLLoader.load(getClass().getResource("isAlreadyMarkedPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(isAlreadyMarkedPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) isAlreadyMarkedPopUpPane.lookup("#dismiss");
        dismissBtn.setOnAction(event -> resume());
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