package co.cmamo;

import co.cmamo.Persona;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 *
 */
@Entity

public class Administrador extends Persona implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String id;

	public Administrador() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
   
}
