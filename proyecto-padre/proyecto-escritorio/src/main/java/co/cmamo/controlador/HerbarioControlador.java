package co.cmamo.controlador;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HerbarioControlador {

	private ManejadorEscenarios manejador;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Group root;

	@FXML
	private Canvas canvas;

	@FXML
	private void initialize() {
		// Image restart = new Image("restart.png");

		Circle targetData = new Circle(100, 100, 32);
//        IntValue points = new IntValue(0);

		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (targetData.contains(e.getX(), e.getY())) {
					double x = 50 + 450 * Math.random();
					double y = 50 + 250 * Math.random();
					targetData.setCenterX(x);
					targetData.setCenterY(y);
//					points.value++;
				} else {					
//					points.value = 0;
				}
			}
		});

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
		gc.setFont(theFont);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);

		Image bullseye = new Image("bullseye.png");

		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// Clear the canvas
				gc.setFill(new Color(0.85, 0.85, 1.0, 1.0));
				gc.fillRect(0, 0, root.getLayoutBounds().getWidth(), root.getLayoutBounds().getHeight());

				gc.drawImage(bullseye, targetData.getCenterX() - targetData.getRadius(),
						targetData.getCenterY() - targetData.getRadius());

				gc.setFill(Color.BLUE);

//				String pointsText = "Points: " + points.value;
//				gc.fillText(pointsText, 360, 36);
//				gc.strokeText(pointsText, 360, 36);
			}
		}.start();
	}

	public HerbarioControlador() {

	}

	public void setEscenarioInicial(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}

}
