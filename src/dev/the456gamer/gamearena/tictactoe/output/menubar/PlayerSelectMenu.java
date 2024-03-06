package dev.the456gamer.gamearena.tictactoe.output.menubar;

import dev.the456gamer.gamearena.tictactoe.Actor;
import dev.the456gamer.gamearena.tictactoe.actortype.ActorMethod;
import dev.the456gamer.gamearena.tictactoe.actortype.ActorTypeStore;
import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * a menu for selecting how a gameSide is controlled 2 instances of this should be created, one for
 * each game gameSide
 * <p>
 * should allow selecting between each class type show id, hover text config ai move delay
 * <p>
 * TODO highlight while its that players turn
 *  draw icon ●, 🔴, ○, ⬤, ⭘ 🔵
 */
// symbol pair? ⭕❌
public class PlayerSelectMenu {

    private final JMenu menu;
    private final GameWindow window;
    private final GameSide side;

    private final List<PlayerSelectItem> selectablePlayers = new ArrayList<>();
    private final JMenu aiDelayMenu;

    public PlayerSelectMenu(GameWindow window, GameSide side) {
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
                this.getActor().setActiveMethod(item.getMethod().getActorMethod());
                refresh();
            });
            radioGroup.add(item.getMenuItem());
        }

        aiDelayMenu = new JMenu("AI Move Delay: 0ms");
        for (int i : new int[]{0, 1, 2, 5, 10, 20, 50, 100}) {
            int delay = i * 10;
            JMenuItem item = new JMenuItem(delay + "ms");
            item.addActionListener(e -> {
                this.setAiMoveDelay(delay);
                aiDelayMenu.setText("AI Move Delay: " + delay + "ms");
                refresh();
            });
            aiDelayMenu.add(item);
        }

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
        menu.setText(side.getSymbol() + ": " + (actor.getActiveMethod() instanceof AiActorMethod ?
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
