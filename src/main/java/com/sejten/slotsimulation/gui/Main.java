package com.sejten.slotsimulation.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.sejten.slotsimulation.slot.Engine;

import java.util.Random;

public class Main extends Application {
    private SlotWindow slotWindowGui;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/gui.fxml"));

        slotWindowGui = new SlotWindow((Pane)root.lookup("#mainWindow"));

        Button spinButton = (Button) root.lookup("#spinButton");
        spinButton.setOnAction(e -> spin());

//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 800, 600));
//        primaryStage.show();
        new Engine();
        Platform.exit();
    }

    private void spin() {
        slotWindowGui.spin();
        for (int i = 0; i < slotWindowGui.getNumberOfColumns(); i++) {
            for (int j = 0; j < slotWindowGui.getNumberOfRows(); j++) {
                String symbol = getRandomSymbol();
                slotWindowGui.setSymbolInColumnRow(symbol, i, j);
            }
        }

    }

    private String getRandomSymbol() {
        String[] symbols = {"HIGH1", "HIGH2", "LOW1", "LOW2"};
        int idx = new Random().nextInt(symbols.length);
        return (symbols[idx]);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
