package com.sejten.slotsimulation.gui;

import com.sejten.slotsimulation.slot.SpinResult;
import com.sejten.slotsimulation.slot.WinningPayline;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Created by piotr.s on 2016-05-25.
 */
public class WinterberriesGui extends SlotGui {
    public WinterberriesGui(Pane rw) {
        super(rw);
        createWSymbols();
    }

    public void spin() {
        removeAllLines();
        stopAllAnimations();
        removeAllIce();
        List<SpinResult> spinResults = sc.spin();

        // if there are additional respins
        if (spinResults.size() > 1) {
            // create thread that shows spins with delay
            Task<Integer> task = new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                    int iterations = 0;
                    try {
                        System.out.println("Amount of respins: " + spinResults.size());
                        spinButton.setDisable(true);
                        for (SpinResult sr : spinResults) {
                            System.out.println(sr);
                            showSpin(sr);
                            iterations++;
                            Thread.sleep(1000);
                        }
                        System.out.println("end of respin");
                        spinButton.setDisable(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return iterations;
                }
            };
            Thread simulationThread = new Thread(task);
            simulationThread.start();
        } else {
            showSpin(spinResults.get(0));
        }
        updateCashBalance();
        //updateWinBalance(spinResults.get(spinResults.size() - 1).prize.get(0).amount);
    }

    public void removeAllIce() {
        for (List<Symbol> r : symbolMatrix)
            for (Symbol s : r) {
                s.hideOverlay("ice");
                s.setState(SymbolState.ACTIVE);
            }
    }

    private void showSpin(SpinResult spinResult) {
        for (int column = 0; column < spinResult.reelWindow.getWidth(); column++)
            for (int row = 0; row < spinResult.reelWindow.getLength(); row++) {
                symbolMatrix.get(row).get(column).changeSymbolTo(spinResult.reelWindow.getSymbolByCoords(column, row).getName());
            }
        if (spinResult.winningPaylines.size() > 0)
            for (WinningPayline p : spinResult.winningPaylines)
                for (int column = 0; column < p.getPayline().size() - 1; column++) {
                    int row = p.getPayline().getRow(column);
                    //show payline
                    rootWindow.lookup(String.format("#payline%d%d%d%d", column, row, column + 1, p.getPayline().getRow(column + 1))).setVisible(true);

                    //freeze symbols
                    if (p.getWinningColumnPositions().contains(column)) {
                        symbolMatrix.get(row).get(column).showOverlay("ice");
                        symbolMatrix.get(row).get(column).setState(SymbolState.FROZEN);
                    }
                }
    }

    private void createWSymbols() {
        for (int column = 0; column < conf.getNumberOfColumns("BASIC"); column++)
            for (int row = 0; row < conf.getNumberOfRows(); row++) {
                Symbol s = symbolMatrix.get(row).get(column);
                Symbol iceSymbol = new Symbol(rootWindow)
                        .setId("iceColumn" + column + "Row" + row)
                        .setHeight(s.getHeight() + 25)
                        .setWidth(s.getWidth() + 25)
                        .setX(s.getX() - 15)
                        .setY(s.getY() - 15)
                        .setImage("ice")
                        .create();
                symbolMatrix.get(row).get(column).addOverlayImage("ice", iceSymbol);
            }
    }
}
