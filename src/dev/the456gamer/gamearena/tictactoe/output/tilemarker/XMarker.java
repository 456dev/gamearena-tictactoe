package dev.the456gamer.gamearena.tictactoe.output.tilemarker;

import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import uk.ac.lancaster.gamearena.Line;

public class XMarker extends TileMarker {

    public static final int SIZE = 30;
    public static final int THICKNESS = 10;
    private final Line l1;
    private final Line l2;

    public XMarker(GameWindow gameWindow, int x, int y) {
        super(gameWindow, x, y);
        this.l1 = new Line(340 + 50 - SIZE + (110 * x), 120 + 50 - SIZE + (110 * y), 340 + 50 + SIZE
            + (110 * x), 120 + 50 + SIZE + (110 * y), THICKNESS, "YELLOW");
        this.l2 = new Line(340 + 50 - SIZE + (110 * x), 120 + 50 + SIZE + (110 * y), 340 + 50 + SIZE
            + (110 * x), 120 + 50 - SIZE + (110 * y), THICKNESS, "YELLOW");
    }

    @Override
    public void addToBoard() {
        gameWindow.arena.addLine(l1);
        gameWindow.arena.addLine(l2);
    }

    @Override
    public void removeFromBoard() {
        gameWindow.arena.removeLine(l1);
        gameWindow.arena.removeLine(l2);
    }
}
