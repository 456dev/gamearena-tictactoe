package dev.the456gamer.gamearena.tictactoe.board;

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


    public enum WinLocationType {
        ROW, COLUMN, DIAGONAL;
    }



}
