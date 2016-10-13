package com.sejten.slotsimulation.slot;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by piotr.s on 2016-08-26.
 */
public class PlayerTest {
    @Test
    public void testPutBet() throws Exception {

    }

    @Test
    public void testGetTotalWonAmount() throws Exception {

    }

    @Test
    public void testGetRtp() throws Exception {
        //given
        // setting up result reel window
        Player p = new Player(0);
        p.putBet();
        p.putBet();
        p.putBet();
        p.putBet();

        //when
        List<Prize> prizes = new ArrayList<>();
        prizes.add(new Prize(50, PrizeType.COINS));
        prizes.add(new Prize(250, PrizeType.COINS));
        prizes.add(new Prize(171, PrizeType.COINS));
        prizes.add(new Prize(3, PrizeType.COINS));
        prizes.add(new Prize(33, PrizeType.COINS));

        prizes.add(new Prize(1, PrizeType.FREESPINS));
        prizes.add(new Prize(2, PrizeType.FREESPINS));
        prizes.add(new Prize(3, PrizeType.FREESPINS));
        prizes.add(new Prize(4, PrizeType.FREESPINS));
        prizes.add(new Prize(5, PrizeType.FREESPINS));

        p.addPrize(prizes);
        //then
        assertEquals(p.getRtp(), 507.0 / (p.getBet() * 4));
    }

    TestGameConf gc = new TestGameConf();
    Symbol sym3 = gc.high1;
    Symbol sym4 = gc.high2;
    Symbol sym5 = gc.high3;
    Symbol sym9 = gc.low1;
    Symbol sym8 = gc.low2;

    @Test
    public void testAddPrize() throws Exception {
        //given
        // setting up result reel window
        Player p = new Player(0);

        //when
        List<Prize> prizes = new ArrayList<>();
        prizes.add(new Prize(50, PrizeType.COINS));
        prizes.add(new Prize(250, PrizeType.COINS));
        prizes.add(new Prize(171, PrizeType.COINS));
        prizes.add(new Prize(3, PrizeType.COINS));
        prizes.add(new Prize(33, PrizeType.COINS));

        prizes.add(new Prize(1, PrizeType.FREESPINS));
        prizes.add(new Prize(2, PrizeType.FREESPINS));
        prizes.add(new Prize(3, PrizeType.FREESPINS));
        prizes.add(new Prize(4, PrizeType.FREESPINS));
        prizes.add(new Prize(5, PrizeType.FREESPINS));

        p.addPrize(prizes);
        //then
        assertEquals(p.getCoinBalance(), 507.0);
        assertEquals(p.getFreespinAmount(), 15);
    }

}