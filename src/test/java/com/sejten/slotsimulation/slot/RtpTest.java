package com.sejten.slotsimulation.slot;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by piotr.s on 2016-08-26.
 */
public class RtpTest {
    @Test
    public void testRtp() {
        GameConf gc = new TestGameConf();
        SlotController sc = new TestSlotController(gc);
        int numberOfSpins = 5000000;
        int iterations;
        String output = "";
        output += "Starting calculation for: " + NumberFormat.getNumberInstance(Locale.ENGLISH).format(numberOfSpins) + " of spins.";
        long tStart = System.currentTimeMillis();
        System.out.println(output);
        for (iterations = 0; iterations < numberOfSpins; iterations++) {
            sc.spin();
        }
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        output += "\nElapsed time: " + elapsedSeconds + " s." + "\n" + sc.getPlayer() + "\n" + sc.getStatisticsProcessor().setNumberOfSpins(numberOfSpins);
        System.out.println(output);
        assert (sc.getPlayer().getRtp() > 0.158);
        assert (sc.getPlayer().getRtp() < 0.160);
    }
}
