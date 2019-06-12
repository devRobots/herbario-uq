package co.cmamo.modelo;

import co.cmamo.Persona;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Permite transformar una persona en formato observable
 *
 * @author EinerZG
 * @version 1.0
 */
public class PersonaObservable {

	/**
	 * cedula observable de un empleado
	 */
	private StringProperty cedula;
	/**
	 * nombre observable de una persona
	 */
	private StringProperty nombre;
	/**
	 * apellido observable de un empleado
	 */
	private StringProperty apellido;
	/**
	 * email observable de un empleado
	 */
	private StringProperty email;
	/**
	 * clave observable de un empleado
	 */
	private StringProperty clave;
	/**
	 * empleado asociado
	 */
	private Persona persona;

	/**
	 *
	 * @param cedula
	 * @param nombre
	 */
	public PersonaObservable(String cedula, String nombre) {
		this.cedula = new SimpleStringProperty(cedula);
		this.nombre = new SimpleStringProperty(nombre);

		apellido = new SimpleStringProperty("Algo");
		email = new SimpleStringProperty("algo@mail.com");
		clave = new SimpleStringProperty("12345");

	}

	/**
	 * constructor que genera con empleado observable con base a un empleado
	 *
	 * @param empleado que se quiere volver observable
	 */
	public PersonaObservable(Persona persona) {
		this.persona = (Persona) persona;
		cedula = new SimpleStringProperty(persona.getId());
		nombre = new SimpleStringProperty(persona.getNombre());
		apellido = new SimpleStringProperty(persona.getApellido());
		email = new SimpleStringProperty(persona.getCorreo());
		clave = new SimpleStringProperty(persona.getClave());
	}

	/**
	 * permite generar una instanci usando todos los empleados
	 *
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param clave
	 */
	public PersonaObservable(String cedula, String nombre, String apellido, String email, String clave) {
		this.cedula = new SimpleStringProperty(cedula);
		this.nombre = new SimpleStringProperty(nombre);
		this.apellido = new SimpleStringProperty(apellido);
		this.email = new SimpleStringProperty(email);
		this.clave = new SimpleStringProperty(clave);
	}

	/**
	 * @return the cedula
	 */
	public StringProperty getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(StringProperty cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the nombre
	 */
	public StringProperty getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public StringProperty getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(StringProperty apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the email
	 */
	public StringProperty getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(StringProperty email) {
		this.email = email;
	}

	/**
	 * @return the clave
	 */
	public StringProperty getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(StringProperty clave) {
		this.clave = clave;
	}

	/**
	 * @return the empleado
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the empleado to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
