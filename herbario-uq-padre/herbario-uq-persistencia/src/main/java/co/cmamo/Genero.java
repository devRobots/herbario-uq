package co.cmamo;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Genero
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Genero.BUSCAR_POR_ID, query = "select genero from Genero genero where genero.id = :id"),
	@NamedQuery(name = Genero.LISTAR_TODOS, query = "select genero from Genero genero"),
	@NamedQuery(name = Genero.LISTAR_TODOS_POR_FAMILIA, query = "select genero from Genero genero where genero.familia.id=:familia"),
	@NamedQuery(name = Genero.OBTENER_TODAS_LAS_PLANTAS, query ="select planta from Genero genero, IN(genero.especies) planta where genero.id = :id")
	})
public class Genero implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String BUSCAR_POR_ID = "Genero_findById";
	public static final String LISTAR_TODOS = "Genero_getAll";
	public static final String LISTAR_TODOS_POR_FAMILIA = "Genero_getAllPerFamilia";
	public static final String OBTENER_TODAS_LAS_PLANTAS = "Genero_getAllPlantas";
	
	/**
	 * Id del Genero
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	/**
	 * nombre del Genero
	 */
	@Column(unique = true, nullable = false, length = 50)
	private String nombre;
	/**
	 * Familia del Genero
	 */
	@ManyToOne
	private Familia familia;
	/**
	 * Especies de la Planta
	 */
	@OneToMany(mappedBy="genero")
	private List<Planta> especies;

	/**
	 * COnstuctor de la Planta
	 */
	public Genero() {
		super();
		especies = new ArrayList<>();
	}
	/**
	 * Get id
	 * @return id
	 */
	public long getId() {
		return this.id;
	}
	/**
	 * Set Id
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * get del Nombre
	 * @return nombre
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * set del Nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * get familia
	 * @return familia
	 */
	public Familia getFamilia() {
		return familia;
	}
	/**
	 * Set familia
	 * @param familia
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	/**
	 * Get especies
	 * @return especies
	 */
	public List<Planta> getEspecies() {
		return especies;
	}
	/**
	 * set Especies
	 * @param especies
	 */
	public void setEspecies(List<Planta> especies) {
		this.especies = especies;
	}
	/**
	 * Agregar especie
	 * @param planta g
	 */
	public void agregarEspecie(Planta g) {
		especies.add(g);
	}
	/**
	 * Eliminar Especie
	 * @param g
	 */
	public void eliminarEspecie(Planta g) {
		especies.remove(g);
	}
	/**
	 * Obtener especie
	 * @param i
	 */
	public void obtenerEspecie(int i) {
		especies.get(i);
	}
}
