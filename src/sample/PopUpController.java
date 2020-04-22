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
    static TitledPane accountLoginPopUpPane;
    static TitledPane enterTwoNamesPopUpPane;
    static Popup PopUp;
    static AnchorPane openingPane;
    static Scene openingScene;
    static AnchorPane gamePane;
    static Scene gameScene;
    static boolean enterNumberPopUpShown = false;
    static boolean enterNamePopUpShown = false;
    static boolean wonGamePopUpShown = false;
    static boolean tiedGamePopUpShown = false;
    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    private static File numberofPlayersPopUpFile = new File("src/sample/numberOfPlayersPopUp.fxml");
    private static InputStream targetStream;

    static {
        try {
            targetStream = new FileInputStream(numberofPlayersPopUpFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * NUMBER OF PLAYERS POPUP
     * PopUp to ask for number of players
     */
    static void numberOfPlayersPopUp() {
        enterNumberPopUpShown = true;
        firstMovePlayer = null;

        PopUp = new Popup(); //creates new popup

        TitledPane numberOfPlayersPopUpPane = null; //calls popup menu created in 'numberOfPlayersPopUp.fxml' file

        try {
            numberOfPlayersPopUpPane = FXMLLoader.load(targetStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(numberOfPlayersPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        openingPane.requestFocus();

        Button onePlayerBtn = (Button) numberOfPlayersPopUpPane.lookup("#onePlayer");
        onePlayerBtn.setOnAction(event -> {
            numberOfPlayers = 1;
            resume();
            enterNumberPopUpShown = false;
            newOrReturningUserPopUp();
            player2.setUsername("Computer");
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
     * NEW OR RETURNING USER POPUP
     * PopUp to ask user if they are new or not
     */
    static void newOrReturningUserPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane newOrReturningUserPopUpPane = null; //calls popup menu created in 'newOrReturningUserPopUp.fxml' file

        try {
            newOrReturningUserPopUpPane = FXMLLoader.load(classLoader.getResource("newOrReturningUserPopUp.fxml"));
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
     * ENTER TWO NAMES POPUP
     * PopUp to ask for both player names
     */
    static void enterTwoNamesPopUp() {
        enterNamePopUpShown = true;
        PopUp = new Popup(); //creates new popup

        enterTwoNamesPopUpPane = null; //calls popup menu created in 'enterTwoNamesPopUp.fxml' file

        try {
            enterTwoNamesPopUpPane = FXMLLoader.load(classLoader.getResource("enterTwoNamesPopUp.fxml"));
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
                player1.setUsername(name1.getText());
            } else {
                player1.setUsername("player1");
            }
            if (!(((TextField) enterTwoNamesPopUpPane.lookup("#player2Name")).getText().equals(""))) {
                TextField name2 = (TextField) enterTwoNamesPopUpPane.lookup("#player2Name");
                player2.setUsername(name2.getText());
            } else {
                player2.setUsername("player2");
            }
            resume();
            startGame();
            enterNamePopUpShown = false;

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
            createAccountPopUpPane = FXMLLoader.load(classLoader.getResource("createAccountPopUp.fxml"));
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
                    player1.setUsername(password.getText());
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
     * ACCOUNT LOGIN POPUP
     * PopUp to login to account
     */
    static void accountLoginPopUp() {
        PopUp = new Popup(); //creates new popup

        accountLoginPopUpPane = null; //calls popup menu created in 'accountLoginPopUp.fxml' file

        try {
            accountLoginPopUpPane = FXMLLoader.load(classLoader.getResource("accountLoginPopUp.fxml"));
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
            player1.setUsername(username.getText());
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
     * USERNAME ALREADY EXISTS POPUP
     * PopUp for when a player tries to enter a username that already exist
     */
    static void usernameAlreadyExistPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane usernameAlreadyExistPopUpPane = null; //calls popup menu created in 'usernameAlreadyExistPopUp.fxml' file

        try {
            usernameAlreadyExistPopUpPane = FXMLLoader.load(classLoader.getResource("usernameAlreadyExistPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(usernameAlreadyExistPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) usernameAlreadyExistPopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            resume();
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);

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
            invalidUsernamePopUpPane = FXMLLoader.load(classLoader.getResource("invalidUsernamePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(invalidUsernamePopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) invalidUsernamePopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            resume();
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);

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
            incorrectInformationPopUpPane = FXMLLoader.load(classLoader.getResource("incorrectInformationPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(incorrectInformationPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) incorrectInformationPopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            resume();
            pauseBtn.setDisable(false);
            undoBtn.setDisable(false);

            accountLoginPopUp();
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
            pausedPupUpPane = FXMLLoader.load(classLoader.getResource("pausedPopUp.fxml"));
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
    static void undoStartPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane undoStartPopUpPane = null; //calls popup menu created in 'undoStartPopUp.fxml' file

        try {
            undoStartPopUpPane = FXMLLoader.load(classLoader.getResource("undoStartPopUp.fxml"));
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
    static void undoTwicePopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane undoTwicePopUpPane = null; //calls popup menu created in 'undoTwicePopUp.fxml' file

        try {
            undoTwicePopUpPane = FXMLLoader.load(classLoader.getResource("undoTwicePopUp.fxml"));
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
     * IS ALREADY MARKED POPUP
     * PopUp for when a player tries to mark an already marked quadrant numerous times
     */
    static void isAlreadyMarkedPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane isAlreadyMarkedPopUpPane = null; //calls popup menu created in 'isAlreadyMarkedPopUp.fxml' file

        try {
            isAlreadyMarkedPopUpPane = FXMLLoader.load(classLoader.getResource("isAlreadyMarkedPopUp.fxml"));
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
     * GAME OVER POPUP
     * PopUp for when the game is over
     */
    static void gameOverPopUp(Player player) {
        updateLeaderBoard(player);

        wonGamePopUpShown = true;

        PopUp = new Popup(); //creates new popup

        TitledPane gameOverPopUpPane = null; //calls popup menu created in 'gameOverPopUp.fxml' file

        try {
            gameOverPopUpPane = FXMLLoader.load(classLoader.getResource("gameOverPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(gameOverPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        Text text = (Text) gameOverPopUpPane.getContent().lookup("#gameOverMessage");
        if (numberOfPlayers == 2 || !player.getUsername().equals("Computer")) {
            text.setText("Congratulations " + player.getUsername() + "! You won!");
        } else if (numberOfPlayers == 1 && player.getUsername().equals("Computer")) {
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
    static void tiePopUp() {
        updateLeaderBoard(null);

        tiedGamePopUpShown = true;

        PopUp = new Popup(); //creates new popup

        TitledPane tiePopUpPane = null; //calls popup menu created in 'tiePopUp.fxml' file

        try {
            tiePopUpPane = FXMLLoader.load(classLoader.getResource("tiePopUp.fxml"));
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
}
