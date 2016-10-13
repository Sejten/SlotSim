package com.sejten.slotsimulation.slot;

import com.sejten.slotsimulation.games.winterberries.WinterberriesGameConf;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.ArrayList;

/**
 * Created by piotr.s on 2016-04-19.
 */
public class StatisticsProcessorTest {
    TestGameConf gc = new TestGameConf();

    @Test
    public void testProcess() throws Exception {
        // setting up result reel window
        StatisticsProcessor sp = new StatisticsProcessor();
        ArrayList wp = new ArrayList<>();
        wp.add(new WinningPayline(new Payline(0, 1, 2, 2, 1)).addWinningColumnPosition(0, 1, 2, 4).setSymbol(gc.high1));
        wp.add(new WinningPayline(new Payline(0, 1, 1, 1, 2)).addWinningColumnPosition(0, 1, 2, 3).setSymbol(gc.high2));
        wp.add(new WinningPayline(new Payline(0, 1, 1, 2, 2)).addWinningColumnPosition(0, 1, 2).setSymbol(gc.high3));
        wp.add(new WinningPayline(new Payline(0, 1, 0, 0, 0)).addWinningColumnPosition(1, 2, 3).setSymbol(gc.high1));
        wp.add(new WinningPayline(new Payline(0, 0, 1, 0, 0)).addWinningColumnPosition(1, 2, 3, 4).setSymbol(gc.low1));
        wp.add(new WinningPayline(new Payline(0, 0, 1, 1, 0)).addWinningColumnPosition(1, 2, 3, 4).setSymbol(gc.low1));
        sp.process(wp);
        wp = new ArrayList<>();
        wp.add(new WinningPayline(new Payline(0, 1, 2, 2, 1)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(gc.high1));
        wp.add(new WinningPayline(new Payline(0, 1, 1, 1, 2)).addWinningColumnPosition(0, 1, 2, 3).setSymbol(gc.high3));
        wp.add(new WinningPayline(new Payline(0, 1, 1, 2, 2)).addWinningColumnPosition(0, 1, 2).setSymbol(gc.low2));
        sp.process(wp);
        wp = new ArrayList<>();
        wp.add(new WinningPayline(new Payline(0, 1, 2, 2, 1)).addWinningColumnPosition(0, 1, 2).setSymbol(gc.high3));
        wp.add(new WinningPayline(new Payline(0, 1, 2, 2, 1)).addWinningColumnPosition(0, 1, 2).setSymbol(gc.high3));
        wp.add(new WinningPayline(new Payline(0, 1, 2, 2, 1)).addWinningColumnPosition(0, 1, 2).setSymbol(gc.high3));
        wp.add(new WinningPayline(new Payline(0, 1, 2, 2, 1)).addWinningColumnPosition(0, 1, 2).setSymbol(gc.high3));

        sp.setNumberOfSpins(50);

        sp.process(wp);
        System.out.println(sp);
        assertEquals(sp.toString(), "Number of spins: 50\n" +
                "Hits: 3\n" +
                "HF: 6%\n");
    }

}