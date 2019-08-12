package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
    private Popup PopUp;
    private Quadrant topLeft;
    private Quadrant topCenter;
    private Quadrant topRight;
    private Quadrant centerLeft;
    private Quadrant center;
    private Quadrant centerRight;
    private Quadrant bottomLeft;
    private Quadrant bottomCenter;
    private Quadrant bottomRight;
    private Player player1 = new Player();
    private Player player2 = new Player();
    private int numberOfPlayers = 0;
    private Quadrant previousQuadrant;
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
        primaryStage.setMinWidth(700); //set the minimum width
        primaryStage.setMinHeight(700); //set the minimum height
        primaryStage.setResizable(false); //makes app able to be resized
        primaryStage.show(); //shows the primaryStage
        numberOfPlayersPopUp(); //ask for number of players

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
        setGame();

        //initialize both player games one
        player1.setGamesWon(0);
        player2.setGamesWon(0);

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
            if (event.getCode() == KeyCode.ENTER && PopUp == null) {
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
     * NUMBER OF PLAYERS POPUP
     * PopUp to ask for number of players
     */
    private void numberOfPlayersPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane numberOfPlayersPopPupPane = null; //calls popup menu created in 'numberOfPlayersPopUp.fxml' file

        try {
            numberOfPlayersPopPupPane = FXMLLoader.load(getClass().getResource("numberOfPlayersPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(numberOfPlayersPopPupPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button onePlayerBtn = (Button) numberOfPlayersPopPupPane.lookup("#onePlayer");
        onePlayerBtn.setOnAction(event -> {
            numberOfPlayers = 1;
            resume();
            enterOneNamePopUp();
            player2.setPlayerName("Computer");
        });
        Button twoPlayersBtn = (Button) numberOfPlayersPopPupPane.lookup("#twoPlayers");
        twoPlayersBtn.setOnAction(event -> {
            numberOfPlayers = 2;
            resume();
            enterTwoNamesPopUp();
        });
    }

    /**
     * ENTER ONE NAME POPUP
     * PopUp to ask for one player name
     */
    private void enterOneNamePopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane enterOneNamePopPupPane = null; //calls popup menu created in 'enterOneNamePopPup.fxml' file

        try {
            enterOneNamePopPupPane = FXMLLoader.load(getClass().getResource("enterOneNamePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(enterOneNamePopPupPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button enterBtn = (Button) enterOneNamePopPupPane.lookup("#enter");
        TitledPane finalEnterNamesPopPupPane = enterOneNamePopPupPane;
        enterBtn.setOnAction(event -> {
            if (finalEnterNamesPopPupPane.lookup("player1Name") != null) {
                String name1 = finalEnterNamesPopPupPane.lookup("player1Name").toString();
                player1.setPlayerName(name1);
            } else {
                player1.setPlayerName("player1");
            }
            resume();
        });
    }

    /**
     * ENTER TWO NAMES POPUP
     * PopUp to ask for both player names
     */
    private void enterTwoNamesPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane enterTwoNamesPopPupPane = null; //calls popup menu created in 'enterTwoNamesPopPup.fxml' file

        try {
            enterTwoNamesPopPupPane = FXMLLoader.load(getClass().getResource("enterTwoNamesPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(enterTwoNamesPopPupPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button enterBtn = (Button) enterTwoNamesPopPupPane.lookup("#enter");
        TitledPane finalEnterNamesPopPupPane = enterTwoNamesPopPupPane;
        enterBtn.setOnAction(event -> {
            if (finalEnterNamesPopPupPane.lookup("player1Name") != null) {
                String name1 = finalEnterNamesPopPupPane.lookup("player1Name").toString();
                player1.setPlayerName(name1);
            } else {
                player1.setPlayerName("player1");
            }
            if (finalEnterNamesPopPupPane.lookup("player2Name") != null) {
                String name2 = finalEnterNamesPopPupPane.lookup("player2Name").toString();
                player2.setPlayerName(name2);
            } else {
                player2.setPlayerName("player2");
            }
            resume();
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
     * PAUSED POPUP
     * PopUp for when the game is paused
     */
    private void pausedPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane pausedPupUpPane = null; //calls popup menu created in 'pausePopUp.fxml' file

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
    private boolean markQuadrant(Quadrant quadrant, boolean isMarked) {
        if ((numberOfPlayers == 2 && isMarked) || (player1.getTurn() && isMarked)) {
            if (!previousQuadrant.equals(quadrant)) {
                counter = 0;
            }
            counter++;
            if (counter >= 3) {
                counter = 0;
                isAlreadyMarkedPopUp();
            }
        } else {
            counter = 0;
            if (player1.getTurn()) {
                quadrant.getPane().setStyle("-fx-background-color: #ff0000");
                quadrant.setIsMarked(true);
                quadrant.setPlayerPlayed(player1);
                player1.setTurn(false);
                player2.setTurn(true);
                if (!checkIfWon(quadrant,player1)) {
                    checkIfTie();
                }
                if (numberOfPlayers == 1 && player2.getTurn()) {
                    getQuadrantToMark(quadrant);
                }
            } else if (numberOfPlayers == 2 && player2.getTurn()) {
                quadrant.getPane().setStyle("-fx-background-color: #0000ff");
                quadrant.setIsMarked(true);
                quadrant.setPlayerPlayed(player2);
                player2.setTurn(false);
                player1.setTurn(true);
                checkIfWon(quadrant, player2);
            }
        }

        previousQuadrant = quadrant;
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
     * CHECK IF WON METHOD
     * Check if player won
     */
    private boolean checkIfWon(Quadrant quadrant, Player player) {
        boolean won = false;

        if (quadrant.getPane().getId().equals("topLeftPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("topCenterPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("topRightPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("centerLeftPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    won = true;
                } else return false;
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("centerPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else return false;
            } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("centerRightPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("bottomLeftPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("bottomCenterPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("bottomRightPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    won = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        if (won) {
            player.setGamesWon(player.getGamesWon() + 1);
            gameOverPopUp(player);
        }
        return won;
    }

    /**
     * CHECK IF TIE METHOD
     * Check if players tied
     */
    private void checkIfTie() {
        if (topLeft.getIsMarked() && topCenter.getIsMarked() && topRight.getIsMarked() && centerLeft.getIsMarked() &&
            center.getIsMarked() && centerRight.getIsMarked() && bottomLeft.getIsMarked() && bottomCenter.getIsMarked() &&
            bottomRight.getIsMarked()) {
            tiePopUp();
        }
    }

    /**
     * GET QUADRANT TO MARK METHOD
     * Find the best quadrant to mark
     */
    private void getQuadrantToMark(Quadrant quadrant) {
        Player player = player1;

        if (quadrant.getPane().getId().equals("topLeftPane")) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("topCenterPane")) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (!bottomCenter.getIsMarked()) {
                    bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomCenter.setIsMarked(true);
                    bottomCenter.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("topRightPane")) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("centerLeftPane")) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (!centerRight.getIsMarked()) {
                    centerRight.getPane().setStyle("-fx-background-color: #0000ff");
                    centerRight.setIsMarked(true);
                    centerRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("centerPane")) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (!bottomCenter.getIsMarked()) {
                    bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomCenter.setIsMarked(true);
                    bottomCenter.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (!centerRight.getIsMarked()) {
                    centerRight.getPane().setStyle("-fx-background-color: #0000ff");
                    centerRight.setIsMarked(true);
                    centerRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("centerRightPane")) {
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (!centerLeft.getIsMarked()) {
                    centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    centerLeft.setIsMarked(true);
                    centerLeft.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("bottomLeftPane")) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("bottomCenterPane")) {
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (!topCenter.getIsMarked()) {
                    topCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    topCenter.setIsMarked(true);
                    topCenter.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("bottomRightPane")) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    player2.setTurn(false);
                    player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        }

        if (!center.getIsMarked()) {
            center.getPane().setStyle("-fx-background-color: #0000ff");
            center.setIsMarked(true);
            center.setPlayerPlayed(player2);
            player2.setTurn(false);
            player1.setTurn(true);
            checkIfWon(quadrant, player2);
            return;
        } else if (!topLeft.getIsMarked()) {
            topLeft.getPane().setStyle("-fx-background-color: #0000ff");
            topLeft.setIsMarked(true);
            topLeft.setPlayerPlayed(player2);
            player2.setTurn(false);
            player1.setTurn(true);
            checkIfWon(quadrant, player2);
            return;
        } else if (!topRight.getIsMarked()) {
            topRight.getPane().setStyle("-fx-background-color: #0000ff");
            topRight.setIsMarked(true);
            topRight.setPlayerPlayed(player2);
            player2.setTurn(false);
            player1.setTurn(true);
            checkIfWon(quadrant, player2);
            return;
        } else if (!bottomLeft.getIsMarked()) {
            bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
            bottomLeft.setIsMarked(true);
            bottomLeft.setPlayerPlayed(player2);
            player2.setTurn(false);
            player1.setTurn(true);
            checkIfWon(quadrant, player2);
            return;
        } else if (!bottomRight.getIsMarked()) {
            bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
            bottomRight.setIsMarked(true);
            bottomRight.setPlayerPlayed(player2);
            player2.setTurn(false);
            player1.setTurn(true);
            checkIfWon(quadrant, player2);
            return;
        } else if (!topCenter.getIsMarked()) {
            topCenter.getPane().setStyle("-fx-background-color: #0000ff");
            topCenter.setIsMarked(true);
            topCenter.setPlayerPlayed(player2);
            player2.setTurn(false);
            player1.setTurn(true);
            checkIfWon(quadrant, player2);
            return;
        } else if (!centerLeft.getIsMarked()) {
            centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
            centerLeft.setIsMarked(true);
            centerLeft.setPlayerPlayed(player2);
            player2.setTurn(false);
            player1.setTurn(true);
            checkIfWon(quadrant, player2);
            return;
        } else if (!centerRight.getIsMarked()) {
            centerRight.getPane().setStyle("-fx-background-color: #0000ff");
            centerRight.setIsMarked(true);
            centerRight.setPlayerPlayed(player2);
            player2.setTurn(false);
            player1.setTurn(true);
            checkIfWon(quadrant, player2);
            return;
        } else if (!bottomCenter.getIsMarked()) {
            bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
            bottomCenter.setIsMarked(true);
            bottomCenter.setPlayerPlayed(player2);
            player2.setTurn(false);
            player1.setTurn(true);
            checkIfWon(quadrant, player2);
            return;
        }
    }

    /**
     * GAME OVER POPUP
     * PopUp for when the game is over
     */
    private void gameOverPopUp(Player player) {
        PopUp = new Popup(); //creates new popup

        TitledPane gameOverPopUpPane = null; //calls popup menu created in 'gameOverPopUp.fxml' file

        try {
            gameOverPopUpPane = FXMLLoader.load(getClass().getResource("gameOverPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(gameOverPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        Text text = (Text) gameOverPopUpPane.lookup("#gameOverMessage");
//        text.setText("Congratulations " + player.getPlayerName() + "! You won!");

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button playAgainBtn = (Button) gameOverPopUpPane.lookup("#playAgain");
        playAgainBtn.setOnAction(event -> {
            setGame();
            resume();
        });

        Button exitBtn = (Button) gameOverPopUpPane.lookup("#exit");
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
     * TIE POPUP
     * PopUp for when the game is tied
     */
    private void tiePopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane tiePopUpPane = null; //calls popup menu created in 'tiePopUp.fxml' file

        try {
            tiePopUpPane = FXMLLoader.load(getClass().getResource("tiePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(tiePopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button playAgainBtn = (Button) tiePopUpPane.lookup("#playAgain");
        playAgainBtn.setOnAction(event -> {
            setGame();
            resume();
        });

        Button exitBtn = (Button) tiePopUpPane.lookup("#exit");
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
     * SET GAME METHOD
     * Sets the board for a new game
     */
    private void setGame() {
        player1.setTurn(true);
        player2.setTurn(false);

        topLeft = new Quadrant();
        topLeft.setPane((Pane) gameScene.lookup("#topLeftPane"));
        topLeft.setIsMarked(false);
        topLeft.getPane().setStyle("-fx-background-color: #404040");
        topCenter = new Quadrant();
        topCenter.setPane((Pane) gameScene.lookup("#topCenterPane"));
        topCenter.setIsMarked(false);
        topCenter.getPane().setStyle("-fx-background-color: #404040");
        topRight = new Quadrant();
        topRight.setPane((Pane) gameScene.lookup("#topRightPane"));
        topRight.setIsMarked(false);
        topRight.getPane().setStyle("-fx-background-color: #404040");
        centerLeft = new Quadrant();
        centerLeft.setPane((Pane) gameScene.lookup("#centerLeftPane"));
        centerLeft.setIsMarked(false);
        centerLeft.getPane().setStyle("-fx-background-color: #404040");
        center = new Quadrant();
        center.setPane((Pane) gameScene.lookup("#centerPane"));
        center.setIsMarked(false);
        center.getPane().setStyle("-fx-background-color: #404040");
        centerRight = new Quadrant();
        centerRight.setPane((Pane) gameScene.lookup("#centerRightPane"));
        centerRight.setIsMarked(false);
        centerRight.getPane().setStyle("-fx-background-color: #404040");
        bottomLeft = new Quadrant();
        bottomLeft.setPane((Pane) gameScene.lookup("#bottomLeftPane"));
        bottomLeft.setIsMarked(false);
        bottomLeft.getPane().setStyle("-fx-background-color: #404040");
        bottomCenter = new Quadrant();
        bottomCenter.setPane((Pane) gameScene.lookup("#bottomCenterPane"));
        bottomCenter.setIsMarked(false);
        bottomCenter.getPane().setStyle("-fx-background-color: #404040");
        bottomRight = new Quadrant();
        bottomRight.setPane((Pane) gameScene.lookup("#bottomRightPane"));
        bottomRight.setIsMarked(false);
        bottomRight.getPane().setStyle("-fx-background-color: #404040");
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