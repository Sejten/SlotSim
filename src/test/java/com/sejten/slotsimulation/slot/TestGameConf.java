package com.sejten.slotsimulation.slot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by piotr.s on 2016-07-22.
 */
public class TestGameConf extends GameConf {

    public Symbol high1 = new Symbol("HIGH1", (x) -> x == 3 ? Optional.of(new Prize(15, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(40, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(500, PrizeType.COINS)) : Optional.empty());
    public Symbol high2 = new Symbol("HIGH2", (x) -> x == 3 ? Optional.of(new Prize(12, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(20, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(150, PrizeType.COINS)) : Optional.empty());
    public Symbol high3 = new Symbol("HIGH3", (x) -> x == 3 ? Optional.of(new Prize(8, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(15, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(75, PrizeType.COINS)) : Optional.empty());
    public Symbol low1 = new Symbol("LOW1", (x) -> x == 3 ? Optional.of(new Prize(5, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(12, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(50, PrizeType.COINS)) : Optional.empty());
    public Symbol low2 = new Symbol("LOW2", (x) -> x == 3 ? Optional.of(new Prize(3, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(12, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(50, PrizeType.COINS)) : Optional.empty());
    public Symbol low3 = new Symbol("LOW3", (x) -> x == 3 ? Optional.of(new Prize(3, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(10, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(25, PrizeType.COINS)) : Optional.empty());
    public Symbol low4 = new Symbol("LOW4", (x) -> x == 3 ? Optional.of(new Prize(3, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(10, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(25, PrizeType.COINS)) : Optional.empty());

    Symbol wild = new Symbol("WILD", SymbolType.WILD, (x) -> Optional.empty());
    Symbol freespin = new Symbol("FREESPIN", SymbolType.FREESPIN, (x) -> x >= 3 ? Optional.of(new Prize(5, PrizeType.FREESPINS)) : Optional.empty());
    Symbol bonusChest = new Symbol("CHEST", SymbolType.SCATTER, (x) -> x >= 1 ? Optional.of(new Prize(2, PrizeType.FREESPINS)) : Optional.empty());


    public TestGameConf() {
        numberOfRows = 3;
        symbols.put("HIGH1", high1);
        symbols.put("HIGH2", high2);
        symbols.put("HIGH3", high3);
        symbols.put("LOW1", low1);
        symbols.put("LOW2", low2);
        symbols.put("LOW3", low3);
        symbols.put("LOW4", low4);

        scatterSymbols.put(freespin.getName(), freespin);
        scatterSymbols.put(bonusChest.getName(), bonusChest);

        List<Reel> basicReels = new ArrayList<>();
        basicReels.add(new Reel("Reel_1", Arrays.asList(high1, high1, high1, low1, low1, low1, high1, high1, low2, low2, low4, low4, high3, freespin, high2, high2, high1, high1, high1, low1, low1, low1, low2, low2, low2, low4, wild, low2, low2, low1, low1, low1, low2, low2, low4, low4, low4, high3, high3, low3, low3, low3, low3, high2, high2, low3, low3, low3, high3, high3, high2, high2, low3, low3, low3, low1, low1, low3, low3, high3, high3, high3, low3, low3)));
        basicReels.add(new Reel("Reel_2", Arrays.asList(low1, low1, low3, low3, high2, high2, high2, low4, low4, low3, low3, high2, high2, freespin, low1, low1, low1, low3, low3, low3, low4, low4, low1, low1, low1, low3, low3, low4, low4, low4, high1, high1, low2, low2, low2, high1, wild, low2, low2, low2, low2, high3, high3, high3, high1, high1, low2, low2, high3, high3, high3, high3)));
        basicReels.add(new Reel("Reel_3", Arrays.asList(high3, high3, low2, low2, low2, low3, low3, low3, low3, low2, low2, low2, high3, freespin, low2, low2, low2, low3, low3, low3, high3, high3, low3, low3, low3, low2, low2, low2, low3, low3, low3, high3, high3, low4, low4, high1, high1, high2, high2, low4, low4, low4, high2, high2, low4, low4, low1, low1, low1, high2, high2, low4, low4, low4, low1, low1, low1, high2, high2, low4, low4, low1, low1, low1)));
        basicReels.add(new Reel("Reel_4", Arrays.asList(high2, high2, high3, high3, high3, low1, low1, low2, low2, low2, low2, high1, high1, high1, high3, high3, high3, high3, high2, high2, high3, high3, low1, low1, low2, low2, low2, low2, low3, low3, low4, low4, low4, low4, low2, low2, low2, high3, high3, high3, high3, low1, low1, low2, low2, low2, wild, low3, low3, low3, low3, low4, low4)));
        basicReels.add(new Reel("Reel_5", Arrays.asList(high1, high1, low4, low4, low4, high2, high2, high2, bonusChest, high3, high3, low1, low1, low2, low2, high1, high1, low3, low3, low3, low3, low4, low4, low2, low2, high2, high2, high2, high2, high3, high3, low3, low3, low4, low4, low4, low4, high2, high2, low3, low3, low3, low3, high1, high1, low4, low4, low4, low4)));
        reels.put("BASIC", basicReels);

        List<Reel> bonusReels = new ArrayList<>();
        bonusReels.add(new Reel("Bonus_Reel_1", Arrays.asList(high1, high1, high1, high1, low1, low1, low1, low2, low2, low2, low4, low4, low4, high1, high1, high1, high1, low1, low1, low1, low1, low2, low2, low2, low2, low4, low4, low4, high1, high1, high1, high1, low1, low1, low1, high2, high2, high2, low2, low2, low2, low2, low1, low1, low1, high1, high1, high1, high1, low2, low2, low2, low4, low4, low4, low4, high2, high2, high2, low3, low3, low3, low3, high2, high2, high3, high3, high3, high3, low4, low4, low4, low4, high2, high2, high2)));
        bonusReels.add(new Reel("Bonus_Reel_2", Arrays.asList(low1, low1, low1, low3, low3, low3, low3, low3, high2, high2, high2, high2, low4, low4, low4, low4, low4, high2, high2, high2, high2, low1, low1, low1, high1, high1, high1, high1, low3, low3, low3, low4, low4, low4, low4, high1, high1, high1, low1, low1, low1, low1, low1, low3, low3, low3, low3, low4, low4, low4, high2, high2, high2, high2, low4, low4, low4, high1, high1, high1, low4, low4, low4, low4, low2, low2, low2, low2, high3, high3, high3, high3, high1, high1, high1, high1, low4, low4, low4, high3, high3, high3, high3)));
        bonusReels.add(new Reel("Bonus_Reel_3", Arrays.asList(high3, high3, high3, high3, high3, low2, low2, low2, low2, low3, low3, low3, low2, low2, low2, low2, high3, high3, high3, low2, low2, low2, low3, low3, low3, low3, high3, high3, high3, high3, high3, high1, high1, high1, high3, high3, high3, high3, low3, low3, low3, low3, low2, low2, low2, low4, low4, low4, low4, high1, high1, high1, low4, low4, low4, low4, high2, high2, low4, low4, low4, low4, high2, high2, high2, high2, low4, low4, low4, low4, low1, low1, low1, low1, low1, high2, high2, high2, low4, low4, low4, low4)));
        bonusReels.add(new Reel("Bonus_Reel_4", Arrays.asList(high2, high2, high3, high3, high3, low1, low1, low1, low1, low1, low2, low2, low2, low2, low3, low3, low3, low3, high2, high2, low4, low4, low4, low4, high1, high1, high1, low2, low2, low2, low2, low2, low4, low4, low4, high3, high3, high3, high3, high3, low1, low1, low1, low1, low2, low2, low2, low2, high3, high3, high3, high3, low1, low1, low1, low1, high3, high3, high3, high3, low3, low3, low2, low2, low2, low2)));
        bonusReels.add(new Reel("Bonus_Reel_5", Arrays.asList(low3, low3, low3, low3, high2, high2, high2, high2, high2, high3, high3, high3, high3, low3, low3, low3, low3, low4, low4, low4, low4, low4, high1, high1, high1, low3, low3, low3, low3, low3, low1, low1, low1, low2, low2, low2, low2, low4, low4, low4, low4, low1, low1, low1, low3, low3, low3, low3, high2, high2, high2, high3, high3, high3, low3, low3, low3, low3, low4, low4, low4, low4, low3, low3, low3, low4, low4, low4, low4, low3, low3, low3, low3, low1, low1, low1, low2, low2, low4, low4, low4, low4)));
        reels.put("BONUS", bonusReels);

        paylines.add(new Payline(0, 0, 0, 0, 0));
        paylines.add(new Payline(1, 1, 1, 1, 1));
        paylines.add(new Payline(2, 2, 2, 2, 2));
        paylines.add(new Payline(0, 1, 2, 1, 0));
        paylines.add(new Payline(2, 1, 0, 1, 2));
        paylines.add(new Payline(0, 0, 1, 0, 0));
        paylines.add(new Payline(2, 2, 1, 2, 2));
        paylines.add(new Payline(0, 0, 2, 0, 0));
        paylines.add(new Payline(2, 2, 0, 2, 2));
        paylines.add(new Payline(0, 1, 1, 1, 0));
        paylines.add(new Payline(2, 1, 1, 1, 2));
        paylines.add(new Payline(0, 2, 2, 2, 0));
        paylines.add(new Payline(2, 0, 0, 0, 2));
        paylines.add(new Payline(0, 1, 0, 1, 0));
        paylines.add(new Payline(2, 1, 2, 1, 2));
        paylines.add(new Payline(0, 2, 0, 2, 0));
        paylines.add(new Payline(2, 0, 2, 0, 2));
        paylines.add(new Payline(1, 1, 0, 1, 1));
        paylines.add(new Payline(1, 1, 2, 1, 1));
        paylines.add(new Payline(1, 0, 0, 0, 1));
        paylines.add(new Payline(1, 2, 2, 2, 1));
        paylines.add(new Payline(1, 0, 1, 0, 1));
        paylines.add(new Payline(1, 2, 1, 2, 1));
        paylines.add(new Payline(0, 2, 1, 2, 0));
        paylines.add(new Payline(2, 0, 1, 0, 2));
    }
}
