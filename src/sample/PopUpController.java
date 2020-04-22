package sample;

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

import static sample.ButtonController.*;
import static sample.GameController.*;
import static sample.LeaderBoardController.*;
import static sample.PlayerController.*;

public class PopUpController {
    // Variables
    static Stage primaryStage;
    private static TitledPane accountLoginPopUpPane;
    private static TitledPane enterTwoNamesPopUpPane;
    private static Popup PopUp;
    private static AnchorPane openingPane;
    private static Scene openingScene;
    private static AnchorPane gamePane;
    private static Scene gameScene;
    private static boolean enterNumberPopUpShown = false;
    private static boolean enterNamePopUpShown = false;
    private static boolean wonGamePopUpShown = false;
    private static boolean tiedGamePopUpShown = false;

    /**
     * SETTER METHODS
     */
    static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    static void setEnterTwoNamesPopUpPane(TitledPane pane) {
        enterTwoNamesPopUpPane = pane;
    }

    static void setAccountLoginPopUpPane(TitledPane pane) {
        accountLoginPopUpPane = pane;
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

    static void setEnterNumberPopUpShown(boolean bool) { enterNumberPopUpShown = bool; }

    static void setEnterNamePopUpShown(boolean bool) { enterNamePopUpShown = bool; }

    static void setWonGamePopUpShown(boolean bool) { wonGamePopUpShown = bool; }

    static void setTiedGamePopUpShown(boolean bool) { tiedGamePopUpShown = bool; }

    /**
     * GETTER METHODS
     */
    static Stage getPrimaryStage() { return primaryStage; }

    static TitledPane getAccountLoginPopUpPane() {
        return accountLoginPopUpPane;
    }

    static TitledPane getEnterTwoNamesPopUpPane() { return enterTwoNamesPopUpPane; }

    static Popup getPopUp() { return PopUp; }

    static AnchorPane getOpeningPane() { return openingPane; }

    static Scene getOpeningScene() { return openingScene; }

    static AnchorPane getGamePane() { return gamePane; }

    static Scene getGameScene() { return gameScene; }

    static boolean getEnterNumberPopUpShown() { return enterNumberPopUpShown; }

    static boolean getEnterNamePopUpShown() { return enterNamePopUpShown; }

    static boolean getWonGamePopUpShown() { return wonGamePopUpShown; }

    static boolean getTiedGamePopUpShown() { return tiedGamePopUpShown; }

    /**
     * NUMBER OF PLAYERS POPUP
     * PopUp to ask for number of players
     */
    static void numberOfPlayersPopUp() {
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
            setAbleToUndo(false);
            resume();
            enterNumberPopUpShown = false;
            newOrReturningUserPopUp();
            getPlayer2().setUsername("Computer");
        });

        Button twoPlayersBtn = (Button) numberOfPlayersPopUpPane.lookup("#twoPlayers");
        twoPlayersBtn.setOnAction(event -> {
            setNumberOfPlayers(2);
            setAbleToUndo(true);
            resume();
            enterNumberPopUpShown = false;
            enterTwoNamesPopUp();
        });
    }

    /**
     * NEW OR RETURNING USER POPUP
     * PopUp to ask user if they are new or not
     */
    static void newOrReturningUserPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane newOrReturningUserPopUpPane = null; //calls popup menu created in 'newOrReturningUserPopUp.fxml' file

        try {
            newOrReturningUserPopUpPane = FXMLLoader.load(PopUpController.class.getResource("newOrReturningUserPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(newOrReturningUserPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        openingPane.requestFocus();

        Button createAccountBtn = (Button) newOrReturningUserPopUpPane.lookup("#createAccount");
        createAccountBtn.setOnAction(event -> {
            resume();
            createAccountPopUp();
        });

        Button loginBtn = (Button) newOrReturningUserPopUpPane.lookup("#login");
        loginBtn.setOnAction(event -> {
            resume();
            accountLoginPopUp();
        });
    }

    /**
     * ACCOUNT LOGIN POPUP
     * PopUp to login to account
     */
    static void accountLoginPopUp() {
        PopUp = new Popup(); //creates new popup

        accountLoginPopUpPane = null; //calls popup menu created in 'accountLoginPopUp.fxml' file

        try {
            accountLoginPopUpPane = FXMLLoader.load(PopUpController.class.getResource("accountLoginPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(accountLoginPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        TextField t1 = ((TextField) accountLoginPopUpPane.lookup("#username"));
        t1.requestFocus();

        Button enterBtn = (Button) accountLoginPopUpPane.lookup("#enter");
        TitledPane finalCreateAccountPopUpPane = accountLoginPopUpPane;
        enterBtn.setOnAction(event -> {
            TextField username;
            TextField password;
            username = (TextField) finalCreateAccountPopUpPane.lookup("#username");
            password = (TextField) finalCreateAccountPopUpPane.lookup("#password");

            //if (Database.contains(username.getText()) && Database.getUser(username).getPassword().equals(password)) {
            getPlayer1().setUsername(username.getText());
            resume();
            startGame();
            enterNumberPopUpShown = false;
            //} else {
            //    incorrectInformationPopUp();
            //}
            resume();
        });
    }

    /**
     * CREATE ACCOUNT POPUP
     * PopUp to create new account
     */
    static void createAccountPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane createAccountPopUpPane = null; //calls popup menu created in 'createAccountPopUp.fxml' file

        try {
            createAccountPopUpPane = FXMLLoader.load(PopUpController.class.getResource("createAccountPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(createAccountPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        TextField t1 = ((TextField) createAccountPopUpPane.lookup("#username"));
        t1.requestFocus();

        Button enterBtn = (Button) createAccountPopUpPane.lookup("#enter");
        TitledPane finalCreateAccountPopUpPane = createAccountPopUpPane;
        enterBtn.setOnAction(event -> {
            TextField username;
            TextField password;

            if (!(((TextField) finalCreateAccountPopUpPane.lookup("#username")).getText().equals(""))) {
                username = (TextField) finalCreateAccountPopUpPane.lookup("#username");
                if (username.getText().length() > 20) {
                    invalidUsernamePopUp();
                }
            } else {

            }
            if (!(((TextField) finalCreateAccountPopUpPane.lookup("#password")).getText().equals(""))) {
                password = (TextField) finalCreateAccountPopUpPane.lookup("#password");
                if (password.getText().length() > 20) {
                    invalidUsernamePopUp();
                } else {
                    getPlayer1().setUsername(password.getText());
                    resume();
                    startGame();
                    enterNamePopUpShown = false;
                }
            }

            //if (!Database.contains(username.getText())) {
            //  usernameAlreadyExistPopUp();
            //} else {
            //  createPlayer();
            //}
            resume();
        });
    }

    /**
     * USERNAME ALREADY EXISTS POPUP
     * PopUp for when a player tries to enter a username that already exist
     */
    static void usernameAlreadyExistPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane usernameAlreadyExistPopUpPane = null; //calls popup menu created in 'usernameAlreadyExistPopUp.fxml' file

        try {
            usernameAlreadyExistPopUpPane = FXMLLoader.load(PopUpController.class.getResource("usernameAlreadyExistPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(usernameAlreadyExistPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) usernameAlreadyExistPopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            resume();
            getPauseBtn().setDisable(false);
            getUndoBtn().setDisable(false);

            createAccountPopUp();
        });
    }

    /**
     * INVALID USERNAME POPUP
     * PopUp for when a player tries to enter an invalid username
     */
    static void invalidUsernamePopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane invalidUsernamePopUpPane = null; //calls popup menu created in 'invalidUsernamePopUp.fxml' file

        try {
            invalidUsernamePopUpPane = FXMLLoader.load(PopUpController.class.getResource("invalidUsernamePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(invalidUsernamePopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) invalidUsernamePopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            resume();
            getPauseBtn().setDisable(false);
            getUndoBtn().setDisable(false);

            createAccountPopUp();
        });
    }

    /**
     * INCORRECT INFORMATION POPUP
     * PopUp for when a player enters a wrong username or password
     */
    static void incorrectInformationPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane incorrectInformationPopUpPane = null; //calls popup menu created in 'incorrectInformationPopUp.fxml' file

        try {
            incorrectInformationPopUpPane = FXMLLoader.load(PopUpController.class.getResource("incorrectInformationPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(incorrectInformationPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) incorrectInformationPopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            resume();
            getPauseBtn().setDisable(false);
            getUndoBtn().setDisable(false);

            accountLoginPopUp();
        });
    }

    /**
     * ENTER TWO NAMES POPUP
     * PopUp to ask for both player names
     */
    static void enterTwoNamesPopUp() {
        enterNamePopUpShown = true;
        PopUp = new Popup(); //creates new popup

        enterTwoNamesPopUpPane = null; //calls popup menu created in 'enterTwoNamesPopUp.fxml' file

        try {
            enterTwoNamesPopUpPane = FXMLLoader.load(PopUpController.class.getResource("enterTwoNamesPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(enterTwoNamesPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        TextField t1 = ((TextField) enterTwoNamesPopUpPane.lookup("#player1Name"));
        t1.requestFocus();

        Button enterBtn = (Button) enterTwoNamesPopUpPane.lookup("#enter");
        enterBtn.setOnAction(event -> {
            if (!(((TextField) enterTwoNamesPopUpPane.lookup("#player1Name")).getText().equals(""))) {
                TextField name1 = (TextField) enterTwoNamesPopUpPane.lookup("#player1Name");
                getPlayer1().setUsername(name1.getText());
            } else {
                getPlayer1().setUsername("player1");
            }
            if (!(((TextField) enterTwoNamesPopUpPane.lookup("#player2Name")).getText().equals(""))) {
                TextField name2 = (TextField) enterTwoNamesPopUpPane.lookup("#player2Name");
                getPlayer2().setUsername(name2.getText());
            } else {
                getPlayer2().setUsername("player2");
            }
            resume();
            startGame();
            enterNamePopUpShown = false;

        });
    }

    /**
     * PAUSED POPUP
     * PopUp for when the game is paused
     */
    static void pausedPopUp() {
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
            resume();
            getPauseBtn().setDisable(false);
            getUndoBtn().setDisable(false);
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
    static void undoStartPopUp() {
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
            resume();
            getPauseBtn().setDisable(false);
            getUndoBtn().setDisable(false);
        });
    }

    /**
     * UNDO POPUP
     * PopUp for when you try and undo twice
     */
    static void undoTwicePopUp() {
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
            resume();
            getPauseBtn().setDisable(false);
            getUndoBtn().setDisable(false);
        });
    }

    /**
     * IS ALREADY MARKED POPUP
     * PopUp for when a player tries to mark an already marked quadrant numerous times
     */
    static void isAlreadyMarkedPopUp() {
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
            resume();
            getPauseBtn().setDisable(false);
            getUndoBtn().setDisable(false);
        });
    }

    /**
     * GAME OVER POPUP
     * PopUp for when the game is over
     */
    static void gameOverPopUp(Player player) {
        updateLeaderBoard(player);

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
            getPauseBtn().setDisable(false);
            getUndoBtn().setDisable(false);
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
    static void tiePopUp() {
        updateLeaderBoard(null);

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
            getPauseBtn().setDisable(false);
            getUndoBtn().setDisable(false);
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
}
