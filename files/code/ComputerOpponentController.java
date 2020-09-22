package code;

import java.util.Random;

import static code.PlayerController.*;
import static code.QuadrantController.*;

public class ComputerOpponentController {
    //Variables
    private static ComputerOpponentState currentState;

    public static void setCurrentState(ComputerOpponentState cs) { currentState = cs; }

    /**
     * GET QUADRANT TO MARK METHOD
     * Find the best quadrant to mark
     */
    static Quadrant getQuadrantToMark(Quadrant quadrant) {
        setPreviousPreviousQuadrant(getPreviousQuadrant());
        setPreviousQuadrant(quadrant);

        switch (currentState) {
            case HARD:
                //Primary Offensive Moves
                if (primaryOffensiveMove() != null) {
                    primaryOffensiveMove();
                }
            case MEDIUM:
                //Defensive Moves
                if (defensiveMove(quadrant) != null) {
                    return defensiveMove(quadrant);
                }
            case EASY:
                //Secondary Offensive Moves
                if (secondaryOffensiveMove() != null) {
                    return secondaryOffensiveMove();
                }
            default:
                //Random Moves
                return randomMove();
        }
    }

    static Quadrant primaryOffensiveMove() {
        if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer2())) {
            if (getTopCenter().getPlayerPlayed() != null && getTopCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopRight().getIsMarked()) {
                    return getTopRight();
                }
            }
            if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopCenter().getIsMarked()) {
                    return getTopCenter();
                }
            } else if (getCenterLeft().getPlayerPlayed() != null && getCenterLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomLeft().getIsMarked()) {
                    return getBottomLeft();
                }
            }
            if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenterLeft().getIsMarked()) {
                    return getCenterLeft();
                }
            }
            if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomLeft().getIsMarked()) {
                    return getBottomLeft();
                }
            }
            if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenter().getIsMarked()) {
                    return getCenter();
                }
            }
        } else if (getTopCenter().getPlayerPlayed() != null && getTopCenter().getPlayerPlayed().equals(getPlayer2())) {
            if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopRight().getIsMarked()) {
                    return getTopRight();
                }
            }
            if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopLeft().getIsMarked()) {
                    return getTopLeft();
                }
            }
            if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomCenter().getIsMarked()) {
                    return getBottomCenter();
                }
            }
            if (getBottomCenter().getPlayerPlayed() != null && getBottomCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenter().getIsMarked()) {
                    return getCenter();
                }
            }
        } else if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer2())) {
            if (getTopCenter().getPlayerPlayed() != null && getTopCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopLeft().getIsMarked()) {
                    return getTopLeft();
                }
            }
            if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopCenter().getIsMarked()) {
                    return getTopCenter();
                }
            }
            if (getCenterRight().getPlayerPlayed() != null && getCenterRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomRight().getIsMarked()) {
                    return getBottomRight();
                }
            }
            if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenterRight().getIsMarked()) {
                    return getCenterRight();
                }
            }
            if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomLeft().getIsMarked()) {
                    return getBottomLeft();
                }
            }
            if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenter().getIsMarked()) {
                    return getCenter();
                }
            }
        } else if (getCenterLeft().getPlayerPlayed() != null && getCenterLeft().getPlayerPlayed().equals(getPlayer2())) {
            if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomLeft().getIsMarked()) {
                    return getBottomLeft();
                }
            }
            if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopLeft().getIsMarked()) {
                    return getTopLeft();
                }
            }
            if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenterRight().getIsMarked()) {
                    return getCenterRight();
                }
            }
            if (getCenterRight().getPlayerPlayed() != null && getCenterRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenter().getIsMarked()) {
                    return getCenter();
                }
            }
        } else if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer2())) {
            if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomRight().getIsMarked()) {
                    return getBottomRight();
                }
            }
            if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopLeft().getIsMarked()) {
                    return getTopLeft();
                }
            }
            if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomLeft().getIsMarked()) {
                    return getBottomLeft();
                }
            }
            if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopRight().getIsMarked()) {
                    return getTopRight();
                }
            }
            if (getTopCenter().getPlayerPlayed() != null && getTopCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomCenter().getIsMarked()) {
                    return getBottomCenter();
                }
            }
            if (getBottomCenter().getPlayerPlayed() != null && getBottomCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopCenter().getIsMarked()) {
                    return getTopCenter();
                }
            }
            if (getCenterLeft().getPlayerPlayed() != null && getCenterLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenterRight().getIsMarked()) {
                    return getCenterRight();
                }
            }
            if (getCenterRight().getPlayerPlayed() != null && getCenterRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenterLeft().getIsMarked()) {
                    return getCenterLeft();
                }
            }
        } else if (getCenterRight().getPlayerPlayed() != null && getCenterRight().getPlayerPlayed().equals(getPlayer2())) {
            if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomRight().getIsMarked()) {
                    return getBottomRight();
                }
            }
            if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopRight().getIsMarked()) {
                    return getTopRight();
                }
            }
            if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenterLeft().getIsMarked()) {
                    return getCenterLeft();
                }
            }
            if (getCenterLeft().getPlayerPlayed() != null && getCenterLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenter().getIsMarked()) {
                    return getCenter();
                }
            }
        } else if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer2())) {
            if (getBottomCenter().getPlayerPlayed() != null && getBottomCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomRight().getIsMarked()) {
                    return getBottomRight();
                }
            }
            if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomCenter().getIsMarked()) {
                    return getBottomCenter();
                }
            }
            if (getCenterLeft().getPlayerPlayed() != null && getCenterLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopLeft().getIsMarked()) {
                    return getTopLeft();
                }
            }
            if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenterLeft().getIsMarked()) {
                    return getCenterLeft();
                }
            }
            if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopRight().getIsMarked()) {
                    return getTopRight();
                }
            }
            if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenter().getIsMarked()) {
                    return getCenter();
                }
            }
        } else if (getBottomCenter().getPlayerPlayed() != null && getBottomCenter().getPlayerPlayed().equals(getPlayer2())) {
            if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomRight().getIsMarked()) {
                    return getBottomRight();
                }
            }
            if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomLeft().getIsMarked()) {
                    return getBottomLeft();
                }
            }
            if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopCenter().getIsMarked()) {
                    return getTopCenter();
                }
            }
            if (getTopCenter().getPlayerPlayed() != null && getTopCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenter().getIsMarked()) {
                    return getCenter();
                }
            }
        } else if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer2())) {
            if (getTopCenter().getPlayerPlayed() != null && getBottomCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomLeft().getIsMarked()) {
                    return getBottomLeft();
                }
            }
            if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getBottomLeft().getIsMarked()) {
                    return getBottomCenter();
                }
            }
            if (getCenterRight().getPlayerPlayed() != null && getCenterRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopRight().getIsMarked()) {
                    return getTopRight();
                }
            }
            if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenterRight().getIsMarked()) {
                    return getCenterRight();
                }
            }
            if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer2())) {
                if (!getTopLeft().getIsMarked()) {
                    return getTopLeft();
                }
            }
            if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer2())) {
                if (!getCenter().getIsMarked()) {
                    return getCenter();
                }
            }
        }

        return null;
    }

    static Quadrant defensiveMove(Quadrant quadrant) {
        switch (quadrant.getPane().getId()) {
            case "topLeftPane":
                if (getTopCenter().getPlayerPlayed() != null && getTopCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopRight().getIsMarked()) {
                        return getTopRight();
                    }
                }
                if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopCenter().getIsMarked()) {
                        return getTopCenter();
                    }
                }
                if (getCenterLeft().getPlayerPlayed() != null && getCenterLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomLeft().getIsMarked()) {
                        return getBottomLeft();
                    }
                }
                if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenterLeft().getIsMarked()) {
                        return getCenterLeft();
                    }
                }
                if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomRight().getIsMarked()) {
                        return getBottomRight();
                    }
                }
                if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenter().getIsMarked()) {
                        return getCenter();
                    }
                }
                break;
            case "topCenterPane":
                if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopRight().getIsMarked()) {
                        return getTopRight();
                    }
                }
                if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopLeft().getIsMarked()) {
                        return getTopLeft();
                    }
                }
                if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomCenter().getIsMarked()) {
                        return getBottomCenter();
                    }
                }
                if (getBottomCenter().getPlayerPlayed() != null && getBottomCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenter().getIsMarked()) {
                        return getCenter();
                    }
                }
                break;
            case "topRightPane":
                if (getTopCenter().getPlayerPlayed() != null && getTopCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopLeft().getIsMarked()) {
                        return getTopLeft();
                    }
                }
                if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopCenter().getIsMarked()) {
                        return getTopCenter();
                    }
                }
                if (getCenterRight().getPlayerPlayed() != null && getCenterRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomRight().getIsMarked()) {
                        return getBottomRight();
                    }
                }
                if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenterRight().getIsMarked()) {
                        return getCenterRight();
                    }
                }
                if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomLeft().getIsMarked()) {
                        return getBottomLeft();
                    }
                }
                if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenter().getIsMarked()) {
                        return getCenter();
                    }
                }
                break;
            case "centerLeftPane":
                if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomLeft().getIsMarked()) {
                        return getBottomLeft();
                    }
                }
                if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopLeft().getIsMarked()) {
                        return getTopLeft();
                    }
                }
                if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenterRight().getIsMarked()) {
                        return getCenterRight();
                    }
                }
                if (getCenterRight().getPlayerPlayed() != null && getCenterRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenter().getIsMarked()) {
                        return getCenter();
                    }
                }
                break;
            case "centerPane":
                if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomRight().getIsMarked()) {
                        return getBottomRight();
                    }
                }
                if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopLeft().getIsMarked()) {
                        return getTopLeft();
                    }
                }
                if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomLeft().getIsMarked()) {
                        return getBottomLeft();
                    }
                }
                if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopRight().getIsMarked()) {
                        return getTopRight();
                    }
                }
                if (getTopCenter().getPlayerPlayed() != null && getTopCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomCenter().getIsMarked()) {
                        return getBottomCenter();
                    }
                }
                if (getBottomCenter().getPlayerPlayed() != null && getBottomCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopCenter().getIsMarked()) {
                        return getTopCenter();
                    }
                }
                if (getCenterLeft().getPlayerPlayed() != null && getCenterLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenterRight().getIsMarked()) {
                        return getCenterRight();
                    }
                }
                if (getCenterRight().getPlayerPlayed() != null && getCenterRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenterRight().getIsMarked()) {
                        return getCenterRight();
                    }
                }
                break;
            case "centerRightPane":
                if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomRight().getIsMarked()) {
                        return getBottomRight();
                    }
                }
                if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopRight().getIsMarked()) {
                        return getTopRight();
                    }
                }
                if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenterLeft().getIsMarked()) {
                        return getCenterLeft();
                    }
                }
                if (getCenterLeft().getPlayerPlayed() != null && getCenterLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenter().getIsMarked()) {
                        return getCenter();
                    }
                }
                break;
            case "bottomLeftPane":
                if (getBottomCenter().getPlayerPlayed() != null && getBottomCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomRight().getIsMarked()) {
                        return getBottomRight();
                    }
                }
                if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomCenter().getIsMarked()) {
                        return getBottomCenter();
                    }
                }
                if (getCenterLeft().getPlayerPlayed() != null && getCenterLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopLeft().getIsMarked()) {
                        return getTopLeft();
                    }
                }
                if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenterLeft().getIsMarked()) {
                        return getCenterLeft();
                    }
                }
                if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopRight().getIsMarked()) {
                        return getTopRight();
                    }
                }
                if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenter().getIsMarked()) {
                        return getCenter();
                    }
                }
                break;
            case "bottomCenterPane":
                if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomRight().getIsMarked()) {
                        return getBottomRight();
                    }
                }
                if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomLeft().getIsMarked()) {
                        return getBottomLeft();
                    }
                }
                if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopCenter().getIsMarked()) {
                        return getTopCenter();
                    }
                }
                if (getTopCenter().getPlayerPlayed() != null && getTopCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenter().getIsMarked()) {
                        return getCenter();
                    }
                }
                break;
            case "bottomRightPane":
                if (getBottomCenter().getPlayerPlayed() != null && getBottomCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomLeft().getIsMarked()) {
                        return getBottomLeft();
                    }
                }
                if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getBottomCenter().getIsMarked()) {
                        return getBottomCenter();
                    }
                }
                if (getCenterRight().getPlayerPlayed() != null && getCenterRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopRight().getIsMarked()) {
                        return getTopRight();
                    }
                }
                if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenterRight().getIsMarked()) {
                        return getCenterRight();
                    }
                }
                if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getTopLeft().getIsMarked()) {
                        return getTopLeft();
                    }
                }
                if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer1())) {
                    if (!getCenter().getIsMarked()) {
                        return getCenter();
                    }
                }
                break;
        }

        return null;
    }

    static Quadrant secondaryOffensiveMove() {
        if (getTopLeft().getPlayerPlayed() != null && getTopLeft().getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (getTopCenter().getPlayerPlayed() == null && getTopRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getTopCenter();
                                case 1:
                                    return getTopRight();
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (getCenterLeft().getPlayerPlayed() == null && getBottomLeft().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenterLeft();
                                case 1:
                                    return getBottomLeft();
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (getCenter().getPlayerPlayed() == null && getBottomRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenter();
                                case 1:
                                    return getBottomRight();
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        } else if (getTopCenter().getPlayerPlayed() != null && getTopCenter().getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (getTopLeft().getPlayerPlayed() == null && getTopRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getTopLeft();
                                case 1:
                                    return getTopRight();
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (getCenter().getPlayerPlayed() == null && getBottomCenter().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenter();
                                case 1:
                                    return getBottomCenter();
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (getTopRight().getPlayerPlayed() != null && getTopRight().getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (getTopCenter().getPlayerPlayed() == null && getTopLeft().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getTopCenter();
                                case 1:
                                    return getTopLeft();
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (getCenterRight().getPlayerPlayed() == null && getBottomRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenterRight();
                                case 1:
                                    return getBottomRight();
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (getCenter().getPlayerPlayed() == null && getBottomLeft().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenter();
                                case 1:
                                    return getBottomLeft();
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        } else if (getCenterLeft().getPlayerPlayed() != null && getCenterLeft().getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (getTopLeft().getPlayerPlayed() == null && getBottomLeft().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getTopLeft();
                                case 1:
                                    return getBottomLeft();
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (getCenter().getPlayerPlayed() == null && getCenterRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenter();
                                case 1:
                                    return getCenterRight();
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (getCenter().getPlayerPlayed() != null && getCenter().getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            boolean case3 = true;
            while (case0 || case1 || case2 || case3) {
                Random rand = new Random();
                int n = rand.nextInt(4);

                switch (n) {
                    case 0:
                        if (getTopLeft().getPlayerPlayed() == null && getBottomRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getTopLeft();
                                case 1:
                                    return getBottomRight();
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (getTopCenter().getPlayerPlayed() == null && getBottomCenter().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getTopCenter();
                                case 1:
                                    return getBottomCenter();
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (getTopRight().getPlayerPlayed() == null && getBottomLeft().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getTopRight();
                                case 1:
                                    return getBottomLeft();
                            }
                        } else {
                            case2 = false;
                        }
                    case 3:
                        if (getCenterLeft().getPlayerPlayed() == null && getCenterRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenterLeft();
                                case 1:
                                    return getCenterRight();
                            }
                        } else {
                            case3 = false;
                        }
                }
            }
        } else if (getCenterRight().getPlayerPlayed() != null && getCenterRight().getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (getTopRight().getPlayerPlayed() == null && getBottomRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getTopRight();
                                case 1:
                                    return getBottomRight();
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (getCenter().getPlayerPlayed() == null && getCenterLeft().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenter();
                                case 1:
                                    return getCenterLeft();
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (getBottomLeft().getPlayerPlayed() != null && getBottomLeft().getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (getCenterLeft().getPlayerPlayed() == null && getTopLeft().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenterLeft();
                                case 1:
                                    return getTopLeft();
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (getBottomCenter().getPlayerPlayed() == null && getBottomRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getBottomCenter();
                                case 1:
                                    return getBottomRight();
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (getCenter().getPlayerPlayed() == null && getTopRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenter();
                                case 1:
                                    return getTopRight();
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        } else if (getBottomCenter().getPlayerPlayed() != null && getBottomCenter().getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (getBottomLeft().getPlayerPlayed() == null && getBottomRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getBottomLeft();
                                case 1:
                                    return getBottomRight();
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (getCenter().getPlayerPlayed() == null && getTopCenter().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenter();
                                case 1:
                                    return getCenter();
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (getBottomRight().getPlayerPlayed() != null && getBottomRight().getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (getCenterRight().getPlayerPlayed() == null && getTopRight().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenterRight();
                                case 1:
                                    return getTopRight();
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (getBottomCenter().getPlayerPlayed() == null && getBottomLeft().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getBottomCenter();
                                case 1:
                                    return getBottomLeft();
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (getCenter().getPlayerPlayed() == null && getTopLeft().getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return getCenter();
                                case 1:
                                    return getTopLeft();
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        }

        return null;
    }

    static Quadrant randomMove() {
        while (true) {
            Random rand = new Random();
            int n = rand.nextInt(9);

            switch (n) {
                case 0:
                    if (!getTopLeft().getIsMarked()) {
                        return getTopLeft();
                    } else {
                        break;
                    }
                case 1:
                    if (!getTopCenter().getIsMarked()) {
                        return getTopCenter();
                    } else {
                        break;
                    }
                case 2:
                    if (!getTopRight().getIsMarked()) {
                        return getTopRight();
                    } else {
                        break;
                    }
                case 3:
                    if (!getCenterLeft().getIsMarked()) {
                        return getCenterLeft();
                    } else {
                        break;
                    }
                case 4:
                    if (!getCenter().getIsMarked()) {
                        return getCenter();
                    } else {
                        break;
                    }
                case 5:
                    if (!getCenterRight().getIsMarked()) {
                        return getCenterRight();
                    } else {
                        break;
                    }
                case 6:
                    if (!getBottomLeft().getIsMarked()) {
                        return getBottomLeft();
                    } else {
                        break;
                    }
                case 7:
                    if (!getBottomCenter().getIsMarked()) {
                        return getBottomCenter();
                    } else {
                        break;
                    }
                case 8:
                    if (!getBottomRight().getIsMarked()) {
                        return getBottomRight();
                    } else {
                        break;
                    }
            }
        }
    }
}
