package com.sejten.slotsimulation.gui;

import com.sejten.slotsimulation.slot.SlotController;
import com.sejten.slotsimulation.winterberries.WinterberriesGameConf;
import com.sejten.slotsimulation.winterberries.WinterberriesSlotController;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by piotr.s on 2016-05-17.
 */
public class SlotSimulationWindow {
    Pane rootWindow;
    SlotController sc = new WinterberriesSlotController(new WinterberriesGameConf());
    Thread simulationThread;

    public SlotSimulationWindow(Pane rw) {
        rootWindow = rw;
        Button runSimulationButton = (Button) rootWindow.lookup("#runSimulationButton");
        runSimulationButton.setOnAction(e -> runSimulation());
    }

    private void runSimulation() {
        int numberOfSpins = 1000000;

        if (simulationThread != null)
            if (simulationThread.isAlive())
                return;

        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                int iterations;
                String output = "";
                output += "Starting calculation for: " + NumberFormat.getNumberInstance(Locale.ENGLISH).format(numberOfSpins) + " of spins.";
                long tStart = System.currentTimeMillis();
                System.out.println(output);
                for (iterations = 0; iterations < numberOfSpins; iterations++) {
                    if (isCancelled()) {
                        break;
                    }
                    sc.spin();
                    updateProgress(iterations, numberOfSpins);
                }
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                double elapsedSeconds = tDelta / 1000.0;

                output += "\nElapsed time: " + elapsedSeconds + " s." + "\n" + sc.getPlayer() + "\n" + sc.getStatisticsProcessor();
                TextArea textArea = (TextArea) rootWindow.lookup("#outputTextarea");
                textArea.setText(output);
                System.out.println(output);
                return iterations;
            }
        };
        simulationThread = new Thread(task);
        simulationThread.start();
        ((ProgressBar) rootWindow.lookup("#simulationProgressbar")).progressProperty().bind(task.progressProperty());
    }
}
