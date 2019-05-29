package co.cmamo;

import co.cmamo.Persona;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 *
 */
@Entity
@NamedQuery(name = Administrador.CONTAR_ADMINS, query = "select count(a) from Administrador a")
public class Administrador extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String CONTAR_ADMINS = "Administrador_ContarAdministradores";

	public Administrador() {
		super();
	}
}
