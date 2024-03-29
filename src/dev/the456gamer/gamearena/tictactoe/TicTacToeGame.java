package dev.the456gamer.gamearena.tictactoe;

import dev.the456gamer.gamearena.tictactoe.actortype.ActorMethod;
import dev.the456gamer.gamearena.tictactoe.actortype.ActorTypeStore;
import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.output.GameEventHandler;
import java.time.Duration;
import java.time.Instant;

public class TicTacToeGame {

    private final GameEventHandler gameEventHandler;
    private final Actor player1 = new Actor(GameSide.X, ActorTypeStore.HUMAN.getActorMethod());
    private final Actor player2 = new Actor(GameSide.O, ActorTypeStore.HUMAN.getActorMethod());
    private BoardState currentBoardState = new BoardState();
    private Instant lastUnpauseTime = Instant.now();
    private Duration timeInGame = Duration.ZERO;
    private boolean gamePaused = false;

    public TicTacToeGame(GameEventHandler gameEventHandler) {
        this(gameEventHandler, ActorTypeStore.HUMAN.getActorMethod(),
            ActorTypeStore.HUMAN.getActorMethod());
    }


    public TicTacToeGame(GameEventHandler gameEventHandler, ActorMethod player1,
        ActorMethod player2) {
        this.player1.setActiveMethod(player1);
        this.player2.setActiveMethod(player2);
        this.gameEventHandler = gameEventHandler;
    }

    public boolean isPlayer1Turn() {
        return getCurrentBoardState().getSideToMove() == GameSide.X;
    }

    public Duration getTimeInGame() {
        if (gamePaused || inInitialState() || isGameEnded()) {
            return timeInGame;
        }
        Duration timeInGame = Duration.between(lastUnpauseTime, Instant.now());
        return this.timeInGame.plus(timeInGame);
    }

    public Actor getCurrentPlayer() {
        return isPlayer1Turn() ? player1 : player2;
    }

    public void pauseGame() {
        if (isGameEnded()) {
            // game is over, no need to pause
            return;
        }

        gamePaused = true;

        if (inInitialState()) {
            gameEventHandler.onPause(this);
            return;
        }
        storeTime();
        gameEventHandler.onPause(this);
    }

    private void storeTime() {
        Duration timeInGame = Duration.between(lastUnpauseTime, Instant.now());
        this.timeInGame = this.timeInGame.plus(timeInGame);
    }

    public void resumeGame() {
        gamePaused = false;
        lastUnpauseTime = Instant.now();
        gameEventHandler.onResume(this);
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public boolean isGameEnded() {
        return currentBoardState.isTerminal();
    }


    public boolean inInitialState() {
        return currentBoardState.isInitialState();
    }

    public Actor getPlayerWithSide(GameSide side) {
        if (player1.getSide() == side) {
            return player1;
        }
        return player2;
    }


    public Actor getPlayer1() {
        return player1;
    }

    public void setPlayer1(ActorMethod player1) {
        // stop the game if it's running. done so time can be adjusted etc
        if (!this.inInitialState() && !this.isGamePaused()) {
            this.pauseGame();
        }
        // special case for gameSide 1 being an AI, as it would normally start the game immediately.
        if (this.inInitialState() && player1 instanceof AiActorMethod && !this.isGamePaused()) {
            this.pauseGame();
        }
        this.player1.setActiveMethod(player1);

    }

    public Actor getPlayer2() {
        return player2;
    }

    public void setPlayer2(ActorMethod player2) {
        if (!this.inInitialState() && !this.isGamePaused()) {
            this.pauseGame();
        }
        this.player2.setActiveMethod(player2);
    }

    public BoardState getCurrentBoardState() {
        return currentBoardState;
    }

    public void move(Move move) {
        if (isGamePaused() || isGameEnded()) {
            return;
        }

        try {
            currentBoardState = currentBoardState.withMove(move);
        } catch (IllegalArgumentException e) {
            System.out.println("Move is invalid: ignoring");
            return;
        }

        if (inInitialState()) {
            gameEventHandler.onFirstMove(this);
            lastUnpauseTime = Instant.now();
        }
        if (isGameEnded()) {
            storeTime();
            gameEventHandler.onGameEnd(this);
        }
    }
}
