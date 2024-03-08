package dev.the456gamer.gamearena.tictactoe.output;

import dev.the456gamer.gamearena.CustomArenaEvents;
import dev.the456gamer.gamearena.CustomGameArena;
import dev.the456gamer.gamearena.tictactoe.TicTacToeGame;
import dev.the456gamer.gamearena.tictactoe.actortype.ActorMethod;
import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.GameState;
import dev.the456gamer.gamearena.tictactoe.output.menubar.MenuBar;
import dev.the456gamer.gamearena.tictactoe.output.text.GameStateText;
import dev.the456gamer.gamearena.tictactoe.output.text.PausedText;
import dev.the456gamer.gamearena.tictactoe.output.text.TimerText;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;
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
    private final WonGameOverlay wonGameOverlay;

    private TicTacToeGame game;


    public GameWindow() {
        window = new JFrame("GameArena Tic Tac Toe");

        arena = new CustomGameArena(1000, 500, false, this);
        arena.setSize(1000, 500);
        arena.setMinimumSize(new Dimension(1000, 500));
        arena.setPreferredSize(new Dimension(1000, 500));
        arena.setMaximumSize(new Dimension(1000, 500));

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.setContentPane(arena);

        window.setBackground(Color.BLACK);

        menuBar = new MenuBar(this);

        window.setJMenuBar(menuBar.getMenuBar());

        grid = new GameGrid(this);

        pausedText = new PausedText(this);

        gameStateText = new GameStateText(this);
        timerText = new TimerText(this);
        wonGameOverlay = new WonGameOverlay(this);

        window.pack();
        window.setMinimumSize(window.getSize());
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        resetGame();

        // final refresh to finish updating layout (font context isn't available until after first a couple of draws?

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                redraw();
            }
        }, 50);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerText.refresh();
            }
        }, 100, 55);

    }


    public void startPlaying() {
        // take input from _current_ player -> can be swapped mid game

        while (true) { // todo quit without this?
            // ensure latest game state: resetting = new game.
            TicTacToeGame game = this.getGame();

            if (game.isGamePaused() || !game.isGameActive()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("interrupted, " + e.getMessage());
                }
                continue;
            }

            // ensure currentplayer and active method stay the same after the thread sleep
            ActorMethod activeMethod = game.getCurrentPlayer().getActiveMethod();
            if (activeMethod instanceof AiActorMethod) {
                try {
                    // TODO ensure this keeps working. swap to new thread to handle player moves?
                    Thread.sleep(game.getCurrentPlayer().getMoveDelay());
                } catch (InterruptedException e) {
                    System.out.println("interrupted, " + e.getMessage());
                }
            }

            Move move;
            try {
                move = activeMethod.getNextMove(game.getCurrentBoardState());
            } catch (TimeoutException e) {
//                System.out.println("move not ready yet");
                continue;
            }
            game.move(move);
            redraw();
        }
    }

    /**
     * resets the game to a new game
     */
    public void resetGame() {
        // TODO this doesnt reset actors -> it should default to human, but instead is current selection
        //  (good) but doesnt pause (bad?)
        //  dev.the456gamer.gamearena.tictactoe.output.menubar.PlayerSelectMenu.setActor -> happens in refresh, which is too late.
        ActorMethod actorMethod = menuBar.getXPlayerSelectMenu().getActiveMethod().getActorMethod();
        game = new TicTacToeGame(this,
            actorMethod,
            menuBar.getOPlayerSelectMenu().getActiveMethod().getActorMethod());

        if (actorMethod instanceof AiActorMethod) {
            game.pauseGame();
        }
        redraw();

    }

    public void redraw() {
        gameStateText.update();
        grid.drawBoardState(game.getCurrentBoardState());
        menuBar.refresh();
        if (game.isGamePaused()) {
            pausedText.show();
        } else {
            pausedText.hide();
        }
        timerText.refresh();
        if (game.getCurrentBoardState().getGameState() == GameState.O_WON
            || game.getCurrentBoardState().getGameState() == GameState.X_WON) {
            wonGameOverlay.show(game.getCurrentBoardState());
        } else {
            wonGameOverlay.hide();
        }
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
    public void onPause(TicTacToeGame game) {
        redraw();
    }

    @Override
    public void onResume(TicTacToeGame game) {
        redraw();
    }

    @Override
    public void onFirstMove(TicTacToeGame game) {
        redraw();
    }

    @Override
    public void onGameEnd(TicTacToeGame game) {
        redraw();

    }

    @Override
    public void onMouseClicked(MouseEvent e) {
        grid.onMouseClicked(e);
    }
}
