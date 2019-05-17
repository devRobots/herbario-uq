package co.cmamo.ejb;

import javax.ejb.Remote;

import co.cmamo.Empleado;
import co.cmamo.excepciones.ElementoRepetidoExcepcion;

@Remote
public interface AdminEJBRemote {

	/**
	 * Registra un empleado en la BD de ser posible
	 * @param empleado Empleado que se quiere registrar
	 * @return Empleado registrado o Null sino se pudo registrar
	 * @throws ElementoRepetidoExcepcion
	 */
	public Empleado registrarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion;
	
}
