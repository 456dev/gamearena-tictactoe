package dev.the456gamer.gamearena.tictactoe.actortype;

import dev.the456gamer.gamearena.tictactoe.TicTacToeGame;

public abstract class BaseActorMethod implements ActorMethod {

    final TicTacToeGame game;

    public BaseActorMethod(TicTacToeGame game) {

        this.game = game;
    }
}