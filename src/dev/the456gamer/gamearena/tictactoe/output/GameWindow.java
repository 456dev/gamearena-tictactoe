package dev.the456gamer.gamearena.tictactoe.output;

import dev.the456gamer.gamearena.tictactoe.TicTacToeGame;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import uk.ac.lancaster.gamearena.GameArena;

/**
 * manager for the game's window.
 */
public class GameWindow {

    public final JFrame window;
    public final GameArena arena;

    private TicTacToeGame game;


    public GameWindow() {
        window = new JFrame("GameArena Tic Tac Toe");

        arena = new GameArena(500, 500, false);

        window.setDefaultCloseOperation(
            WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(arena);
        window.setSize(500, 500);
        window.setResizable(false); // easy way out.
        resetGame();
        window.setVisible(true);
    }

    /**
     * resets the game to a new game
     */
    public void resetGame() {
        game = new TicTacToeGame();
    }


    /**
     * gets the currently active TicTacToe Game
     * @return the game
     */
    public TicTacToeGame getGame() {
        return game;
    }
}
