package dev.the456gamer.gamearena.tictactoe.output.text;

import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Field;
import uk.ac.lancaster.gamearena.GameArena;
import uk.ac.lancaster.gamearena.Text;

public class GameStateText {

    private final GameWindow window;
    private final Text text;


    public GameStateText(GameWindow window) {
        this.window = window;
        // just needs to be good enough that its hard to see it shifting on first update as it centers itself.
        this.text = new Text("tmp", 25, 425, 60, "WHITE");
        window.arena.addText(text);
    }

    public void update() {
        switch (window.getGame().getCurrentBoardState().getGameState()) {
            case INITIAL:
                text.setText("New Game");
                break;
            case IN_PROGRESS:
                text.setText(window.getGame().getCurrentPlayer().getSide().name() + "'s turn");
                break;
            case DRAW:
                text.setText("Game Over: draw");
                break;
            case X_WON:
                text.setText("Game Over: X has won");
                break;
            case O_WON:
                text.setText("Game Over: O has won");
                break;
        }

        // center text by getting font render context with reflection hack
        Field graphics2dField = null;
        Graphics2D graphics2d;
        try {
            graphics2dField = GameArena.class.getDeclaredField("graphics");
            graphics2dField.setAccessible(true);
            graphics2d = (Graphics2D) graphics2dField.get(window.arena);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Font font = new Font("SansSerif", Font.BOLD, text.getSize());

        if (graphics2d == null) {
            return;
        }
        Rectangle2D stringBounds = font.getStringBounds(text.getText(),
            graphics2d.getFontRenderContext());
        int xPos = (int) ((1000 / 2) - (stringBounds.getWidth() / 2));
        text.setXPosition(xPos);
    }

}
