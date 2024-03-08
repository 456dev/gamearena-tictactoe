package dev.the456gamer.gamearena.tictactoe.board.state;

import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.WinLocations;
import dev.the456gamer.gamearena.tictactoe.board.WinLocations.WinLocationType;
import java.util.ArrayList;
import java.util.List;

public class BoardState {

    private final GameSide sideToMove;
    private GameState gameState;
    private final List<Move> validMoves = new ArrayList<>(9);
    private boolean calculatedMoves = false;
    private WinLocations wonBoardPosition;

    private boolean inital = false;
    private final GameSide[][] board;

    public BoardState() {
        this(new GameSide[3][3], GameSide.X);
        inital = true;
    }

    private BoardState(GameSide[][] backingList, GameSide sideToMove) {
        board = backingList;
        this.sideToMove = sideToMove;
    }

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

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] != null) {
                    wonBoardPosition = WinLocations.getWinLocation(WinLocationType.COLUMN, i);
                    return board[i][0] == GameSide.X ? GameState.X_WON : GameState.O_WON;
                }
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] != null) {
                    wonBoardPosition = WinLocations.getWinLocation(WinLocationType.ROW, i);
                    return board[0][i] == GameSide.X ? GameState.X_WON : GameState.O_WON;
                }
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] != null) {
                wonBoardPosition = WinLocations.getWinLocation(WinLocationType.DIAGONAL, 0);
                return board[0][0] == GameSide.X ? GameState.X_WON : GameState.O_WON;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] != null) {
                wonBoardPosition = WinLocations.getWinLocation(WinLocationType.DIAGONAL, 1);
                return board[0][2] == GameSide.X ? GameState.X_WON : GameState.O_WON;
            }
        }

        boolean boardFull = true, boardEmpty = true;

        for (GameSide[] row : board) {
            for (GameSide tile : row) {
                if (tile == null) {
                    boardFull = false;
                } else {
                    boardEmpty = false;
                }
            }
        }

        if (boardFull) {
            return GameState.DRAW;
        }

        if (boardEmpty) {
            inital = true;
            return GameState.INITIAL;
        }

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
        List<Move> validMoves = new ArrayList<>(9);

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board[x][y] == null) {
                    validMoves.add(new Move(sideToMove, x, y, this));
                }
            }
        }

        return validMoves;
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

        GameSide[][] side = new GameSide[3][3];

        // deep object clone
        for (int i = 0; i < 3; i++) {
            side[i] = board[i].clone();
        }

        side[move.x][move.y] = move.gameSide;

        return new BoardState(side, move.gameSide == GameSide.X ? GameSide.O : GameSide.X);
    }

    public GameSide getTileState(int x, int y) {
        return board[x][y];
    }

    public GameSide getSideToMove() {
        return sideToMove;
    }
}
