package co.cmamo;

import co.cmamo.Persona;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Recolector
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Recolector.LISTAR_RECOLECTOR, query = "select r from Recolector r")
})
public class Recolector extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String LISTAR_RECOLECTOR = "Recolector_ListarTodos";
	
	/**
	 * Constructor recolector
	 */
	public Recolector() {
		super();
	}
}
