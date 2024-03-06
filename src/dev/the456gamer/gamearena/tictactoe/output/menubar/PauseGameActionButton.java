package dev.the456gamer.gamearena.tictactoe.output.menubar;

import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import javax.swing.JMenuItem;

public class PauseGameActionButton {


    private final GameWindow gameWindow;
    private final JMenuItem menuItem;

    public PauseGameActionButton(GameWindow gameWindow) {

        this.menuItem = new JMenuItem("Pause");
        this.menuItem.addActionListener(e -> {

            if (gameWindow.getGame().isGamePaused()) {
                gameWindow.getGame().resumeGame();
            } else {
                gameWindow.getGame().pauseGame();
            }
            refresh();
        });
        this.gameWindow = gameWindow;

    }

    public void refresh() {
        if (gameWindow.getGame().isGamePaused()) {
            this.menuItem.setText("Resume");
            this.menuItem.setToolTipText("You want to keep playing?");
            return;
        }
        this.menuItem.setText("Pause");
        this.menuItem.setToolTipText("Break Time.");
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
