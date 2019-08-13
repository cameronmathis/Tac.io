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
    private int numberOfPlayers = 0;
    private boolean ableToUndo = false;
    private Quadrant currentQuadrant;
    private Quadrant previousQuadrant;
    private Quadrant previousPreviousQuadrant;
    private int counter;
    private boolean gameOver;

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
        pauseBtn = (Button) gameScene.lookup("#pause");
        undoBtn = (Button) gameScene.lookup("#undo");

        /**
         * SHORTCUT KEYS
         */
        paused = false;

        openingScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && PopUp == null) {
                start();
            } else if (event.getCode() == KeyCode.ENTER && PopUp != null) {
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
                        TextField name2 = (TextField) enterTwoNamesPopUpPane.lookup("#player1Name");
                        player1.setPlayerName(name2.getText());
                    } else {
                        player2.setPlayerName("player2");
                    }
                }
                resume();
                pauseBtn.setDisable(false);
                pauseBtn.setDisable(false);
            }
        });

        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE && !paused) {
                pause();
            } else if (event.getCode() == KeyCode.ENTER && PopUp != null) {
                resume();
                pauseBtn.setDisable(false);
                pauseBtn.setDisable(false);
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
            enterOneNamePopUp();
            player2.setPlayerName("Computer");
        });
        Button twoPlayersBtn = (Button) numberOfPlayersPopUpPane.lookup("#twoPlayers");
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
        });
    }

    /**
     * ENTER TWO NAMES POPUP
     * PopUp to ask for both player names
     */
    private void enterTwoNamesPopUp() {
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
                TextField name2 = (TextField) enterTwoNamesPopUpPane.lookup("#player1Name");
                player1.setPlayerName(name2.getText());
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
            try {
                System.exit(0);
                PopUp.hide();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
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
        boolean start = true;
        if (currentQuadrant == null) {
            start = true;
        } else {
            start = false;
        }

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
                        getQuadrantToMark(quadrant);
                    }
                }
            } else if (!gameOver && numberOfPlayers == 2 && player2.getTurn()) {
                quadrant.getPane().setStyle("-fx-background-color: #0000ff");
                quadrant.setIsMarked(true);
                quadrant.setPlayerPlayed(player2);
                player2.setTurn(false);
                player1.setTurn(true);
                checkIfWon(quadrant, player2);
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
            pauseBtn.setDisable(true);
            undoBtn.setDisable(true);
            player.setGamesWon(player.getGamesWon() + 1);
            gameOverPopUp(player);
        }
        gameOver = won;
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
            pauseBtn.setDisable(true);
            undoBtn.setDisable(true);
            tiePopUp();
            gameOver = true;
        }
        gameOver = false;
    }

    /**
     * GET QUADRANT TO MARK METHOD
     * Find the best quadrant to mark
     */
    private void getQuadrantToMark(Quadrant quadrant) {
        previousPreviousQuadrant = previousQuadrant;
        previousQuadrant = quadrant;

        //Offensive Moves
        if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topRight, player2);
                    return;
                }
            } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!topCenter.getIsMarked()) {
                    topCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    topCenter.setIsMarked(true);
                    topCenter.setPlayerPlayed(player2);
                    currentQuadrant = topCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topCenter, player2);
                    return;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomLeft, player2);
                    return;
                }
            } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!centerLeft.getIsMarked()) {
                    centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    centerLeft.setIsMarked(true);
                    centerLeft.setPlayerPlayed(player2);
                    currentQuadrant = centerLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(centerLeft, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomRight, player2);
                    return;
                }
            } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(center, player2);
                    return;
                }
            }
        } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topRight, player2);
                    return;
                }
            } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topLeft, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!bottomCenter.getIsMarked()) {
                    bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomCenter.setIsMarked(true);
                    bottomCenter.setPlayerPlayed(player2);
                    currentQuadrant = bottomCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomCenter, player2);
                    return;
                }
            } else if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(center, player2);
                    return;
                }
            }
        } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topLeft, player2);
                    return;
                }
            } else if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!topCenter.getIsMarked()) {
                    topCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    topCenter.setIsMarked(true);
                    topCenter.setPlayerPlayed(player2);
                    currentQuadrant = topCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topCenter, player2);
                    return;
                }
            } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomRight, player2);
                    return;
                }
            } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!centerRight.getIsMarked()) {
                    centerRight.getPane().setStyle("-fx-background-color: #0000ff");
                    centerRight.setIsMarked(true);
                    centerRight.setPlayerPlayed(player2);
                    currentQuadrant = centerRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(centerRight, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomLeft, player2);
                    return;
                }
            } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(center, player2);
                    return;
                }
            }
        } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomLeft, player2);
                    return;
                }
            } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topLeft, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!centerRight.getIsMarked()) {
                    centerRight.getPane().setStyle("-fx-background-color: #0000ff");
                    centerRight.setIsMarked(true);
                    centerRight.setPlayerPlayed(player2);
                    currentQuadrant = centerRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(centerRight, player2);
                    return;
                }
            } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(center, player2);
                    return;
                }
            }
        } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomRight, player2);
                    return;
                }
            } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topLeft, player2);
                    return;
                }
            } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomLeft, player2);
                    return;
                }
            } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topRight, player2);
                    return;
                }
            } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
                if (!bottomCenter.getIsMarked()) {
                    bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomCenter.setIsMarked(true);
                    bottomCenter.setPlayerPlayed(player2);
                    currentQuadrant = bottomCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomCenter, player2);
                    return;
                }
            } else if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
                if (!topCenter.getIsMarked()) {
                    topCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    topCenter.setIsMarked(true);
                    topCenter.setPlayerPlayed(player2);
                    currentQuadrant = topCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topCenter, player2);
                    return;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
                if (!centerRight.getIsMarked()) {
                    centerRight.getPane().setStyle("-fx-background-color: #0000ff");
                    centerRight.setIsMarked(true);
                    centerRight.setPlayerPlayed(player2);
                    currentQuadrant = centerRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(centerRight, player2);
                    return;
                }
            } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
                if (!centerLeft.getIsMarked()) {
                    centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    centerLeft.setIsMarked(true);
                    centerLeft.setPlayerPlayed(player2);
                    currentQuadrant = centerLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(centerLeft, player2);
                    return;
                }
            }
        } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomRight, player2);
                    return;
                }
            } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topRight, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!centerLeft.getIsMarked()) {
                    centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    centerLeft.setIsMarked(true);
                    centerLeft.setPlayerPlayed(player2);
                    currentQuadrant = centerLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(centerLeft, player2);
                    return;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(center, player2);
                    return;
                }
            }
        } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomRight, player2);
                    return;
                }
            } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!bottomCenter.getIsMarked()) {
                    bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomCenter.setIsMarked(true);
                    bottomCenter.setPlayerPlayed(player2);
                    currentQuadrant = bottomCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomCenter, player2);
                    return;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topLeft, player2);
                    return;
                }
            } else if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!centerLeft.getIsMarked()) {
                    centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    centerLeft.setIsMarked(true);
                    centerLeft.setPlayerPlayed(player2);
                    currentQuadrant = centerLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(centerLeft, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topRight, player2);
                    return;
                }
            } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(center, player2);
                    return;
                }
            }
        } else if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomRight, player2);
                    return;
                }
            } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomLeft, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!topCenter.getIsMarked()) {
                    topCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    topCenter.setIsMarked(true);
                    topCenter.setPlayerPlayed(player2);
                    currentQuadrant = topCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topCenter, player2);
                    return;
                }
            } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(center, player2);
                    return;
                }
            }
        } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player2)) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player2)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomLeft, player2);
                    return;
                }
            } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player2)) {
                if (!bottomCenter.getIsMarked()) {
                    bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomCenter.setIsMarked(true);
                    bottomCenter.setPlayerPlayed(player2);
                    currentQuadrant = bottomCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(bottomCenter, player2);
                    return;
                }
            } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player2)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topRight, player2);
                    return;
                }
            } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player2)) {
                if (!centerRight.getIsMarked()) {
                    centerRight.getPane().setStyle("-fx-background-color: #0000ff");
                    centerRight.setIsMarked(true);
                    centerRight.setPlayerPlayed(player2);
                    currentQuadrant = centerRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(centerRight, player2);
                    return;
                }
            } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player2)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(topLeft, player2);
                    return;
                }
            } else if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player2)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(center, player2);
                    return;
                }
            }
        }

        //Defensive Moves
        if (quadrant.getPane().getId().equals("topLeftPane")) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player1)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                if (!topCenter.getIsMarked()) {
                    topCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    topCenter.setIsMarked(true);
                    topCenter.setPlayerPlayed(player2);
                    currentQuadrant = topCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player1)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                if (!centerLeft.getIsMarked()) {
                    centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    centerLeft.setIsMarked(true);
                    centerLeft.setPlayerPlayed(player2);
                    currentQuadrant = centerLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("topCenterPane")) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                if (!bottomCenter.getIsMarked()) {
                    bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomCenter.setIsMarked(true);
                    bottomCenter.setPlayerPlayed(player2);
                    currentQuadrant = bottomCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player1)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("topRightPane")) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player1)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                if (!topCenter.getIsMarked()) {
                    topCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    topCenter.setIsMarked(true);
                    topCenter.setPlayerPlayed(player2);
                    currentQuadrant = topCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player1)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                if (!centerRight.getIsMarked()) {
                    centerRight.getPane().setStyle("-fx-background-color: #0000ff");
                    centerRight.setIsMarked(true);
                    centerRight.setPlayerPlayed(player2);
                    currentQuadrant = centerRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("centerLeftPane")) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                if (!centerRight.getIsMarked()) {
                    centerRight.getPane().setStyle("-fx-background-color: #0000ff");
                    centerRight.setIsMarked(true);
                    centerRight.setPlayerPlayed(player2);
                    currentQuadrant = centerRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player1)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("centerPane")) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player1)) {
                if (!bottomCenter.getIsMarked()) {
                    bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomCenter.setIsMarked(true);
                    bottomCenter.setPlayerPlayed(player2);
                    currentQuadrant = bottomCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player1)) {
                if (!topCenter.getIsMarked()) {
                    topCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    topCenter.setIsMarked(true);
                    topCenter.setPlayerPlayed(player2);
                    currentQuadrant = topCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player1)) {
                if (!centerRight.getIsMarked()) {
                    centerRight.getPane().setStyle("-fx-background-color: #0000ff");
                    centerRight.setIsMarked(true);
                    centerRight.setPlayerPlayed(player2);
                    currentQuadrant = centerRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player1)) {
                if (!centerLeft.getIsMarked()) {
                    centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    centerLeft.setIsMarked(true);
                    centerLeft.setPlayerPlayed(player2);
                    currentQuadrant = centerLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("centerRightPane")) {
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                if (!centerLeft.getIsMarked()) {
                    centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    centerLeft.setIsMarked(true);
                    centerLeft.setPlayerPlayed(player2);
                    currentQuadrant = centerLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player1)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("bottomLeftPane")) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player1)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                if (!bottomCenter.getIsMarked()) {
                    bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomCenter.setIsMarked(true);
                    bottomCenter.setPlayerPlayed(player2);
                    currentQuadrant = bottomCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(player1)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                if (!centerLeft.getIsMarked()) {
                    centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    centerLeft.setIsMarked(true);
                    centerLeft.setPlayerPlayed(player2);
                    currentQuadrant = centerLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("bottomCenterPane")) {
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                if (!bottomRight.getIsMarked()) {
                    bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomRight.setIsMarked(true);
                    bottomRight.setPlayerPlayed(player2);
                    currentQuadrant = bottomRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(player1)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                if (!topCenter.getIsMarked()) {
                    topCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    topCenter.setIsMarked(true);
                    topCenter.setPlayerPlayed(player2);
                    currentQuadrant = topCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(player1)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        } else if (quadrant.getPane().getId().equals("bottomRightPane")) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(player1)) {
                if (!bottomLeft.getIsMarked()) {
                    bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomLeft.setIsMarked(true);
                    bottomLeft.setPlayerPlayed(player2);
                    currentQuadrant = bottomLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(player1)) {
                if (!bottomCenter.getIsMarked()) {
                    bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
                    bottomCenter.setIsMarked(true);
                    bottomCenter.setPlayerPlayed(player2);
                    currentQuadrant = bottomCenter;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(player1)) {
                if (!topRight.getIsMarked()) {
                    topRight.getPane().setStyle("-fx-background-color: #0000ff");
                    topRight.setIsMarked(true);
                    topRight.setPlayerPlayed(player2);
                    currentQuadrant = topRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(player1)) {
                if (!centerRight.getIsMarked()) {
                    centerRight.getPane().setStyle("-fx-background-color: #0000ff");
                    centerRight.setIsMarked(true);
                    centerRight.setPlayerPlayed(player2);
                    currentQuadrant = centerRight;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(player1)) {
                if (!topLeft.getIsMarked()) {
                    topLeft.getPane().setStyle("-fx-background-color: #0000ff");
                    topLeft.setIsMarked(true);
                    topLeft.setPlayerPlayed(player2);
                    currentQuadrant = topLeft;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(player1)) {
                if (!center.getIsMarked()) {
                    center.getPane().setStyle("-fx-background-color: #0000ff");
                    center.setIsMarked(true);
                    center.setPlayerPlayed(player2);
                    currentQuadrant = center;
                    player2.setTurn(false);
                    this.player1.setTurn(true);
                    checkIfWon(quadrant, player2);
                    return;
                }
            }
        }

        //Random Moves
        if (!center.getIsMarked()) {
            center.getPane().setStyle("-fx-background-color: #0000ff");
            center.setIsMarked(true);
            center.setPlayerPlayed(player2);
            currentQuadrant = center;
            player2.setTurn(false);
            this.player1.setTurn(true);
            checkIfWon(center, player2);
            return;
        } else if (!topLeft.getIsMarked()) {
            topLeft.getPane().setStyle("-fx-background-color: #0000ff");
            topLeft.setIsMarked(true);
            topLeft.setPlayerPlayed(player2);
            currentQuadrant = topLeft;
            player2.setTurn(false);
            this.player1.setTurn(true);
            checkIfWon(topLeft, player2);
            return;
        } else if (!topRight.getIsMarked()) {
            topRight.getPane().setStyle("-fx-background-color: #0000ff");
            topRight.setIsMarked(true);
            topRight.setPlayerPlayed(player2);
            currentQuadrant = topRight;
            player2.setTurn(false);
            this.player1.setTurn(true);
            checkIfWon(topRight, player2);
            return;
        } else if (!bottomLeft.getIsMarked()) {
            bottomLeft.getPane().setStyle("-fx-background-color: #0000ff");
            bottomLeft.setIsMarked(true);
            bottomLeft.setPlayerPlayed(player2);
            currentQuadrant = bottomLeft;
            player2.setTurn(false);
            this.player1.setTurn(true);
            checkIfWon(bottomLeft, player2);
            return;
        } else if (!bottomRight.getIsMarked()) {
            bottomRight.getPane().setStyle("-fx-background-color: #0000ff");
            bottomRight.setIsMarked(true);
            bottomRight.setPlayerPlayed(player2);
            currentQuadrant = bottomRight;
            player2.setTurn(false);
            this.player1.setTurn(true);
            checkIfWon(bottomRight, player2);
            return;
        } else if (!topCenter.getIsMarked()) {
            topCenter.getPane().setStyle("-fx-background-color: #0000ff");
            topCenter.setIsMarked(true);
            topCenter.setPlayerPlayed(player2);
            currentQuadrant = topCenter;
            player2.setTurn(false);
            this.player1.setTurn(true);
            checkIfWon(topCenter, player2);
            return;
        } else if (!centerLeft.getIsMarked()) {
            centerLeft.getPane().setStyle("-fx-background-color: #0000ff");
            centerLeft.setIsMarked(true);
            centerLeft.setPlayerPlayed(player2);
            currentQuadrant = centerLeft;
            player2.setTurn(false);
            this.player1.setTurn(true);
            checkIfWon(centerLeft, player2);
            return;
        } else if (!centerRight.getIsMarked()) {
            centerRight.getPane().setStyle("-fx-background-color: #0000ff");
            centerRight.setIsMarked(true);
            centerRight.setPlayerPlayed(player2);
            currentQuadrant = centerRight;
            player2.setTurn(false);
            this.player1.setTurn(true);
            checkIfWon(centerLeft, player2);
            return;
        } else if (!bottomCenter.getIsMarked()) {
            bottomCenter.getPane().setStyle("-fx-background-color: #0000ff");
            bottomCenter.setIsMarked(true);
            bottomCenter.setPlayerPlayed(player2);
            currentQuadrant = bottomCenter;
            player2.setTurn(false);
            this.player1.setTurn(true);
            checkIfWon(bottomCenter, player2);
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
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);
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
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);
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