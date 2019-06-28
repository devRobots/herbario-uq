package co.cmamo.modelo;

import co.cmamo.EstadoPeticion;
import co.cmamo.Peticion;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Permite crear Peticiones Observable
 * 
 * @author Yesid Rosas, Cristian Quiceno
 *
 */
public class PeticionObservable {
	/**
	 * cedula observable de un empleado
	 */
	private StringProperty id;
	/**
	 * nombre observable de una persona
	 */
	private StringProperty especie;
	/**
	 * apellido observable de un empleado
	 */
	private StringProperty genero;
	/**
	 * email observable de un empleado
	 */
	private StringProperty familia;
	/**
	 * email observable de un empleado
	 */
	private StringProperty estado;
	/**
	 * TODO: Una peticion
	 */
	private Peticion peticion;
	
	/**
	 * permite generar una instancia de la peticion
	 * @param peticion
	 */
	public PeticionObservable(Peticion peticion) {
		this.peticion = peticion;
		id = new SimpleStringProperty(peticion.getId()+"");
		especie = new SimpleStringProperty(peticion.getEspecie().getEspecie());
		genero = new SimpleStringProperty(peticion.getEspecie().getGenero().getNombre());
		familia = new SimpleStringProperty(peticion.getEspecie().getGenero().getFamilia().getNombre());
		estado = new SimpleStringProperty(peticion.getEstado().name());
	}

	/**
	 * Get Id
	 * @return id
	 */
	public StringProperty getId() {
		return id;
	}

	/**
	 * Set Id
	 * @param id
	 */
	public void setId(StringProperty id) {
		this.id = id;
	}

	/**
	 * Get Especie
	 * @return especie
	 */
	public StringProperty getEspecie() {
		return especie;
	}

	/**
	 * Set Especie
	 * @param especie
	 */
	public void setEspecie(StringProperty especie) {
		this.especie = especie;
	}

	/**
	 * Get Genero
	 * @return genero
	 */
	public StringProperty getGenero() {
		return genero;
	}

	/**
	 * Set Genero
	 * @param genero
	 */
	public void setGenero(StringProperty genero) {
		this.genero = genero;
	}

	/**
	 * Get familia
	 * @return familia
	 */
	public StringProperty getFamilia() {
		return familia;
	}

	/**
	 * Set Familia
	 * @param familia
	 */
	public void setFamilia(StringProperty familia) {
		this.familia = familia;
	}

	/**
	 * Get Peticion
	 * @return peticion
	 */
	public Peticion getPeticion() {
		return peticion;
	}

	/**
	 * Set Peticion
	 * @param peticion
	 */
	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
	}

	/**
	 * Get Estado
	 * @return estado
	 */
	public StringProperty getEstado() {
		return estado;
	}

	/**
	 * Set estado
	 * @param estado
	 */
	public void setEstado(StringProperty estado) {
		this.estado = estado;
	}
}
