package dev.the456gamer.gamearena;

import java.awt.event.MouseEvent;

public class CustomGameArena extends uk.ac.lancaster.gamearena.GameArena {

    private final CustomArenaEvents eventHandler;

    public CustomGameArena(int width, int height, CustomArenaEvents eventHandler) {
        super(width, height);
        this.eventHandler = eventHandler;
    }

    public CustomGameArena(int width, int height, boolean createWindow,
        CustomArenaEvents eventHandler) {
        super(width, height, createWindow);
        this.eventHandler = eventHandler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        eventHandler.onMouseClicked(e);
    }
}
