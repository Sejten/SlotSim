package com.sejten.slotsimulation.games.vikings;

import com.sejten.slotsimulation.slot.*;

import java.util.*;

/**
 * Created by piotr.s on 2016-07-13.
 */
public class VikingsGameConf extends GameConf {

    public VikingsGameConf() {
        numberOfRows = 3;
        Symbol h1 = new Symbol("HIGH1", (x) -> x == 3 ? Optional.of(new Prize(30, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(100, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(250, PrizeType.COINS)) : Optional.empty());
        Symbol h2 = new Symbol("HIGH2", (x) -> x == 3 ? Optional.of(new Prize(30, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(100, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(225, PrizeType.COINS)) : Optional.empty());
        Symbol h3 = new Symbol("HIGH3", (x) -> x == 3 ? Optional.of(new Prize(25, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(75, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(200, PrizeType.COINS)) : Optional.empty());
        Symbol h4 = new Symbol("HIGH4", (x) -> x == 3 ? Optional.of(new Prize(20, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(75, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(175, PrizeType.COINS)) : Optional.empty());
        Symbol l1 = new Symbol("LOW1", (x) -> x == 3 ? Optional.of(new Prize(6, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(30, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(80, PrizeType.COINS)) : Optional.empty());
        Symbol l2 = new Symbol("LOW2", (x) -> x == 3 ? Optional.of(new Prize(6, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(30, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(70, PrizeType.COINS)) : Optional.empty());
        Symbol l3 = new Symbol("LOW3", (x) -> x == 3 ? Optional.of(new Prize(5, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(25, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(60, PrizeType.COINS)) : Optional.empty());
        Symbol l4 = new Symbol("LOW4", (x) -> x == 3 ? Optional.of(new Prize(5, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(25, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(50, PrizeType.COINS)) : Optional.empty());
        // wild 1
        Symbol wild = new Symbol("WILD", SymbolType.WILD, (x) -> Optional.empty());
        // freespin 0
        Symbol freespin = new Symbol("FREESPIN", SymbolType.FREESPIN, (x) -> x == 3 ? Optional.of(new Prize(8, PrizeType.FREESPINS)) : x == 4 ? Optional.of(new Prize(16, PrizeType.FREESPINS)) : x == 5 ? Optional.of(new Prize(24, PrizeType.FREESPINS)) : Optional.empty());
        // 2
        Symbol bonusChest = new Symbol("CHEST", SymbolType.SCATTER, (x) -> {
            if (x < 1)
                return Optional.empty();
            Map<Optional<Prize>, Double> weightedPrizes = new HashMap<>();
            weightedPrizes.put(Optional.of(new Prize(25 * RandomGenerator.nextInt(2, 8), PrizeType.COINS)), 84.0);
            weightedPrizes.put(Optional.of(new Prize(100 * RandomGenerator.nextInt(5, 10), PrizeType.COINS)), 4.0);
            weightedPrizes.put(Optional.of(new Prize(8, PrizeType.FREESPINS)), 9.0);
            weightedPrizes.put(Optional.of(new Prize(RandomGenerator.nextInt(9, 12), PrizeType.COINS)), 3.0);
            return RandomGenerator.getWeightedRandom(weightedPrizes.entrySet().stream());
        });

        symbols.put(h1.getName(), h1);
        symbols.put(h2.getName(), h2);
        symbols.put(h3.getName(), h3);
        symbols.put(h4.getName(), h4);
        symbols.put(l1.getName(), l1);
        symbols.put(l2.getName(), l2);
        symbols.put(l3.getName(), l3);
        symbols.put(l4.getName(), l4);
        symbols.put(wild.getName(), wild);

        scatterSymbols.put(freespin.getName(), freespin);
        scatterSymbols.put(bonusChest.getName(), bonusChest);

        List<Reel> basicReels = new ArrayList<>();
        basicReels.add(new Reel("Reel_1", Arrays.asList(
                h1, h1, h3, h3, h3, l2, l2, h2, h2, h2, l4, l4, l2, l2, l2, freespin, l3, l3, l3, wild, l4, l4, l4, h2, h2, h4, h4, h4, h4, freespin, l3, l3, l1, l1, l1, l1, l4, l4, wild, h1, h1, l1, l1, l1, l3, l3, l3, h4, h4, l4, l4, l4, l1, l1, l1, l4, l4, h1, h2, h2, h4, h4, h4, h4, l3, l3, l1, l1, l2, l2, l2, h3, h3, l1, l1, l3, l3, wild, l4, l4, l1, l1
        )));
        basicReels.add(new Reel("Reel_2", Arrays.asList(
                h1, h1, h4, h4, h3, h3, h2, h2, l3, l3, h4, h4, l1, l1, l1, h3, h3, h3, l4, l4, wild, l2, l2, l2, l4, l4, l3, l3, l3, wild, l4, l4, l4, l1, l1, l1, l1, freespin, h1, h1, h3, h3, h3, l4, l4, l4, l2, l2, l2, h3, h3, l2, l2, l2, h3, h3, l3, l3, l2, l2, l1, l1, wild, l4, l4, l1, l1
        )));
        basicReels.add(new Reel("Reel_3", Arrays.asList(
                l2, l2, h3, h3, h3, l4, l4, l4, l4, h3, h3, l2, l2, freespin, l3, l3, l3, l3, wild, l2, l2, l2, l4, l4, l4, l2, l2, l2, l1, l1, l1, l2, l2, l2, h2, h2, wild, h4, h4, h4, h4, l2, l2, l3, l3, l3, l3, l2, l2, h2, h1, freespin, l3, l3, l2, l2, l1, l1
        )));
        basicReels.add(new Reel("Reel_4", Arrays.asList(
                h1, h1, h4, h4, h4, h4, l4, l4, l4, l2, l2, h2, h2, l3, l3, l3, l4, l4, l4, l1, l1, l1, h1, h1, h2, h2, h2, h3, h3, h3, wild, l2, l2, l2, l2, freespin, h2, h2, h2, h3, h3, h3, h4, h4, l3, l3, l3, wild, l4, l4, l4, l1, l1, l2, l2, l3, l3, wild, l4, l4, l1, l1
        )));
        basicReels.add(new Reel("Reel_5", Arrays.asList(
                h1, h1, h2, h2, h2, h3, h3, h4, h4, h4, h4, h3, h3, h3, h4, h4, h4, h4, h1, h1, l2, l2, l2, l2, freespin, l4, l4, l4, l4, bonusChest, l1, l3, l3, l1, l1, l1, h1, h1, h2, h2, h2, l1, l1, l1, wild, l1, l1, l1, h4, h4, l4, l4, h1, h1, h3, h3, h3, h4, h4, h4, h4, l2, l2, l2, h1, h1, h2, h2, h2, h4, h4, h3, h3, h3, h4, h4, h4, h4, h1, h1, l2, l4, l4, l2, l2, l2, h3, h3, l2, l2, l2, l2, wild, l3, l3, l3, l1, l1, l1, wild, l4, l4, l4, l1, l1, l1, h1, h1, h1, h2, h2, l4, l4, l4, l4, h3, h3, l4, l4, l4, wild, wild, h2, h2, h2, l1, l1, l1, l3, l3, h3, h3, h3, h4, h4, freespin, l3, l3, wild, wild, h3, h3, h3, h1, h1, h1, h4, h4, h4, h4, h1, h1, l4, l4, freespin, h4, h4, h4, h4, l2, l2, l2, h1, h1, l3, l3, l3, l1, l1, l1, wild, wild, l4, l4, l4, l1, l1, l1, h2, h2, l1, l1, h2, h2
        )));
        reels.put("BASIC", basicReels);

        List<Reel> bonusReels = new ArrayList<>();
        bonusReels.add(new Reel("Bonus_Reel_1", Arrays.asList(
                l1, l1, l1, h4, h4, l3, l3, l2, l2, l2, h2, h2, l4, l4, l3, l3, l3, h1, h1, l4, l4, l4, l3, l3, l2, l2, l2, l2, l1, l1, h4, h4, l1, l1, l1, l1, l2, l2, h3, h3, l4, l4, l3, l3, h2, h2, l3, l3, l4, l4, h1, h1
        )));
        bonusReels.add(new Reel("Bonus_Reel_2", Arrays.asList(
                l1, l1, l1, h1, h1, h4, h4, l1, l1, l2, l2, l2, h2, h2, l4, l4, l3, l3, l3, l3, l3, h1, h1, l4, l4, l2, l2, h3, h3, l4, l4, l4, l3, l3, l3, l3, h2, h2, l1, l1, l4, l4, l2, l2, l4, l4, l1, l1, h4, h4, l2, l2, l3, l3, l1, l1, l2, l2, l4, l4
        )));
        bonusReels.add(new Reel("Bonus_Reel_3", Arrays.asList(
                l1, l1, l1, h4, h4, l4, l4, l2, l2, l2, h2, h2, l1, l1, h4, h4, l2, l2, l3, l3, l3, h1, h1, l1, l1, l4, l4, l2, l2, l4, l4, l3, l3, l3, l3, h3, h3, l4, l4, h1, h1, l1, l1, l3, l3, h2, h2, l4, l4, l4, l2, l2, l1, l1, l2, l2
        )));
        bonusReels.add(new Reel("Bonus_Reel_4", Arrays.asList(
                l4, l4, l4, h1, h1, l2, l2, h3, h3, h3, l3, l3, l3, l4, l4, l4, h3, h3, h3, l3, l3, l3, l1, l1, l1, l4, l4, l4, h2, h2, h2, l4, l4, l4, l3, l3, l3, l4, l4, h4, h4, h4, l2, l2, l2, h4, h4, l3, l3, l3, l4, l4, l4, l4, l2, l2, l2, h3, h3, h3, l4, l4, l3, l3, l4, l1, l1, l1, l2, l2, h4, h4, h4, l3, l3, l3, h4, h4, h4, l1, l1, l1, l2, l2, l3, l3, l3, l2, l2, l1, l1, h2, h2, h2, l4, l4, l3, l3, h2, h2, l2, l2, l2, l1, l1, l1
        )));
        bonusReels.add(new Reel("Bonus_Reel_5", Arrays.asList(
                l2, l2, l2, h1, h1, l4, l4, l4, l1, l1, l2, l2, l2, h4, h4, h4, l2, l2, l2, l1, l1, l2, h2, h2, h2, l3, l3, l3, l3, h1, l2, l2, h1, bonusChest, l3, l3, l3, l2, l2, h3, h3, l2, l2, l3, l3, h1, h1, h1, l2, l1, l1, h4, h4, l3, l3, l2, l2, l3, l3, h3, h3, h3, l4, l4, l4, l3, l3, l3, bonusChest, l2, l2, l2, l2, bonusChest, l1, l1, l1, l4, l4, l4, h1, h1, l4, l4, l3, l3, h3, h3, h3, l1, l1, l1, l3, l3, h3, l4, l4, l2, l2, h3, h3, l1, l1, h3, l3, l3, l3, l3, h3, l3, l3, l2, l2, h3, h3, h4, h4, h4, l3, l1, l1, l2, l2, l3, l3, l3, h2, h2, h2, l2, l2, l4, l4, l2, l2, l4, l4, l2, l2, l2, h2, h2, h2, h2, l4, l1, l1, l4, bonusChest, l2, l2, l2, h4, h4, l4, l4, l1, l1, h4
        )));
        reels.put("BONUS", bonusReels);

        paylines.add(new Payline(0, 0, 0, 0, 0));
        paylines.add(new Payline(1, 1, 1, 1, 1));
        paylines.add(new Payline(2, 2, 2, 2, 2));
        paylines.add(new Payline(3, 3, 3, 3, 3));
        paylines.add(new Payline(0, 1, 2, 1, 0));
        paylines.add(new Payline(1, 2, 3, 2, 1));
        paylines.add(new Payline(2, 1, 0, 1, 2));
        paylines.add(new Payline(3, 2, 1, 2, 3));
        paylines.add(new Payline(0, 1, 1, 1, 0));
        paylines.add(new Payline(1, 2, 2, 2, 1));
        paylines.add(new Payline(2, 3, 3, 3, 2));
        paylines.add(new Payline(3, 2, 2, 2, 3));
        paylines.add(new Payline(2, 1, 1, 1, 2));
        paylines.add(new Payline(1, 0, 0, 0, 1));
        paylines.add(new Payline(0, 1, 0, 1, 0));
        paylines.add(new Payline(1, 2, 1, 2, 1));
        paylines.add(new Payline(2, 3, 2, 3, 2));
        paylines.add(new Payline(3, 2, 3, 2, 3));
        paylines.add(new Payline(2, 1, 2, 1, 2));
        paylines.add(new Payline(1, 0, 1, 0, 1));
        paylines.add(new Payline(0, 0, 1, 0, 0));
        paylines.add(new Payline(1, 1, 2, 1, 1));
        paylines.add(new Payline(1, 1, 0, 1, 1));
        paylines.add(new Payline(2, 2, 1, 2, 2));
        paylines.add(new Payline(3, 3, 2, 3, 3));
    }
}
