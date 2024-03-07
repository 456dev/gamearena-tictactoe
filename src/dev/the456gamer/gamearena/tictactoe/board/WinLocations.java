package dev.the456gamer.gamearena.tictactoe.board;

import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.output.GridCoordinate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum WinLocations {

    // Horizontal
    TOP_ROW(WinLocationType.ROW, 0),
    MIDDLE_ROW(WinLocationType.ROW, 1),
    BOTTOM_ROW(WinLocationType.ROW, 2),

    // Vertical
    LEFT_COLUMN(WinLocationType.COLUMN, 0),
    MIDDLE_COLUMN(WinLocationType.COLUMN, 1),
    RIGHT_COLUMN(WinLocationType.COLUMN, 2),

    // Diagonal
    TOP_LEFT_DIAGONAL(WinLocationType.DIAGONAL, 0),
    TOP_RIGHT_DIAGONAL(WinLocationType.DIAGONAL, 1);

    private final WinLocationType winLocationType;
    private final int i;

    WinLocations(WinLocationType winLocationType, int i) {

        this.winLocationType = winLocationType;
        this.i = i;
    }

    public static WinLocations getWinLocation(WinLocationType winLocationType, int i) {
        for (WinLocations winLocation : WinLocations.values()) {
            if (winLocation.winLocationType == winLocationType && winLocation.i == i) {
                return winLocation;
            }
        }
        return null;
    }

    public static List<WinLocations> getWinLocationsThroughPoint(int x, int y) {
        List<WinLocations> winLocations = new ArrayList<>();
        if (x == 0) {
            winLocations.add(LEFT_COLUMN);
        } else if (x == 1) {
            winLocations.add(MIDDLE_COLUMN);
        } else if (x == 2) {
            winLocations.add(RIGHT_COLUMN);
        }
        if (y == 0) {
            winLocations.add(TOP_ROW);
        } else if (y == 1) {
            winLocations.add(MIDDLE_ROW);
        } else if (y == 2) {
            winLocations.add(BOTTOM_ROW);
        }

        if (x == y) {
            winLocations.add(TOP_LEFT_DIAGONAL);
        }

        if (x + y == 2) {
            winLocations.add(TOP_RIGHT_DIAGONAL);
        }
        return winLocations;
    }

    public GridCoordinate[] getPoints() {
        switch (winLocationType) {
            case ROW:
                return new GridCoordinate[]{
                    new GridCoordinate(0, i),
                    new GridCoordinate(1, i),
                    new GridCoordinate(2, i)
                };
            case COLUMN:
                return new GridCoordinate[]{
                    new GridCoordinate(i, 0),
                    new GridCoordinate(i, 1),
                    new GridCoordinate(i, 2)
                };
            case DIAGONAL:
                if (i == 0) {
                    return new GridCoordinate[]{
                        new GridCoordinate(0, 0),
                        new GridCoordinate(1, 1),
                        new GridCoordinate(2, 2)
                    };
                } else {
                    return new GridCoordinate[]{
                        new GridCoordinate(0, 2),
                        new GridCoordinate(1, 1),
                        new GridCoordinate(2, 0)
                    };
                }
        }
        return null;

    }

    public boolean isBlocked(BoardState state) {
        return Arrays.stream(Objects.requireNonNull(getPoints())).anyMatch(
            (point) -> state.getTileState(point.x(), point.y()) == state.getSideToMove()
                .getOpponent());
    }

    public boolean hasWon(BoardState state) {
        return Arrays.stream(Objects.requireNonNull(getPoints())).allMatch(
            (point) -> state.getTileState(point.x(), point.y()) == state.getSideToMove());
    }


    public enum WinLocationType {
        ROW, COLUMN, DIAGONAL;
    }


}
