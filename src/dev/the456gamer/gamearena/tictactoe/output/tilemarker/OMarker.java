package dev.the456gamer.gamearena.tictactoe.output.tilemarker;

import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import uk.ac.lancaster.gamearena.Ball;

public class OMarker extends TileMarker {

    public static final int DIAMETER = 80;
    public static final int THICKNESS = 20;
    private final Ball circle1;
    private final Ball circle2;

    public OMarker(GameWindow gameWindow, int x, int y) {
        super(gameWindow, x, y);
        circle1 = new Ball(340 + 50 + (110 * x), 120 + 50 + (110 * y), DIAMETER, "RED");
        circle2 = new Ball(340 + 50 + (110 * x), 120 + 50 + (110 * y), DIAMETER - THICKNESS,
            "BLACK");
    }

    @Override
    public void addToBoard() {
        gameWindow.arena.addBall(circle1);
        gameWindow.arena.addBall(circle2);

    }

    @Override
    public void removeFromBoard() {
        gameWindow.arena.removeBall(circle1);
        gameWindow.arena.removeBall(circle2);
    }
}
