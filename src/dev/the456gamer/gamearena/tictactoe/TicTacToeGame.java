package dev.the456gamer.gamearena.tictactoe;

import dev.the456gamer.gamearena.tictactoe.actortype.ActorMethod;
import dev.the456gamer.gamearena.tictactoe.actortype.ActorTypeStore;
import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.board.state.GameState;

public class TicTacToeGame {

    private final Actor player1 = new Actor(GameSide.X, ActorTypeStore.HUMAN.getActorMethod());
    private final Actor player2 = new Actor(GameSide.O, ActorTypeStore.HUMAN.getActorMethod());

    private BoardState currentBoardState = new BoardState();

    public boolean isPlayer1Turn() {
        return getCurrentBoardState().getSideToMove() == GameSide.X;
    }

    // TODO time tracking
    //  last unpause time
    //  total time in game
    //  on pause -> get difference between now and last unpause time, add to total time
    //  on unpause -> set last unpause time to now
    //  on end, trigger pause? or manually stop timer?
    //  on first move start timer. (even though it's not paused.)

    private int msInGame = 0;


    private boolean gamePaused = false;

    public Actor getCurrentPlayer() {
        return isPlayer1Turn() ? player1 : player2;
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
        if (!this.inIntitalState()) {
            this.pauseGame();
        }
        // special case for gameSide 1 being an AI, as it would normally start the game immediately.
        if (this.inIntitalState() && player1 instanceof AiActorMethod) {
            this.pauseGame();
        }
        this.player1.setActiveMethod(player1);

    }

    public Actor getPlayer2() {
        return player2;
    }

    public void setPlayer2(ActorMethod player2) {
        if (!this.inIntitalState()) {
            this.pauseGame();
        }
        this.player2.setActiveMethod(player2);
    }

    public BoardState getCurrentBoardState() {
        return currentBoardState;
    }
}
