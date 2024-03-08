package dev.the456gamer.gamearena.tictactoe.actortype;

import dev.the456gamer.gamearena.tictactoe.actortype.artificial.DeterministicRandomActorMethod;
import dev.the456gamer.gamearena.tictactoe.actortype.artificial.StupidAiActorMethod;
import dev.the456gamer.gamearena.tictactoe.actortype.artificial.SubPerfectAiActorMethod;
import dev.the456gamer.gamearena.tictactoe.actortype.artificial.TopLeftAiActorMethod;
import dev.the456gamer.gamearena.tictactoe.actortype.artificial.nondeterministic.RandomRandomActorMethod;

public enum ActorTypeStore {
    HUMAN(new HumanActorMethod()),

    PERFECT(new SubPerfectAiActorMethod()),

    STUPID(new StupidAiActorMethod()),

    TOP_LEFT(new TopLeftAiActorMethod()),

    DETERMINISTIC_RANDOM(new DeterministicRandomActorMethod()),

    RANDOM_RANDOM(new RandomRandomActorMethod());

    private final ActorMethod actorMethod;

    ActorTypeStore(ActorMethod actorMethod) {
        this.actorMethod = actorMethod;
    }

    public ActorMethod getActorMethod() {
        return actorMethod;
    }
}
