package co.cmamo.modelo;

import co.cmamo.EstadoPeticion;
import co.cmamo.Peticion;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
	
	private Peticion peticion;
	
	public PeticionObservable(Peticion peticion) {
		this.peticion = peticion;
		id = new SimpleStringProperty(peticion.getId()+"");
		especie = new SimpleStringProperty(peticion.getEspecie().getEspecie());
		genero = new SimpleStringProperty(peticion.getEspecie().getGenero().getNombre());
		familia = new SimpleStringProperty(peticion.getEspecie().getGenero().getFamilia().getNombre());
		estado = new SimpleStringProperty(peticion.getEstado().name());
	}

	public StringProperty getId() {
		return id;
	}

	public void setId(StringProperty id) {
		this.id = id;
	}

	public StringProperty getEspecie() {
		return especie;
	}

	public void setEspecie(StringProperty especie) {
		this.especie = especie;
	}

	public StringProperty getGenero() {
		return genero;
	}

	public void setGenero(StringProperty genero) {
		this.genero = genero;
	}

	public StringProperty getFamilia() {
		return familia;
	}

	public void setFamilia(StringProperty familia) {
		this.familia = familia;
	}

	public Peticion getPeticion() {
		return peticion;
	}

	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
	}

	public StringProperty getEstado() {
		return estado;
	}

	public void setEstado(StringProperty estado) {
		this.estado = estado;
	}
}
