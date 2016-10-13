package com.sejten.slotsimulation.slot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr.s
 */
public class PaylineAnalyser {
    private final GameConf gameConfig;
    boolean startFromLeft = true;
    boolean startFromRight = false;

    public PaylineAnalyser(GameConf g) {
        gameConfig = g;
    }

    /**
     * Payline must be getPrizesFromNormalSymbols from left or right or both
     * winning symbols must start on first reel
     *
     * @param rw
     * @return
     */
    public List<WinningPayline> evaluateReelWindow(ReelWindow rw) {
        int numOfOccur;
        List<WinningPayline> winningPaylines = new ArrayList<>();
        WinningPayline wp;

        for (Payline payline : gameConfig.getPaylines()) {
            int column = 0;
            Symbol s1 = rw.getSymbolByCoords(0, payline.getRow(0));
            Symbol s2 = rw.getSymbolByCoords(1, payline.getRow(1));
            Symbol s3 = rw.getSymbolByCoords(2, payline.getRow(2));
            if (!(isAllowed(s1) && isAllowed(s2) && isAllowed(s3)))
                continue;
            // match column 0 1 2
            if (s1.equals(s2) && s1.equals(s3)) {
                numOfOccur = 3;
                wp = new WinningPayline(payline);
                wp.addWinningColumnPosition(0, 1, 2);
                // find out if there is more symbols
                for (int i = 3; i < payline.size(); i++) {
                    if (s1.equals(rw.getSymbolByCoords(i, payline.getRow(i)))) {
                        numOfOccur++;
                        wp.addWinningColumnPosition(i);
                    } else
                        break;
                }
                // set wining symbol
                findWiningSymbol(rw, wp);
                winningPaylines.add(wp);
            }
        }
        return winningPaylines;
    }

    public boolean isAllowed(Symbol s) {
        return s.getType() == SymbolType.NORMAL || s.getType() == SymbolType.WILD;
    }

    public void findWiningSymbol(ReelWindow rw, WinningPayline wp) {
        Symbol winningSymbol = null;
        int wilds = 0;
        for (Integer column : wp.getWinningColumnPositions()) {
            Symbol s = rw.getSymbolByCoords(column, wp.getWinningRowPosition(column));
            // if its normal symbols
            if (s.getType() == SymbolType.NORMAL) {
                // if its first
                if (winningSymbol == null)
                    winningSymbol = s;
                    // if not find bigger one from these two
                else if (!winningSymbol.isBiggerThan(s)) {
                    winningSymbol = s;
                }
            //else count wilds
            } else if (s.getType() == SymbolType.WILD) {
                wilds++;
            }
        }
        // if there are only wilds
        if (wp.getWinningColumnPositions().size() == wilds)
            winningSymbol = gameConfig.getHighestSymbol();
        if (winningSymbol == null)
            throw new IllegalArgumentException("Wining symbol cannot be null");
        wp.setSymbol(winningSymbol);
    }
}
