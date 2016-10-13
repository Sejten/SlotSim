package com.sejten.slotsimulation.games.winterberries;

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
        ReelWindow rw = createWindow(gameConf.getReels("BASIC"));
        List<SpinResult> spins = new ArrayList<>();
        //first spin
        spins.add(new SpinResult(rw, paylineAnalyser.evaluateReelWindow(rw)));
        if (spins.get(0).winningPaylines.size() > 0) {
            Symbol f = getFreezingSymbol(spins.get(0).winningPaylines);
            // play bonus game
            spins.addAll(bonusGame(rw, f));
            int multiplier = multiplierEvaluator(spins.get(spins.size() - 1).reelWindow, f);
            List<Prize> prize = new PrizeEvaluator(gameConf, statisticsProcessor).getPrizesFromNormalSymbols(spins.get(spins.size() - 1).winningPaylines);
            prize.stream().forEach(p -> p.amount *= multiplier);
            player.addPrize(prize);
            spins.get(spins.size() - 1).prize = prize;
            statisticsProcessor.process(spins.get(spins.size() - 1).winningPaylines);
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
        int replaced;
        SpinResult sr;
        List<SpinResult> spins = new ArrayList<>();
        ReelWindow b_w = new ReelWindow(basicWindow);
        do {
            ReelWindow new_spin = createWindow(gameConf.getReels("BONUS"));
            // create new window by replacing not frozen symbols with new spin
            //iterate through whole window and set new symbols to not frozen symbols
            replaced = 0;
            for (int column = 0; column < b_w.getWidth(); column++)
                for (int row = 0; row < b_w.getLength(); row++) {
                    if (!b_w.getSymbolByCoords(column, row).equals(freezingSymbol)) {
                        if (new_spin.getSymbolByCoords(column, row).equals(freezingSymbol)) {
                            b_w.setSymbolByCoords(freezingSymbol, column, row);
                            replaced++;
                        }
                    }
                }
            if (replaced > 0) {
                // evaluate wins on new window
                sr = new SpinResult(b_w);
                sr.winningPaylines = paylineAnalyser.evaluateReelWindow(sr.reelWindow);
                // copy reel window and save it
                spins.add(new SpinResult(sr));
            }
        } while (replaced != 0);
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
        int columnsFull = 0;
        boolean isColumnFull = true;
        for (int column = 0; column < window.getWidth(); column++) {
            for (int row = 0; row < window.getLength(); row++) {
                if (!window.getSymbolByCoords(column, row).equals(freezingSymbol))
                    isColumnFull = false;
            }
            if (isColumnFull)
                columnsFull++;
            else
                break;
        }
        if (columnsFull == 0)
            return 1;
        else
            return columnsFull;
    }
}
