package dev.the456gamer.gamearena.tictactoe.output.tilemarker;

import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.output.GameWindow;

public abstract class TileMarker {

    public final GameWindow gameWindow;
    public final int x;
    public final int y;

    public TileMarker(GameWindow gameWindow, int x, int y) {
        this.gameWindow = gameWindow;
        this.x = x;
        this.y = y;
    }

    public static TileMarker createMarker(GameSide gameSide, GameWindow gameWindow, int x, int y) {
        if (gameSide == GameSide.X) {
            return new XMarker(gameWindow, x, y);
        } else {
            return new OMarker(gameWindow, x, y);
        }
    }

    public abstract void addToBoard();

    public abstract void removeFromBoard();
}
