package com.sejten.slotsimulation.gui;

import com.sejten.slotsimulation.gui.animations.SlotAnimation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;

import static javafx.animation.Animation.INDEFINITE;

/**
 * Visual representation of slot symbol
 */
public class Symbol {
    private final Pane rootWindow;
    private ImageView image;
    private SlotAnimation animation;
    private SymbolState state = SymbolState.ACTIVE;
    private HashMap<String, Symbol> overlays = new HashMap<>();
    private Label payTable;

    public ImageView getImage() {
        return image;
    }

    public Symbol(Pane rw) {
        rootWindow = rw;
        image = new ImageView();
        createPaytable();
        image.setOnMouseClicked((event) -> {
            togglePaytable();
        });

    }

    public Symbol setId(String name) {
        image.setId(name);
        return this;
    }

    public Symbol setHeight(double h) {
        image.setFitHeight(h);
        return this;
    }

    public Symbol setWidth(double w) {
        image.setFitWidth(w);
        return this;
    }

    public Symbol setX(double x) {
        image.setX(x);
        return this;
    }

    public Symbol setY(double y) {
        image.setY(y);
        return this;
    }

    public double getX() {
        return image.getX();
    }

    public double getY() {
        return image.getY();
    }

    public void setToolTip(String text) {
        Tooltip t = new Tooltip(text);
        Tooltip.install(image, t);
    }

    public void setMotionBlurOnSymbol() {
        MotionBlur mb = new MotionBlur(90, 0);
        image.setEffect(mb);

        KeyValue blurValue = new KeyValue(mb.radiusProperty(), 90);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(100), blurValue);

        KeyValue yValue = new KeyValue(image.yProperty(), image.getY() - 20);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), yValue);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().addAll(keyFrame2);
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
    }

    public Symbol setImage(String name) {
        image.setImage(ImageProvider.getInstance().getImageByName(name));
        return this;
    }

    public double getWidth() {
        return image.getFitWidth();
    }

    public double getHeight() {
        return image.getFitHeight();
    }

    public void playAnimation(SlotAnimation a) {
        animation = a.setCycleCount(INDEFINITE).play();
    }

    public void setVisible(boolean val) {
        image.setVisible(val);
    }

    public void stopAnimation() {
        if (animation != null)
            animation.stop();
    }

    public void setState(SymbolState state) {
        this.state = state;
    }

    /**
     * Changes symbol image into given symbol name
     *
     * @param newImageName
     */
    public void changeSymbolTo(String newImageName) {
        if (state == SymbolState.ACTIVE) {
            setMotionBlurOnSymbol();
            setImage(newImageName);
            setToolTip(newImageName);
        }
    }

    public void addOverlayImage(String name, Symbol symbol) {
        overlays.put(name, symbol);
        symbol.setVisible(false);
    }

    public void showOverlay(String name) {
        overlays.get(name).setVisible(true);
    }

    public void hideOverlay(String name) {
        overlays.get(name).setVisible(false);
    }

    public Symbol create() {
        rootWindow.getChildren().add(getImage());
        return this;
    }

    private void togglePaytable() {
        payTable.setLayoutX(getX() + 80);
        payTable.setLayoutY(getY());
        payTable.setPrefHeight(getHeight());
        payTable.setPrefWidth(50);
        payTable.toFront();
        if (payTable.isVisible())
            payTable.setVisible(false);
        else
            payTable.setVisible(true);
    }

    private void createPaytable() {
        Label l = new Label("5 100\n4 50\n3 20");
        l.setAlignment(Pos.CENTER);
        l.setId("tooltip");
        rootWindow.getChildren().add(l);
        payTable = l;
        payTable.setVisible(false);
    }
}
