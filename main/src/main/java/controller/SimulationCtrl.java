package controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;
import javafx.scene.control.Slider;
import model.*;
import view.RestaurantView;

public class SimulationCtrl {
    @FXML
    private Canvas canvas;
    @FXML
    private Button startButton;
    @FXML
    private Button terminateButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Slider simSpeed;
    @FXML
    private 
    @FXML

    @FXML

    private GraphicsContext gc;
    private RestaurantCtrl simulation;
    private AnimationTimer timer;
    private boolean running = false;
    private int simDuration = 50;
    
    public void handle(long now) {

    }

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        simulation = new RestaurantCtrl();
        this.view = new RestaurantView();
    }
}
