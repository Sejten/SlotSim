package com.sejten.slotsimulation.slot;

import java.util.List;

/**
 * Created by piotr.s
 */
public class PrizeEvaluator {
    private final GameConf gameConfig;

    public PrizeEvaluator(GameConf g) {
        gameConfig = g;
    }

    public double evaluate(List<WinningPayline> winningPaylines) {
        double prize = 0;
        for (WinningPayline wp : winningPaylines) {
            Symbol s = wp.getSymbol();
            prize += s.getPrize(wp.getWinningSymbolCount());
        }
        return prize;
    }
}
