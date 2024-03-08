package dev.the456gamer.gamearena.tictactoe.output.menubar;

import dev.the456gamer.gamearena.tictactoe.Actor;
import dev.the456gamer.gamearena.tictactoe.actortype.ActorMethod;
import dev.the456gamer.gamearena.tictactoe.actortype.ActorTypeStore;
import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 * a menu for selecting how a gameSide is controlled 2 instances of this should be created, one for
 * each game gameSide
 * <p>
 * should allow selecting between each class type show id, hover text config ai move delay
 */
// symbol pair? ⭕❌
public class PlayerSelectMenu {

    private final JMenu menu;
    private final GameWindow window;
    private final GameSide side;

    private final List<PlayerSelectItem> selectablePlayers = new ArrayList<>();
    private final JMenu aiDelayMenu;

    public ActorTypeStore getActiveMethod() {
        return activeMethod;
    }

    private ActorTypeStore activeMethod = ActorTypeStore.HUMAN;

    public PlayerSelectMenu(GameWindow window, GameSide side,
        Consumer<ActorMethod> actorMethodSetter) {
        this.window = window;
        this.side = side;
        // only null until first update, then never after (hopefully)
        // since its linked to game (to set the settings), it cant be pre-set.
        this.actor = null;
        menu = new JMenu("Player: tmp");

        ButtonGroup radioGroup = new ButtonGroup();
        for (ActorTypeStore itemType : ActorTypeStore.values()) {
            PlayerSelectItem item = new PlayerSelectItem(itemType);
            selectablePlayers.add(item);
            menu.add(item.getMenuItem());
            item.getMenuItem().addActionListener(e -> {
                // trust that its setup right.
                this.activeMethod = item.getMethod();
                actorMethodSetter.accept(item.getMethod().getActorMethod());
                // larger scope, it needs to redraw the pause button
                window.redraw();
            });
            radioGroup.add(item.getMenuItem());
        }

        aiDelayMenu = new JMenu("AI Move Delay: 0ms");
        Map<Integer, JRadioButtonMenuItem> delayButtons = new HashMap<>();
        ButtonGroup aiDelayGroup = new ButtonGroup();
        for (int delay : new int[]{0, 10, 20, 50, 100, 200, 500, 1000}) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(delay + "ms");
            item.addActionListener(e -> {
                this.setAiMoveDelay(delay);
                aiDelayMenu.setText("AI Move Delay: " + delay + "ms");
                refresh();
            });
            aiDelayGroup.add(item);
            aiDelayMenu.add(item);
            delayButtons.put(delay, item);
        }

        delayButtons.getOrDefault(100, delayButtons.entrySet().stream().findAny().orElseThrow()
            .getValue()).setSelected(true);

        menu.add(aiDelayMenu);


    }

    public Actor getActor() {
        return actor;
    }

    private Actor actor;

    public int getAiMoveDelay() {
        return this.actor.getMoveDelay();
    }

    public void setAiMoveDelay(int delay) {
        this.actor.setMoveDelay(delay);
    }


    public JMenu getMenu() {
        return menu;
    }

    public void setActor(Actor actor) {
        if (this.actor == null) {
            this.actor = actor;
            return;
        }

        ActorMethod activeMethod = this.actor.getActiveMethod();
        int moveDelay = this.actor.getMoveDelay();
        this.actor = actor;
        this.actor.setActiveMethod(activeMethod);
        this.actor.setMoveDelay(moveDelay);

    }

    public void refresh() {
        setActor(window.getGame().getPlayerWithSide(side));
        menu.setText((actor.getSide() == window.getGame().getCurrentPlayer().getSide() ? "╭" : " ")
            + side.getSymbol() + ": " + (actor.getActiveMethod() instanceof AiActorMethod ?
            actor.getMoveDelay() + "ms delay then " : "") + actor.getActiveMethod().getName());
        menu.setToolTipText(actor.getActiveMethod().getTooltip());
        aiDelayMenu.setText("AI Move Delay: " + actor.getMoveDelay() + "ms");

        for (PlayerSelectItem item : selectablePlayers) {
            if (item.getMethod().getActorMethod() == actor.getActiveMethod()) {
                item.getMenuItem().setSelected(true);
            }
        }
    }

}
