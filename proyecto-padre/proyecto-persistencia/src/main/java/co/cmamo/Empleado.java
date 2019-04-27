package co.cmamo;

import co.cmamo.Persona;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Empleado
 *
 */
@Entity

public class Empleado extends Persona implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Empleado() {
		super();
	}
   
}
