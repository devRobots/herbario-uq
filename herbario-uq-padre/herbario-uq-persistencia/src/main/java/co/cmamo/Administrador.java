package co.cmamo;

import co.cmamo.Persona;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 *
 */
@Entity
@NamedQueries({	
	@NamedQuery(name = Administrador.CONTAR_ADMINS, query = "select count(a) from Administrador a"),
	@NamedQuery(name = Administrador.ADMIN_POR_CORREO, query = "select admin from Administrador admin where admin.correo=:correo")
})
public class Administrador extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String CONTAR_ADMINS = "Administrador_ContarAdministradores";
	public static final String ADMIN_POR_CORREO = "Administrador_ObtenerAdminsPerCorreo";
	
	public Administrador() {
		super();
	}
}
