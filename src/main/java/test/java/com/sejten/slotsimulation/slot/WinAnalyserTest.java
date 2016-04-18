package test.java.com.sejten.slotsimulation.slot;

import com.sejten.slotsimulation.slot.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by piotr.s
 */
public class WinAnalyserTest {
    @Test
    public void testEvaluateReelWindow() throws Exception {
        // given
        ReelWindow window;
        GameConf gc = new GameConf();
        WinAnalyser we = new WinAnalyser(gc);
        Reel r1;
        Reel r2;
        Reel r3;
        Reel r4;
        Reel r5;
        List<WinningPayline> winningPaylins;
        List<WinningPayline> shouldBe;
        Symbol sym3 = gc.getSymbol("SYM3");
        Symbol sym4 = gc.getSymbol("SYM4");
        Symbol sym5 = gc.getSymbol("SYM5");
        Symbol sym6 = gc.getSymbol("SYM6");
        Symbol sym7 = gc.getSymbol("SYM7");
        Symbol sym8 = gc.getSymbol("SYM8");
        Symbol sym9 = gc.getSymbol("SYM9");

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
        shouldBe.add(new WinningPayline(new Payline(2, 2, 2, 2, 2)).addWinningColumnPosition(0, 1, 2, 3, 4));
        shouldBe.add(new WinningPayline(new Payline(0, 1, 2, 1, 0)).addWinningColumnPosition(1, 2, 3));
        shouldBe.add(new WinningPayline(new Payline(0, 2, 2, 2, 0)).addWinningColumnPosition(1, 2, 3));
        shouldBe.add(new WinningPayline(new Payline(2, 1, 2, 1, 2)).addWinningColumnPosition(0, 1, 2, 3, 4));
        shouldBe.add(new WinningPayline(new Payline(2, 0, 2, 0, 2)).addWinningColumnPosition(2, 3, 4));
        shouldBe.add(new WinningPayline(new Payline(1, 1, 2, 1, 1)).addWinningColumnPosition(1, 2, 3, 4));
        shouldBe.add(new WinningPayline(new Payline(1, 2, 2, 2, 1)).addWinningColumnPosition(1, 2, 3, 4));
        //then
        assertEquals(winningPaylins, shouldBe);

        // when
        r1 = new Reel("Reel_1", Arrays.asList(sym3, sym4, sym5));
        r2 = new Reel("Reel_2", Arrays.asList(sym6, sym7, sym8));
        r3 = new Reel("Reel_3", Arrays.asList(sym9, sym6, sym4));
        r4 = new Reel("Reel_4", Arrays.asList(sym6, sym3, sym7));
        r5 = new Reel("Reel_5", Arrays.asList(sym8, sym9, sym6));
        window = new ReelWindow();
        window.addReel(r1, r2, r3, r4, r5);

        winningPaylins = we.evaluateReelWindow(window);

        shouldBe = new ArrayList<>();
        shouldBe.add(new WinningPayline(new Payline(0, 0, 1, 0, 0)).addWinningColumnPosition(1, 2, 3));
        shouldBe.add(new WinningPayline(new Payline(1, 0, 1, 0, 1)).addWinningColumnPosition(1, 2, 3));
        shouldBe.add(new WinningPayline(new Payline(2, 0, 1, 0, 2)).addWinningColumnPosition(1, 2, 3, 4));
        //then
        assertEquals(winningPaylins, shouldBe);
    }

}