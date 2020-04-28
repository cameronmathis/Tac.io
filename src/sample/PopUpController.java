package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.*;

import static sample.ButtonController.*;
import static sample.GameController.*;
import static sample.PlayerController.*;

public class PopUpController {
    // Variables
    static Stage primaryStage;
    private static TitledPane accountLoginPopUpPane;
    private static TitledPane enterTwoNamesPopUpPane;
    private static TitledPane createAccountPopUpPane;
    private static Popup PopUp;
    private static AnchorPane openingPane;
    private static Scene openingScene;
    private static AnchorPane gamePane;
    private static Scene gameScene;
    private static boolean enterNumberPopUpShown = false;
    private static boolean accountLoginPopUpShown = false;
    private static boolean createAccountPopUpShown = false;
    private static boolean createAccountErrorPopUpShown = false;
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

    static void setCreateAccountPopUpPane(TitledPane pane) {
        createAccountPopUpPane = pane;
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

    static void setEnterNumberPopUpShown(boolean bool) {
        enterNumberPopUpShown = bool;
    }

    static void setAccountLoginPopUpShown(boolean bool) {
        accountLoginPopUpShown = bool;
    }

    static void setCreateAccountPopUpShown(boolean bool) {
        createAccountPopUpShown = bool;
    }

    static void setCreateAccountErrorPopUpShown(boolean bool) {
        createAccountErrorPopUpShown = bool;
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

    static TitledPane getAccountLoginPopUpPane() {
        return accountLoginPopUpPane;
    }

    static TitledPane getEnterTwoNamesPopUpPane() {
        return enterTwoNamesPopUpPane;
    }

    static TitledPane getCreateAccountPopUpPane() {
        return createAccountPopUpPane;
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

    static boolean getEnterNumberPopUpShown() {
        return enterNumberPopUpShown;
    }

    static boolean getAccountLoginPopUpShown() {
        return accountLoginPopUpShown;
    }

    static boolean getCreateAccountPopUpShown() {
        return createAccountPopUpShown;
    }

    static boolean getCreateAccountErrorPopUpShown() {
        return createAccountErrorPopUpShown;
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
            newOrReturningUserPopUp();
            getPlayer2().setUsername("Computer");
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
            hidePopUp();
            createAccountPopUp();
        });

        Button loginBtn = (Button) newOrReturningUserPopUpPane.lookup("#login");
        loginBtn.setOnAction(event -> {
            hidePopUp();
            accountLoginPopUp();
        });
    }

    /**
     * ACCOUNT LOGIN POPUP
     * PopUp to login to account
     */
    static void accountLoginPopUp() {
        PopUp = new Popup(); //creates new popup
        accountLoginPopUpShown = true;

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
        enterBtn.setOnAction(event -> {
            Player tempPlayer = new Player();
            TextField username = (TextField) accountLoginPopUpPane.lookup("#username");
            TextField password = (PasswordField) accountLoginPopUpPane.lookup("#password");
            if (username.getText().equals("") || (username.getText().length() > 250)) {
                hidePopUp();
                usernameDoesNotExistPopUp();
                return;
            } else if (false) {
                hidePopUp();
                usernameDoesNotExistPopUp();
                return;
            } else if (true) {
                tempPlayer.setUsername(username.getText());
            }

            if ((password.getText().length() < 7) || (password.getText().length() > 250)) {
                hidePopUp();
                incorrectPasswordPopUp();
                return;
            } else if (!password.getText().equals(password.getText())) {
                hidePopUp();
                incorrectPasswordPopUp();
                return;
            }
            setPlayer1(importPlayer(tempPlayer, username.getText(), password.getText(), 0, 0));
            hidePopUp();
            startGame();
            accountLoginPopUpShown = false;
        });
    }

    /**
     * CREATE ACCOUNT POPUP
     * PopUp to create new account
     */
    static void createAccountPopUp() {
        setCreateAccountPopUpShown(true);
        PopUp = new Popup(); //creates new popup
        createAccountPopUpShown = true;

        createAccountPopUpPane = null; //calls popup menu created in 'createAccountPopUp.fxml' file

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
        enterBtn.setOnAction(event -> {
            TextField username = (TextField) createAccountPopUpPane.lookup("#username");
            if (username.getText().equals("") || (username.getText().length() > 250)) {
                hidePopUp();
                invalidUsernamePopUp();
                return;
            } else if (false) {
                hidePopUp();
                usernameAlreadyExistPopUp();
                return;
            }

            TextField password1 = (PasswordField) createAccountPopUpPane.lookup("#password1");
            TextField password2 = (PasswordField) createAccountPopUpPane.lookup("#password2");
            if (!password1.getText().equals(password2.getText())) {
                hidePopUp();
                passwordsDontMatchPopUp();
                return;
            } else if (password1.getText().equals("") || (password1.getText().length() < 7) || (password1.getText().length() > 250)) {
                hidePopUp();
                invalidPasswordPopUp();
                return;
            }
            setPlayer1(createPlayer(new Player(), username.getText(), password1.getText()));
            hidePopUp();
            startGame();
            createAccountPopUpShown = false;
        });
    }

    /**
     * USERNAME DOES NOT ALREADY EXISTS POPUP
     * PopUp for when a player tries to enter a username that does not exist
     */
    static void usernameDoesNotExistPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane usernameDoesNotExistPopUpPane = null; //calls popup menu created in 'usernameDoesNotExistPopUp.fxml' file

        try {
            usernameDoesNotExistPopUpPane = FXMLLoader.load(PopUpController.class.getResource("usernameDoesNotExistPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(usernameDoesNotExistPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) usernameDoesNotExistPopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            hidePopUp();

            accountLoginPopUp();
        });
    }

    /**
     * INVALID PASSWORDS POPUP
     * PopUp for when a player tries to enter an invalid password
     */
    static void incorrectPasswordPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane incorrectPasswordPopUpPane = null; //calls popup menu created in 'incorrectPasswordPopUp.fxml' file

        try {
            incorrectPasswordPopUpPane = FXMLLoader.load(PopUpController.class.getResource("incorrectPasswordPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(incorrectPasswordPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) incorrectPasswordPopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            hidePopUp();

            accountLoginPopUp();
        });
    }

    /**
     * USERNAME ALREADY EXISTS POPUP
     * PopUp for when a player tries to enter a username that already exist
     */
    static void usernameAlreadyExistPopUp() {
        setCreateAccountErrorPopUpShown(true);
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
            hidePopUp();

            createAccountPopUp();
        });
    }

    /**
     * INVALID USERNAME POPUP
     * PopUp for when a player tries to enter an invalid username
     */
    static void invalidUsernamePopUp() {
        setCreateAccountErrorPopUpShown(true);
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
            hidePopUp();

            createAccountPopUp();
        });
    }

    /**
     * PASSWORDS DON'T MATCH POPUP
     * PopUp for passwords do not match
     */
    static void passwordsDontMatchPopUp() {
        setCreateAccountErrorPopUpShown(true);
        PopUp = new Popup(); //creates new popup

        TitledPane passwordsDontMatchPopUpPane = null; //calls popup menu created in 'passwordsDontMatchPopUp.fxml' file

        try {
            passwordsDontMatchPopUpPane = FXMLLoader.load(PopUpController.class.getResource("passwordsDontMatchPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(passwordsDontMatchPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) passwordsDontMatchPopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            hidePopUp();

            createAccountPopUp();
        });
    }

    /**
     * INVALID PASSWORDS POPUP
     * PopUp for when a player tries to enter an invalid password
     */
    static void invalidPasswordPopUp() {
        setCreateAccountErrorPopUpShown(true);
        PopUp = new Popup(); //creates new popup

        TitledPane invalidPasswordPopUpPane = null; //calls popup menu created in 'invalidPasswordPopUp.fxml' file

        try {
            invalidPasswordPopUpPane = FXMLLoader.load(PopUpController.class.getResource("invalidPasswordPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(invalidPasswordPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) invalidPasswordPopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            hidePopUp();

            createAccountPopUp();
        });
    }

    /**
     * ENTER TWO NAMES POPUP
     * PopUp to ask for both player names
     */
    static void enterTwoNamesPopUp() {
        accountLoginPopUpShown = true;
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
            hidePopUp();
            startGame();
            accountLoginPopUpShown = false;
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
        setCreateAccountPopUpShown(false);
        setAccountLoginPopUpShown(false);
        boolean t = getAccountLoginPopUpShown();
        setWonGamePopUpShown(false);
        setEnterNumberPopUpShown(false);
        setTiedGamePopUpShown(false);
        setCreateAccountErrorPopUpShown(false);

        try {
            getPopUp().hide();
            setPopUp(null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        getPauseBtn().setDisable(false);
        getUndoBtn().setDisable(false);
    }
}
