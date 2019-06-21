package co.cmamo.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import co.cmamo.Familia;
import co.cmamo.Genero;
import co.cmamo.Planta;
import co.cmamo.modelo.AdministradorDelegado;
import co.cmamo.modelo.TaxonomiaObservable;
import co.cmamo.util.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EdicionTaxonomiaControlador {

	private DashboardControlador controlador;
	private Stage escenarioEditar;
	private byte[] imagen;

	private Object antecesor;
	
	@FXML
	private TextField txtNombre;
	
	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnModificar;
	
	@FXML
	private Button btnBuscar;

	private TaxonomiaObservable taxonomia;

	public void setModo(boolean edicion) {
		btnAceptar.setVisible(!edicion);
		btnModificar.setVisible(edicion);
	}

	@FXML
	void aceptar(ActionEvent event) {
		if (!txtNombre.getText().isEmpty()) {
			AdministradorDelegado delegado = controlador.getAdministradorDelegado();

			if (antecesor == null) {
				Familia familia = new Familia();
				familia.setNombre(txtNombre.getText());

				if (delegado.registrarFamilia(familia)) {
					controlador.agregarTaxonomiaALista((Familia) familia);
					Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
				}
			} else if (antecesor instanceof Familia) {
				Genero genero = new Genero();
				genero.setNombre(txtNombre.getText());
				genero.setFamilia((Familia) antecesor);

				if (delegado.registrarGenero(genero)) {
					controlador.agregarTaxonomiaALista((Genero) genero);
					Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
				}
			} else {
				Planta planta = new Planta();
				planta.setEspecie(txtNombre.getText());
				planta.setGenero((Genero) antecesor);
				planta.setImagen(imagen);

				if (delegado.registrarPlanta(planta)) {
					controlador.agregarTaxonomiaALista((Planta) planta);
					Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
				}
			}

			txtNombre.clear();
		}
    }

	@FXML
	void modificar(ActionEvent event) {
		if (!txtNombre.getText().isEmpty()) {
			AdministradorDelegado delegado = controlador.getAdministradorDelegado();

			if (antecesor == null) {
				Familia familia = delegado.obtenerFamilia(taxonomia.getNombre().get());
				familia.setNombre(txtNombre.getText());

				if (delegado.modificarFamilia(familia)) {
					controlador.agregarTaxonomiaALista((Familia) familia);
					Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
				}
			} else if (antecesor instanceof Familia) {
				Genero genero = (Genero)taxonomia.getTaxonomia();
				genero.setNombre(txtNombre.getText());

				if (delegado.modificarGenero(genero)) {
					controlador.agregarTaxonomiaALista((Genero) genero);
					Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
				}
			} else {
				Planta planta = (Planta) taxonomia.getTaxonomia();
				planta.setEspecie(txtNombre.getText());
				//Agregar imagen
				planta.setImagen(imagen);

				if (delegado.modificarPlanta(planta)) {
					controlador.agregarTaxonomiaALista((Planta) planta);
					Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
				}
			}

			txtNombre.clear();
		}
    }

	@FXML
	void buscar(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar Imagen");
		
		//Agregar Filtros para facilitar la busqueda
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", ".jpg"),
				new FileChooser.ExtensionFilter("PNG", ".png"));
		//Obtener imagen seleccionada
		File imgFile = fileChooser.showOpenDialog(null);
		
		//transformar file a byte[]
		if (imgFile != null) {
			byte[] bytes = getBytesFromFile(imgFile);
			imagen = bytes;
		}
	}
	/**
	 * 
	 * @param imgFile
	 * @return byte []
	 */
	private byte[] getBytesFromFile(File file) {
		//Obtiene el tamanio
		long tamanio = file.length();
		
		byte[] bytes = new byte[(int)tamanio];
		
		try {
			InputStream is = new FileInputStream(file);
			int numRead = 0;
			numRead = is.read(bytes);
			is.close();
			
		} catch (Exception e) {
			
		}
		
		return bytes;
	}

	@FXML
	void cancelar(ActionEvent event) {
		txtNombre.clear();
		escenarioEditar.close();
	}

    @FXML
    void initialize() {

    }
    
    public void cargarCampos(TaxonomiaObservable taxonomiaObservable) {
    	txtNombre.setText(taxonomiaObservable.getNombre().get());
	}
    
    public void setAntecesor(Object antecesor) {
    	this.antecesor = antecesor;
    }
    
    /**
	 * permite cargar el manejador de escenarios
	 *
	 * @param manejador
	 */
	public void setManejador(DashboardControlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * permite modificar el escenario
	 *
	 * @param escenarioEditar
	 */
	public void setEscenarioEditar(Stage escenarioEditar) {
		this.escenarioEditar = escenarioEditar;
	}
}
