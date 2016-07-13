package com.sejten.slotsimulation.slot;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.sqrt;

/**
 * Created by piotr.s
 */
public class Player {
    private double coinBalance = 1000;
    private final double bet = 25.0;

    private List<Double> totalWon = new ArrayList<>();
    private double totalBet = 0;
    private int numberOfBets = 0;
    private double variance = 0;

    public double getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(double coinBalance) {
        this.coinBalance = coinBalance;
    }

    public double getBet() {
        return bet;
    }

    public void addPrize(double prize) {
        coinBalance += prize;
        totalWon.add(prize);
    }

    public void putBet() {
        coinBalance -= bet;
        totalBet += bet;
        numberOfBets++;
    }

    public Double getTotalWonAmount() {
        return totalWon.stream().mapToDouble(Double::doubleValue).sum();
    }

    public Double getExpectedValue() {
        return getTotalWonAmount() / numberOfBets;
    }

    public Double getStdDev() {
        return sqrt(getVariance());
    }

    public Double getVariance() {
        Double ex = getExpectedValue();
        return totalWon.stream().map(p -> Math.pow(p - ex, 2)).mapToDouble(Double::doubleValue).sum() / numberOfBets;
    }

    @Override
    public String toString() {
        return "Rtp: " + getRtp() +
                " \nTotal Bet: " + NumberFormat.getNumberInstance(Locale.ENGLISH).format(totalBet) +
                " \nTotal Won: " + NumberFormat.getNumberInstance(Locale.ENGLISH).format(getTotalWonAmount()) +
                " \nExpected value: " + NumberFormat.getNumberInstance(Locale.ENGLISH).format(getExpectedValue()) +
                " \nVariance: " + NumberFormat.getNumberInstance(Locale.ENGLISH).format(getVariance()) +
                " \nStd dev: " + NumberFormat.getNumberInstance(Locale.ENGLISH).format(getStdDev());
    }

    public double getRtp() {
        return getTotalWonAmount() / totalBet;
    }
}
