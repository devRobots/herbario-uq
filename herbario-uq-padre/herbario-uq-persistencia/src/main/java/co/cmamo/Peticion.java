package co.cmamo;

import co.cmamo.EstadoPeticion;
import co.cmamo.Planta;
import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Peticion
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Peticion.BUSCAR_POR_ID, query = "select peticion from Peticion peticion where peticion.id = :id"),
		@NamedQuery(name = Peticion.LISTAR_TODOS, query = "select peticion from Peticion peticion"),
		@NamedQuery(name = Peticion.CONTAR_PERSONAS_ACEPTADAS, query = "select count(p) from Peticion p where p.estado = :estado group by p.fecha"),
		@NamedQuery(name = Peticion.OBTENER_LISTADO_RECOLECTORES, query = "select DISTINCT peticion.persona from Peticion peticion" ),
		@NamedQuery(name = Peticion.OBTENER_POR_FECHA, query = "select peticion.id, peticion.especie.genero, peticion.especie, peticion.persona.id, peticion.persona.correo from Peticion peticion where peticion.fecha = :fecha"),
		@NamedQuery(name = Peticion.OBTENER_POR_FECHA_DTO, query ="select new co.cmamo.dto.RegistroDTO(peticion.id, peticion.especie.genero, peticion.especie, peticion.persona.id, peticion.persona.correo) from Peticion peticion where peticion.fecha = :fecha")
})
public class Peticion implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String BUSCAR_POR_ID = "Peticion_findById";
	public static final String LISTAR_TODOS = "Peticion_getAll";
	public static final String CONTAR_PERSONAS_ACEPTADAS = "Peticion_countPersonaAceptadas";
	
	public static final String OBTENER_LISTADO_RECOLECTORES = "Peticion_getRecolectores";
	public static final String OBTENER_POR_FECHA = "Peticion_getAllConFechaPedido";
	public static final String OBTENER_POR_FECHA_DTO = "Peticion_getAllConFechaRegistroDTO";

	/**
	 * Id de la peticion
	 */
	@Id
	private long id;
	/**
	 * Estado de la Peticion
	 */
	@Enumerated(EnumType.ORDINAL)
	private EstadoPeticion estado;
	/**
	 * Persona que envia la solicitud
	 */
	@ManyToOne
	private Persona persona;
	/**
	 * Especie de la planta(nombre)
	 */
	@OneToOne
	private Planta especie;
	/**
	 * Respuesta de la peticion
	 */
	@Column(length = 150)
	private String respuesta;
	/**
	 * Fecha y hora de la peticion
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	/**
	 * Constructor de la peticion
	 */
	public Peticion() {
		super();
	}

	/**
	 * Get de la Respuesta de la peticion
	 * 
	 * @return String respuesta
	 */
	public String getRespuesta() {
		return this.respuesta;
	}

	/**
	 * set de la Respuesta de la Peticion
	 * 
	 * @param respuesta
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * get del Estado de la Peticion
	 * 
	 * @return
	 */
	public EstadoPeticion getEstado() {
		return this.estado;
	}

	/**
	 * set del Estado de la Peticion
	 * 
	 * @param estado
	 */
	public void setEstado(EstadoPeticion estado) {
		this.estado = estado;
	}

	/**
	 * get de la Especie (Nombre) de la Planta de la Peticion
	 * 
	 * @return
	 */
	public Planta getEspecie() {
		return this.especie;
	}

	/**
	 * set de la Especia de la planta de la Peticion
	 * 
	 * @param especie
	 */
	public void setEspecie(Planta especie) {
		this.especie = especie;
	}

	/**
	 * Get de la Id de la Peticion
	 * 
	 * @return
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * set de la Id de la Peticion
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Persona getSolicitante() {
		return persona;
	}

	public void setSolicitante(Persona solicitante) {
		this.persona = solicitante;
	}
}
