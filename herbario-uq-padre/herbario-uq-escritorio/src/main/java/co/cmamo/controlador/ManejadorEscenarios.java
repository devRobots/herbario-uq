package co.cmamo.controlador;

import java.io.IOException;

import co.cmamo.Main;
import co.cmamo.Empleado;
import co.cmamo.Persona;
import co.cmamo.modelo.AdministradorDelegado;
import co.cmamo.modelo.EmpleadoObservable;
import co.cmamo.util.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
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
	/**
	 * para almacenar empleados observables
	 */
	private ObservableList<EmpleadoObservable> empleadosObservables;
	/**
	 * conexion con capa de negocio
	 */
	private AdministradorDelegado administradorDelegado;

	@FXML
	void close() {
		System.exit(0);
	}

	@FXML
	void irAlHerbario() {
		cargarEscenaHerbario();
	}

	@FXML
	void irAlPanel() {
		cargarEscenaPanel();
	}
	
	@FXML
	void acercaDe() {
		Utilidades.mostrarMensaje("Hola mundo", "Esto es un mensaje de prueba", -2);
	}

	public ManejadorEscenarios() {

	}

	/**
	 * recibe el escenario principla de la aplicacion
	 * 
	 * @param escenario inicial
	 */
	public ManejadorEscenarios(Stage escenario) {

		this.escenario = escenario;

		administradorDelegado = AdministradorDelegado.administradorDelegado;
		empleadosObservables = FXCollections.observableArrayList();

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
			empleadosObservables = administradorDelegado.listarEmpleadosObservables();

			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(Main.class.getResource("./vista/detalle_empleado.fxml"));

			panelDashboard = (AnchorPane) loader2.load();
			bordePane.setCenter(panelDashboard);

			EmpleadoControlador controlador = loader2.getController();
			controlador.setEscenarioInicial(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * carga una escena en el centro del escenario
	 */
	public void cargarEscenaPanel() {
		if (panelDashboard == null) {
			try {
				administradorDelegado = AdministradorDelegado.administradorDelegado;
				empleadosObservables = administradorDelegado.listarEmpleadosObservables();
				
				FXMLLoader loader2 = new FXMLLoader();
				loader2.setLocation(Main.class.getResource("./vista/detalle_empleado.fxml"));

				panelDashboard = (AnchorPane) loader2.load();

				EmpleadoControlador controlador = loader2.getController();
				controlador.setEscenarioInicial(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		bordePane.setCenter(panelDashboard);
	}

	/**
	 * carga una escena en el centro del escenario
	 */
	public void cargarEscenaHerbario() {
		if (panelHerbario == null) {
			try {
				FXMLLoader loader3 = new FXMLLoader();
				loader3.setLocation(Main.class.getResource("./vista/herbario.fxml"));

				panelHerbario = (AnchorPane) loader3.load();

				HerbarioControlador controlador = loader3.getController();
				controlador.setEscenarioInicial(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		bordePane.setCenter(panelHerbario);
	}

	/**
	 * muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioCrearEmpleado() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_empleado.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionEmpleadoControlador empleadoControlador = loader.getController();
			empleadoControlador.setEscenarioEditar(escenarioCrear);
			empleadoControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return empleados observables
	 */
	public ObservableList<EmpleadoObservable> getEmpleadosObservables() {
		return empleadosObservables;
	}

	/**
	 * permite agrega una liente a la lista obsevable
	 * 
	 * @param empleado
	 */
	public void agregarALista(Persona empleado) {
		empleadosObservables.add(new EmpleadoObservable(empleado));
	}

	/**
	 * deveulve una instancia del escenario
	 * 
	 * @return escenario
	 */
	public Stage getEscenario() {
		return escenario;
	}

	/**
	 * permite registrar una persona en la base de datos
	 * 
	 * @param empleado a registrar
	 * @return true si quedo registrado
	 */
	public boolean registrarEmpleado(Empleado empleado) {
		try {
			return administradorDelegado.registrarEmpleado(empleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * permite eliminar un empleado
	 * 
	 * @param empleado a ser eliminado
	 * @return true si fue eliminado false si no
	 */
	public boolean eliminarEmpleado(Empleado empleado) {
		return administradorDelegado.eliminarEmpleado(empleado);
	}
}
