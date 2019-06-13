package co.cmamo.controlador;

import java.util.ArrayList;

import co.cmamo.Empleado;
import co.cmamo.EstadoActividad;
import co.cmamo.Persona;
import co.cmamo.Recolector;
import co.cmamo.modelo.AdministradorDelegado;
import co.cmamo.modelo.PersonaObservable;
import co.cmamo.util.Utilidades;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Permite controlar la vista editar_empleado
 *
 * @author EinerZG version 1.0
 */
public class EdicionPersonaControlador {

	/**
	 * campo para la cedula
	 */
	@FXML
	private TextField cmpCedula;
	/**
	 * campo para el nombre
	 */
	@FXML
	private TextField cmpNombre;
	/**
	 * campo para el apellido
	 */
	@FXML
	private TextField cmpApellido;
	/**
	 * campo para el email
	 */
	@FXML
	private TextField cmpCorreo;
	/**
	 * campo para la calve
	 */
	@FXML
	private PasswordField cmpClave;

	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditar;
	/**
	 * instancia del manejador que lo invoco
	 */
	private DashboardControlador controlador;

	private Persona persona;
	/**
	 *
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * permite cargar la informacion de un persona para realizar una edicion
	 *
	 * @param persona empleado a editar
	 */
	public void cargarPersona(PersonaObservable persona) {
		if (persona != null) {
			cmpCedula.setText(persona.getCedula().getValue());
			cmpNombre.setText(persona.getNombre().getValue());
			cmpApellido.setText(persona.getApellido().getValue());
			cmpCorreo.setText(persona.getEmail().getValue());
			cmpClave.setText(persona.getClave().getValue());
		}
	}
	
	public void setTipo(Persona persona) {
		this.persona = persona;
	}

	/**
	 * permite registrar una persona en la base de datos
	 */
	@FXML
	public void registrarPersona() {
		if (!cmpApellido.getText().isEmpty() && !cmpCedula.getText().isEmpty() && !cmpClave.getText().isEmpty() && !cmpCorreo.getText().isEmpty() && !cmpNombre.getText().isEmpty()) {
			persona.setId(cmpCedula.getText());
			persona.setNombre(cmpNombre.getText());
			persona.setApellido(cmpApellido.getText());
			persona.setClave(cmpClave.getText());
			persona.setCorreo(cmpCorreo.getText());
			persona.setEstado(EstadoActividad.ACTIVO);
			persona.setPeticiones(new ArrayList<>());

			AdministradorDelegado delegado = controlador.getAdministradorDelegado();

			if (persona instanceof Empleado) {
				if (delegado.registrarEmpleado((Empleado) persona)) {
					controlador.agregarPersonaALista((Empleado) persona);
					Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensaje("Registro", "Error en registro!!");
				}
			} else if (persona instanceof Recolector) {
				if (delegado.registrarRecolector((Recolector) persona)) {
					controlador.agregarPersonaALista((Recolector) persona);
					Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensaje("Registro", "Error en registro!!");
				}
			} else {
				Utilidades.mostrarMensaje("Registro", "Error en registro!!");
			}
		}
	}

	/**
	 * permite editar la informacion de una persona
	 */
	@FXML
	private void modificar() {
		// TODO terminar modificar empleado
		escenarioEditar.close();
	}

	/**
	 * permite cerrar la ventana de editar y crear
	 */
	@FXML
	private void cancelar() {
		escenarioEditar.close();
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