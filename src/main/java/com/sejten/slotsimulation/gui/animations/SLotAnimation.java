package com.sejten.slotsimulation.gui.animations;

import javafx.animation.Animation;
import javafx.scene.image.ImageView;

/**
 * Created by piotr.s on 2016-05-20.
 */
public class SlotAnimation {
    protected Animation animation;
    protected ImageView image;

    public SlotAnimation setCycleCount(int c) {
        animation.setCycleCount(c);
        return this;
    }

    public SlotAnimation play() {
        animation.play();
        return this;
    }

    public SlotAnimation stop() {
        animation.stop();
        image.setVisible(false);
        return this;
    }
}

