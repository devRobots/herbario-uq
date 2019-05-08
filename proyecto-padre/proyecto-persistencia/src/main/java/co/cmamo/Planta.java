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

	@Id
	private String id;
	private String especie;
	@ManyToOne
	private Genero genero;

	public Planta() {
		super();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEspecie() {
		return this.especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
}
