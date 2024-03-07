package dev.the456gamer.gamearena.tictactoe.output.menubar;

import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar {

    private final JMenuBar menuBar;
    private final GameWindow gameWindow;
    private final PauseGameActionButton pauseAction;
    private final PlayerSelectMenu XPlayerSelectMenu;
    private final PlayerSelectMenu OPlayerSelectMenu;

    public MenuBar(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        menuBar = new JMenuBar();

        XPlayerSelectMenu = new PlayerSelectMenu(gameWindow, GameSide.X);
        menuBar.add(XPlayerSelectMenu.getMenu());
        OPlayerSelectMenu = new PlayerSelectMenu(gameWindow, GameSide.O);
        menuBar.add(OPlayerSelectMenu.getMenu());
        menuBar.add(Box.createHorizontalGlue());


        JMenu actionsMenu = new JMenu("Actions");
        actionsMenu.setToolTipText("More Actions? Yes Please.");
        // quit action
        JMenuItem quitAction = new JMenuItem("Quit");
        quitAction.setToolTipText("Quit the game");
        quitAction.addActionListener(e -> System.exit(0));
        actionsMenu.add(quitAction);


        // pause game action
        pauseAction = new PauseGameActionButton(gameWindow);
        actionsMenu.add(pauseAction.getMenuItem());

        JMenuItem resetAction = new JMenuItem("Reset");
        resetAction.setToolTipText("(╯°□°)╯︵ ┻━┻");
        resetAction.addActionListener(e -> gameWindow.resetGame());
        actionsMenu.add(resetAction);

        menuBar.add(actionsMenu);

        JMenu debugMenu = new JMenu("Debug");

        JMenuItem swapActivePlayerAction = new JMenuItem("swap active player");
        swapActivePlayerAction.addActionListener(e -> gameWindow.getGame().setPlayer1Turn(!gameWindow.getGame().isPlayer1Turn()));
        debugMenu.add(swapActivePlayerAction);

        JMenuItem refreshAction = new JMenuItem("Redraw");
        refreshAction.addActionListener(e -> gameWindow.redraw());
        debugMenu.add(refreshAction);

        menuBar.add(debugMenu);

    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void refresh() {
        // refresh all menu items
        pauseAction.refresh();
        XPlayerSelectMenu.refresh();
        OPlayerSelectMenu.refresh();
    }
}
