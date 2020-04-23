package sample;

import java.util.Random;

import static sample.PlayerController.*;
import static sample.QuadrantController.*;

public class ComputerController {
    // Variablesa
    private static Quadrant topLeft = getTopLeft();
    private static Quadrant topCenter = getTopCenter();
    private static Quadrant topRight = getTopRight();
    private static Quadrant centerLeft = getCenterLeft();
    private static Quadrant center = getCenter();
    private static Quadrant centerRight = getCenterRight();
    private static Quadrant bottomLeft = getBottomLeft();
    private static Quadrant bottomCenter = getBottomCenter();
    private static Quadrant bottomRight = getBottomRight();

    static Quadrant primaryOffensiveMove() {
        if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
        } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerLeft.getIsMarked()) {
                    return centerLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomRight.getIsMarked()) {
                    return bottomRight;
                }
            }
            if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!topCenter.getIsMarked()) {
                    return topCenter;
                }
            }
            if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
            if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomLeft.getIsMarked()) {
                    return bottomLeft;
                }
            }
            if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!bottomCenter.getIsMarked()) {
                    return bottomCenter;
                }
            }
            if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!topRight.getIsMarked()) {
                    return topRight;
                }
            }
            if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
                if (!centerRight.getIsMarked()) {
                    return centerRight;
                }
            }
            if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
                if (!topLeft.getIsMarked()) {
                    return topLeft;
                }
            }
            if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
                if (!center.getIsMarked()) {
                    return center;
                }
            }
        }

        return null;
    }

    static Quadrant defensiveMove(Quadrant quadrant) {
        switch (quadrant.getPane().getId()) {
            case "topLeftPane":
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "topCenterPane":
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "topRightPane":
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "centerLeftPane":
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "centerPane":
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                break;
            case "centerRightPane":
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "bottomLeftPane":
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "bottomCenterPane":
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    }
                }
                if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
                    }
                }
                if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
            case "bottomRightPane":
                if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    }
                }
                if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    }
                }
                if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topRight.getIsMarked()) {
                        return topRight;
                    }
                }
                if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer1())) {
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    }
                }
                if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer1())) {
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    }
                }
                if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer1())) {
                    if (!center.getIsMarked()) {
                        return center;
                    }
                }
                break;
        }

        return null;
    }

    static Quadrant secondaryOffensiveMove() {
        if (topLeft.getPlayerPlayed() != null && topLeft.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (topCenter.getPlayerPlayed() == null && topRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topCenter;
                                case 1:
                                    return topRight;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (centerLeft.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return centerLeft;
                                case 1:
                                    return bottomLeft;
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return bottomRight;
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        } else if (topCenter.getPlayerPlayed() != null && topCenter.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (topLeft.getPlayerPlayed() == null && topRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topLeft;
                                case 1:
                                    return topRight;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (center.getPlayerPlayed() == null && bottomCenter.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return bottomCenter;
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (topRight.getPlayerPlayed() != null && topRight.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (topCenter.getPlayerPlayed() == null && topLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topCenter;
                                case 1:
                                    return topLeft;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (centerRight.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return centerRight;
                                case 1:
                                    return bottomRight;
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return bottomLeft;
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        } else if (centerLeft.getPlayerPlayed() != null && centerLeft.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (topLeft.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return bottomLeft;
                                case 1:
                                    return topLeft;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (center.getPlayerPlayed() == null && centerRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return centerRight;
                                case 1:
                                    return center;
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (center.getPlayerPlayed() != null && center.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            boolean case3 = true;
            while (case0 || case1 || case2 || case3) {
                Random rand = new Random();
                int n = rand.nextInt(4);

                switch (n) {
                    case 0:
                        if (topLeft.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return bottomRight;
                                case 1:
                                    return topLeft;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (topCenter.getPlayerPlayed() == null && bottomCenter.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topCenter;
                                case 1:
                                    return bottomCenter;
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (topRight.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topRight;
                                case 1:
                                    return bottomLeft;
                            }
                        } else {
                            case2 = false;
                        }
                    case 3:
                        if (centerLeft.getPlayerPlayed() == null && centerRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return centerRight;
                                case 1:
                                    return centerLeft;
                            }
                        } else {
                            case3 = false;
                        }
                }
            }
        } else if (centerRight.getPlayerPlayed() != null && centerRight.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (topRight.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topRight;
                                case 1:
                                    return bottomRight;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (center.getPlayerPlayed() == null && centerLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return centerLeft;
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (bottomLeft.getPlayerPlayed() != null && bottomLeft.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (centerLeft.getPlayerPlayed() == null && topLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return centerLeft;
                                case 1:
                                    return topLeft;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (bottomCenter.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return bottomRight;
                                case 1:
                                    return bottomCenter;
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null && topRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return topRight;
                            }
                        } else {
                            case2 = false;
                        }
                }
            }
        } else if (bottomCenter.getPlayerPlayed() != null && bottomCenter.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            while (case0 || case1) {
                Random rand = new Random();
                int n = rand.nextInt(2);

                switch (n) {
                    case 0:
                        if (bottomLeft.getPlayerPlayed() == null && bottomRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return bottomLeft;
                                case 1:
                                    return bottomRight;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (center.getPlayerPlayed() == null && topCenter.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topCenter;
                                case 1:
                                    return center;
                            }
                        } else {
                            case1 = false;
                        }
                }
            }
        } else if (bottomRight.getPlayerPlayed() != null && bottomRight.getPlayerPlayed().equals(getPlayer2())) {
            boolean case0 = true;
            boolean case1 = true;
            boolean case2 = true;
            while (case0 || case1 || case2) {
                Random rand = new Random();
                int n = rand.nextInt(3);

                switch (n) {
                    case 0:
                        if (centerRight.getPlayerPlayed() == null && topRight.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return topRight;
                                case 1:
                                    return centerRight;
                            }
                        } else {
                            case0 = false;
                        }
                    case 1:
                        if (bottomCenter.getPlayerPlayed() == null && bottomLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return bottomCenter;
                                case 1:
                                    return bottomLeft;
                            }
                        } else {
                            case1 = false;
                        }
                    case 2:
                        if (center.getPlayerPlayed() == null && topLeft.getPlayerPlayed() == null) {
                            int n2 = rand.nextInt(2);
                            switch (n2) {
                                case 0:
                                    return center;
                                case 1:
                                    return topLeft;
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
                    if (!topLeft.getIsMarked()) {
                        return topLeft;
                    } else {
                        break;
                    }
                case 1:
                    if (!topCenter.getIsMarked()) {
                        return topCenter;
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
                    if (!centerLeft.getIsMarked()) {
                        return centerLeft;
                    } else {
                        break;
                    }
                case 4:
                    if (!center.getIsMarked()) {
                        return center;
                    } else {
                        break;
                    }
                case 5:
                    if (!centerRight.getIsMarked()) {
                        return centerRight;
                    } else {
                        break;
                    }
                case 6:
                    if (!bottomLeft.getIsMarked()) {
                        return bottomLeft;
                    } else {
                        break;
                    }
                case 7:
                    if (!bottomCenter.getIsMarked()) {
                        return bottomCenter;
                    } else {
                        break;
                    }
                case 8:
                    if (!bottomRight.getIsMarked()) {
                        return bottomRight;
                    } else {
                        break;
                    }
            }
        }
    }
}
