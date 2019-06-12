package co.cmamo.controlador;

import java.io.IOException;

import co.cmamo.Empleado;
import co.cmamo.Main;
import co.cmamo.Persona;
import co.cmamo.modelo.AdministradorDelegado;
import co.cmamo.modelo.PersonaObservable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardControlador {
	private ManejadorEscenarios manejador;

	private AdministradorDelegado delegado;

	private ObservableList<PersonaObservable> empleadosObservables;
	private ObservableList<PersonaObservable> recolectoresObservables;

	private Stage ventanaCrearPersona;
	private EdicionPersonaControlador controladorCrearPersona;

	@FXML
	private TableView<PersonaObservable> tablaEmpleados;

	@FXML
	private TableColumn<PersonaObservable, String> cedulaEmpleadoColumna;

	@FXML
	private TableColumn<PersonaObservable, String> nombreEmpleadoColumna;

	@FXML
	private TableView<PersonaObservable> tablaRecolectores;

	@FXML
	private TableColumn<PersonaObservable, String> cedulaRecolectorColumna;

	@FXML
	private TableColumn<PersonaObservable, String> nombreRecolectorColumna;

	@FXML
	private Label txtCedulaEmpleado;

	@FXML
	private Label txtNombreEmpleado;

	@FXML
	private Label txtApellidoEmpleado;

	@FXML
	private Label txtCorreoEmpleado;

	@FXML
	private Label txtClaveEmpleado;

	@FXML
	private Label txtCedulaRecolector;

	@FXML
	private Label txtNombreRecolector;

	@FXML
	private Label txtApellidoRecolector;

	@FXML
	private Label txtCorreoRecolector;

	@FXML
	private Label txtClaveRecolector;

	@FXML
	private Label txtIdPeticion;

	@FXML
	private Label txtEspeciePeticion;

	@FXML
	private Label txtGeneroPeticion;

	@FXML
	private Label txtFamiliaPeticion;

	public DashboardControlador() {
	}

	public void setEscenarioInicial(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}

	public AdministradorDelegado getAdministradorDelegado() {
		return delegado;
	}

	@FXML
	private void initialize() {
		delegado = AdministradorDelegado.administradorDelegado;

		empleadosObservables = delegado.listarEmpleadosObservables();
		recolectoresObservables = delegado.listarRecolectoresObservables();

		cedulaEmpleadoColumna.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getCedula());
		nombreEmpleadoColumna.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getNombre());

		mostrarDetalleEmpleado(null);

		tablaEmpleados.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleEmpleado(newValue));

		tablaEmpleados.setItems(empleadosObservables);
	}

	@FXML
	void agregarEmpleado(ActionEvent event) {
		cargarEscenarioCrearPersona(tablaEmpleados.getSelectionModel().getSelectedItem());
		tablaEmpleados.refresh();
	}

	@FXML
	void agregarRecolector(ActionEvent event) {

	}

	@FXML
	void aprobarPeticion(ActionEvent event) {

	}

	@FXML
	void editarEmpleado(ActionEvent event) {

	}

	@FXML
	void editarRecolector(ActionEvent event) {

	}

	@FXML
	void invalidarEmpleado(ActionEvent event) {
		delegado.eliminarEmpleado((Empleado) tablaEmpleados.getSelectionModel().getSelectedItem().getPersona());
	}

	@FXML
	void invalidarRecolector(ActionEvent event) {

	}

	@FXML
	void rechazarPeticion(ActionEvent event) {

	}

	public void mostrarDetalleEmpleado(PersonaObservable empleado) {
		if (empleado != null) {
			txtCedulaEmpleado.setText(empleado.getCedula().getValue());
			txtNombreEmpleado.setText(empleado.getNombre().getValue());
			txtApellidoEmpleado.setText(empleado.getApellido().getValue());
			txtCorreoEmpleado.setText(empleado.getEmail().getValue());
			txtClaveEmpleado.setText(empleado.getClave().getValue());
		} else {
			txtCedulaEmpleado.setText("");
			txtNombreEmpleado.setText("");
			txtApellidoEmpleado.setText("");
			txtCorreoEmpleado.setText("");
			txtClaveEmpleado.setText("");
		}
	}

	public void mostrarDetalleRecolector(PersonaObservable recolector) {
		if (recolector != null) {
			txtCedulaRecolector.setText(recolector.getCedula().getValue());
			txtNombreRecolector.setText(recolector.getNombre().getValue());
			txtApellidoRecolector.setText(recolector.getApellido().getValue());
			txtCorreoRecolector.setText(recolector.getEmail().getValue());
			txtClaveRecolector.setText(recolector.getClave().getValue());
		} else {
			txtCedulaRecolector.setText("");
			txtNombreRecolector.setText("");
			txtApellidoRecolector.setText("");
			txtCorreoRecolector.setText("");
			txtClaveRecolector.setText("");
		}
	}

	public void cargarEscenarioCrearPersona(PersonaObservable persona) {
		if (ventanaCrearPersona == null) {
			try {
				// se carga la interfaz
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("./vista/editar_persona.fxml"));
				AnchorPane page = (AnchorPane) loader.load();

				// se crea el escenario
				ventanaCrearPersona = new Stage();
				ventanaCrearPersona.setTitle("Crear");
				Scene scene = new Scene(page);
				ventanaCrearPersona.setScene(scene);

				// se carga el controlador
				controladorCrearPersona = loader.getController();
				controladorCrearPersona.setEscenarioEditar(ventanaCrearPersona);
				controladorCrearPersona.setManejador(this);

				// se crea el escenario

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		controladorCrearPersona.limpiarCampos();
		controladorCrearPersona.cargarPersona(persona);

		ventanaCrearPersona.showAndWait();
	}

	public void agregarEmpleadoALista(Persona persona) {
		if (persona instanceof Empleado) {
			empleadosObservables.add(new PersonaObservable(persona));
		} else {
			recolectoresObservables.add(new PersonaObservable(persona));
		}
	}
}
