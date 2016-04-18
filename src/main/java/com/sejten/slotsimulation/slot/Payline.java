package com.sejten.slotsimulation.slot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by piotr.s
 */
public class Payline {
    private final List<Integer> paylineColumns = new ArrayList<>();

    public Payline(Integer... payline) {
        paylineColumns.addAll(Arrays.asList(payline));
    }

    public int size() {
        return paylineColumns.size();
    }

    public int getRow(int col) {
        return paylineColumns.get(col);
    }

    public String toString() {
        return paylineColumns.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o.equals(toString());
    }
}
