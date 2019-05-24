package co.cmamo.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.cmamo.*;

@Remote
public interface AdminEJBRemote {
	
	String JNDI = "";

	/**
	 * Registra un empleado en la BD si es posible
	 * @param empleado Empleado que se quiere registrar
	 * @return boolean Si se registro o no
	 */
	public boolean crearEmpleado(Empleado empleado);
	
	/**
	 * Modifica un empleado en la BD si es posible
	 * @param anterior Empleado que se busca modificar
	 * @param nuevo	Empleado con que se va a modificar
	 * @return boolean Si se modifico o no
	 */
	public boolean modificarEmpleado(Empleado anterior, Empleado nuevo);
	
	/**
	 * Buscar un empleado en la BD si es posible
	 * @param cosa Criterio de busqueda del Empleado
	 * @return empleado Empleado que se buscaba o null sino existe
	 */
	public Empleado buscarEmpleado(String cosa);

	/**
	 * Lista todos los empleados en la BD
	 * @return List<Empleado> Empleados que se encontraron
	 */
	public List<Empleado> listarEmpleados();

	/**
	 * Invalida un empleado en la BD si es posible
	 * @param empleado Empleado que se quiere invalidar
	 * @return boolean Si se invalido o no
	 */
	public boolean invalidarEmpleado(Empleado empleado);
	/**
	 * Registra un recolector en la BD si es posible
	 * @param empleado Recolector que se quiere registrar
	 * @return boolean Si se registro o no
	 */
	public boolean crearRecolector(Recolector recolector);
	
	/**
	 * Modifica un recolector en la BD si es posible
	 * @param anterior Recolector que se busca modificar
	 * @param nuevo	Recolector con que se va a modificar
	 * @return boolean Si se modifico o no
	 */
	public boolean modificarRecolector(Recolector anterior, Recolector nuevo);
	
	/**
	 * Buscar un recolector en la BD si es posible
	 * @param cosa Criterio de busqueda del Recolector
	 * @return recolector Recolector que se buscaba o null sino existe
	 */
	public Recolector buscarRecolector(String cosa);

	/**
	 * Lista todos los recolectores en la BD
	 * @return List<Recolector> Recolectores que se encontraron
	 */
	public List<Recolector> listarRecolectores();

	/**
	 * Invalida un recolector en la BD si es posible
	 * @param recolector Recolector que se quiere invalidar
	 * @return boolean Si se invalido o no
	 */
	public boolean invalidarRecolector(Recolector recolector);

}