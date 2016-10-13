package com.sejten.slotsimulation.games.winterberries;

import com.sejten.slotsimulation.slot.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by piotr.s on 2016-04-20.
 */
public class WinterberriesGameConf extends GameConf {

    public WinterberriesGameConf() {
        numberOfRows = 3;
        symbols.put("HIGH1", new Symbol("HIGH1", (x) -> x == 3 ? Optional.of(new Prize(15, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(40, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(500, PrizeType.COINS)) : Optional.empty()));
        symbols.put("HIGH2", new Symbol("HIGH2", (x) -> x == 3 ? Optional.of(new Prize(12, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(20, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(150, PrizeType.COINS)) : Optional.empty()));
        symbols.put("HIGH3", new Symbol("HIGH3", (x) -> x == 3 ? Optional.of(new Prize(8, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(15, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(75, PrizeType.COINS)) : Optional.empty()));
        symbols.put("LOW1", new Symbol("LOW1", (x) -> x == 3 ? Optional.of(new Prize(5, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(12, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(50, PrizeType.COINS)) : Optional.empty()));
        symbols.put("LOW2", new Symbol("LOW2", (x) -> x == 3 ? Optional.of(new Prize(3, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(12, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(50, PrizeType.COINS)) : Optional.empty()));
        symbols.put("LOW3", new Symbol("LOW3", (x) -> x == 3 ? Optional.of(new Prize(3, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(10, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(25, PrizeType.COINS)) : Optional.empty()));
        symbols.put("LOW4", new Symbol("LOW4", (x) -> x == 3 ? Optional.of(new Prize(3, PrizeType.COINS)) : x == 4 ? Optional.of(new Prize(10, PrizeType.COINS)) : x == 5 ? Optional.of(new Prize(25, PrizeType.COINS)) : Optional.empty()));

        Symbol sym3 = symbols.get("HIGH1");
        Symbol sym4 = symbols.get("HIGH2");
        Symbol sym5 = symbols.get("HIGH3");
        Symbol sym6 = symbols.get("LOW1");
        Symbol sym7 = symbols.get("LOW2");
        Symbol sym8 = symbols.get("LOW3");
        Symbol sym9 = symbols.get("LOW4");

        final List<Reel> basicReels = new ArrayList<>();
        basicReels.add(new Reel("Reel_1", Arrays.asList(sym3, sym3, sym3, sym6, sym6, sym6, sym3, sym3, sym7, sym7, sym9, sym9, sym5, sym5, sym4, sym4, sym3, sym3, sym3, sym6, sym6, sym6, sym7, sym7, sym7, sym9, sym9, sym7, sym7, sym6, sym6, sym6, sym7, sym7, sym9, sym9, sym9, sym5, sym5, sym8, sym8, sym8, sym8, sym4, sym4, sym8, sym8, sym8, sym5, sym5, sym4, sym4, sym8, sym8, sym8, sym6, sym6, sym8, sym8, sym5, sym5, sym5, sym8, sym8)));
        basicReels.add(new Reel("Reel_2", Arrays.asList(sym6, sym6, sym8, sym8, sym4, sym4, sym4, sym9, sym9, sym8, sym8, sym4, sym4, sym6, sym6, sym6, sym6, sym8, sym8, sym8, sym9, sym9, sym6, sym6, sym6, sym8, sym8, sym9, sym9, sym9, sym3, sym3, sym7, sym7, sym7, sym3, sym3, sym7, sym7, sym7, sym7, sym5, sym5, sym5, sym3, sym3, sym7, sym7, sym5, sym5, sym5, sym5)));
        basicReels.add(new Reel("Reel_3", Arrays.asList(sym5, sym5, sym7, sym7, sym7, sym8, sym8, sym8, sym8, sym7, sym7, sym7, sym5, sym5, sym7, sym7, sym7, sym8, sym8, sym8, sym5, sym5, sym8, sym8, sym8, sym7, sym7, sym7, sym8, sym8, sym8, sym5, sym5, sym9, sym9, sym3, sym3, sym4, sym4, sym9, sym9, sym9, sym4, sym4, sym9, sym9, sym6, sym6, sym6, sym4, sym4, sym9, sym9, sym9, sym6, sym6, sym6, sym4, sym4, sym9, sym9, sym6, sym6, sym6)));
        basicReels.add(new Reel("Reel_4", Arrays.asList(sym4, sym4, sym5, sym5, sym5, sym6, sym6, sym7, sym7, sym7, sym7, sym3, sym3, sym3, sym5, sym5, sym5, sym5, sym4, sym4, sym5, sym5, sym6, sym6, sym7, sym7, sym7, sym7, sym8, sym8, sym9, sym9, sym9, sym9, sym7, sym7, sym7, sym5, sym5, sym5, sym5, sym6, sym6, sym7, sym7, sym7, sym7, sym8, sym8, sym8, sym8, sym9, sym9)));
        basicReels.add(new Reel("Reel_5", Arrays.asList(sym3, sym3, sym9, sym9, sym9, sym4, sym4, sym4, sym4, sym5, sym5, sym6, sym6, sym7, sym7, sym3, sym3, sym8, sym8, sym8, sym8, sym9, sym9, sym7, sym7, sym4, sym4, sym4, sym4, sym5, sym5, sym8, sym8, sym9, sym9, sym9, sym9, sym4, sym4, sym8, sym8, sym8, sym8, sym3, sym3, sym9, sym9, sym9, sym9)));
        reels.put("BASIC", basicReels);

        final List<Reel> bonusReels = new ArrayList<>();
        bonusReels.add(new Reel("Bonus_Reel_1", Arrays.asList(sym3, sym3, sym3, sym3, sym6, sym6, sym6, sym7, sym7, sym7, sym9, sym9, sym9, sym3, sym3, sym3, sym3, sym6, sym6, sym6, sym6, sym7, sym7, sym7, sym7, sym9, sym9, sym9, sym3, sym3, sym3, sym3, sym6, sym6, sym6, sym4, sym4, sym4, sym7, sym7, sym7, sym7, sym6, sym6, sym6, sym3, sym3, sym3, sym3, sym7, sym7, sym7, sym9, sym9, sym9, sym9, sym4, sym4, sym4, sym8, sym8, sym8, sym8, sym4, sym4, sym5, sym5, sym5, sym5, sym9, sym9, sym9, sym9, sym4, sym4, sym4)));
        bonusReels.add(new Reel("Bonus_Reel_2", Arrays.asList(sym6, sym6, sym6, sym8, sym8, sym8, sym8, sym8, sym4, sym4, sym4, sym4, sym9, sym9, sym9, sym9, sym9, sym4, sym4, sym4, sym4, sym6, sym6, sym6, sym3, sym3, sym3, sym3, sym8, sym8, sym8, sym9, sym9, sym9, sym9, sym3, sym3, sym3, sym6, sym6, sym6, sym6, sym6, sym8, sym8, sym8, sym8, sym9, sym9, sym9, sym4, sym4, sym4, sym4, sym9, sym9, sym9, sym3, sym3, sym3, sym9, sym9, sym9, sym9, sym7, sym7, sym7, sym7, sym5, sym5, sym5, sym5, sym3, sym3, sym3, sym3, sym9, sym9, sym9, sym5, sym5, sym5, sym5)));
        bonusReels.add(new Reel("Bonus_Reel_3", Arrays.asList(sym5, sym5, sym5, sym5, sym5, sym7, sym7, sym7, sym7, sym8, sym8, sym8, sym7, sym7, sym7, sym7, sym5, sym5, sym5, sym7, sym7, sym7, sym8, sym8, sym8, sym8, sym5, sym5, sym5, sym5, sym5, sym3, sym3, sym3, sym5, sym5, sym5, sym5, sym8, sym8, sym8, sym8, sym7, sym7, sym7, sym9, sym9, sym9, sym9, sym3, sym3, sym3, sym9, sym9, sym9, sym9, sym4, sym4, sym9, sym9, sym9, sym9, sym4, sym4, sym4, sym4, sym9, sym9, sym9, sym9, sym6, sym6, sym6, sym6, sym6, sym4, sym4, sym4, sym9, sym9, sym9, sym9)));
        bonusReels.add(new Reel("Bonus_Reel_4", Arrays.asList(sym4, sym4, sym5, sym5, sym5, sym6, sym6, sym6, sym6, sym6, sym7, sym7, sym7, sym7, sym8, sym8, sym8, sym8, sym4, sym4, sym9, sym9, sym9, sym9, sym3, sym3, sym3, sym7, sym7, sym7, sym7, sym7, sym9, sym9, sym9, sym5, sym5, sym5, sym5, sym5, sym6, sym6, sym6, sym6, sym7, sym7, sym7, sym7, sym5, sym5, sym5, sym5, sym6, sym6, sym6, sym6, sym5, sym5, sym5, sym5, sym8, sym8, sym7, sym7, sym7, sym7)));
        bonusReels.add(new Reel("Bonus_Reel_5", Arrays.asList(sym8, sym8, sym8, sym8, sym4, sym4, sym4, sym4, sym4, sym5, sym5, sym5, sym5, sym8, sym8, sym8, sym8, sym9, sym9, sym9, sym9, sym9, sym3, sym3, sym3, sym8, sym8, sym8, sym8, sym8, sym6, sym6, sym6, sym7, sym7, sym7, sym7, sym9, sym9, sym9, sym9, sym6, sym6, sym6, sym8, sym8, sym8, sym8, sym4, sym4, sym4, sym5, sym5, sym5, sym8, sym8, sym8, sym8, sym9, sym9, sym9, sym9, sym8, sym8, sym8, sym9, sym9, sym9, sym9, sym8, sym8, sym8, sym8, sym6, sym6, sym6, sym7, sym7, sym9, sym9, sym9, sym9)));
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
