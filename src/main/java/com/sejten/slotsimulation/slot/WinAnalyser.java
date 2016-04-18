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
            for (int column = 0; column < payline.size(); column++) {
                numOfOccur = 0;
                int nextColumn = column + 1;
                if (nextColumn < payline.size() - 1) {
                    // if current and next symbols are the same
                    if (rw.getSymbolByCoords(column, payline.getRow(column)).equals(rw.getSymbolByCoords(nextColumn, payline.getRow(nextColumn)))) {
                        numOfOccur = 2;
                        wp = new WinningPayline(payline);
                        // iterate through next symbols
                        for (int i = nextColumn; i < payline.size() - 1; i++) {
                            // if current and next symbols are the same
                            if (rw.getSymbolByCoords(i, payline.getRow(i)).equals(rw.getSymbolByCoords(i + 1, payline.getRow(i + 1)))) {
                                numOfOccur++;
                                if (numOfOccur == 3) {
                                    wp.setPayline(payline);
                                    wp.setSymbol(rw.getSymbolByCoords(i, payline.getRow(i)));
                                    wp.addWinningColumnPosition(column, nextColumn, i + 1);
                                } else
                                    wp.addWinningColumnPosition(i + 1);

                            } else
                                break;
                        }
                        break;
                    }
                }

            }
            if (numOfOccur > 2) {
                winningPaylines.add(wp);
            }
        }
        return winningPaylines;
    }
}
