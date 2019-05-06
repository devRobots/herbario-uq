package co.cmamo;

import co.cmamo.Persona;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Recolector
 *
 */
@Entity

public class Recolector extends Persona implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String id;
	
	public Recolector() {
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
