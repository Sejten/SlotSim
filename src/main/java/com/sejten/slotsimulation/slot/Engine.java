package com.sejten.slotsimulation.slot;

import java.text.NumberFormat;
import java.util.*;

/**
 * Created by piotr.s
 */
public class Engine {
    private final GameConf gf = new GameConf();
    private final Player player = new Player();
    private final WinAnalyser we = new WinAnalyser(gf);
    private final StatisticsProcessor sp;
    private final Random randomGen = new Random();

    public Engine() {
        int numberOfSpins = 100000;
        sp = new StatisticsProcessor(numberOfSpins);
        System.out.println("Starting calculation for: " + NumberFormat.getNumberInstance(Locale.US).format(numberOfSpins) + " of spins.");
        long tStart = System.currentTimeMillis();
        for (int i = 0; i < numberOfSpins; i++) {
            spin();
        }
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println("Elapsed time: " + elapsedSeconds + " s.");
        System.out.println("Rtp: " + player.getRtp());
        System.out.println(sp);
    }

    private ReelWindow createWindow() {
        ReelWindow window = new ReelWindow();
        int pos;
        for (Reel r : gf.getReels()) {
            pos = randomGen.nextInt(r.getReelLength());
            window.addReel(r.getSymbolAt(pos, gf.getNumberOfRows()));
        }
        return window;
    }

    private void spin() {
        player.putBet();
        ReelWindow rw = createWindow();
        List<WinningPayline> winningPaylines = we.evaluateReelWindow(rw);
        if (winningPaylines.size() > 0) {
            double prize = new PrizeEvaluator(gf).evaluate(winningPaylines);
            player.addPrize(prize);
            sp.process(winningPaylines);

//            System.out.println("=== SPIN ===");
//            System.out.println(rw);
//            winningPaylines.forEach(System.out::println);
//            System.out.println("You have won: " + prize + " coins.");
//            System.out.println("=== END OF SPIN ===\n");

        }
    }
}
