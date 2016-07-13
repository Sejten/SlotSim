package com.sejten.slotsimulation.slot;

import com.sejten.slotsimulation.winterberries.WinterberriesGameConf;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Created by piotr.s on 2016-04-19.
 */
public class ReelTest {
    @Test
    public void testGetSymbolAt() throws Exception {
        GameConf gc = new WinterberriesGameConf();
        // when
        Symbol sym3 = gc.getSymbol("SYM3");
        Symbol sym4 = gc.getSymbol("SYM4");
        Symbol sym5 = gc.getSymbol("SYM5");
        Symbol sym6 = gc.getSymbol("SYM6");
        Symbol sym7 = gc.getSymbol("SYM7");
        Symbol sym8 = gc.getSymbol("SYM8");
        Symbol sym9 = gc.getSymbol("SYM9");

        Reel r1 = new Reel("Reel_1", Arrays.asList(sym3, sym4, sym5, sym6, sym7, sym8, sym9));

        assertEquals(r1.getSymbolAt(0, 3).toString(), "SYM3 SYM4 SYM5");
        assertEquals(r1.getSymbolAt(1, 5).toString(), "SYM4 SYM5 SYM6 SYM7 SYM8");
        assertEquals(r1.getSymbolAt(6, 3).toString(), "SYM9 SYM3 SYM4");
        assertEquals(r1.getSymbolAt(7, 3).toString(), "SYM3 SYM4 SYM5");
    }

}