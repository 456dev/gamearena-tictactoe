package dev.the456gamer.gamearena.tictactoe.board.state;

/**
 * Represents the side a gameSide is playing on
 * X or O, where player1 moves first every time
 */
public enum GameSide {
    X("❌"), O("⭕");

    private final String symbol;

    public String getSymbol() {
        return symbol;
    }

    GameSide(String symbol) {
        this.symbol = symbol;
    }

    public GameSide getOpponent() {
        return this == X ? O : X;
    }
}
