package co.cmamo;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Planta
 *
 */
@Entity

public class Planta implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Id de la Planta
	 */
	@Id
	private String id;
	/**
	 * especie de la Planta
	 */
	private String especie;
	/**
	 * Genero de la Planta
	 */
	@ManyToOne
	private Genero genero;
	/**
	 * Constructor de la planta
	 */
	public Planta() {
		super();
	}

	/**
	 *  Get de Id
	 * @return la Id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * Set de la Id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Get de especie
	 * @return especie
	 */
	public String getEspecie() {
		return this.especie;
	}
	/**
	 * Set de Esoecie
	 * @param especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	/**
	 * Get de Genero
	 * @return genero
	 */
	public Genero getGenero() {
		return genero;
	}
	/**
	 * set Genero
	 * @param genero
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
}
