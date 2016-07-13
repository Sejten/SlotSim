package com.sejten.slotsimulation.slot;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by piotr.s
 */
public class StatisticsProcessor {
    private int numberOfSpins;
    private int hits = 0;
    private final Map<Symbol, Map<Integer, Integer>> symbolsHitsCounter = new HashMap<>();
    //private final Map<Payline, Integer> paylineHF = new HashMap<>();

    public void process(List<WinningPayline> winningPaylines) {
        if (winningPaylines.size() == 0)
            return;
        hits++;
        for (WinningPayline wp : winningPaylines) {
//            if (!paylineHF.containsKey(wp.getPayline()))
//                paylineHF.put(wp.getPayline(), 0);
//            paylineHF.put(wp.getPayline(), paylineHF.get(wp.getPayline()) + 1);


            if (!symbolsHitsCounter.containsKey(wp.getSymbol()))
                symbolsHitsCounter.put(wp.getSymbol(), new HashMap<>());
            Map<Integer, Integer> currentSymbolHitCount = symbolsHitsCounter.get(wp.getSymbol());
            if (!currentSymbolHitCount.containsKey(wp.getWinningSymbolCount()))
                currentSymbolHitCount.put(wp.getWinningSymbolCount(), 0);
            Integer numberOfHits = currentSymbolHitCount.get(wp.getWinningSymbolCount());
            currentSymbolHitCount.put(wp.getWinningSymbolCount(), numberOfHits + 1);
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        String s = "Number of spins: " + NumberFormat.getNumberInstance(Locale.ENGLISH).format(numberOfSpins) + "\n" +
                "Hits: " + hits + "\n" +
                "HF: " + NumberFormat.getNumberInstance(Locale.US).format((hits / new Double(numberOfSpins)) * 100.0) + "%\n";

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
//        s += "\n" + paylineHF;
        return s;
    }

    public void setNumberOfSpins(int numberOfSpins) {
        this.numberOfSpins = numberOfSpins;
    }
}
