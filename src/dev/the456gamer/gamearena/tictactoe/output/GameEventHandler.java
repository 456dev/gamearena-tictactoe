package dev.the456gamer.gamearena.tictactoe.output;

import dev.the456gamer.gamearena.tictactoe.TicTacToeGame;

public interface GameEventHandler {

    void onPause(TicTacToeGame game);
    void onResume(TicTacToeGame game);

}
