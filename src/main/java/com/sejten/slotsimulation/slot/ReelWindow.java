package com.sejten.slotsimulation.slot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by piotr.s
 */
public class ReelWindow {
    private final List<Reel> reelWindow = new ArrayList<>();

    public void addReel(Reel... reel) {
        reelWindow.addAll(Arrays.asList(reel));
    }

    private Reel getReelByColumn(int column) {
        return reelWindow.get(column);
    }

    public Symbol getSymbolByCoords(int column, int row) {
        return getReelByColumn(column).getSymbolAt(row);
    }

    public String toString() {
        if (reelWindow.size() == 0)
            return "";
        String windowString = "";
        List<List<String>> a = new ArrayList<>();
        for (Reel r : reelWindow) {
            List<String> b = new ArrayList<>();
            b.addAll(Arrays.asList(r.toString().split(" ")));
            a.add(b);
        }
        for (int j = 0; j < a.get(0).size(); j++) {
            for (List<String> anA : a) {
                windowString += anA.get(j) + " ";
            }
            windowString += "\n";
        }
        return windowString.trim();
    }
}
