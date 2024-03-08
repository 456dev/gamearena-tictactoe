package dev.the456gamer.gamearena.tictactoe.output.menubar;

import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar {

    private final JMenuBar menuBar;
    private final PauseGameActionButton pauseAction;
    private final PlayerSelectMenu XPlayerSelectMenu;
    private final PlayerSelectMenu OPlayerSelectMenu;

    public MenuBar(GameWindow gameWindow) {
        menuBar = new JMenuBar();

        XPlayerSelectMenu = new PlayerSelectMenu(gameWindow, GameSide.X,
            player1 -> gameWindow.getGame().setPlayer1(player1));
        menuBar.add(XPlayerSelectMenu.getMenu());
        OPlayerSelectMenu = new PlayerSelectMenu(gameWindow, GameSide.O,
            player2 -> gameWindow.getGame().setPlayer2(player2));
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

//        JMenu debugMenu = new JMenu("Debug");
//
//        JMenuItem refreshAction = new JMenuItem("Redraw");
//        refreshAction.addActionListener(e -> gameWindow.redraw());
//        debugMenu.add(refreshAction);
//
//        menuBar.add(debugMenu);

    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public PlayerSelectMenu getXPlayerSelectMenu() {
        return XPlayerSelectMenu;
    }

    public PlayerSelectMenu getOPlayerSelectMenu() {
        return OPlayerSelectMenu;
    }

    public void refresh() {
        // refresh all menu items
        pauseAction.refresh();
        XPlayerSelectMenu.refresh();
        OPlayerSelectMenu.refresh();
    }
}
