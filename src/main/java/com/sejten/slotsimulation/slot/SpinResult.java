package com.sejten.slotsimulation.slot;

import java.util.List;

/**
 * Created by piotr.s on 2016-05-18.
 */
public class SpinResult {
    public List<WinningPayline> winningPaylines;
    public ReelWindow reelWindow;
    public double prize = 0;

    public SpinResult(ReelWindow rw, List<WinningPayline> wp) {
        winningPaylines = wp;
        reelWindow = rw;
    }

    public SpinResult(ReelWindow rw) {
        reelWindow = rw;
    }

    public SpinResult(SpinResult sr) {
        reelWindow = sr.reelWindow;
        winningPaylines = sr.winningPaylines;
        prize = sr.prize;
    }

    @Override
    public String toString() {
        String s = reelWindow.toString();
        s += "\n";
        for (WinningPayline p : winningPaylines) {
            s += p.toString();
            s += "\n";
        }
        return s;
    }
}
