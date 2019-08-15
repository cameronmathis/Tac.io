package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Main extends Application {

    //Variables
    private Stage primaryStage;
    private AnchorPane openingPane;
    private Scene openingScene;
    private TitledPane enterOneNamePopUpPane;
    private TitledPane enterTwoNamesPopUpPane;
    private AnchorPane gamePane;
    private Scene gameScene;
    private boolean paused;
    private boolean enterNumberPopUpShown = false;
    private boolean enterNamePopUpShown = false;
    private boolean wonGamePopUpShown = false;
    private boolean tiedGamePopUpShown = false;
    private Popup PopUp;
    private Button pauseBtn;
    private Button undoBtn;
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
    private int numberOfPlayers;
    private boolean ableToUndo = false;
    private Quadrant currentQuadrant;
    private Quadrant previousQuadrant;
    private Quadrant previousPreviousQuadrant;
    private int counter;
    private boolean gameOver;
    private Player firstMovePlayer;

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

        openingScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && PopUp == null) {
            } else if ((event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.NUMPAD1) && enterNumberPopUpShown) {
                numberOfPlayers = 1;
                resume();
                enterNumberPopUpShown = false;
                enterOneNamePopUp();
                player2.setPlayerName("Computer");
            } else if ((event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.NUMPAD2) && enterNumberPopUpShown) {
                numberOfPlayers = 2;
                resume();
                enterNumberPopUpShown = false;
                enterTwoNamesPopUp();
            } else if (event.getCode() == KeyCode.ENTER && enterNamePopUpShown) {
                if (numberOfPlayers == 1) {
                    if (enterOneNamePopUpPane.lookup("#player1Name") != null) {
                        TextField name1 = (TextField) enterOneNamePopUpPane.lookup("#player1Name");
                        player1.setPlayerName(name1.getText());
                    } else {
                        player1.setPlayerName("player1");
                    }
                } else if (numberOfPlayers == 2) {
                    if (enterTwoNamesPopUpPane.lookup("#player1Name") != null) {
                        TextField name1 = (TextField) enterTwoNamesPopUpPane.lookup("#player1Name");
                        player1.setPlayerName(name1.getText());
                    } else {
                        player1.setPlayerName("player1");
                    }
                    if (enterTwoNamesPopUpPane.lookup("#player2Name") != null) {
                        TextField name2 = (TextField) enterTwoNamesPopUpPane.lookup("#player2Name");
                        player2.setPlayerName(name2.getText());
                    } else {
                        player2.setPlayerName("player2");
                    }
                }
                resume();
                start();
                enterNamePopUpShown = false;
                pauseBtn.setDisable(false);
                undoBtn.setDisable(false);
            }
        });

        gameScene.setOnKeyPressed(event -> {
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
     * NUMBER OF PLAYERS POPUP
     * PopUp to ask for number of players
     */
    private void numberOfPlayersPopUp() {
        enterNumberPopUpShown = true;

        PopUp = new Popup(); //creates new popup

        TitledPane numberOfPlayersPopUpPane = null; //calls popup menu created in 'numberOfPlayersPopUp.fxml' file

        try {
            numberOfPlayersPopUpPane = FXMLLoader.load(getClass().getResource("numberOfPlayersPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(numberOfPlayersPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button onePlayerBtn = (Button) numberOfPlayersPopUpPane.lookup("#onePlayer");
        onePlayerBtn.setOnAction(event -> {
            numberOfPlayers = 1;
            resume();
            enterNumberPopUpShown = false;
            enterOneNamePopUp();
            player2.setPlayerName("Computer");
        });
        Button twoPlayersBtn = (Button) numberOfPlayersPopUpPane.lookup("#twoPlayers");
        twoPlayersBtn.setOnAction(event -> {
            numberOfPlayers = 2;
            resume();
            enterNumberPopUpShown = false;
            enterTwoNamesPopUp();
        });
    }

    /**
     * ENTER ONE NAME POPUP
     * PopUp to ask for one player name
     */
    private void enterOneNamePopUp() {
        enterNamePopUpShown = true;

        PopUp = new Popup(); //creates new popup

        enterOneNamePopUpPane = null; //calls popup menu created in 'enterOneNamePopUp.fxml' file

        try {
            enterOneNamePopUpPane = FXMLLoader.load(getClass().getResource("enterOneNamePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(enterOneNamePopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button enterBtn = (Button) enterOneNamePopUpPane.lookup("#enter");
        enterBtn.setOnAction(event -> {
            if (enterOneNamePopUpPane.lookup("#player1Name") != null) {
                TextField name1 = (TextField) enterOneNamePopUpPane.lookup("#player1Name");
                player1.setPlayerName(name1.getText());
            } else {
                player1.setPlayerName("player1");
            }
            resume();
            start();
            enterNamePopUpShown = false;
        });
    }

    /**
     * ENTER TWO NAMES POPUP
     * PopUp to ask for both player names
     */
    private void enterTwoNamesPopUp() {
        enterNamePopUpShown = true;
        PopUp = new Popup(); //creates new popup

        enterTwoNamesPopUpPane = null; //calls popup menu created in 'enterTwoNamesPopUp.fxml' file

        try {
            enterTwoNamesPopUpPane = FXMLLoader.load(getClass().getResource("enterTwoNamesPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(enterTwoNamesPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button enterBtn = (Button) enterTwoNamesPopUpPane.lookup("#enter");
        enterBtn.setOnAction(event -> {
            if (enterTwoNamesPopUpPane.lookup("#player1Name") != null) {
                TextField name1 = (TextField) enterTwoNamesPopUpPane.lookup("#player1Name");
                player1.setPlayerName(name1.getText());
            } else {
                player1.setPlayerName("player1");
            }
            if (enterTwoNamesPopUpPane.lookup("#player2Name") != null) {
                TextField name2 = (TextField) enterTwoNamesPopUpPane.lookup("#player2Name");
                player2.setPlayerName(name2.getText());
            } else {
                player2.setPlayerName("player2");
            }
            resume();
            start();
            enterNamePopUpShown = false;
        });
    }

    /**
     * START METHOD
     * Starts the game
     */
    private void start() {
        setGame();
        pauseBtn.setDisable(false);
        undoBtn.setDisable(false);
        primaryStage.setScene(gameScene); //sets the scene on the stage
        primaryStage.show();
    }

    /**
     * PAUSE METHOD
     * Pause the game
     */
    private void pause() {
        paused = true;
        pauseBtn.setDisable(true);
        undoBtn.setDisable(true);
        pausedPopUp();
    }

    /**
     * UNDO METHOD
     * Undo the last move
     */
    private void undo() {
        if (ableToUndo && numberOfPlayers == 2) {
            currentQuadrant.setIsMarked(false);
            currentQuadrant = previousQuadrant;
            previousQuadrant = previousPreviousQuadrant;
            if (player1.getTurn()) {
                player1.setTurn(false);
                player2.setTurn(true);
            } else if (player2.getTurn()) {
                player2.setTurn(false);
                player1.setTurn(true);
            }
        } else if (ableToUndo && numberOfPlayers == 1) {
            if (player1.getTurn()) {
                currentQuadrant.setIsMarked(false);
                currentQuadrant = previousPreviousQuadrant;
                previousQuadrant.setIsMarked(false);
                previousQuadrant = null;
                previousPreviousQuadrant = null;
            } else if (player2.getTurn()) {
                currentQuadrant.setIsMarked(false);
                currentQuadrant = previousQuadrant;
                previousQuadrant = null;
                previousPreviousQuadrant = null;
                player2.setTurn(false);
                player1.setTurn(true);
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
        resumeBtn.setOnAction(browseDismissEvent -> {
            resume();
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);
        });

        Button exitBtn = (Button) pausedPupUpPane.lookup("#exit");
        exitBtn.setOnAction(event -> {
            primaryStage.setScene(openingScene); //sets the scene on the stage
            primaryStage.show(); //shows the primaryStage
            resume();
            numberOfPlayersPopUp();
        });
    }

    /**
     * UNDO POPUP
     * PopUp for when you try and undo before a play is made
     */
    private void undoStartPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane undoStartPopUpPane = null; //calls popup menu created in 'undoStartPopUp.fxml' file

        try {
            undoStartPopUpPane = FXMLLoader.load(getClass().getResource("undoStartPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(undoStartPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) undoStartPopUpPane.lookup("#dismiss");
        dismissBtn.setOnAction(browseDismissEvent -> {
            resume();
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);
        });
    }

    /**
     * UNDO POPUP
     * PopUp for when you try and undo twice
     */
    private void undoTwicePopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane undoTwicePopUpPane = null; //calls popup menu created in 'undoTwicePopUp.fxml' file

        try {
            undoTwicePopUpPane = FXMLLoader.load(getClass().getResource("undoTwicePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(undoTwicePopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) undoTwicePopUpPane.lookup("#dismiss");
        dismissBtn.setOnAction(browseDismissEvent -> {
            resume();
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);
        });
    }

    /**
     * CHECK MARKED METHOD
     * Check if square is playable and responds accordingly
     */
    private boolean markQuadrant(Quadrant quadrant, boolean isMarked) {
        boolean start;
        start = currentQuadrant == null;

        if ((numberOfPlayers == 2 && isMarked) || (player1.getTurn() && isMarked)) {
            if (!previousQuadrant.equals(quadrant)) {
                counter = 0;
            }
            counter++;
            if (counter >= 3) {
                counter = 0;
                pauseBtn.setDisable(true);
                undoBtn.setDisable(true);
                isAlreadyMarkedPopUp();
            }
        } else {
            if (!start) {
                previousPreviousQuadrant = previousQuadrant;
                previousQuadrant = currentQuadrant;
            }
            currentQuadrant = quadrant;
            ableToUndo = true;
            counter = 0;
            if (!gameOver && player1.getTurn()) {
                quadrant.getPane().setStyle("-fx-background-color: #ff0000");
                quadrant.setIsMarked(true);
                quadrant.setPlayerPlayed(player1);
                player1.setTurn(false);
                player2.setTurn(true);
                if (!checkIfWon(quadrant, player1)) {
                    checkIfTie();
                    if (!gameOver && numberOfPlayers == 1 && player2.getTurn()) {
                        quadrant = getQuadrantToMark(quadrant);
                        quadrant.getPane().setStyle("-fx-background-color: #0000ff");
                        quadrant.setIsMarked(true);
                        quadrant.setPlayerPlayed(player2);
                        currentQuadrant = quadrant;
                        player2.setTurn(false);
                        this.player1.setTurn(true);
                        if (!checkIfWon(quadrant, player2)) {
                            checkIfTie();
                        }
                    }
                }
            } else if (!gameOver && numberOfPlayers == 2 && player2.getTurn()) {
                quadrant.getPane().setStyle("-fx-background-color: #0000ff");
                quadrant.setIsMarked(true);
                quadrant.setPlayerPlayed(player2);
                player2.setTurn(false);
                player1.setTurn(true);
                if (!checkIfWon(quadrant, player2)) {
                    checkIfTie();
                }
            }
        }

        return true;
    }

    /**
     * IS ALREADY MARKED POPUP
     * PopUp for when a player tries to mark an already marked quadrant numerous times
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
        dismissBtn.setOnAction(event -> {
            resume();
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);
        });
    }

    /**
     * CHECK IF WON METHOD
     * Check if player won
     */
    private boolean checkIfWon(Quadrant quadrant, Player player) {
        if (quadrant.getPane().getId().equals("topLeftPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("topCenterPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("topRightPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("centerLeftPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("centerPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("centerRightPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("bottomLeftPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("bottomCenterPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        } else if (quadrant.getPane().getId().equals("bottomRightPane") && quadrant.getPlayerPlayed().equals(player)) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player)) {
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player)) {
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player)) {
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player)) {
                    gameOver = true;
                    pauseBtn.setDisable(true);
                    undoBtn.setDisable(true);
                    player.setGamesWon(player.getGamesWon() + 1);
                    gameOverPopUp(player);
                    return true;
                }
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * CHECK IF TIE METHOD
     * Check if players tied
     */
    private void checkIfTie() {
        if (topLeft.getIsMarked() && topCenter.getIsMarked() && topRight.getIsMarked() && centerLeft.getIsMarked() &&
                center.getIsMarked() && centerRight.getIsMarked() && bottomLeft.getIsMarked() && bottomCenter.getIsMarked() &&
                bottomRight.getIsMarked()) {
            pauseBtn.setDisable(true);
            undoBtn.setDisable(true);
            tiePopUp();
            gameOver = true;
        }
    }

    /**
     * GET QUADRANT TO MARK METHOD
     * Find the best quadrant to mark
     */
    private Quadrant getQuadrantToMark(Quadrant quadrant) {
        previousPreviousQuadrant = previousQuadrant;
        previousQuadrant = quadrant;

        //Primary Offensive Moves
        if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
        } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        }

        //Defensive Moves
        switch (quadrant.getPane().getId()) {
            case "topLeftPane":
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player1)) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player1)) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "topCenterPane":
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player1)) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "topRightPane":
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player1)) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player1)) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "centerLeftPane":
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player1)) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "centerPane":
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player1)) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player1)) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player1)) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player1)) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                break;
            case "centerRightPane":
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player1)) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "bottomLeftPane":
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player1)) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player1)) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "bottomCenterPane":
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player1)) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "bottomRightPane":
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player1)) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player1)) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
        }

        //Secondary Offensive Moves
        if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
            while (true) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (topCenter.getPlayerPlayed() == null && topRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(1);
                            switch (n2) {
                                case 0 :
                                    return topCenter;
                                case 1 :
                                    return topRight;
                            }
                        }
                    case 1:
                        if (centerLeft.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(1);
                            switch (n2) {
                                case 0 :
                                    return centerLeft;
                                case 1 :
                                    return bottomLeft;
                            }
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(1);
                            switch (n2) {
                                case 0 :
                                    return center;
                                case 1 :
                                    return bottomRight;
                            }
                        }
                }
            }
        } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
            while (true) {
                Random rand = new Random();
                int n = rand.nextInt(1);

                switch (n) {
                    case 0:
                        if (topLeft.getPlayerPlayed() == null && topRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(1);
                            switch (n2) {
                                case 0 :
                                    return topLeft;
                                case 1 :
                                    return topRight;
                            }
                        }
                    case 1:
                        if (center.getPlayerPlayed() == null  && bottomCenter.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(1);
                            switch (n2) {
                                case 0 :
                                    return center;
                                case 1 :
                                    return bottomCenter;
                            }
                        }
                }
            }
        } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
            while (true) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (topCenter.getPlayerPlayed() == null) {
                            return topCenter;
                        }
                    case 1:
                        if (centerRight.getPlayerPlayed() == null) {
                            return centerRight;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null) {
                            return center;
                        }
                }
            }
        } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
            while (true) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (topLeft.getPlayerPlayed() == null) {
                            return topLeft;
                        }
                    case 1:
                        if (bottomLeft.getPlayerPlayed() == null) {
                            return bottomLeft;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null) {
                            return center;
                        }
                }
            }
        } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
            while (true) {
                Random rand = new Random();
                int n = rand.nextInt(8);

                switch (n) {
                    case 0:
                        if (topLeft.getPlayerPlayed() == null) {
                            return topLeft;
                        }
                    case 1:
                        if (topCenter.getPlayerPlayed() == null) {
                            return topCenter;
                        }
                    case 2:
                        if (topRight.getPlayerPlayed() == null) {
                            return topRight;
                        }
                    case 3:
                        if (centerLeft.getPlayerPlayed() == null) {
                            return centerLeft;
                        }
                    case 4:
                        if (centerRight.getPlayerPlayed() == null) {
                            return centerRight;
                        }
                    case 5:
                        if (bottomLeft.getPlayerPlayed() == null) {
                            return bottomLeft;
                        }
                    case 6:
                        if (bottomCenter.getPlayerPlayed() == null) {
                            return bottomCenter;
                        }
                    case 7:
                        if (bottomRight.getPlayerPlayed() == null) {
                            return bottomRight;
                        }
                }
            }
        } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
            while (true) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (topRight.getPlayerPlayed() == null) {
                            return topRight;
                        }
                    case 1:
                        if (bottomRight.getPlayerPlayed() == null) {
                            return bottomRight;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null) {
                            return center;
                        }
                }
            }
        } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
            while (true) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (centerLeft.getPlayerPlayed() == null) {
                            return centerLeft;
                        }
                    case 1:
                        if (bottomCenter.getPlayerPlayed() == null) {
                            return bottomCenter;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null) {
                            return center;
                        }
                }
            }
        } else if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
            while (true) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (bottomLeft.getPlayerPlayed() == null) {
                            return bottomLeft;
                        }
                    case 1:
                        if (bottomRight.getPlayerPlayed() == null) {
                            return bottomRight;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null) {
                            return center;
                        }
                }
            }
        } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
            while (true) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (centerRight.getPlayerPlayed() == null) {
                            return centerRight;
                        }
                    case 1:
                        if (bottomCenter.getPlayerPlayed() == null) {
                            return bottomCenter;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null) {
                            return center;
                        }
                }
            }
        }

        //Random Moves
        while (true) {
            Random rand = new Random();
            int n = rand.nextInt(9);

            switch (n) {
                case 0:
                    if (!center.getIsMarked()) {
                        return center;
                    } else {
                        break;
                    }
                case 1:
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    } else {
                        break;
                    }
                case 2:
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    } else {
                        break;
                    }
                case 3:
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    } else {
                        break;
                    }
                case 4:
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    } else {
                        break;
                    }
                case 5:
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    } else {
                        break;
                    }
                case 6:
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    } else {
                        break;
                    }
                case 7:
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    } else {
                        break;
                    }
                case 8:
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    } else {
                        break;
                    }
            }
        }
    }

    /**
     * GAME OVER POPUP
     * PopUp for when the game is over
     */
    private void gameOverPopUp(Player player) {
        wonGamePopUpShown = true;

        PopUp = new Popup(); //creates new popup

        TitledPane gameOverPopUpPane = null; //calls popup menu created in 'gameOverPopUp.fxml' file

        try {
            gameOverPopUpPane = FXMLLoader.load(getClass().getResource("gameOverPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(gameOverPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        Text text = (Text) gameOverPopUpPane.getContent().lookup("#gameOverMessage");
        if (numberOfPlayers == 2 || !player.getPlayerName().equals("Computer")) {
            text.setText("Congratulations " + player.getPlayerName() + "! You won!");
        } else if (numberOfPlayers == 1 && player.getPlayerName().equals("Computer")) {
            text.setText("Sorry, you lost:(");
        }

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button playAgainBtn = (Button) gameOverPopUpPane.lookup("#playAgain");
        playAgainBtn.setOnAction(event -> {
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);
            setGame();
            resume();
        });

        Button quitBtn = (Button) gameOverPopUpPane.lookup("#quit");
        quitBtn.setOnAction(event -> {
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
        tiedGamePopUpShown = true;

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
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);
            setGame();
            resume();
        });

        Button quitBtn = (Button) tiePopUpPane.lookup("#quit");
        quitBtn.setOnAction(event -> {
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
        if (firstMovePlayer == null) {
            player1.setTurn(true);
            player2.setTurn(false);
            firstMovePlayer = player1;
        } else if (firstMovePlayer.equals(player1)) {
            player1.setTurn(false);
            player2.setTurn(true);
            firstMovePlayer = player2;
        } else if (firstMovePlayer.equals(player2)) {
            player1.setTurn(true);
            player2.setTurn(false);
            firstMovePlayer = player1;
        }

        pauseBtn.setDisable(false);
        undoBtn.setDisable(false);

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

        gameOver = false;

        if (firstMovePlayer.equals(player2) && numberOfPlayers == 1) {
            Quadrant quadrant = getQuadrantToMark(topLeft);
            quadrant.getPane().setStyle("-fx-background-color: #0000ff");
            quadrant.setIsMarked(true);
            quadrant.setPlayerPlayed(player2);
            currentQuadrant = quadrant;
            player2.setTurn(false);
            this.player1.setTurn(true);
        }
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