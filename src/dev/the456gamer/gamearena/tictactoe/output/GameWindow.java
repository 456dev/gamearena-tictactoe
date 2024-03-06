package dev.the456gamer.gamearena.tictactoe.output;

import dev.the456gamer.gamearena.CustomArenaEvents;
import dev.the456gamer.gamearena.CustomGameArena;
import dev.the456gamer.gamearena.tictactoe.TicTacToeGame;
import dev.the456gamer.gamearena.tictactoe.output.menubar.MenuBar;
import dev.the456gamer.gamearena.tictactoe.output.text.GameStateText;
import dev.the456gamer.gamearena.tictactoe.output.text.PausedText;
import dev.the456gamer.gamearena.tictactoe.output.text.TimerText;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import uk.ac.lancaster.gamearena.GameArena;

/**
 * manager for the game's window.
 */
public class GameWindow implements GameEventHandler, CustomArenaEvents {

    public final JFrame window;
    public final GameArena arena;

    public final GameGrid grid;
    private final PausedText pausedText;
    private final GameStateText gameStateText;
    private final MenuBar menuBar;
    private final TimerText timerText;

    private TicTacToeGame game;


    public GameWindow() {
        window = new JFrame("GameArena Tic Tac Toe");

//        window.setMinimumSize(new Dimension(1000, 500));

        arena = new CustomGameArena(1000, 500, false, this);
        arena.setSize(1000, 500);
        arena.setMinimumSize(new Dimension(1000, 500));
        arena.setPreferredSize(new Dimension(1000, 500));
        arena.setMaximumSize(new Dimension(1000, 500));

        window.setDefaultCloseOperation(
            WindowConstants.EXIT_ON_CLOSE);

        window.setContentPane(arena);

        window.setBackground(Color.BLACK);

        menuBar = new MenuBar(this);

        window.setJMenuBar(menuBar.getMenuBar());

        grid = new GameGrid(this);

        pausedText = new PausedText(this);
        pausedText.show();

        gameStateText = new GameStateText(this);
        timerText = new TimerText(this);

        window.pack();
        window.setMinimumSize(window.getSize());
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        resetGame();

        // final refresh to finish updating layout (font context isnt avalible until after first couple of draws?

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                redraw();
            }
        }, 50);

    }

    /**
     * resets the game to a new game
     */
    public void resetGame() {
        game = new TicTacToeGame();
        redraw();

    }

    public void redraw() {
        gameStateText.update();
        grid.drawBoardState(game.getCurrentBoardState());
        menuBar.refresh();

    }


    /**
     * gets the currently active TicTacToe Game
     *
     * @return the game
     */
    public TicTacToeGame getGame() {
        return game;
    }

    @Override
    public void onPause() {
        pausedText.show();

    }

    @Override
    public void onResume() {
        pausedText.hide();

    }

    @Override
    public void onMouseClicked(MouseEvent e) {
        grid.onMouseClicked(e);
    }
}
