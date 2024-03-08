package dev.the456gamer.gamearena.tictactoe.output.text;

import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import java.util.Timer;
import java.util.TimerTask;
import uk.ac.lancaster.gamearena.Text;

public class PausedText {

    private final GameWindow gameWindow;

    // text shown when the game is paused. should never be enabled while game is terminated.
    // flashes opacity every 1 second.

    private final Text text;
    private final Timer timer;
    private TimerTask task;


    public PausedText(GameWindow gameWindow) {
        this.text = new Text("Game Paused", 25, 40, 100, "WHITE");
        this.gameWindow = gameWindow;
        timer = new Timer();
    }

    public void show() {
        text.setColour("WHITE");

        if (task != null) {
            task.cancel();
        }
        gameWindow.arena.removeText(text);
        gameWindow.arena.addText(text);
        task = new TimerTask() {
            @Override
            public void run() {
                if (text.getColour().equals("WHITE")) {
                    text.setColour("GREY");
                } else {
                    text.setColour("WHITE");
                }
            }
        };
        timer.schedule(task, 1000, 1000);

    }

    public void hide() {
        if (task != null) {
            task.cancel();
        }
        gameWindow.arena.removeText(text);

        task = null;

    }
}
