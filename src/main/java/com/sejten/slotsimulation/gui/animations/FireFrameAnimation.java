package com.sejten.slotsimulation.gui.animations;

import com.sejten.slotsimulation.gui.SpriteAnimation;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Created by piotr.s on 2016-05-19.
 */
public class FireFrameAnimation extends SlotAnimation {

    public FireFrameAnimation(ImageView i) {
        int COLUMNS = 6;
        int COUNT = 18;
        int OFFSET_X = 0;
        int OFFSET_Y = 0;
        int WIDTH = 375;
        int HEIGHT = 300;
        image = i;
        image.setVisible(true);
        animation = new SpriteAnimation(
                image,
                Duration.millis(800.0),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
    }


}
