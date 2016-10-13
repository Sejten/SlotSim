package com.sejten.slotsimulation.slot;

import java.util.Arrays;
import java.util.List;

/**
 * Created by piotr.s
 */
public abstract class SlotController {
    protected GameConf gameConf;
    protected final Player player = new Player();
    protected final PaylineAnalyser paylineAnalyser;
    protected final StatisticsProcessor statisticsProcessor;
    protected int numberOfSpins = 0;
    protected  PrizeEvaluator prizeEvaluator;

    public SlotController(GameConf gameConf) {
        this.gameConf = gameConf;
        this.paylineAnalyser = new PaylineAnalyser(gameConf);
        statisticsProcessor = new StatisticsProcessor();
        prizeEvaluator = new PrizeEvaluator(gameConf,statisticsProcessor);
    }

    protected ReelWindow createWindow(List<Reel> reels) {
        ReelWindow window = new ReelWindow();
        Integer pos;
        for (Reel r : reels) {
            pos = RandomGenerator.nextInt(r.getReelLength());
            window.addReel(r.getSymbolAt(pos, gameConf.getNumberOfRows()));
        }
        return window;
    }

    public List<SpinResult> spin() {
        numberOfSpins++;
        player.putBet();
        ReelWindow rw = createWindow(gameConf.getReels("BONUS"));
        SpinResult sr = new SpinResult(rw, paylineAnalyser.evaluateReelWindow(rw));
        if (sr.winningPaylines.size() > 0) {
            List<Prize> prizes = new PrizeEvaluator(gameConf, statisticsProcessor).getPrizesFromNormalSymbols(sr.winningPaylines);
            player.addPrize(prizes);
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
