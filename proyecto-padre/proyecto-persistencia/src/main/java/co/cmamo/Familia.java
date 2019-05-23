package co.cmamo;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Familia
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Familia.BUSCAR_POR_ID, query = "select familia from Familia familia where familia.id = :id"),
	@NamedQuery(name = Familia.LISTAR_TODOS, query = "select familia from Familia familia"),
	@NamedQuery(name = Familia.CONTAR_TODOS, query = "select count(f) from Familia f"),
	@NamedQuery(name = Familia.FAMILIA_MAS_ESPECIES, query = "select max(count(f)) from Familia f")
})
public class Familia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String BUSCAR_POR_ID = "Familia_findById";
	public static final String LISTAR_TODOS = "Familia_getAll";
	public static final String CONTAR_TODOS = "Familia_countAll";
	public static final String FAMILIA_MAS_ESPECIES = "Familia_getMaxEspecies";
	
	/**
	 * ID de la familia
	 */
	@Id
	@Column(unique = true, nullable = false)
	private String id;
	/**
	 * nombre de la familia
	 */
	@Column(unique = true, nullable = false, length = 50)
	private String nombre;
	/**
	 * generos de la familia
	 */
	@OneToMany(mappedBy="familia")
	private List<Genero> generos;
	/**
	 * constructor de la familia
	 */
	public Familia() {
		super();
		generos = new ArrayList<>();
	}
	/**
	 * get de la Id
	 * @return id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * set de la Id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Get del Nombre
	 * @return nombre
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * Set de nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Get de Generos
	 * @return list<Generos>
	 */
	public List<Genero> getGeneros() {
		return generos;
	}
	/**
	 * set de generos
	 * @param generos
	 */
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	/**
	 * agregar generos
	 * @param g
	 */
	public void agregarGenero(Genero g) {
		generos.add(g);
	}
	/**
	 * eliminar genero
	 * @param g
	 */
	public void eliminarGenero(Genero g) {
		generos.remove(g);
	}
	/**
	 * Obtener Genero
	 * @param i
	 */
	public void obtenerGenero(int i) {
		generos.get(i);
	}
}
