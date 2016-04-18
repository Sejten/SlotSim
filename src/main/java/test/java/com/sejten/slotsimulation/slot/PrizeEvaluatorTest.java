package test.java.com.sejten.slotsimulation.slot;

import com.sejten.slotsimulation.slot.*;
import org.testng.annotations.Test;


import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by piotr.s
 */
public class PrizeEvaluatorTest {
    @Test
    public void testEvaluate() throws Exception {
        //given
        GameConf gc = new GameConf();
        // setting up result reel window
        Symbol sym3 = gc.getSymbol("SYM3");
        Symbol sym4 = gc.getSymbol("SYM4");
        Symbol sym5 = gc.getSymbol("SYM5");
        Symbol sym8 = gc.getSymbol("SYM8");
        Symbol sym9 = gc.getSymbol("SYM9");
        Reel r1 = new Reel("Reel_1", Arrays.asList(sym3, sym3, sym9));
        Reel r2 = new Reel("Reel_2", Arrays.asList(sym4, sym9, sym9));
        Reel r3 = new Reel("Reel_3", Arrays.asList(sym5, sym8, sym9));
        Reel r4 = new Reel("Reel_4", Arrays.asList(sym9, sym9, sym9));
        Reel r5 = new Reel("Reel_5", Arrays.asList(sym3, sym9, sym9));
        ReelWindow window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);

        WinAnalyser we = new WinAnalyser(gc);
        List<WinningPayline> winningPaylines = we.evaluateReelWindow(window);

        //when
        double prize = new PrizeEvaluator(gc).evaluate(winningPaylines);

        //then
        assertEquals(prize, 79.0);
    }

}