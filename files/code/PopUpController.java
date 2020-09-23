package code;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.*;

import static code.ButtonController.*;
import static code.ComputerOpponentController.*;
import static code.ComputerOpponentState.*;
import static code.GameController.*;
import static code.PlayerController.*;

public class PopUpController {
    // Variables
    static Stage primaryStage;
    private static TitledPane selectDifficultyPopUpPane;
    private static Popup PopUp;
    private static AnchorPane openingPane;
    private static Scene openingScene;
    private static AnchorPane gamePane;
    private static Scene gameScene;
    private static boolean isPopUpShowing = false;
    private static boolean enterNumberPopUpShown = false;
    private static boolean wonGamePopUpShown = false;
    private static boolean tiedGamePopUpShown = false;

    /**
     * SETTER METHODS
     */
    static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    static void setPopUp(Popup p) {
        PopUp = p;
    }

    static void setOpeningPane(AnchorPane p) {
        openingPane = p;
    }

    static void setOpeningScene(Scene s) {
        openingScene = s;
    }

    static void setGamePane(AnchorPane p) {
        gamePane = p;
    }

    static void setGameScene(Scene s) {
        gameScene = s;
    }

    static void setIsPopUpShowing(boolean bool) { isPopUpShowing = bool; }

    static void setEnterNumberPopUpShown(boolean bool) {
        enterNumberPopUpShown = bool;
    }

    static void setWonGamePopUpShown(boolean bool) {
        wonGamePopUpShown = bool;
    }

    static void setTiedGamePopUpShown(boolean bool) {
        tiedGamePopUpShown = bool;
    }

    /**
     * GETTER METHODS
     */
    static Stage getPrimaryStage() {
        return primaryStage;
    }

    static Popup getPopUp() {
        return PopUp;
    }

    static AnchorPane getOpeningPane() {
        return openingPane;
    }

    static Scene getOpeningScene() {
        return openingScene;
    }

    static AnchorPane getGamePane() {
        return gamePane;
    }

    static Scene getGameScene() {
        return gameScene;
    }

    static boolean getIsPopUpShowing() { return isPopUpShowing;}

    static boolean getEnterNumberPopUpShown() {
        return enterNumberPopUpShown;
    }

    static boolean getWonGamePopUpShown() {
        return wonGamePopUpShown;
    }

    static boolean getTiedGamePopUpShown() {
        return tiedGamePopUpShown;
    }

    /**
     * NUMBER OF PLAYERS POPUP
     * PopUp to ask for number of players
     */
    static void numberOfPlayersPopUp() {
        setIsPopUpShowing(true);
        enterNumberPopUpShown = true;
        setFirstMovePlayer(null);

        PopUp = new Popup(); //creates new popup

        TitledPane numberOfPlayersPopUpPane = null; //calls popup menu created in 'numberOfPlayersPopUp.fxml' file

        try {
            numberOfPlayersPopUpPane = FXMLLoader.load(PopUpController.class.getResource("numberOfPlayersPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(numberOfPlayersPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        openingPane.requestFocus();

        Button onePlayerBtn = (Button) numberOfPlayersPopUpPane.lookup("#onePlayer");
        onePlayerBtn.setOnAction(event -> {
            setNumberOfPlayers(1);
            hidePopUp();
            enterNumberPopUpShown = false;
            getPlayer2().setUsername("Computer");
            enterOneNamePopUp();
        });

        Button twoPlayersBtn = (Button) numberOfPlayersPopUpPane.lookup("#twoPlayers");
        twoPlayersBtn.setOnAction(event -> {
            setNumberOfPlayers(2);
            hidePopUp();
            enterNumberPopUpShown = false;
            enterTwoNamesPopUp();
        });
    }

    /**
     * ENTER ONE NAME POPUP
     * PopUp to ask for player name
     */
    static void enterOneNamePopUp() {
        setIsPopUpShowing(true);
        PopUp = new Popup(); //creates new popup

        selectDifficultyPopUpPane = null; //calls popup menu created in 'enterOneNamePopUp.fxml' file

        try {
            selectDifficultyPopUpPane = FXMLLoader.load(PopUpController.class.getResource("enterOneNamePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(selectDifficultyPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        TextField t1 = ((TextField) selectDifficultyPopUpPane.lookup("#player1Name"));
        t1.requestFocus();

        Button enterBtn = (Button) selectDifficultyPopUpPane.lookup("#enter");
        enterBtn.setOnAction(event -> {
            if (!(((TextField) selectDifficultyPopUpPane.lookup("#player1Name")).getText().trim().equals(""))) {
                TextField name1 = (TextField) selectDifficultyPopUpPane.lookup("#player1Name");
                getPlayer1().setUsername(name1.getText().trim());
            } else {
                getPlayer1().setUsername("player1");
            }
            hidePopUp();
            selectDifficultyPopUp();
        });
    }

    /**
     * SELECT DIFFICULTY POPUP
     * PopUp to ask for player name
     */
    static void selectDifficultyPopUp() {
        setIsPopUpShowing(true);
        PopUp = new Popup(); //creates new popup

        selectDifficultyPopUpPane = null; //calls popup menu created in 'selectDifficultyPopUp.fxml' file

        try {
            selectDifficultyPopUpPane = FXMLLoader.load(PopUpController.class.getResource("selectDifficultyPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(selectDifficultyPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button beginnerBtn = (Button) selectDifficultyPopUpPane.lookup("#beginner");
        beginnerBtn.setOnAction(event -> {
            setCurrentState(BEGINNER);
            hidePopUp();
            startGame();
        });Button easyBtn = (Button) selectDifficultyPopUpPane.lookup("#easy");
        easyBtn.setOnAction(event -> {
            setCurrentState(EASY);
            hidePopUp();
            startGame();
        });
        Button mediumBtn = (Button) selectDifficultyPopUpPane.lookup("#medium");
        mediumBtn.setOnAction(event -> {
            setCurrentState(MEDIUM);
            hidePopUp();
            startGame();
        });
        Button hardBtn = (Button) selectDifficultyPopUpPane.lookup("#hard");
        hardBtn.setOnAction(event -> {
            setCurrentState(HARD);
            hidePopUp();
            startGame();
        });
    }

    /**
     * ENTER TWO NAMES POPUP
     * PopUp to ask for both player names
     */
    static void enterTwoNamesPopUp() {
        setIsPopUpShowing(true);
        PopUp = new Popup(); //creates new popup

        selectDifficultyPopUpPane = null; //calls popup menu created in 'enterTwoNamesPopUp.fxml' file

        try {
            selectDifficultyPopUpPane = FXMLLoader.load(PopUpController.class.getResource("enterTwoNamesPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(selectDifficultyPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        TextField t1 = ((TextField) selectDifficultyPopUpPane.lookup("#player1Name"));
        t1.requestFocus();

        Button enterBtn = (Button) selectDifficultyPopUpPane.lookup("#enter");
        enterBtn.setOnAction(event -> {
            if (!(((TextField) selectDifficultyPopUpPane.lookup("#player1Name")).getText().trim().equals(""))) {
                TextField name1 = (TextField) selectDifficultyPopUpPane.lookup("#player1Name");
                getPlayer1().setUsername(name1.getText().trim());
            } else {
                getPlayer1().setUsername("player1");
            }
            if (!(((TextField) selectDifficultyPopUpPane.lookup("#player2Name")).getText().trim().equals(""))) {
                TextField name2 = (TextField) selectDifficultyPopUpPane.lookup("#player2Name");
                getPlayer2().setUsername(name2.getText().trim());
            } else {
                getPlayer2().setUsername("player2");
            }
            hidePopUp();
            startGame();
        });
    }

    /**
     * PAUSED POPUP
     * PopUp for when the game is paused
     */
    static void pausedPopUp() {
        setIsPopUpShowing(true);
        PopUp = new Popup(); //creates new popup

        TitledPane pausedPupUpPane = null; //calls popup menu created in 'pausePopUp.fxml' file

        try {
            pausedPupUpPane = FXMLLoader.load(PopUpController.class.getResource("pausedPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(pausedPupUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        gamePane.requestFocus();

        Button resumeBtn = (Button) pausedPupUpPane.lookup("#resume");
        resumeBtn.setOnAction(browseDismissEvent -> {
            hidePopUp();
        });

        Button exitBtn = (Button) pausedPupUpPane.lookup("#exit");
        exitBtn.setOnAction(event -> {
            primaryStage.setScene(openingScene); //sets the scene on the stage
            primaryStage.show(); //shows the primaryStage
            hidePopUp();
            numberOfPlayersPopUp();
        });
    }

    /**
     * UNDO POPUP
     * PopUp for when you try and undo before a play is made
     */
    static void undoStartPopUp() {
        setIsPopUpShowing(true);
        PopUp = new Popup(); //creates new popup

        TitledPane undoStartPopUpPane = null; //calls popup menu created in 'undoStartPopUp.fxml' file

        try {
            undoStartPopUpPane = FXMLLoader.load(PopUpController.class.getResource("undoStartPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(undoStartPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) undoStartPopUpPane.lookup("#dismiss");
        dismissBtn.setOnAction(browseDismissEvent -> {
            hidePopUp();
        });
    }

    /**
     * UNDO POPUP
     * PopUp for when you try and undo twice
     */
    static void undoTwicePopUp() {
        setIsPopUpShowing(true);
        PopUp = new Popup(); //creates new popup

        TitledPane undoTwicePopUpPane = null; //calls popup menu created in 'undoTwicePopUp.fxml' file

        try {
            undoTwicePopUpPane = FXMLLoader.load(PopUpController.class.getResource("undoTwicePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(undoTwicePopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) undoTwicePopUpPane.lookup("#dismiss");
        dismissBtn.setOnAction(browseDismissEvent -> {
            hidePopUp();
        });
    }

    /**
     * IS ALREADY MARKED POPUP
     * PopUp for when a player tries to mark an already marked quadrant numerous times
     */
    static void isAlreadyMarkedPopUp() {
        setIsPopUpShowing(true);
        PopUp = new Popup(); //creates new popup

        TitledPane isAlreadyMarkedPopUpPane = null; //calls popup menu created in 'isAlreadyMarkedPopUp.fxml' file

        try {
            isAlreadyMarkedPopUpPane = FXMLLoader.load(PopUpController.class.getResource("isAlreadyMarkedPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(isAlreadyMarkedPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) isAlreadyMarkedPopUpPane.lookup("#dismiss");
        dismissBtn.setOnAction(event -> {
            hidePopUp();
        });
    }

    /**
     * GAME OVER POPUP
     * PopUp for when the game is over
     */
    static void gameOverPopUp(Player player) {
        setIsPopUpShowing(true);
        wonGamePopUpShown = true;

        PopUp = new Popup(); //creates new popup

        TitledPane gameOverPopUpPane = null; //calls popup menu created in 'gameOverPopUp.fxml' file

        try {
            gameOverPopUpPane = FXMLLoader.load(PopUpController.class.getResource("gameOverPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(gameOverPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        Text text = (Text) gameOverPopUpPane.getContent().lookup("#gameOverMessage");
        if (getNumberOfPlayers() == 2 || !player.getUsername().equals("Computer")) {
            text.setText("Congratulations " + player.getUsername() + "! You won!");
        } else if (getNumberOfPlayers() == 1 && player.getUsername().equals("Computer")) {
            text.setText("Sorry, you lost:(");
        }

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button playAgainBtn = (Button) gameOverPopUpPane.lookup("#playAgain");
        playAgainBtn.setOnAction(event -> {
            setGame();
            hidePopUp();
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
    static void tiePopUp() {
        setIsPopUpShowing(true);
        tiedGamePopUpShown = true;

        PopUp = new Popup(); //creates new popup

        TitledPane tiePopUpPane = null; //calls popup menu created in 'tiePopUp.fxml' file

        try {
            tiePopUpPane = FXMLLoader.load(PopUpController.class.getResource("tiePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(tiePopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button playAgainBtn = (Button) tiePopUpPane.lookup("#playAgain");
        playAgainBtn.setOnAction(event -> {
            setGame();
            hidePopUp();
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
     * HIDE POPUP METHOD
     * Hides current PopUp
     */
    static void hidePopUp() {
        setWonGamePopUpShown(false);
        setEnterNumberPopUpShown(false);
        setTiedGamePopUpShown(false);

        try {
            getPopUp().hide();
            setPopUp(null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        getPauseBtn().setDisable(false);
        getUndoBtn().setDisable(false);
        setIsPopUpShowing(false);
    }
}
