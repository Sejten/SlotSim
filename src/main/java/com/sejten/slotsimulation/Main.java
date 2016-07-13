package com.sejten.slotsimulation;

import com.sejten.slotsimulation.gui.SlotPlayWindow;
import com.sejten.slotsimulation.gui.SlotSimulationWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private SlotPlayWindow slotPlayWindowGui;
    private SlotSimulationWindow runSimulationWindowGui;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/gui.fxml"));

        slotPlayWindowGui = new SlotPlayWindow((AnchorPane) root.lookup("#playAnchorPane"));
        runSimulationWindowGui = new SlotSimulationWindow((AnchorPane) root.lookup("#simulationAnchorPane"));

        primaryStage.setTitle("Slot Simulator");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/fxml/style.css");
        primaryStage.setScene(scene);

        primaryStage.show();

        //Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
