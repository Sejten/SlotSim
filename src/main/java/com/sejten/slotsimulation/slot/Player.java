package com.sejten.slotsimulation.slot;

/**
 * Created by piotr.s
 */
public class Player {
    private double coinBalance = 1000;
    private final double bet = 1.0;

    private double totalWon = 0;
    private double totalBet = 0;

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
        totalWon += prize;
    }

    public void putBet() {
        coinBalance -= bet;
        totalBet += bet;
    }

    @Override
    public String toString() {
        return "Coin balance";
    }

    public double getRtp() {
        return totalWon / totalBet;
    }
}
