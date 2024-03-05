package dev.the456gamer.gamearena.tictactoe.board;

import dev.the456gamer.gamearena.tictactoe.board.state.Player;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;

public class Move {

    // constructor
    // verify move is valid



    public final Player player;
    public final int x;
    public final int y;
    private final BoardState previousBoardState;

    public Move(Player player, int x, int y, BoardState startState) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.previousBoardState = startState;
    }

    // TODO pos of tile that move was made.


    public BoardState getPreviousBoardState() {
        return previousBoardState;
    }


    public BoardState getBoardState() {
        return getPreviousBoardState().withMove(this);
    }
    // needs to represent a tile position to go to

}
