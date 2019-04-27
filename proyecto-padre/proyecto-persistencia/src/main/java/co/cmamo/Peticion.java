package co.cmamo;

import co.cmamo.EstadoPeticion;
import co.cmamo.Planta;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Peticion
 *
 */
@Entity

public class Peticion implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Id de la peticion
	 */
	@Id
	@Column(unique=true,nullable=false)
	private String id;
	/**
	 * Estado de la Peticion
	 */
	@Enumerated(EnumType.STRING)
	private EstadoPeticion estado;
	/**
	 * Especie de la planta(nombre)
	 */
	@OneToOne
	private Planta especie;   
	/**
	 * Respuesta de la peticion
	 */
	private String respuesta;

	/**
	 * Constructor de la peticion
	 */
	public Peticion() {
		super();
	}   
	/**
	 *  Get de la Respuesta de la peticion
	 * @return String respuesta
	 */
	public String getRespuesta() {
		return this.respuesta;
	}
	
	/**
	 * set de la Respuesta de la Peticion
	 * @param respuesta
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}   
	/**
	 * get del Estado de la Peticion
	 * @return
	 */
	public EstadoPeticion getEstado() {
		return this.estado;
	}

	/**
	 *  set del Estado de la Peticion
	 * @param estado
	 */
	public void setEstado(EstadoPeticion estado) {
		this.estado = estado;
	}   
	/**
	 * get de la Especie (Nombre) de la Planta de la Peticion
	 * @return
	 */
	public Planta getEspecie() {
		return this.especie;
	}
	/**
	 * set de la Especia de la planta de la Peticion
	 * @param especie
	 */
	public void setEspecie(Planta especie) {
		this.especie = especie;
	}   
	/**
	 * Get de la Id de la Peticion
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * set de la Id de la Peticion
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
}
