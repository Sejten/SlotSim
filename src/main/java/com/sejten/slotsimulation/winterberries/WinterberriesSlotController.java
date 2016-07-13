package com.sejten.slotsimulation.winterberries;

import com.sejten.slotsimulation.slot.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr.s on 2016-04-21.
 */
public class WinterberriesSlotController extends SlotController {

    public WinterberriesSlotController(GameConf wgc) {
        super(wgc);
    }

    /**
     * General spin method
     *
     * @return
     */
    public List<SpinResult> spin() {
        numberOfSpins++;
        player.putBet();
        ReelWindow rw = createWindow(gameConf.getReels());
        List<SpinResult> spins = new ArrayList<>();
        //first spin
        SpinResult sr = new SpinResult(rw, winAnalyser.evaluateReelWindow(rw));
        spins.add(sr);
        if (sr.winningPaylines.size() > 0) {
            Symbol f = getFreezingSymbol(sr.winningPaylines);
            // play bonus game
            spins.addAll(bonusGame(rw, f));
            int multiplier = multiplierEvaluator(rw, f);

            sr = new SpinResult(rw, winAnalyser.evaluateReelWindow(rw));

            double prize = new PrizeEvaluator(gameConf).evaluate(sr.winningPaylines);
            prize *= multiplier;
            player.addPrize(prize);
            sr.prize = prize;
            statisticsProcessor.process(sr.winningPaylines);
            spins.add(sr);
        }
        return spins;
    }

    /**
     * Determine which symbol is freezing symbol
     *
     * @param winningPaylines
     * @return
     */
    private Symbol getFreezingSymbol(List<WinningPayline> winningPaylines) {
        Symbol freezingSymbol = winningPaylines.get(0).getSymbol();
        for (WinningPayline wp : winningPaylines)
            assert freezingSymbol == wp.getSymbol();
        return freezingSymbol;
    }

    /**
     * Bonus game that is played when symbols are frozen
     *
     * @param basicWindow
     * @param freezingSymbol
     * @return
     */
    private List<SpinResult> bonusGame(ReelWindow basicWindow, Symbol freezingSymbol) {
        int symbolCount = 0;
        List<SpinResult> spins = new ArrayList<>();
        SpinResult sr;
        do {
            symbolCount = basicWindow.countSymbols(freezingSymbol);

            sr = new SpinResult(createWindow(((WinterberriesGameConf) gameConf).getBonusReels()));
            sr.winningPaylines = winAnalyser.evaluateReelWindow(sr.reelWindow);

            for (int column = 0; column < basicWindow.getReelsWindowWidth(); column++)
                for (int row = 0; row < basicWindow.getReelsLength(); row++) {
                    if (!basicWindow.getSymbolByCoords(column, row).equals(freezingSymbol)) {
                        if (sr.reelWindow.getSymbolByCoords(column, row).equals(freezingSymbol)) {
                            basicWindow.setSymbolByCoords(freezingSymbol, column, row);
                        }
                    }
                }

            // copy reel window and save it
            spins.add(new SpinResult(sr));
        } while (symbolCount != basicWindow.countSymbols(freezingSymbol));
        return spins;
    }

    /**
     * Evaluate multiplier for full columns
     *
     * @param window
     * @param freezingSymbol
     * @return
     */
    private int multiplierEvaluator(ReelWindow window, Symbol freezingSymbol) {
        int multiplier = 1;
        boolean isColumnFull = true;
        for (int column = 0; column < window.getReelsWindowWidth(); column++) {
            for (int row = 0; row < window.getReelsLength(); row++) {
                if (!window.getSymbolByCoords(column, row).equals(freezingSymbol))
                    isColumnFull = false;
            }
            if (isColumnFull)
                multiplier++;
        }
        if (multiplier > 1)
            multiplier--;
        return multiplier;
    }
}
