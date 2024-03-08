package dev.the456gamer.gamearena.tictactoe.board;

import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;

public class Move {

    public final GameSide gameSide;
    public final int x;
    public final int y;
    private final BoardState previousBoardState;

    public Move(GameSide gameSide, int x, int y, BoardState startState) {
        if (startState.getTileState(x, y) != null) {
            throw new IllegalArgumentException("Tile is already occupied");
        }
        this.gameSide = gameSide;
        this.x = x;
        this.y = y;
        this.previousBoardState = startState;
    }


    public BoardState getPreviousBoardState() {
        return previousBoardState;
    }


    public BoardState getBoardState() {
        return getPreviousBoardState().withMove(this);
    }
    // needs to represent a tile position to go to

}
