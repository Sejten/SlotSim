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
    // symbol -> num of occurences:count
    private final Map<Symbol, Map<Integer, Integer>> symbolsHitsCounter = new HashMap<>();
    //symbol -> prize:count
    private final Map<Symbol, Map<Prize, Integer>> scatterPrizeCounter = new HashMap<>();
    private final Map<Symbol, Map<Prize, Integer>> freespinPrizeCounter = new HashMap<>();
    private final Map<Symbol, Map<Integer, Integer>> scatterHitCounter = new HashMap<>();
    //private final Map<Payline, Integer> paylineHF = new HashMap<>();
    private final Map<String, Integer> customCounter = new HashMap<>();

    public void process(List<WinningPayline> winningPaylines) {
        if (winningPaylines.size() == 0)
            return;
        hits++;
        for (WinningPayline wp : winningPaylines) {
//            if (!paylineHF.containsKey(wp.getPayline()))
//                paylineHF.put(wp.getPayline(), 0);
//            paylineHF.put(wp.getPayline(), paylineHF.get(wp.getPayline()) + 1);

            Map<Integer, Integer> currentSymbolHitCount = symbolsHitsCounter.computeIfAbsent(wp.getSymbol(), k -> new HashMap<>());
            Integer numberOfHits = currentSymbolHitCount.computeIfAbsent(wp.getWinningSymbolCount(), k -> 0);
            currentSymbolHitCount.put(wp.getWinningSymbolCount(), numberOfHits + 1);
        }
    }

    @Override
    public String toString() {
        Iterator it;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        StringBuilder output = new StringBuilder();
        output.append("Number of spins: ")
                .append(NumberFormat.getNumberInstance(Locale.ENGLISH).format(numberOfSpins))
                .append("\n")
                .append("Hits: ")
                .append(hits)
                .append("\n")
                .append("HF: ")
                .append(NumberFormat.getNumberInstance(Locale.US).format((hits / new Double(numberOfSpins)) * 100.0))
                .append("%\n");

        it = scatterPrizeCounter.entrySet().iterator();
        int total = scatterPrizeCounter.values().stream().mapToInt(e -> e.values().stream().mapToInt(Number::intValue).sum()).sum();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            output.append("Symbol: ").append(pair.getKey()).append("\n");
            for (Object o : ((Map<Optional<Prize>, Integer>) pair.getValue()).entrySet()) {
                Map.Entry pair2 = (Map.Entry) o;
                output.append("   Prize: ")
                        .append(pair2.getKey())
                        .append(" count: ")
                        .append(pair2.getValue())
                        .append(" - ")
                        .append((int) (((int) pair2.getValue() / (double) total) * 100))
                        .append("% of total: ")
                        .append(total)
                        .append("\n");
            }
            it.remove();
        }
        it = freespinPrizeCounter.entrySet().iterator();
        total = freespinPrizeCounter.values().stream().mapToInt(e -> e.values().stream().mapToInt(Number::intValue).sum()).sum();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            output.append("Symbol: ").append(pair.getKey()).append("\n");
            for (Object o : ((Map<Optional<Prize>, Integer>) pair.getValue()).entrySet()) {
                Map.Entry pair2 = (Map.Entry) o;
                output.append("   Prize: ")
                        .append(pair2.getKey())
                        .append(" count: ")
                        .append(pair2.getValue())
                        .append(" - ")
                        .append((int) (((int) pair2.getValue() / (double) total) * 100))
                        .append("% of total: ")
                        .append(total)
                        .append("\n");
            }
            it.remove();
        }
        it = scatterHitCounter.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            output.append("Symbol: ")
                    .append(pair.getKey())
                    .append(" hits - ")
                    .append(pair.getValue())
                    .append("\n");
            it.remove();
        }
        it = symbolsHitsCounter.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            output.append("Symbol: ").append(pair.getKey()).append("\n");
            for (Object o : ((Map<Integer, Integer>) pair.getValue()).entrySet()) {
                Map.Entry pair2 = (Map.Entry) o;
                output.append("   Hits: ")
                        .append(pair2.getKey()).append(" -> ")
                        .append(pair2.getValue())
                        .append("\n").append("   HF: 1 in ")
                        .append(df.format(numberOfSpins / new Double((int) pair2.getValue())).replace(",", "."))
                        .append("\n");
            }
            it.remove();
        }
        for (Map.Entry e : customCounter.entrySet()) {
            output.append(e.getKey());
            output.append(" : ");
            output.append(e.getValue());
            output.append("\n");
        }
//        s += "\n" + paylineHF;
        return output.toString();
    }

    public StatisticsProcessor setNumberOfSpins(int numberOfSpins) {
        this.numberOfSpins = numberOfSpins;
        return this;
    }

    public void processScatter(Symbol s, int occurences, Optional<Prize> p) {
        Map<Prize, Integer> currentCount;
        if (s.getType() == SymbolType.FREESPIN) {
            currentCount = freespinPrizeCounter.computeIfAbsent(s, k -> new HashMap<>());
        } else {
            currentCount = scatterPrizeCounter.computeIfAbsent(s, k -> new HashMap<>());
        }
        Integer count = currentCount.computeIfAbsent(p.get(), k -> 0);
        currentCount.put(p.get(), count + 1);
        Map<Integer, Integer> currentScatterCount = scatterHitCounter.computeIfAbsent(s, k -> new HashMap<>());
        count = currentScatterCount.computeIfAbsent(occurences, k -> 0);
        currentScatterCount.put(occurences, count + 1);
    }

    public void countItem(String name, Integer increaseBy) {
        customCounter.put(name, customCounter.computeIfAbsent(name, k -> 0) + increaseBy);
    }
}
