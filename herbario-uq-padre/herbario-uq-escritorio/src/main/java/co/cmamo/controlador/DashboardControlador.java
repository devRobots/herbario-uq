package co.cmamo.controlador;

import java.io.IOException;

import co.cmamo.Empleado;
import co.cmamo.Familia;
import co.cmamo.Genero;
import co.cmamo.Main;
import co.cmamo.Persona;
import co.cmamo.Planta;
import co.cmamo.Recolector;
import co.cmamo.modelo.AdministradorDelegado;
import co.cmamo.modelo.TaxonomiaObservable;
import co.cmamo.modelo.PersonaObservable;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
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
	private ObservableList<TaxonomiaObservable> familiasObservables;
	private ObservableList<TaxonomiaObservable> generosObservables;
	private ObservableList<TaxonomiaObservable> especiesObservables;

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
		nombreEspecieColumna.setCellValueFactory(celda -> celda.getValue().getNombre());

		mostrarDetalleEmpleado(null);
		mostrarDetalleRecolector(null);

		tablaEmpleados.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleEmpleado(newValue));
		tablaEmpleados.setItems(empleadosObservables);

		tablaRecolectores.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleRecolector(newValue));
		tablaRecolectores.setItems(recolectoresObservables);

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

		ventanaCrearPersona.showAndWait();
	}

	@FXML
	void agregarRecolector(ActionEvent event) {
		if (ventanaCrearPersona == null) {
			inicializarEscenarioCrearPersona();
		}
		controladorCrearPersona.cargarPersona(null);
		controladorCrearPersona.setTipo(new Recolector());

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
		PersonaObservable personaObservable = tablaEmpleados.getSelectionModel().getSelectedItem();
		if (personaObservable != null) {
			Recolector persona = (Recolector) personaObservable.getPersona();
			delegado.eliminarRecolector(persona);
		}
	}

	@FXML
	void aprobarPeticion(ActionEvent event) {
		
	}
	
	@FXML
	void rechazarPeticion(ActionEvent event) {

	}
	
	@FXML
    void agregarFamilia(ActionEvent event) {
		if (ventanaCrearTaxonomia == null) {
			inicializarEscenarioCrearTaxonomia();
		}

		controladorCrearTaxonomia.setAntecesor(null);
		ventanaCrearTaxonomia.showAndWait();
    }

    @FXML
    void editarFamilia(ActionEvent event) {
		if (ventanaCrearTaxonomia == null) {
			inicializarEscenarioCrearTaxonomia();
		}

		controladorCrearTaxonomia.setAntecesor(null);
		controladorCrearTaxonomia.cargarCampos(tablaFamilias.getSelectionModel().getSelectedItem());
		ventanaCrearTaxonomia.showAndWait();
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

		controladorCrearTaxonomia.setAntecesor(tablaFamilias.getSelectionModel().getSelectedItem().getTaxonomia());
		ventanaCrearTaxonomia.showAndWait();
    }

    @FXML
    void editarGenero(ActionEvent event) {
		if (ventanaCrearTaxonomia == null) {
			inicializarEscenarioCrearTaxonomia();
		}

		controladorCrearTaxonomia.setAntecesor(tablaFamilias.getSelectionModel().getSelectedItem().getTaxonomia());
		controladorCrearTaxonomia.cargarCampos(tablaGeneros.getSelectionModel().getSelectedItem());
		ventanaCrearTaxonomia.showAndWait();
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

		controladorCrearTaxonomia.setAntecesor(tablaGeneros.getSelectionModel().getSelectedItem().getTaxonomia());
		ventanaCrearTaxonomia.showAndWait();
    }

    @FXML
    void editarEspecie(ActionEvent event) {
		if (ventanaCrearTaxonomia == null) {
			inicializarEscenarioCrearTaxonomia();
		}

		controladorCrearTaxonomia.setAntecesor(tablaGeneros.getSelectionModel().getSelectedItem().getTaxonomia());
		controladorCrearTaxonomia.cargarCampos(tablaEspecies.getSelectionModel().getSelectedItem());
		ventanaCrearTaxonomia.showAndWait();
    }

    @FXML
    void eliminarEspecie(ActionEvent event) {
    	TaxonomiaObservable taxonomia = tablaFamilias.getSelectionModel().getSelectedItem();
    	if (taxonomia != null) {
    		Planta especie = (Planta) taxonomia.getTaxonomia();
    		delegado.eliminarPlanta(especie);
		}
    }
    
    public void listarDeFamilias(TaxonomiaObservable taxonomia) {
    	Familia familia = (Familia)taxonomia.getTaxonomia();

    	generosObservables = delegado.listarGenerosObservables(familia);
    	tablaGeneros.setItems(generosObservables);

    	especiesObservables = delegado.listarEspeciesObservables(familia);
    	tablaEspecies.setItems(especiesObservables);
    }
    
    public void listarDeGeneros(TaxonomiaObservable taxonomia) {
    	if (taxonomia != null) {
    		Genero genero = (Genero)taxonomia.getTaxonomia();

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
	
	public void editarPersona(PersonaObservable persona, Persona p) {
		if (ventanaCrearPersona == null) {
			inicializarEscenarioCrearPersona();
		}
		controladorCrearPersona.cargarPersona(persona);
		controladorCrearPersona.setTipo(p);

		ventanaCrearPersona.showAndWait();
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
		} else if (persona instanceof Recolector){
			recolectoresObservables.add(new PersonaObservable(persona));
			tablaRecolectores.refresh();
		}
	}

	public void agregarTaxonomiaALista(Object taxonomia) {
		if (taxonomia instanceof Familia) {
			familiasObservables.add(new TaxonomiaObservable((Familia)taxonomia));
			tablaFamilias.refresh();
		} else if (taxonomia instanceof Genero){
			generosObservables.add(new TaxonomiaObservable((Genero)taxonomia));
			tablaGeneros.refresh();
		}
		else {
			especiesObservables.add(new TaxonomiaObservable((Planta)taxonomia));
			tablaEspecies.refresh();
		}
	}
}
