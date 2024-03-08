package dev.the456gamer.gamearena.tictactoe.output;

import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.board.state.GameState;
import uk.ac.lancaster.gamearena.Line;

public class WonGameOverlay {

    public static final double THICKNESS = 25;
    private final GameWindow window;
    Line line;

    // Shows the end line, to display the winner
    // 8 different positions directions, 2 different players = 16 different possibilities

    public WonGameOverlay(GameWindow window) {

        this.window = window;
    }

    public void show(BoardState state) {
        // Show the line

        if (state.getGameState() != GameState.O_WON && state.getGameState() != GameState.X_WON) {
            return;
        }

        GridCoordinate[] points = state.getWonBoardPosition().getPoints();

        if (points == null) {
            return;
        }

        hide();

        line = new Line(340 + 50 + (110 * points[0].x()), 120 + 50 + (110 * points[0].y()),
            340 + 50 + (110 * points[2].x()), 120 + 50 + (110 * points[2].y()), THICKNESS,
            "BLUE");
        window.arena.addLine(line);

    }

    public void hide() {
        if (line != null) {
            window.arena.removeLine(line);
            line = null;
        }

    }

}
