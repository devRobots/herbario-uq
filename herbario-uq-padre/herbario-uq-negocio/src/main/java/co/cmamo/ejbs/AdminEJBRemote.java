package co.cmamo.ejbs;

import java.util.List;

import javax.ejb.Remote;

import co.cmamo.*;

@Remote
public interface AdminEJBRemote {
	
	public static final String JNDI = "java:global/herbario-uq-ear/herbario-uq-negocio/AdminEJB!co.cmamo.ejbs.AdminEJB, java:global/herbario-uq-ear/herbario-uq-negocio/AdminEJB!co.cmamo.ejbs.AdminEJBRemote";

	/**
	 * Registra un empleado en la BD si es posible
	 * @param empleado Empleado que se quiere registrar
	 * @return boolean Si se registro o no
	 */
	public boolean crearEmpleado(Empleado empleado);
	
	/**
	 * Modifica un empleado en la BD si es posible
	 * @param mod	Empleado con que se va a modificar
	 * @return boolean Si se modifico o no
	 */
	public boolean modificarEmpleado(Empleado mod);
	
	/**
	 * Buscar un empleado en la BD si es posible
	 * @param id Criterio de busqueda del Empleado
	 * @return empleado Empleado que se buscaba o null sino existe
	 */
	public Empleado buscarEmpleado(String id);

	/**
	 * Lista todos los empleados en la BD
	 * @return List<Empleado> Empleados que se encontraron
	 */
	public List<Empleado> listarEmpleados();

	/**
	 * Invalida un empleado en la BD si es posible
	 * @param id ID del empleado que se quiere invalidar
	 * @return boolean Si se invalido o no
	 */
	public boolean invalidarEmpleado(String id);

	/**
	 * Registra un recolector en la BD si es posible
	 * @param empleado Recolector que se quiere registrar
	 * @return boolean Si se registro o no
	 */
	public boolean crearRecolector(Recolector recolector);
	
	/**
	 * Modifica un recolector en la BD si es posible
	 * @param mod	Recolector con que se va a modificar
	 * @return boolean Si se modifico o no
	 */
	public boolean modificarRecolector(Recolector mod);
	
	/**
	 * Buscar un recolector en la BD si es posible
	 * @param id Criterio de busqueda del Recolector
	 * @return recolector Recolector que se buscaba o null sino existe
	 */
	public Recolector buscarRecolector(String id);

	/**
	 * Lista todos los recolectores en la BD
	 * @return List<Recolector> Recolectores que se encontraron
	 */
	public List<Recolector> listarRecolectores();

	/**
	 * Invalida un recolector en la BD si es posible
	 * @param id ID del recolector que se quiere invalidar
	 * @return boolean Si se invalido o no
	 */
	public boolean invalidarRecolector(String id);

	/**
	 * Registra un familia en la BD si es posible
	 * @param familia Familia que se quiere registrar
	 * @return boolean Si se registro o no
	 */
	public boolean crearFamilia(Familia familia);

	/**
	 * Modifica un familia en la BD si es posible
	 * @param mod	Familia con que se va a modificar
	 * @return boolean Si se modifico o no
	 */
	public boolean modificarFamilia(Familia mod);

	/**
	 * Buscar un familia en la BD si es posible
	 * @param id Criterio de busqueda de la familia
	 * @return familia Familia que se buscaba o null sino existe
	 */
	public Familia buscarFamilia(String id);

	/**
	 * Lista todos las familias en la BD
	 * @return List<Familia> Familias que se encontraron
	 */
	public List<Familia> listarFamilias();

	/**
	 * Invalida una familia en la BD si es posible
	 * @param id ID de la familia que se quiere invalidar
	 * @return boolean Si se elimino o no
	 */
	public boolean eliminarFamilia(String id);

	/**
	 * Registra un genero en la BD si es posible
	 * @param genero Genero que se quiere registrar
	 * @return boolean Si se registro o no
	 */
	public boolean crearGenero(Genero genero);
	
	/**
	 * Modifica un genero en la BD si es posible
	 * @param mod	Genero con que se va a modificar
	 * @return boolean Si se modifico o no
	 */
	public boolean modificarGenero(Genero mod);
	
	/**
	 * Buscar un genero en la BD si es posible
	 * @param id Criterio de busqueda del Genero
	 * @return genero Genero que se buscaba o null sino existe
	 */
	public Genero buscarGenero(String id);

	/**
	 * Lista todos los generos en la BD
	 * @return List<Genero> Generos que se encontraron
	 */
	public List<Genero> listarGeneros();

	/**
	 * Invalida un genero en la BD si es posible
	 * @param id ID del genero que se quiere invalidar
	 * @return boolean Si se elimino o no
	 */
	public boolean eliminarGenero(String id);

	/**
	 * Registra una planta en la BD si es posible
	 * @param planta Planta que se quiere registrar
	 * @return boolean Si se registro o no
	 */
	public boolean crearPlanta(Planta planta);
	
	/**
	 * Buscar un planta en la BD si es posible
	 * @param id Criterio de busqueda de la Planta
	 * @return planta Planta que se buscaba o null sino existe
	 */
	public Planta buscarPlanta(String id);

	/**
	 * Lista todos las plantas en la BD
	 * @return List<Planta> Plantas que se encontraron
	 */
	public List<Planta> listarPlantas();

}