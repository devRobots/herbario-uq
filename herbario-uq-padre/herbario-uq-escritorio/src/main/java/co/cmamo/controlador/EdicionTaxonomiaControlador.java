package co.cmamo.controlador;

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
import javafx.stage.Stage;

public class EdicionTaxonomiaControlador {

	private DashboardControlador controlador;
	private Stage escenarioEditar;
	
	private Object antecesor;
	
    @FXML
    private TextField txtNombre;
	
    @FXML
    private Button btnAceptar;
	
    @FXML
    private Button btnModificar;
 
    
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
    			}
    			else {
    				Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
    			}
    		}	
        	else	 if (antecesor instanceof Familia) {
        		Genero genero = new Genero();
    			genero.setNombre(txtNombre.getText());
    			genero.setFamilia((Familia)antecesor);
    			
    			if (delegado.registrarGenero(genero)) {
    				controlador.agregarTaxonomiaALista((Genero) genero);
    				Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
    				escenarioEditar.close();
    			}
    			else {
    				Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
    			}
    		}
        	else {
        		Planta planta = new Planta();
    			planta.setEspecie(txtNombre.getText());
    			planta.setGenero((Genero)antecesor);
    			
    			if (delegado.registrarPlanta(planta)) {
    				controlador.agregarTaxonomiaALista((Planta) planta);
    				Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
    				escenarioEditar.close();
    			}
    			else {
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
        	
//        	if (antecesor == null) {
//    			Familia familia = new Familia();
//    			familia.setNombre(txtNombre.getText());
//    			
//    			if (delegado.registrarFamilia(familia)) {
//    				controlador.agregarTaxonomiaALista((Familia) familia);
//    				Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
//    				escenarioEditar.close();
//    			}
//    			else {
//    				Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
//    			}
//    		}	
//        	else	 if (antecesor instanceof Familia) {
//        		Genero genero = new Genero();
//    			genero.setNombre(txtNombre.getText());
//    			genero.setFamilia((Familia)antecesor);
//    			
//    			if (delegado.registrarGenero(genero)) {
//    				controlador.agregarTaxonomiaALista((Genero) genero);
//    				Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
//    				escenarioEditar.close();
//    			}
//    			else {
//    				Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
//    			}
//    		}
//        	else {
//        		Planta planta = new Planta();
//    			planta.setEspecie(txtNombre.getText());
//    			planta.setGenero((Genero)antecesor);
//    			
//    			if (delegado.registrarPlanta(planta)) {
//    				controlador.agregarTaxonomiaALista((Planta) planta);
//    				Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
//    				escenarioEditar.close();
//    			}
//    			else {
//    				Utilidades.mostrarMensaje("Registro", "Error en registro!!", -2);
//    			}
//        	}
//
//        	txtNombre.clear();
		}
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
