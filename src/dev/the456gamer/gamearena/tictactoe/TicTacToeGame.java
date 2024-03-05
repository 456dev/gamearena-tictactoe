package dev.the456gamer.gamearena.tictactoe;

import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.board.state.GameState;
import dev.the456gamer.gamearena.tictactoe.player.AiActor;
import dev.the456gamer.gamearena.tictactoe.player.Actor;

public class TicTacToeGame {

    private Actor player1;
    private Actor player2;

    private BoardState currentBoardState;

    private GameState gameState;

    private boolean isPlayer1Turn;

    private int msInGame; // TODO better time tracking?


    private boolean gamePaused;

    public Actor getCurrentPlayer() {
        return isPlayer1Turn ? player1 : player2;
    }

    public void pauseGame() {
        gamePaused = true;
    }

    public void resumeGame() {
        gamePaused = false;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public boolean isGameActive() {
        return !(currentBoardState.isTerminal());
    }


    public boolean inIntitalState() {
        return currentBoardState.isInitialState();
    }

    public Actor getPlayer1() {
        return player1;
    }

    public void setPlayer1(Actor player1) {
        // stop the game if it's running. done so time can be adjusted etc
        if (!this.inIntitalState()) {
            this.pauseGame();
        }
        // special case for player 1 being an AI, as it would normally start the game immediately.
        if (this.inIntitalState() && player1 instanceof AiActor) {
            this.pauseGame();
        }
        this.player1 = player1;

    }

    public Actor getPlayer2() {
        return player2;
    }

    public void setPlayer2(Actor player2) {
        if (!this.inIntitalState()) {
            this.pauseGame();
        }
        this.player2 = player2;
    }

}
