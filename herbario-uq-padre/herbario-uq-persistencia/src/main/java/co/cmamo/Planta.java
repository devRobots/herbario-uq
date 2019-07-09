package co.cmamo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import javafx.scene.image.Image;

/**
 * Entity implementation class for Entity: Planta
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Planta.BUSCAR_POR_ID, query = "select planta from Planta planta where planta.id = :id"),
	@NamedQuery(name = Planta.LISTAR_TODOS, query = "select planta from Planta planta"),
	@NamedQuery(name = Planta.LISTAR_POR_GENERO, query = "select planta from Planta planta where planta.genero.id =:genero"),
	@NamedQuery(name = Planta.LISTAR_POR_FAMILIA, query = "select planta from Planta planta where planta.genero.familia.id =:familia"),
	@NamedQuery(name = Planta.OBTENER_FAMILIA, query = "select planta.genero.familia from Planta planta where planta.id =:id")
})
public class Planta implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * LLamado para los queries
	 */
	public static final String BUSCAR_POR_ID = "Planta_findById";
	public static final String LISTAR_TODOS = "Planta_getAll";
	public static final String LISTAR_POR_GENERO = "Planta_getGenero";
	public static final String LISTAR_POR_FAMILIA = "Planta_getFamilia";
	
	public static final String OBTENER_FAMILIA = "Platna_getFamiliaRelacionada";
	
	/**
	 * Id de la Planta
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
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
	 * imagen de la Planta
	 */
	@Lob
	private byte[] imagen;
	
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
	public long getId() {
		return this.id;
	}
	/**
	 * Set de la Id
	 * @param id
	 */
	public void setId(long id) {
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
	/**
	 * Get imagen
	 * @return imagen
	 */
	public byte[] getImagen(){
		return imagen;
	}
	/**
	 * set imagen
	 * @param imagen
	 */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * Set imagen con File
	 * @param file
	 */
	private void setImagenConFile(File file) {
		//Obtiene el tamanio
		long tamanio = file.length();
		
		byte[] bytes = new byte[(int)tamanio];
		
		try {
			InputStream is = new FileInputStream(file);
			int numRead = 0;
			numRead = is.read(bytes);
			is.close();
			
		} catch (Exception e) {
			
		}
		
		this.imagen = bytes;
	}
	
	
}
