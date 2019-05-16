package co.cmamo;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Planta
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Planta.BUSCAR_POR_ID, query = "select planta from Planta planta where planta.id = :id"),
	@NamedQuery(name = Planta.LISTAR_TODOS, query = "select planta from Planta planta"),
	@NamedQuery(name = Planta.LISTAR_POR_GENERO, query = "select planta from Planta planta where planta.genero.id =:genero"),
	@NamedQuery(name = Planta.LISTAR_POR_FAMILIA, query = "select planta from Planta planta where planta.genero.familia =:familia")
})
public class Planta implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * LLamado para los querys
	 */
	public static final String BUSCAR_POR_ID = "Planta_findById";
	public static final String LISTAR_TODOS = "Planta_getAll";
	public static final String LISTAR_POR_GENERO = "Planta_getGenero";
	public static final String LISTAR_POR_FAMILIA = "Planta_getFamilia";
	
	/**
	 * Id de la Planta
	 */
	@Id
	@Column(unique = true, nullable = false)
	private String id;
	/**
	 * especie de la Planta
	 */
	@Column(unique = true, nullable = false, length = 50)
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
	 * Set de Especie
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
