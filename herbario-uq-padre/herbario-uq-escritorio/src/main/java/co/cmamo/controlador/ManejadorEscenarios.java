package co.cmamo.controlador;

import java.io.IOException;

import co.cmamo.Main;
import co.cmamo.util.Utilidades;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Permite manejar los escenarios que tiene la aplicacion
 *
 * @author EinerZG
 * @version 1.0
 */
public class ManejadorEscenarios {

	/**
	 * contenedor prinpipal de la aplicacion
	 */
	private Stage escenario;
	/**
	 * tipo de panel inicial
	 */
	@FXML
	private BorderPane bordePane;
	/**
	 * Panel de la escena Dashboard
	 */
	@FXML
	private AnchorPane panelDashboard;
	/**
	 * Panel de la escena Herbario
	 */
	@FXML
	private AnchorPane panelHerbario;

	@FXML
	void recargar() {
		cargarEscena();
	}

	@FXML
	void close() {
		System.exit(0);
	}

	@FXML
	void irAlHerbario() {

	}

	@FXML
	void irAlPanel() {

	}

	@FXML
	void acercaDe() {
		Utilidades.mostrarMensaje("Hola mundo", "Esto es un mensaje de prueba", 1);
	}

	public ManejadorEscenarios() {

	}

	/**
	 * recibe el escenario principla de la aplicacion
	 *
	 * @param escenario inicial
	 */
	public ManejadorEscenarios(Stage escenario) {

		escenario.setMinWidth(600);
		escenario.setMinHeight(400);
		
		this.escenario = escenario;

		try {
			// se inicializa el escenario
			escenario.setTitle("Herbario UQ");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/inicio.fxml"));

			bordePane = (BorderPane) loader.load();

			// se carga la escena
			Scene scene = new Scene(bordePane);
			escenario.setScene(scene);
			escenario.show();

			cargarEscena();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * carga una escena en el centro del escenario
	 */
	public void cargarEscena() {
		try {
			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(Main.class.getResource("./vista/dashboard.fxml"));

			panelDashboard = (AnchorPane) loader2.load();
			bordePane.setCenter(panelDashboard);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * deveulve una instancia del escenario
	 *
	 * @return escenario
	 */
	public Stage getEscenario() {
		return escenario;
	}
}
