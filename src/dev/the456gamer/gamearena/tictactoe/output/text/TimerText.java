package dev.the456gamer.gamearena.tictactoe.output.text;

import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import uk.ac.lancaster.gamearena.Text;

public class TimerText {

    private final GameWindow window;
    private final Text text;

    public TimerText(GameWindow window) {
        this.window = window;
        this.text = new Text("Timer: xx:xx.xxx", 25, 40, 60, "WHITE");
        window.arena.addText(text);
    }

    public void refresh() {
        text.setText("Timer: %d.%03d".formatted(window.getGame().getTimeInGame().toSeconds(),
            window.getGame().getTimeInGame().toMillis() % 1000));
    }


}
