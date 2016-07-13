package com.sejten.slotsimulation.gui;

import javafx.scene.layout.Pane;

/**
 * Created by piotr.s
 */
public class SlotPlayWindow {
    private SlotGui sgui;

    public SlotPlayWindow(Pane rw) {
        sgui = new WinterberriesGui(rw);
    }
}
