package com.sejten.slotsimulation.slot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr.s
 */
public class WinAnalyser {
    private final GameConf gameConfig;

    public WinAnalyser(GameConf g) {
        gameConfig = g;
    }

    public List<WinningPayline> evaluateReelWindow(ReelWindow rw) {
        int numOfOccur = 0;
        List<WinningPayline> winningPaylines = new ArrayList<>();
        WinningPayline wp = null;

        for (Payline payline : gameConfig.getPaylines()) {
            int column = 0;
            if (rw.getSymbolByCoords(column, payline.getRow(column)).equals(rw.getSymbolByCoords(column + 1, payline.getRow(column + 1))) && rw.getSymbolByCoords(column, payline.getRow(column)).equals(rw.getSymbolByCoords(column + 2, payline.getRow(column + 2)))) {
                numOfOccur = 3;
                wp = new WinningPayline(payline);
                wp.setSymbol(rw.getSymbolByCoords(0, payline.getRow(0)));
                wp.addWinningColumnPosition(0, 1, 2);
                for (int i = column + 3; i < payline.size(); i++) {
                    if (rw.getSymbolByCoords(0, payline.getRow(0)).equals(rw.getSymbolByCoords(i, payline.getRow(i)))) {
                        numOfOccur++;
                        wp.addWinningColumnPosition(i);
                    } else
                        break;
                }
                if (numOfOccur > 2) {
                    winningPaylines.add(wp);
                }
            }
        }
        return winningPaylines;
    }
}
