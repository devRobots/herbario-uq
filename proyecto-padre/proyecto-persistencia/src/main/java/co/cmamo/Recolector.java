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
	/**
	 * Constructor recolector
	 */
	public Recolector() {
		super();
	}
}
