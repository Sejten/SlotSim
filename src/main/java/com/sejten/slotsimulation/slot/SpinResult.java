package com.sejten.slotsimulation.slot;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is container for result of single spin
 */
public class SpinResult {
    public List<WinningPayline> winningPaylines;
    public ReelWindow reelWindow;
    public List<Prize> prize = new ArrayList();

    public SpinResult(ReelWindow rw, List<WinningPayline> wp) {
        winningPaylines = wp;
        reelWindow = rw;
    }

    public SpinResult(ReelWindow rw) {
        reelWindow = new ReelWindow(rw);
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
