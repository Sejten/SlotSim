package com.sejten.slotsimulation.slot;

import com.sejten.slotsimulation.games.winterberries.WinterberriesGameConf;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Created by piotr.s on 2016-04-19.
 */
public class ReelTest {
    @Test
    public void testGetSymbolAt() throws Exception {

        // when
        Symbol sym3 = new Symbol("SYM3", (x) -> null);
        Symbol sym4 = new Symbol("SYM4", (x) -> null);
        Symbol sym5 = new Symbol("SYM5", (x) -> null);
        Symbol sym6 = new Symbol("SYM6", (x) -> null);
        Symbol sym7 = new Symbol("SYM7", (x) -> null);
        Symbol sym8 = new Symbol("SYM8", (x) -> null);
        Symbol sym9 = new Symbol("SYM9", (x) -> null);

        Reel r1 = new Reel("Reel_1", Arrays.asList(sym3, sym4, sym5, sym6, sym7, sym8, sym9));

        assertEquals(r1.getSymbolAt(0, 3).toString(), "SYM3 SYM4 SYM5");
        assertEquals(r1.getSymbolAt(1, 5).toString(), "SYM4 SYM5 SYM6 SYM7 SYM8");
        assertEquals(r1.getSymbolAt(6, 3).toString(), "SYM9 SYM3 SYM4");
        assertEquals(r1.getSymbolAt(7, 3).toString(), "SYM3 SYM4 SYM5");
    }

}