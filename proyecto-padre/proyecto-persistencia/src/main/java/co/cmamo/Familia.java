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
public class Familia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String nombre;
	@OneToMany
	private List<Genero> generos;

	public Familia() {
		super();
		generos = new ArrayList<>();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public void agregarGenero(Genero g) {
		generos.add(g);
	}

	public void eliminarGenero(Genero g) {
		generos.remove(g);
	}

	public void obtenerGenero(int i) {
		generos.get(i);
	}
}
