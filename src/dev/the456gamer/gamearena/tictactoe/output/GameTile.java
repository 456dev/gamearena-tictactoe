package dev.the456gamer.gamearena.tictactoe.output;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;

import dev.the456gamer.gamearena.tictactoe.actortype.HumanActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.output.tilemarker.TileMarker;
import java.awt.event.MouseEvent;
import uk.ac.lancaster.gamearena.Rectangle;

public class GameTile {

    public final int windowXStart, windowYStart, windowXEnd, windowYEnd;
    private final GameWindow gameWindow;
    private final int x;
    private final int y;
    private TileMarker marker;
    private GameSide gameSide;

    public GameTile(GameWindow gameWindow, int x, int y) {
        // bg for a tile? leave blank.
        windowXStart = 340 + (110 * x);
        windowYStart = 120 + (110 * y);
        windowXEnd = windowXStart + 100;
        windowYEnd = windowYStart + 100;
        // probably not needed but allowed for debugging by changing bg colour
        Rectangle clickTarget = new Rectangle(340 + (110 * x), 120 + (110 * y), 100, 100, "BLACK");
        gameWindow.arena.addRectangle(clickTarget);

        this.gameWindow = gameWindow;
        this.x = x;
        this.y = y;
    }

    public GameSide getPlayer() {
        return gameSide;
    }

    public void setPlayer(GameSide gameSide) {
        if (this.marker != null) {
            this.marker.removeFromBoard();
        }
        // sets the gameSide of the tile
        this.gameSide = gameSide;
        if (gameSide != null) {
            this.marker = TileMarker.createMarker(gameSide, gameWindow, this.x, this.y);
            this.marker.addToBoard();
        }
    }

    public void onMouseClicked(MouseEvent e) {

        int onMask = SHIFT_DOWN_MASK;
        if (((e.getModifiersEx() & (onMask | CTRL_DOWN_MASK)) == onMask)) {
            if (e.getButton() == 1) {
                if (getPlayer() != GameSide.X) {
                    setPlayer(GameSide.X);
                } else {
                    setPlayer(GameSide.O);
                }
            } else if (e.getButton() == 3) {
                setPlayer(null);
            }
            return;
        }

        if (gameWindow.getGame().isGameEnded() || gameWindow.getGame().isGamePaused()) {
            System.out.println("ignoring click: Game is not active or is paused");
            return;
        }
        if (gameWindow.getGame().getCurrentPlayer() == null) {
            System.out.println("ignoring click: No current player");
            return;
        }

        if (!(gameWindow.getGame().getCurrentPlayer()
            .getActiveMethod() instanceof HumanActorMethod human)) {
            System.out.println("ignoring click: Current player is not human");
            return;
        }

        human.makeMove(x, y);
    }


}
