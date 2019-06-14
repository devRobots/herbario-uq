package co.cmamo.controlador;

import java.io.IOException;

import co.cmamo.Empleado;
import co.cmamo.EstadoPeticion;
import co.cmamo.Familia;
import co.cmamo.Genero;
import co.cmamo.Main;
import co.cmamo.Persona;
import co.cmamo.Peticion;
import co.cmamo.Planta;
import co.cmamo.Recolector;
import co.cmamo.modelo.AdministradorDelegado;
import co.cmamo.modelo.TaxonomiaObservable;
import co.cmamo.modelo.PersonaObservable;
import co.cmamo.modelo.PeticionObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardControlador {
	private AdministradorDelegado delegado;

	private ObservableList<PersonaObservable> empleadosObservables;
	private ObservableList<PersonaObservable> recolectoresObservables;
	private ObservableList<TaxonomiaObservable> familiasObservables;
	private ObservableList<TaxonomiaObservable> generosObservables;
	private ObservableList<TaxonomiaObservable> especiesObservables;
	private ObservableList<PeticionObservable> peticionesObservables;

	private Stage ventanaCrearPersona;
	private EdicionPersonaControlador controladorCrearPersona;

	private Stage ventanaCrearTaxonomia;
	private EdicionTaxonomiaControlador controladorCrearTaxonomia;

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
	private TableView<TaxonomiaObservable> tablaFamilias;

	@FXML
	private TableColumn<TaxonomiaObservable, String> idFamiliaColumna;

	@FXML
	private TableColumn<TaxonomiaObservable, String> nombreFamiliaColumna;

	@FXML
	private TableView<TaxonomiaObservable> tablaGeneros;

	@FXML
	private TableColumn<TaxonomiaObservable, String> idGeneroColumna;

	@FXML
	private TableColumn<TaxonomiaObservable, String> nombreGeneroColumna;

	@FXML
	private TableView<TaxonomiaObservable> tablaEspecies;

	@FXML
	private TableColumn<TaxonomiaObservable, String> idEspecieColumna;

	@FXML
	private TableColumn<TaxonomiaObservable, String> nombreEspecieColumna;

	@FXML
	private TableView<PeticionObservable> tablaPeticiones;

	@FXML
	private TableColumn<PeticionObservable, String> idPeticionColumna;

	@FXML
	private TableColumn<PeticionObservable, String> estadoPeticionColumna;

	@FXML
	private TableColumn<PeticionObservable, String> nombrePeticionColumna;

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

	@FXML
	private TextArea txtRespuestaPeticion;

	public DashboardControlador() {
	}

	public AdministradorDelegado getAdministradorDelegado() {
		return delegado;
	}

	@FXML
	private void initialize() {
		delegado = AdministradorDelegado.administradorDelegado;

		empleadosObservables = delegado.listarEmpleadosObservables();
		recolectoresObservables = delegado.listarRecolectoresObservables();
		peticionesObservables = delegado.listarPeticionesObservables();

		familiasObservables = delegado.listarFamiliasObservables();
		generosObservables = FXCollections.observableArrayList();
		especiesObservables = FXCollections.observableArrayList();

		cedulaEmpleadoColumna.setCellValueFactory(celda -> celda.getValue().getCedula());
		nombreEmpleadoColumna.setCellValueFactory(celda -> celda.getValue().getNombre());

		cedulaRecolectorColumna.setCellValueFactory(celda -> celda.getValue().getCedula());
		nombreRecolectorColumna.setCellValueFactory(celda -> celda.getValue().getNombre());

		idFamiliaColumna.setCellValueFactory(celda -> celda.getValue().getId());
		nombreFamiliaColumna.setCellValueFactory(celda -> celda.getValue().getNombre());

		idGeneroColumna.setCellValueFactory(celda -> celda.getValue().getId());
		nombreGeneroColumna.setCellValueFactory(celda -> celda.getValue().getNombre());

		idEspecieColumna.setCellValueFactory(celda -> celda.getValue().getId());
		estadoPeticionColumna.setCellValueFactory(celda -> celda.getValue().getEstado());
		nombreEspecieColumna.setCellValueFactory(celda -> celda.getValue().getNombre());

		mostrarDetalleEmpleado(null);
		mostrarDetalleRecolector(null);
		mostrarDetallePeticion(null);

		tablaEmpleados.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleEmpleado(newValue));
		tablaEmpleados.setItems(empleadosObservables);

		tablaRecolectores.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleRecolector(newValue));
		tablaRecolectores.setItems(recolectoresObservables);

		tablaPeticiones.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetallePeticion(newValue));
		tablaPeticiones.setItems(peticionesObservables);

		tablaFamilias.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> listarDeFamilias(newValue));
		tablaFamilias.setItems(familiasObservables);

		tablaGeneros.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> listarDeGeneros(newValue));
		tablaGeneros.setItems(generosObservables);
	}

	@FXML
	void agregarEmpleado(ActionEvent event) {
		if (ventanaCrearPersona == null) {
			inicializarEscenarioCrearPersona();
		}
		controladorCrearPersona.cargarPersona(null);
		controladorCrearPersona.setTipo(new Empleado());
		controladorCrearPersona.setModo(false);

		ventanaCrearPersona.showAndWait();
	}

	@FXML
	void agregarRecolector(ActionEvent event) {
		if (ventanaCrearPersona == null) {
			inicializarEscenarioCrearPersona();
		}
		controladorCrearPersona.cargarPersona(null);
		controladorCrearPersona.setTipo(new Recolector());
		controladorCrearPersona.setModo(false);

		ventanaCrearPersona.showAndWait();
	}

	@FXML
	void editarEmpleado(ActionEvent event) {
		PersonaObservable persona = tablaEmpleados.getSelectionModel().getSelectedItem();
		editarPersona(persona, new Empleado());
	}

	@FXML
	void editarRecolector(ActionEvent event) {
		PersonaObservable persona = tablaRecolectores.getSelectionModel().getSelectedItem();
		editarPersona(persona, new Recolector());
	}

	@FXML
	void invalidarEmpleado(ActionEvent event) {
		PersonaObservable personaObservable = tablaEmpleados.getSelectionModel().getSelectedItem();
		if (personaObservable != null) {
			Empleado persona = (Empleado) personaObservable.getPersona();
			delegado.eliminarEmpleado(persona);
		}
	}

	@FXML
	void invalidarRecolector(ActionEvent event) {
		PersonaObservable personaObservable = tablaRecolectores.getSelectionModel().getSelectedItem();
		if (personaObservable != null) {
			Recolector persona = (Recolector) personaObservable.getPersona();
			delegado.eliminarRecolector(persona);
		}
	}

	@FXML
	void aprobarPeticion(ActionEvent event) {
		PeticionObservable peticionObservable = tablaPeticiones.getSelectionModel().getSelectedItem();
		
		if (peticionObservable != null) {
			Peticion peticion = peticionObservable.getPeticion();

			delegado.registrarFamilia(peticion.getEspecie().getGenero().getFamilia());
			delegado.registrarGenero(peticion.getEspecie().getGenero());
			delegado.registrarPlanta(peticion.getEspecie());
			
			peticion.setEstado(EstadoPeticion.APROBADO);
			delegado.modificarPeticion(peticion);
		}
	}

	@FXML
	void rechazarPeticion(ActionEvent event) {
		PeticionObservable peticionObservable = tablaPeticiones.getSelectionModel().getSelectedItem();
		
		if (peticionObservable != null) {
			Peticion peticion = peticionObservable.getPeticion();

			peticion.setRespuesta(txtRespuestaPeticion.getText());
			peticion.setEstado(EstadoPeticion.RECHAZADO);
			
			delegado.modificarPeticion(peticion);
		}
	}

	@FXML
	void agregarFamilia(ActionEvent event) {
		if (ventanaCrearTaxonomia == null) {
			inicializarEscenarioCrearTaxonomia();
		}

		controladorCrearTaxonomia.setAntecesor(null);
		controladorCrearTaxonomia.setModo(false);
		ventanaCrearTaxonomia.showAndWait();
	}

	@FXML
	void editarFamilia(ActionEvent event) {
		if (ventanaCrearTaxonomia == null) {
			inicializarEscenarioCrearTaxonomia();
		}

		TaxonomiaObservable taxonomia = tablaFamilias.getSelectionModel().getSelectedItem();
		if (taxonomia != null) {
			controladorCrearTaxonomia.setAntecesor(null);
			controladorCrearTaxonomia.cargarCampos(taxonomia);
			controladorCrearTaxonomia.setModo(true);
			ventanaCrearTaxonomia.showAndWait();
		}
	}

	@FXML
	void eliminarFamilia(ActionEvent event) {
		TaxonomiaObservable taxonomia = tablaFamilias.getSelectionModel().getSelectedItem();
		if (taxonomia != null) {
			Familia familia = (Familia) taxonomia.getTaxonomia();
			delegado.eliminarFamilia(familia);
		}
	}

	@FXML
	void agregarGenero(ActionEvent event) {
		if (ventanaCrearTaxonomia == null) {
			inicializarEscenarioCrearTaxonomia();
		}

		TaxonomiaObservable taxonomia = tablaFamilias.getSelectionModel().getSelectedItem();
		if (taxonomia != null) {			
			controladorCrearTaxonomia.setAntecesor(tablaFamilias.getSelectionModel().getSelectedItem().getTaxonomia());
			controladorCrearTaxonomia.setModo(false);
			ventanaCrearTaxonomia.showAndWait();
		}
	}

	@FXML
	void editarGenero(ActionEvent event) {
		if (ventanaCrearTaxonomia == null) {
			inicializarEscenarioCrearTaxonomia();
		}

		TaxonomiaObservable taxonomia = tablaGeneros.getSelectionModel().getSelectedItem();
		if (taxonomia != null) {			
			controladorCrearTaxonomia.setAntecesor(tablaFamilias.getSelectionModel().getSelectedItem().getTaxonomia());
			controladorCrearTaxonomia.cargarCampos(taxonomia);
			controladorCrearTaxonomia.setModo(true);
			ventanaCrearTaxonomia.showAndWait();
		}
	}

	@FXML
	void eliminarGenero(ActionEvent event) {
		TaxonomiaObservable taxonomia = tablaGeneros.getSelectionModel().getSelectedItem();
		if (taxonomia != null) {
			Genero genero = (Genero) taxonomia.getTaxonomia();
			delegado.eliminarGenero(genero);
		}
	}

	@FXML
	void agregarEspecie(ActionEvent event) {
		if (ventanaCrearTaxonomia == null) {
			inicializarEscenarioCrearTaxonomia();
		}

		TaxonomiaObservable taxonomia = tablaGeneros.getSelectionModel().getSelectedItem();
		if (taxonomia != null) {			
			controladorCrearTaxonomia.setAntecesor(tablaGeneros.getSelectionModel().getSelectedItem().getTaxonomia());
			controladorCrearTaxonomia.setModo(false);
			ventanaCrearTaxonomia.showAndWait();
		}
	}

	@FXML
	void editarEspecie(ActionEvent event) {
		if (ventanaCrearTaxonomia == null) {
			inicializarEscenarioCrearTaxonomia();
		}

		TaxonomiaObservable taxonomia = tablaEspecies.getSelectionModel().getSelectedItem();
		if (taxonomia != null) {
			controladorCrearTaxonomia.setAntecesor(tablaGeneros.getSelectionModel().getSelectedItem().getTaxonomia());
			controladorCrearTaxonomia.cargarCampos(taxonomia);
			controladorCrearTaxonomia.setModo(true);
			ventanaCrearTaxonomia.showAndWait();			
		}
	}

	@FXML
	void eliminarEspecie(ActionEvent event) {
		TaxonomiaObservable taxonomia = tablaEspecies.getSelectionModel().getSelectedItem();
		if (taxonomia != null) {
			Planta especie = (Planta) taxonomia.getTaxonomia();
			delegado.eliminarPlanta(especie);
		}
	}

	public void listarDeFamilias(TaxonomiaObservable taxonomia) {
		if (taxonomia != null) {
			Familia familia = (Familia) taxonomia.getTaxonomia();

			generosObservables = delegado.listarGenerosObservables(familia);
			tablaGeneros.setItems(generosObservables);

			especiesObservables = delegado.listarEspeciesObservables(familia);
			tablaEspecies.setItems(especiesObservables);
		}
	}

	public void listarDeGeneros(TaxonomiaObservable taxonomia) {
		if (taxonomia != null) {
			Genero genero = (Genero) taxonomia.getTaxonomia();

			especiesObservables = delegado.listarEspeciesObservables(genero);
			tablaEspecies.setItems(especiesObservables);
		}
	}

	public void mostrarDetalleEmpleado(PersonaObservable empleado) {
		if (empleado != null) {
			txtCedulaEmpleado.setText(empleado.getCedula().getValue());
			txtNombreEmpleado.setText(empleado.getNombre().getValue());
			txtApellidoEmpleado.setText(empleado.getApellido().getValue());
			txtCorreoEmpleado.setText(empleado.getEmail().getValue());
			txtClaveEmpleado.setText(empleado.getClave().getValue());
		} else {
			txtCedulaEmpleado.setText("Empty");
			txtNombreEmpleado.setText("Empty");
			txtApellidoEmpleado.setText("Empty");
			txtCorreoEmpleado.setText("Empty");
			txtClaveEmpleado.setText("Empty");
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
			txtCedulaRecolector.setText("Empty");
			txtNombreRecolector.setText("Empty");
			txtApellidoRecolector.setText("Empty");
			txtCorreoRecolector.setText("Empty");
			txtClaveRecolector.setText("Empty");
		}
	}

	public void mostrarDetallePeticion(PeticionObservable newValue) {
		if (newValue != null) {
			txtIdPeticion.setText(newValue.getId().get());
			txtEspeciePeticion.setText(newValue.getEspecie().get());
			txtGeneroPeticion.setText(newValue.getGenero().get());
			txtFamiliaPeticion.setText(newValue.getFamilia().get());
		}
		else {
			txtIdPeticion.setText("Empty");
			txtEspeciePeticion.setText("Empty");
			txtGeneroPeticion.setText("Empty");
			txtFamiliaPeticion.setText("Empty");
		}
	}

	public void editarPersona(PersonaObservable persona, Persona p) {
		if (ventanaCrearPersona == null) {
			inicializarEscenarioCrearPersona();
		}
		if (persona != null) {
			controladorCrearPersona.cargarPersona(persona);
			controladorCrearPersona.setTipo(p);
			controladorCrearPersona.setModo(true);

			ventanaCrearPersona.showAndWait();
		}
	}

	public void inicializarEscenarioCrearPersona() {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void inicializarEscenarioCrearTaxonomia() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_taxonomia.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			ventanaCrearTaxonomia = new Stage();
			ventanaCrearTaxonomia.setTitle("Crear");
			Scene scene = new Scene(page);
			ventanaCrearTaxonomia.setScene(scene);

			controladorCrearTaxonomia = loader.getController();
			controladorCrearTaxonomia.setEscenarioEditar(ventanaCrearTaxonomia);
			controladorCrearTaxonomia.setManejador(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void agregarPersonaALista(Persona persona) {
		if (persona instanceof Empleado) {
			empleadosObservables.add(new PersonaObservable(persona));
			tablaEmpleados.refresh();
		} else if (persona instanceof Recolector) {
			recolectoresObservables.add(new PersonaObservable(persona));
			tablaRecolectores.refresh();
		}
	}

	public void agregarTaxonomiaALista(Object taxonomia) {
		if (taxonomia instanceof Familia) {
			familiasObservables = delegado.listarFamiliasObservables();
			tablaFamilias.setItems(familiasObservables);
			tablaFamilias.refresh();
		} else if (taxonomia instanceof Genero) {
			Familia familia = (Familia) tablaFamilias.getSelectionModel().getSelectedItem().getTaxonomia();
			generosObservables = delegado.listarGenerosObservables(familia);
			tablaGeneros.setItems(generosObservables);
			tablaGeneros.refresh();
		} else {
			Genero genero = (Genero) tablaGeneros.getSelectionModel().getSelectedItem().getTaxonomia();
			especiesObservables = delegado.listarEspeciesObservables(genero);
			tablaEspecies.setItems(especiesObservables);
			tablaEspecies.refresh();
		}
	}
}
