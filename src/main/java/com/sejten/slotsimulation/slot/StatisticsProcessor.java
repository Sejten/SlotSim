package com.sejten.slotsimulation.slot;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by piotr.s
 */
public class StatisticsProcessor {
    private final int numberOfSpins;
    private int hits = 0;
    private final Map<Symbol, Map<Integer, Integer>> symbolsHitsCounter = new HashMap<>();

    public StatisticsProcessor(int numberOfSpins) {
        this.numberOfSpins = numberOfSpins;

    }

    public void process(List<WinningPayline> winningPaylines) {
        if (winningPaylines.size() == 0)
            return;
        hits++;
        for (WinningPayline wp : winningPaylines) {
            if (symbolsHitsCounter.containsKey(wp.getSymbol())) {
                Map<Integer, Integer> currentSymbolHitCount = symbolsHitsCounter.get(wp.getSymbol());
                if (currentSymbolHitCount.containsKey(wp.getWinningSymbolCount())) {
                    Integer numberOfHits = currentSymbolHitCount.get(wp.getWinningSymbolCount());
                    currentSymbolHitCount.put(wp.getWinningSymbolCount(), numberOfHits + 1);
                } else {
                    currentSymbolHitCount.put(wp.getWinningSymbolCount(), 0);
                }
            } else {
                symbolsHitsCounter.put(wp.getSymbol(), new HashMap<>());
            }
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        String s = "Number of spins: " + NumberFormat.getNumberInstance(Locale.US).format(numberOfSpins) + "\n" +
                "Hits: " + hits + "\n" +
                "HF: " + NumberFormat.getNumberInstance(Locale.US).format((hits / new Double(numberOfSpins))*100.0) + "%\n";
        Iterator it = symbolsHitsCounter.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            s += "Symbol:" + pair.getKey() + "\n";
            for (Object o : ((Map<Integer, Integer>) pair.getValue()).entrySet()) {
                Map.Entry pair2 = (Map.Entry) o;
                s += "   Hits: " + pair2.getKey() + " -> " + pair2.getValue() + "\n";
                s += "   HF: 1 in " + df.format(numberOfSpins / new Double((int) pair2.getValue())).replace(",", ".") + "\n";
            }
            it.remove();
        }
        return s;
    }
}
