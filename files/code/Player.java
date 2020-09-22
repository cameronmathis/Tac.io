package code;

/**
 * PLAYER OBJECT
 */
public class Player {
    //Variables
    private String username;
    private boolean isTurn;

    /**
     * SETTER METHODS
     */
    void setUsername(String playerName) {
        this.username = playerName;
    }

    void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    /**
     * GETTER METHODS
     */
    String getUsername() {
        return username;
    }

    boolean getTurn() { return isTurn; }

    @Override
    public String toString() { return "Name: " + getUsername(); }

}
