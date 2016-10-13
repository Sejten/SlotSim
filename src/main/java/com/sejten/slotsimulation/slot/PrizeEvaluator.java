package com.sejten.slotsimulation.slot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by piotr.s
 */
public class PrizeEvaluator {
    private final GameConf gameConfig;
    private final StatisticsProcessor statisticsProcessor;

    public PrizeEvaluator(GameConf g, StatisticsProcessor s) {
        gameConfig = g;
        statisticsProcessor = s;
    }

    public List<Prize> getPrizesFromNormalSymbols(List<WinningPayline> winningPaylines) {
        List<Prize> prizes = new ArrayList<>();
        for (WinningPayline wp : winningPaylines) {
            Symbol s = wp.getSymbol();
            prizes.add(s.getPrize(wp.getWinningSymbolCount()).get());
        }
        return prizes;
    }

    public List<Prize> getPrizesFromScatterSymbols(ReelWindow rw) {
        int scatterCount;
        List<Prize> prizes = new ArrayList<>();
        for (Symbol s : gameConfig.scatterSymbols.values()) {
            scatterCount = rw.countSymbols(s);
            if (scatterCount > 0) {
                Optional<Prize> p = s.getPrize(scatterCount);
                if (p.isPresent()) {
                    prizes.add(p.get());
                    statisticsProcessor.processScatter(s, scatterCount, p);
                }
            }
        }
        return prizes;
    }
}
