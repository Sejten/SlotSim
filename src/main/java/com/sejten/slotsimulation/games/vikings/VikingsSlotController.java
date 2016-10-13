package com.sejten.slotsimulation.games.vikings;

import com.sejten.slotsimulation.slot.*;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by piotr.s on 2016-07-14.
 */
public class VikingsSlotController extends SlotController {
    public VikingsSlotController(GameConf wgc) {
        super(wgc);
    }


    public List<SpinResult> spin() {
        numberOfSpins++;
        ReelWindow rw;
        List<SpinResult> spins = new ArrayList<>();
        if (player.isPlayingFreespins()) {
            spins = bonusGame();
        } else {
            player.putBet();
            rw = createWindow(gameConf.getReels("BASIC"));
            spins.add(new SpinResult(rw, paylineAnalyser.evaluateReelWindow(rw)));
            evaluatePrizes(spins.get(0));
        }
        return spins;
    }

    private ReelWindow generateWilds(ReelWindow windowBefore) {
        ReelWindow windowAfter = new ReelWindow(windowBefore);
        int highSymbolCounter = 0;
        List<String> highSymbols = Arrays.asList("HIGH1", "HIGH2", "HIGH3", "HIGH4");
        for (int i = 0; i < windowAfter.getWidth(); i++) {
            for (int j = 0; j < windowAfter.getLength(); j++) {
                if (highSymbols.contains(windowAfter.getSymbolByCoords(i, j).getName())) {
                    highSymbolCounter++;
                }
            }
        }
        statisticsProcessor.countItem("High symbols seen", highSymbolCounter);
        for (int i = 0; i < windowAfter.getWidth(); i++) {
            for (int j = 0; j < windowAfter.getLength(); j++) {
                if (highSymbols.contains(windowAfter.getSymbolByCoords(i, j).getName())) {
                    double prob = 0.045 + 0.2495 / (1 + highSymbolCounter);
                    double randomD = RandomGenerator.nextDouble();
                    if (prob > randomD) {
                        windowAfter.setSymbolByCoords(gameConf.getSymbol("WILD"), i, j);
                        statisticsProcessor.countItem("High symbols converted into wilds", 1);
                    }
                }
            }
        }
        return windowAfter;
    }

    private void evaluatePrizes(SpinResult sr) {
        List<Prize> prizes = new ArrayList<>();
        if (sr.winningPaylines.size() > 0) {
            prizes = prizeEvaluator.getPrizesFromNormalSymbols(sr.winningPaylines);
            statisticsProcessor.process(sr.winningPaylines);
        }
        prizes.addAll(prizeEvaluator.getPrizesFromScatterSymbols(sr.reelWindow));
        player.addPrize(prizes);
    }

    private List<SpinResult> bonusGame() {
        List<SpinResult> spins = new ArrayList<>();
        SpinResult sr;
        ReelWindow startingWindow = createWindow(gameConf.getReels("BONUS"));
        // save vanilla window
        spins.add(new SpinResult(new SpinResult(startingWindow)));

        do {
            player.putBet();
            // turn hight into wilds
            startingWindow = generateWilds(startingWindow);
            sr = new SpinResult(startingWindow);
            sr.winningPaylines = paylineAnalyser.evaluateReelWindow(startingWindow);
            evaluatePrizes(sr);
            spins.add(new SpinResult(sr));

            ReelWindow newWindow = createWindow(gameConf.getReels("BONUS"));
            //each symbol that is not wild replace with new symbol
            for (int column = 0; column < startingWindow.getWidth(); column++)
                for (int row = 0; row < startingWindow.getLength(); row++) {
                    if (startingWindow.getSymbolByCoords(column, row).getType() != SymbolType.WILD) {
                        startingWindow.setSymbolByCoords(newWindow.getSymbolByCoords(column, row), column, row);
                    }
                }
        } while (player.isPlayingFreespins());
        return spins;
    }
}
