package com.sejten.slotsimulation.gui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

/**
 * Created by piotr.s
 */
public class SlotWindow {
    private final GridPane slotWindow;
    private final ImagePathProvider iprovider;
    private static final int columns = 4;
    private static final int rows = 4;


    public SlotWindow(Pane rootWindow) {
        slotWindow = new GridPane();
        iprovider = new ImagePathProvider();
        slotWindow.setLayoutX(10);
        slotWindow.setLayoutY(10);
        slotWindow.setMinWidth(rootWindow.getPrefWidth() - 20);
        slotWindow.setMinHeight(400);
        slotWindow.setGridLinesVisible(true);
        slotWindow.setAlignment(Pos.CENTER);

        initializeColumnsAndRows();
        createImages();
        rootWindow.getChildren().add(slotWindow);
    }

    private void createImages() {
        ImageView iv;
        for (int column = 0; column < getNumberOfColumns(); column++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                iv = new ImageView();
                iv.setId("image" + column + row);
                iv.setFitHeight(100);
                iv.setFitWidth(100);
                slotWindow.add(iv, column, row);
                setSymbolInColumnRow("HIGH1", column, row);
            }
        }

    }

    private void initializeColumnsAndRows() {
        for (int i = 0; i < columns; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / columns);
            colConst.setHalignment(HPos.CENTER);
            slotWindow.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < rows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / rows);
            slotWindow.getRowConstraints().add(rowConst);
        }
    }

    public void setSymbolInColumnRow(String symbol, int column, int row) {
        ImageView i = (ImageView) slotWindow.lookup("#image" + column + row);
        i.setImage(iprovider.getImageBySymbol(symbol));
    }

    public int getNumberOfRows() {
        return rows;
    }

    public int getNumberOfColumns() {
        return columns;
    }

    public void spin() {
        for (int i = 0; i < getNumberOfColumns(); i++) {
            for (int j = 0; j < getNumberOfRows(); j++) {
                setMotionBlurOnSymbol(i, j);
            }
        }

    }

    private void setMotionBlurOnSymbol(int column, int row) {
        ImageView i = (ImageView) slotWindow.lookup("#image" + column + row);
        MotionBlur mb = new MotionBlur(90, 0);
        i.setEffect(mb);

        KeyValue blurValue = new KeyValue(mb.radiusProperty(), 90);

        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(100),blurValue);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().addAll(keyFrame2);
        timeline.play();
    }
}
