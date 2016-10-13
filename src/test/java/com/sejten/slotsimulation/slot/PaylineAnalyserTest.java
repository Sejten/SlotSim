package com.sejten.slotsimulation.slot;

import com.sejten.slotsimulation.games.winterberries.WinterberriesGameConf;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by piotr.s
 */
public class PaylineAnalyserTest {
    TestGameConf gc = new TestGameConf();
    PaylineAnalyser we = new PaylineAnalyser(gc);
    Reel r1;
    Reel r2;
    Reel r3;
    Reel r4;
    Reel r5;
    ReelWindow window;
    List<WinningPayline> winningPaylins;
    List<WinningPayline> shouldBe;
    Symbol sym3 = gc.high1;
    Symbol sym4 = gc.high2;
    Symbol sym5 = gc.high3;
    Symbol sym6 = gc.low1;
    Symbol sym7 = gc.low2;
    Symbol sym8 = gc.low3;
    Symbol sym9 = gc.low4;

    @Test
    public void testEvaluateReelWindow() throws Exception {
        // given

        // when
        r1 = new Reel("Reel_1", Arrays.asList(sym3, sym3, sym9));
        r2 = new Reel("Reel_2", Arrays.asList(sym4, sym9, sym9));
        r3 = new Reel("Reel_3", Arrays.asList(sym5, sym8, sym9));
        r4 = new Reel("Reel_4", Arrays.asList(sym9, sym9, sym9));
        r5 = new Reel("Reel_5", Arrays.asList(sym3, sym9, sym9));
        window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);

        winningPaylins = we.evaluateReelWindow(window);

        shouldBe = new ArrayList<>();
        shouldBe.add(new WinningPayline(new Payline(2, 2, 2, 2, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym9));
        shouldBe.add(new WinningPayline(new Payline(2, 1, 2, 1, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym9));
        //then
        assertEquals(winningPaylins, shouldBe);

        // when
        r1 = new Reel("Reel_1", Arrays.asList(sym6, sym6, sym6));
        r2 = new Reel("Reel_2", Arrays.asList(sym6, sym7, sym8));
        r3 = new Reel("Reel_3", Arrays.asList(sym9, sym6, sym4));
        r4 = new Reel("Reel_4", Arrays.asList(sym6, sym3, sym7));
        r5 = new Reel("Reel_5", Arrays.asList(sym8, sym9, sym6));
        window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);

        winningPaylins = we.evaluateReelWindow(window);

        shouldBe = new ArrayList<>();
        shouldBe.add(new WinningPayline(new Payline(0, 0, 1, 0, 0)).addWinningColumnPosition(0, 1, 2, 3).setSymbol(sym6));
        shouldBe.add(new WinningPayline(new Payline(1, 0, 1, 0, 1)).addWinningColumnPosition(0, 1, 2, 3).setSymbol(sym6));
        shouldBe.add(new WinningPayline(new Payline(2, 0, 1, 0, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym6));
        //then
        assertEquals(winningPaylins, shouldBe);

        // when
        r1 = new Reel("Reel_1", Arrays.asList(sym4, sym8, sym8));
        r2 = new Reel("Reel_2", Arrays.asList(sym4, sym4, sym4));
        r3 = new Reel("Reel_3", Arrays.asList(sym6, sym6, sym4));
        r4 = new Reel("Reel_4", Arrays.asList(sym9, sym7, sym7));
        r5 = new Reel("Reel_5", Arrays.asList(sym8, sym8, sym3));
        window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);

        winningPaylins = we.evaluateReelWindow(window);

        shouldBe = new ArrayList<>();
        shouldBe.add(new WinningPayline(new Payline(0, 1, 2, 1, 0)).addWinningColumnPosition(0, 1, 2).setSymbol(sym4));
        shouldBe.add(new WinningPayline(new Payline(0, 0, 2, 0, 0)).addWinningColumnPosition(0, 1, 2).setSymbol(sym4));
        shouldBe.add(new WinningPayline(new Payline(0, 2, 2, 2, 0)).addWinningColumnPosition(0, 1, 2).setSymbol(sym4));
        //then
        assertEquals(winningPaylins, shouldBe);

        // when
        r1 = new Reel("Reel_1", Arrays.asList(gc.low3, gc.high1, gc.high1));
        r2 = new Reel("Reel_2", Arrays.asList(gc.high1, gc.high1, gc.low2));
        r3 = new Reel("Reel_3", Arrays.asList(gc.high1, gc.high1, gc.high2));
        r4 = new Reel("Reel_4", Arrays.asList(gc.low2, gc.high1, gc.high1));
        r5 = new Reel("Reel_5", Arrays.asList(gc.high1, gc.high1, gc.low2));
        window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);

        winningPaylins = we.evaluateReelWindow(window);

        shouldBe = new ArrayList<>();
        shouldBe.add(new WinningPayline(new Payline(1, 1, 1, 1, 1)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(gc.high1));
        shouldBe.add(new WinningPayline(new Payline(2, 1, 0, 1, 2)).addWinningColumnPosition(0, 1, 2, 3).setSymbol(gc.high1));
        shouldBe.add(new WinningPayline(new Payline(2, 1, 1, 1, 2)).addWinningColumnPosition(0, 1, 2, 3).setSymbol(gc.high1));
        shouldBe.add(new WinningPayline(new Payline(2, 0, 0, 0, 2)).addWinningColumnPosition(0, 1, 2).setSymbol(gc.high1));
        shouldBe.add(new WinningPayline(new Payline(1, 1, 0, 1, 1)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(gc.high1));
        shouldBe.add(new WinningPayline(new Payline(1, 0, 0, 0, 1)).addWinningColumnPosition(0, 1, 2).setSymbol(gc.high1));
        shouldBe.add(new WinningPayline(new Payline(1, 0, 1, 0, 1)).addWinningColumnPosition(0, 1, 2).setSymbol(gc.high1));
        shouldBe.add(new WinningPayline(new Payline(2, 0, 1, 0, 2)).addWinningColumnPosition(0, 1, 2).setSymbol(gc.high1));
        //then
        assertEquals(winningPaylins, shouldBe);
    }

    @Test
    public void testEvaluateReelWithWildWindow() throws Exception {
        //wild
        Symbol wild = new Symbol("WILD", SymbolType.WILD, (x) -> null);

        r1 = new Reel("Reel_1", Arrays.asList(sym3, sym4, sym9));
        r2 = new Reel("Reel_2", Arrays.asList(sym4, sym4, sym9));
        r3 = new Reel("Reel_3", Arrays.asList(sym5, wild, wild));
        r4 = new Reel("Reel_4", Arrays.asList(sym9, sym9, sym7));
        r5 = new Reel("Reel_5", Arrays.asList(sym3, sym9, sym8));
        window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);
        winningPaylins = we.evaluateReelWindow(window);
        shouldBe = new ArrayList<>();
        shouldBe.add(new WinningPayline(new Payline(1, 1, 1, 1, 1)).addWinningColumnPosition(0, 1, 2).setSymbol(sym4));
        shouldBe.add(new WinningPayline(new Payline(2, 2, 2, 2, 2)).addWinningColumnPosition(0, 1, 2).setSymbol(sym9));
        shouldBe.add(new WinningPayline(new Payline(2, 2, 1, 2, 2)).addWinningColumnPosition(0, 1, 2).setSymbol(sym9));
        shouldBe.add(new WinningPayline(new Payline(1, 1, 2, 1, 1)).addWinningColumnPosition(0, 1, 2).setSymbol(sym4));
        shouldBe.add(new WinningPayline(new Payline(1, 0, 1, 0, 1)).addWinningColumnPosition(0, 1, 2).setSymbol(sym4));
        //then
        assertEquals(winningPaylins, shouldBe);
    }

    @Test
    public void testEvaluateReelWithManyWildWindow() throws Exception {
        //wild
        Symbol wild = new Symbol("WILD", SymbolType.WILD, (x) -> null);

        r1 = new Reel("Reel_1", Arrays.asList(wild, wild, wild));
        r2 = new Reel("Reel_2", Arrays.asList(wild, wild, wild));
        r3 = new Reel("Reel_3", Arrays.asList(sym7, wild, wild));
        r4 = new Reel("Reel_4", Arrays.asList(wild, wild, sym9));
        r5 = new Reel("Reel_5", Arrays.asList(wild, wild, wild));
        window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);
        winningPaylins = we.evaluateReelWindow(window);
        shouldBe = new ArrayList<>();
        shouldBe.add(new WinningPayline(new Payline(0, 0, 0, 0, 0)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym7));
        shouldBe.add(new WinningPayline(new Payline(1, 1, 1, 1, 1)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));
        shouldBe.add(new WinningPayline(new Payline(2, 2, 2, 2, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym9));
        shouldBe.add(new WinningPayline(new Payline(0, 1, 2, 1, 0)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));
        shouldBe.add(new WinningPayline(new Payline(2, 1, 0, 1, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym7));

        shouldBe.add(new WinningPayline(new Payline(0, 0, 1, 0, 0)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));
        shouldBe.add(new WinningPayline(new Payline(2, 2, 1, 2, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym9));
        shouldBe.add(new WinningPayline(new Payline(0, 0, 2, 0, 0)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));
        shouldBe.add(new WinningPayline(new Payline(2, 2, 0, 2, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym7));
        shouldBe.add(new WinningPayline(new Payline(0, 1, 1, 1, 0)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));

        shouldBe.add(new WinningPayline(new Payline(2, 1, 1, 1, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));
        shouldBe.add(new WinningPayline(new Payline(0, 2, 2, 2, 0)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym9));
        shouldBe.add(new WinningPayline(new Payline(2, 0, 0, 0, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym7));
        shouldBe.add(new WinningPayline(new Payline(0, 1, 0, 1, 0)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym7));

        shouldBe.add(new WinningPayline(new Payline(2, 1, 2, 1, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));
        shouldBe.add(new WinningPayline(new Payline(0, 2, 0, 2, 0)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym7));
        shouldBe.add(new WinningPayline(new Payline(2, 0, 2, 0, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));
        shouldBe.add(new WinningPayline(new Payline(1, 1, 0, 1, 1)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym7));
        shouldBe.add(new WinningPayline(new Payline(1, 1, 2, 1, 1)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));

        shouldBe.add(new WinningPayline(new Payline(1, 0, 0, 0, 1)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym7));
        shouldBe.add(new WinningPayline(new Payline(1, 2, 2, 2, 1)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym9));
        shouldBe.add(new WinningPayline(new Payline(1, 0, 1, 0, 1)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));
        shouldBe.add(new WinningPayline(new Payline(1, 2, 1, 2, 1)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym9));
        shouldBe.add(new WinningPayline(new Payline(0, 2, 1, 2, 0)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym9));
        shouldBe.add(new WinningPayline(new Payline(2, 0, 1, 0, 2)).addWinningColumnPosition(0, 1, 2, 3, 4).setSymbol(sym3));

        //then
        assertEquals(winningPaylins, shouldBe);
    }

}