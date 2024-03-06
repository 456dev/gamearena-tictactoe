package dev.the456gamer.gamearena.tictactoe.board.state;

import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.WinLocations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardState {

    private GameState gameState;
    private final List<Move> validMoves = new ArrayList<>(9);
    private boolean calculatedMoves = false;
    private WinLocations wonBoardPosition;

    private boolean inital = false;
    private final GameSide[][] board;

    public BoardState() {
        this(new GameSide[3][3]);
        inital = true;
    }

    private BoardState(GameSide[][] backingList) {
        board = backingList;
    }

    // todo figure out representation of the 9 cells
    //  metadata needed: if there is a tile on there? which gameSide's tile is it?.
    //  that should be it. ActiveBoardState has the play() method that will be used to create a new the board state.
    //  with the new move made by the gameSide added.


    public GameState getGameState() {
        if (gameState == null) {
            gameState = calculateGameState();
        }

        return gameState;
    }

    private GameState calculateGameState() {
        if (inital) {
            return GameState.INITIAL;
        }
        // TODO
        // check win
        //  3 in a row *3
        //  3 in a column *3
        //  3 in a diagonal *2
        // this should set wonBoardPosition too

        // check draw
        //  if no more moves can be made

        // if none of the above, then the game is still in progress

        // this is the same for every identical board state, so we can cache it.
        return GameState.IN_PROGRESS;
    }

    public List<Move> getValidMoves() {
        if (!calculatedMoves) {
            validMoves.addAll(calculateValidMoves());
            calculatedMoves = true;
        }
        return validMoves;
    }

    private List<Move> calculateValidMoves() {
        // TODO
        return Collections.emptyList();
    }

    public boolean isTerminal() {
        return !(getGameState() == GameState.IN_PROGRESS || getGameState() == GameState.INITIAL);
    }

    public WinLocations getWonBoardPosition() {
        if (getGameState() == GameState.X_WON || getGameState() == GameState.O_WON) {
            return wonBoardPosition;
        }
        return null;
    }

    public boolean isInitialState() {
        // true if there has been no moves made yet.
        return getGameState() == GameState.INITIAL;
    }


    public BoardState withMove(Move move) {
        if (!getValidMoves().contains(move) || !move.getPreviousBoardState().equals(this)) {
            throw new IllegalArgumentException("Move is not valid");
        }

        GameSide[][] backingList = board.clone();
        backingList[move.x][move.x] = move.gameSide;

        return new BoardState(backingList);
    }

    public GameSide getTileState(int x, int y) {
        return board[x][y];
    }
}
