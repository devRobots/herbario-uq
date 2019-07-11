/**
 * 
 */
package co.cmamo.modelo;

import java.util.List;

import javax.naming.InitialContext;

import co.cmamo.Empleado;
import co.cmamo.Familia;
import co.cmamo.Genero;
import co.cmamo.Persona;
import co.cmamo.Peticion;
import co.cmamo.Planta;
import co.cmamo.Recolector;
import co.cmamo.ejbs.AdminEJB;
import co.cmamo.ejbs.AdminEJBRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Delegado que permite conectar la capa de negocio con la de presentacion
 * 
 * @author EinerZG
 * @version 1.0
 */
public class AdministradorDelegado {

	/**
	 * instancia que representa el ejb remoto de administrador
	 */
	private AdminEJBRemote adminEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static AdministradorDelegado administradorDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private AdministradorDelegado() {
		try {
			adminEJB = new AdminEJB();
			adminEJB = (AdminEJBRemote) new InitialContext().lookup(AdminEJBRemote.JNDI);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static AdministradorDelegado instancia() {

		if (administradorDelegado == null) {
			administradorDelegado = new AdministradorDelegado();
			return administradorDelegado;
		}
		return administradorDelegado;
	}
	
	public boolean iniciarSesion(String correo, String clave) {
		return adminEJB.iniciarSesion(correo, clave) == null ? false : true;
	}
	
	public Persona buscarPersonaPorCorreo(String correo) {
		return adminEJB.buscarPersonaPorCorreo(correo);
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param empleado empleado a agregar
	 * @return devuelve true si el empleado fue eliminado
	 */
	public boolean registrarEmpleado(Empleado empleado) {
		return adminEJB.crearEmpleado(empleado);
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Empleado> listarEmpleados() {
		return adminEJB.listarEmpleados();
	}

	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 */
	public boolean eliminarEmpleado(Empleado empleado) {
		return adminEJB.invalidarEmpleado(empleado.getId());
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param recolector empleado a agregar
	 * @return devuelve true si el empleado fue eliminado
	 */
	public boolean registrarRecolector(Recolector recolector) {
		return adminEJB.crearRecolector(recolector);
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Recolector> listarRecolectores() {
		return adminEJB.listarRecolectores();
	}

	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 */
	public boolean eliminarRecolector(Recolector recolector) {
		return adminEJB.invalidarRecolector(recolector.getId());
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param recolector empleado a agregar
	 * @return devuelve true si el empleado fue eliminado
	 */
	public Peticion registrarPeticion(Peticion peticion) {
		return adminEJB.crearPeticion(peticion);
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Peticion> listarPeticiones() {
		return adminEJB.listarPeticiones();
	}

	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 */
	public boolean eliminarPeticion(Peticion peticion) {
		return adminEJB.eliminarPeticion(peticion.getId());
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param recolector empleado a agregar
	 * @return devuelve true si el empleado fue eliminado
	 */
	public boolean registrarFamilia(Familia familia) {
		return adminEJB.crearFamilia(familia) == null ? false : true;
	}

	public Familia obtenerFamilia(String nombre) {
		return adminEJB.buscarFamilia(nombre);
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Familia> listarFamilias() {
		return adminEJB.listarFamilias();
	}

	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 */
	public boolean eliminarFamilia(Familia familia) {
		return adminEJB.eliminarFamilia(familia.getId());
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param recolector empleado a agregar
	 * @return devuelve true si el empleado fue eliminado
	 */
	public boolean registrarGenero(Genero genero) {
		return adminEJB.crearGenero(genero) == null ? false : true;
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Genero> listarGeneros(Familia familia) {
		return adminEJB.listarGeneros(familia);
	}

	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 */
	public boolean eliminarGenero(Genero genero) {
		return adminEJB.eliminarGenero(genero.getId());
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param recolector empleado a agregar
	 * @return devuelve true si el empleado fue eliminado
	 */
	public boolean registrarPlanta(Planta planta) {
		return adminEJB.crearPlanta(planta)!=null;
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Planta> listarPlantas() {
		return adminEJB.listarPlantas();
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Planta> listarPlantas(Familia familia) {
		return adminEJB.listarPlantas(familia);
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Planta> listarPlantas(Genero genero) {
		return adminEJB.listarPlantas(genero);
	}

	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 */
	public boolean eliminarPlanta(Planta planta) {
		return adminEJB.eliminarPlanta(planta.getId());
	}

	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 */
	public ObservableList<PersonaObservable> listarEmpleadosObservables() {
		List<Empleado> empleados = listarEmpleados();

		ObservableList<PersonaObservable> empleadosObservables = FXCollections.observableArrayList();

		for (Persona persona : empleados) {
			empleadosObservables.add(new PersonaObservable(persona));
		}

		return empleadosObservables;
	}

	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 */
	public ObservableList<PersonaObservable> listarRecolectoresObservables() {
		List<Recolector> recolectores = listarRecolectores();

		ObservableList<PersonaObservable> recolectoresObservables = FXCollections.observableArrayList();

		for (Persona persona : recolectores) {
			recolectoresObservables.add(new PersonaObservable(persona));
		}

		return recolectoresObservables;
	}

	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 */
	public ObservableList<TaxonomiaObservable> listarFamiliasObservables() {
		List<Familia> familias = listarFamilias();

		ObservableList<TaxonomiaObservable> familiasObservables = FXCollections.observableArrayList();

		for (Familia familia : familias) {
			familiasObservables.add(new TaxonomiaObservable(familia));
		}

		return familiasObservables;
	}

	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 */
	public ObservableList<TaxonomiaObservable> listarGenerosObservables(Familia familia) {
		List<Genero> generos = listarGeneros(familia);

		ObservableList<TaxonomiaObservable> generosObservables = FXCollections.observableArrayList();

		for (Genero genero : generos) {
			generosObservables.add(new TaxonomiaObservable(genero));
		}

		return generosObservables;
	}

	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 */
	public ObservableList<TaxonomiaObservable> listarEspeciesObservables(Familia familia) {
		List<Planta> especies = listarPlantas(familia);

		ObservableList<TaxonomiaObservable> especiesObservables = FXCollections.observableArrayList();

		for (Planta especie : especies) {
			especiesObservables.add(new TaxonomiaObservable(especie));
		}

		return especiesObservables;
	}

	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 */
	public ObservableList<TaxonomiaObservable> listarEspeciesObservables(Genero genero) {
		List<Planta> especies = listarPlantas(genero);

		ObservableList<TaxonomiaObservable> especiesObservables = FXCollections.observableArrayList();

		for (Planta especie : especies) {
			especiesObservables.add(new TaxonomiaObservable(especie));
		}

		return especiesObservables;
	}

	public ObservableList<PeticionObservable> listarPeticionesObservables() {
		List<Peticion> peticiones = listarPeticiones();

		ObservableList<PeticionObservable> peticionesObservables = FXCollections.observableArrayList();

		for (Peticion peticion : peticiones) {
			peticionesObservables.add(new PeticionObservable(peticion));
		}

		return peticionesObservables;
	}

	public void modificarPeticion(Peticion peticion) {
		adminEJB.modificarPeticion(peticion);
	}

	public boolean modificarEmpleado(Empleado empleado) {
		return adminEJB.modificarEmpleado(empleado);
	}

	public boolean modificarRecolector(Recolector recolector) {
		return adminEJB.modificarRecolector(recolector);
	}

	public boolean modificarFamilia(Familia familia) {
		return adminEJB.modificarFamilia(familia);
	}
	public boolean modificarGenero(Genero genero) {
		return adminEJB.modificarGenero(genero);
	}
	public boolean modificarPlanta(Planta planta) {
		return adminEJB.modificarPlanta(planta);
	}

}
