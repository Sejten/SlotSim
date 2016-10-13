package com.sejten.slotsimulation.slot;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by piotr.s
 */
public class PrizeConditionEvaluatorTest {
    TestGameConf gc = new TestGameConf();
    Symbol sym3 = gc.high1;
    Symbol sym4 = gc.high2;
    Symbol sym5 = gc.high3;
    Symbol sym9 = gc.low1;
    Symbol sym8 = gc.low2;

    @Test
    public void testEvaluate() throws Exception {
        //given
        // setting up result reel window
        Reel r1 = new Reel("Reel_1", Arrays.asList(sym5, sym8, sym9));
        Reel r2 = new Reel("Reel_2", Arrays.asList(sym5, sym8, sym9));
        Reel r3 = new Reel("Reel_3", Arrays.asList(sym8, sym8, sym9));
        Reel r4 = new Reel("Reel_4", Arrays.asList(sym8, sym9, sym5));
        Reel r5 = new Reel("Reel_5", Arrays.asList(sym3, sym3, sym5));
        ReelWindow window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);

        PaylineAnalyser we = new PaylineAnalyser(gc);
        List<WinningPayline> winningPaylines = we.evaluateReelWindow(window);

        //when
        List<Prize> prizes = new PrizeEvaluator(gc, new StatisticsProcessor()).getPrizesFromNormalSymbols(winningPaylines);

        //then
        assertEquals(prizes.size(), 3);
        assertEquals(prizes.get(0).amount, 3.0);
        assertEquals(prizes.get(0).type, PrizeType.COINS);
        assertEquals(prizes.get(1).amount, 5.0);
        assertEquals(prizes.get(1).type, PrizeType.COINS);
        assertEquals(prizes.get(2).amount, 3.0);
        assertEquals(prizes.get(2).type, PrizeType.COINS);
    }

    @Test
    public void testEvaluateFreespinSymbols() throws Exception {
        //given
        // setting up result reel window
        Reel r1 = new Reel("Reel_1", Arrays.asList(sym5, gc.freespin, sym9));
        Reel r2 = new Reel("Reel_2", Arrays.asList(sym5, gc.freespin, sym9));
        Reel r3 = new Reel("Reel_3", Arrays.asList(sym8, gc.freespin, sym9));
        Reel r4 = new Reel("Reel_4", Arrays.asList(sym8, sym9, sym5));
        Reel r5 = new Reel("Reel_5", Arrays.asList(sym3, sym3, sym5));
        ReelWindow window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);

        PaylineAnalyser we = new PaylineAnalyser(gc);
        List<WinningPayline> winningPaylines = we.evaluateReelWindow(window);

        //when
        List<Prize> prizes = new PrizeEvaluator(gc, new StatisticsProcessor()).getPrizesFromNormalSymbols(winningPaylines);
        List<Prize> scatterPrizes = new PrizeEvaluator(gc, new StatisticsProcessor()).getPrizesFromScatterSymbols(window);

        //then
        assertEquals(prizes.size(), 1);
        assertEquals(prizes.get(0).amount, 5.0);
        assertEquals(prizes.get(0).type, PrizeType.COINS);
        assertEquals(scatterPrizes.size(), 1);
        assertEquals(scatterPrizes.get(0).amount, 5.0);
        assertEquals(scatterPrizes.get(0).type, PrizeType.FREESPINS);
    }

    @Test
    public void testEvaluateChestSymbols() throws Exception {
        //given
        // setting up result reel window
        Reel r1 = new Reel("Reel_1", Arrays.asList(sym5, gc.freespin, sym9));
        Reel r2 = new Reel("Reel_2", Arrays.asList(sym5, gc.freespin, sym9));
        Reel r3 = new Reel("Reel_3", Arrays.asList(sym8, gc.freespin, sym9));
        Reel r4 = new Reel("Reel_4", Arrays.asList(gc.bonusChest, sym9, sym5));
        Reel r5 = new Reel("Reel_5", Arrays.asList(sym3, sym3, sym5));
        ReelWindow window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);

        PaylineAnalyser we = new PaylineAnalyser(gc);
        List<WinningPayline> winningPaylines = we.evaluateReelWindow(window);

        //when
        List<Prize> prizes = new PrizeEvaluator(gc, new StatisticsProcessor()).getPrizesFromNormalSymbols(winningPaylines);
        List<Prize> scatterPrizes = new PrizeEvaluator(gc, new StatisticsProcessor()).getPrizesFromScatterSymbols(window);

        //then
        assertEquals(prizes.size(), 1);
        assertEquals(prizes.get(0).amount, 5.0);
        assertEquals(prizes.get(0).type, PrizeType.COINS);
        assertEquals(scatterPrizes.size(), 2);
        assertEquals(scatterPrizes.get(0).amount, 2.0);
        assertEquals(scatterPrizes.get(0).type, PrizeType.FREESPINS);
        assertEquals(scatterPrizes.get(1).amount, 5.0);
        assertEquals(scatterPrizes.get(1).type, PrizeType.FREESPINS);
    }

}