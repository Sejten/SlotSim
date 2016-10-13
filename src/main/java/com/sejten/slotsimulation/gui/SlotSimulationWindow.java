package com.sejten.slotsimulation.gui;

import com.google.common.collect.ImmutableMap;
import com.sejten.slotsimulation.games.vikings.VikingsGameConf;
import com.sejten.slotsimulation.games.vikings.VikingsSlotController;
import com.sejten.slotsimulation.slot.SlotController;
import com.sejten.slotsimulation.games.winterberries.WinterberriesGameConf;
import com.sejten.slotsimulation.games.winterberries.WinterberriesSlotController;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Created by piotr.s on 2016-05-17.
 */
public class SlotSimulationWindow {
    Pane rootWindow;
    SlotController sc;
    Thread simulationThread;
    int numberOfSpins = 15;

    private Map<String, SlotController> games = ImmutableMap.of(
      "Winterberries", new WinterberriesSlotController(new WinterberriesGameConf()),
      "Vikings", new VikingsSlotController(new VikingsGameConf())
    );

    public SlotSimulationWindow(Pane rw) {
        rootWindow = rw;

        Button runSimulationButton = (Button) rootWindow.lookup("#runSimulationButton");
        ComboBox gamesCombo = (ComboBox) rootWindow.lookup("#gamesCombo");
        // set games in combo
        gamesCombo.setItems(FXCollections.observableArrayList(
          games.keySet()
        ));

        // combo setup
        // select first item
        gamesCombo.getSelectionModel().select(0);
        setActiveGame(gamesCombo.getSelectionModel().getSelectedItem().toString());
        // attach action event
        gamesCombo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setActiveGame(((ComboBox) e.getSource()).getSelectionModel().getSelectedItem().toString());
            }
        });

        TextField spinCount = (TextField) rootWindow.lookup("#spinCount");

        //get number of spins and run simulation
        runSimulationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (spinCount.getText().matches("\\d{1,10}")) {
                    numberOfSpins = Integer.valueOf(spinCount.getText());
                    spinCount.setText(NumberFormat.getNumberInstance(Locale.US).format(Integer.valueOf(spinCount.getText())));
                } else {
                    numberOfSpins = 1000000;
                    spinCount.setText(Integer.toString(numberOfSpins));
                }
                runSimulation();
            }
        });
        // --
//        numberOfSpins = 1000000;
//        setActiveGame("Winterberries");
//        runSimulation();
    }


    private void setActiveGame(String gameName) {
        sc = games.get(gameName);
    }

    private void runSimulation() {
        if (simulationThread != null)
            if (simulationThread.isAlive())
                return;

        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                int iterations;
                StringBuilder output = new StringBuilder();
                String error = "";
                TextArea textArea = (TextArea) rootWindow.lookup("#outputTextarea");
                Label rtpLabel = (Label) rootWindow.lookup("#rtpLabel");

                output.append("Starting calculation for: ")
                  .append(NumberFormat.getNumberInstance(Locale.ENGLISH).format(numberOfSpins))
                  .append(" of spins for ")
                  .append(sc.getClass().getSimpleName());
                long tStart = System.currentTimeMillis();
                System.out.println(output);
                textArea.setText(output.toString());
                for (iterations = 0; iterations < numberOfSpins; iterations++) {
                    if (isCancelled()) {
                        break;
                    }
                    try {
                        sc.spin();
                    } catch (Exception e) {
                        e.printStackTrace();
                        error = e.toString();
                        break;
                    }
                    updateProgress(iterations, numberOfSpins);
                }
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                double elapsedSeconds = tDelta / 1000.0;

                output.append("\nElapsed time: ")
                  .append(elapsedSeconds)
                  .append(" s.\n")
                  .append(sc.getPlayer()).append("\n")
                  .append(sc.getStatisticsProcessor().setNumberOfSpins(numberOfSpins));

                if (error.equals("")) {
                    textArea.setText(output.toString());
                } else
                    textArea.setText("Error:\n" + error);
                System.out.println(output);
                return iterations;
            }
        };
        // print exceptions
        task.exceptionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Exception ex = (Exception) newValue;
                ex.printStackTrace();
            }
        });
        simulationThread = new Thread(task);
        simulationThread.start();
        ((ProgressBar) rootWindow.lookup("#simulationProgressbar")).progressProperty().bind(task.progressProperty());
    }
}
