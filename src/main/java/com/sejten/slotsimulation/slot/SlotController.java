package com.sejten.slotsimulation.slot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by piotr.s
 */
public abstract class SlotController {
    protected GameConf gameConf;
    protected final Player player = new Player();
    protected final WinAnalyser winAnalyser;
    protected final StatisticsProcessor statisticsProcessor;
    protected final Random randomGen = new Random();
    protected int numberOfSpins = 0;

    public SlotController(GameConf gameConf) {
        this.gameConf = gameConf;
        this.winAnalyser = new WinAnalyser(gameConf);
        statisticsProcessor = new StatisticsProcessor();
    }

    protected ReelWindow createWindow(List<Reel> reels) {
        ReelWindow window = new ReelWindow();
        Integer pos;
        for (Reel r : reels) {
            pos = randomGen.nextInt(r.getReelLength());
            window.addReel(r.getSymbolAt(pos, gameConf.getNumberOfRows()));
        }
        return window;
    }

    public List<SpinResult> spin() {
        numberOfSpins++;
        player.putBet();
        ReelWindow rw = createWindow(gameConf.getReels());
        SpinResult sr = new SpinResult(rw, winAnalyser.evaluateReelWindow(rw));
        if (sr.winningPaylines.size() > 0) {
            double prize = new PrizeEvaluator(gameConf).evaluate(sr.winningPaylines);
            player.addPrize(prize);
            statisticsProcessor.process(sr.winningPaylines);
        }
        return Arrays.asList(sr);
    }

    public Player getPlayer() {
        return player;
    }

    public StatisticsProcessor getStatisticsProcessor() {
        return statisticsProcessor;
    }
}
