package dev.the456gamer.gamearena.tictactoe.output;

import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import java.awt.event.MouseEvent;
import uk.ac.lancaster.gamearena.Line;

public class GameGrid {

    public static final int THICKNESS = 5;

    // single instance, has the 4 hatch lines #
    // also stores the tiles?

    private final GameTile[][] tiles = new GameTile[3][3];

    public GameGrid(GameWindow gameWindow) {
        Line[] hatchLines = new Line[4];
        for (int i = 0; i < 2; i++) {
            hatchLines[i] = new Line(340, 225 + (110 * i), 660, 225 + (110 * i), THICKNESS,
                "WHITE");
        }

        for (int i = 0; i < 2; i++) {
            hatchLines[i + 2] = new Line(445 + (110 * i), 120, 445 + (110 * i), 440, THICKNESS,
                "WHITE");
        }

        for (Line hatchLine : hatchLines) {
            gameWindow.arena.addLine(hatchLine);
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y] = new GameTile(gameWindow, x, y);
            }
        }
    }

    /**
     * Draws the board state onto the game grid
     *
     * @param boardState the board state to draw
     */
    public void drawBoardState(BoardState boardState) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                GameSide tileState = boardState.getTileState(x, y);

                tiles[x][y].setPlayer(tileState);
            }
        }

    }

    public void onMouseClicked(MouseEvent e) {
        for (GameTile[] tileRow : tiles) {
            for (GameTile tile : tileRow) {
                if (e.getX() >= tile.windowXStart && e.getX() <= tile.windowXEnd
                    && e.getY() >= tile.windowYStart && e.getY() <= tile.windowYEnd) {
                    tile.onMouseClicked(e);
                }
            }
        }
    }
}
