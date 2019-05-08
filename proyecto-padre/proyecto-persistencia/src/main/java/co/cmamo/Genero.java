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

public class Genero implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String nombre;
	@ManyToOne
	private Familia familia;
	@OneToMany
	private List<Planta> especies;

	public Genero() {
		super();
		especies = new ArrayList<>();
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

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public List<Planta> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Planta> especies) {
		this.especies = especies;
	}

	public void agregarEspecie(Planta g) {
		especies.add(g);
	}

	public void eliminarEspecie(Planta g) {
		especies.remove(g);
	}

	public void obtenerEspecie(int i) {
		especies.get(i);
	}
}
