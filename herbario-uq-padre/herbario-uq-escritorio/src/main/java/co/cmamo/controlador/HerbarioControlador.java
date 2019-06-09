package co.cmamo.controlador;

import java.util.ArrayList;

import co.cmamo.Planta;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HerbarioControlador {
	
	private ArrayList<Planta> especies;
	private ArrayList<Rectangle> especies_dibujos;

	private ManejadorEscenarios manejador;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Group root;

	@FXML
	private Canvas canvas;

	@FXML
	private void initialize() {
		especies = new ArrayList<Planta>();
		especies_dibujos = new ArrayList<Rectangle>();

		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				for (Rectangle rectangle : especies_dibujos) {
					if (rectangle.contains(e.getX(), e.getY())) {
						double x = 50 + 450 * Math.random();
						double y = 50 + 250 * Math.random();
						rectangle.setX(x);
						rectangle.setY(y);
					}
				}
			}
		});

		GraphicsContext gc = canvas.getGraphicsContext2D();

		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// Clear the canvas
				gc.setFill(new Color(0.45, 1.0, 0.60, 1.0));
				gc.fillRect(0, 0, 
						root.getLayoutBounds().getWidth(),
						root.getLayoutBounds().getHeight());

				if (especies_dibujos.size() < 5) {
					double x = 50 + 450 * Math.random();
					double y = 50 + 250 * Math.random();
					Rectangle rect = new Rectangle(x, y, 25, 45);
					
					especies.add(new Planta());
					especies_dibujos.add(rect);
				}
				
				for (Rectangle rectangle : especies_dibujos) {
					gc.setFill(new Color(0, 0, 0, 0.5));
					gc.fillRect(rectangle.getX(), rectangle.getY(),
							rectangle.getWidth(), rectangle.getHeight());
				}
			}
		}.start();
	}

	public HerbarioControlador() {
	}

	public void setEscenarioInicial(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}

}
