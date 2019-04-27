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
	 * Familia de la Planta
	 */
	private String familia;
	/**
	 * Genero de la Planta
	 */
	private String genero; 
	/**
	 * Especie(Nombre) de la Planta
	 */
	@Id
	private String especie;

	/**
	 * Constructor de la planta
	 */
	public Planta() {
		super();
	}  
	/**
	 * Get de la familia de la Planta
	 * @return
	 */
	public String getFamilia() {
		return this.familia;
	}
	/**
	 * Set de la Familia de la Planta
	 * @param familia
	 */
	public void setFamilia(String familia) {
		this.familia = familia;
	}   
	/**
	 * Get del Genero de la Planta
	 * @return
	 */
	public String getGenero() {
		return this.genero;
	}
	/**
	 * Set del Genero de la Planta
	 * @param genero
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}  
	/**
	 * Get de la Especie de la planta
	 * @return
	 */
	public String getEspecie() {
		return this.especie;
	}
	/**
	 * Set de la especie de la Planta
	 * @param especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}
}