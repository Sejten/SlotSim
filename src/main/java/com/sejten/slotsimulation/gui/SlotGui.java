package com.sejten.slotsimulation.gui;

import com.sejten.slotsimulation.slot.GameConf;
import com.sejten.slotsimulation.slot.Payline;
import com.sejten.slotsimulation.slot.SlotController;
import com.sejten.slotsimulation.games.winterberries.WinterberriesGameConf;
import com.sejten.slotsimulation.games.winterberries.WinterberriesSlotController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static javafx.animation.Animation.INDEFINITE;

/**
 * Created by piotr.s on 2016-05-25.
 */
public class SlotGui {
    protected Button spinButton;
    protected List<List<Symbol>> symbolMatrix;
    protected List<Line> listOfLines = new ArrayList<Line>();
    protected Pane rootWindow;
    protected GameConf conf = new WinterberriesGameConf();
    protected SlotController sc = new WinterberriesSlotController(conf);

    protected int symbolMargin = 20;
    protected int symbolImageWidth = 100;
    protected int symbolImageHeight = 100;

    public SlotGui(Pane rw) {
        rootWindow = rw;
        spinButton = (Button) rootWindow.lookup("#spinButton");
        spinButton.setOnAction(e -> spin());
        createSymbols();
        createPaylines();
        updateCashBalance();
    }

    public void spin() {

    }

    private void drawPaylineFragment(int c1, int r1, int c2, int r2) {
        Line line = new Line();
        double startX = symbolMatrix.get(r1).get(c1).getX() + symbolMatrix.get(r1).get(c1).getWidth() / 2;
        double startY = symbolMatrix.get(r1).get(c1).getY() + symbolMatrix.get(r1).get(c1).getHeight() / 2;
        double endX = symbolMatrix.get(r2).get(c2).getX() + symbolMatrix.get(r1).get(c2).getWidth() / 2;
        double endY = symbolMatrix.get(r2).get(c2).getY() + symbolMatrix.get(r1).get(c2).getHeight() / 2;
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStrokeWidth(5);
        line.setStroke(Color.YELLOWGREEN);
        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(10);
        line.setEffect(blur);

        KeyValue blurValue = new KeyValue(blur.radiusProperty(), 0);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), blurValue);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().addAll(keyFrame2);
        timeline.play();
        line.setId("payline" + c1 + r1 + c2 + r2);
        line.setVisible(false);
        listOfLines.add(line);

        rootWindow.getChildren().add(line);
    }

    protected void updateCashBalance() {
        ((Label) rootWindow.lookup("#balanceAmountLabel")).setText(String.valueOf(sc.getPlayer().getCoinBalance()));
    }

    protected void updateWinBalance(double amount) {
        ((Label) rootWindow.lookup("#roundWinAmountLabel")).setText(String.valueOf(amount));
    }

    private void createPaylines() {
        for (Payline p : conf.getPaylines())
            for (int column = 0; column < p.size() - 1; column++) {
                int row = p.getRow(column);
                drawPaylineFragment(column, row, column + 1, p.getRow(column + 1));
            }
    }

    public void removeAllLines() {
        for (Line l : listOfLines)
            l.setVisible(false);
    }

    protected void stopAllAnimations() {
        for (int column = 0; column < conf.getNumberOfColumns("BASIC"); column++)
            for (int row = 0; row < conf.getNumberOfRows(); row++)
                symbolMatrix.get(row).get(column).stopAnimation();
    }

    private void createSymbols() {
        int xoffset = 0;
        int yoffset = 0;
        symbolMatrix = new ArrayList<>();

        for (int row = 0; row < conf.getNumberOfRows(); row++) {
            List<Symbol> rowArray = new ArrayList<>();
            for (int column = 0; column < conf.getNumberOfColumns("BASIC"); column++) {
                // symbol image
                Symbol s = new Symbol(rootWindow)
                        .setId("symbolImageColumn" + column + "Row" + row)
                        .setHeight(symbolImageHeight)
                        .setWidth(symbolImageWidth)
                        .setX(symbolMargin + xoffset)
                        .setY(symbolMargin + yoffset)
                        .create();
                rowArray.add(s);
                xoffset += symbolMargin + symbolImageWidth;
            }
            yoffset += symbolMargin + symbolImageHeight;
            xoffset = 0;
            symbolMatrix.add(rowArray);
        }
    }
}
