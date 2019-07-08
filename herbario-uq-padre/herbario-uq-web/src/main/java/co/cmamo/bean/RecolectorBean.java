package co.cmamo.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.cmamo.EstadoActividad;
import co.cmamo.Recolector;
import co.cmamo.ejbs.AdminEJB;
import co.cmamo.util.Util;

/**
 * Permite manejar todas las operaciones empleados
 * 
 * @author yesid Rosas && Cristian Quiceno
 * @version 1.0
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "recolectorBean")
@SessionScoped
public class RecolectorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Recolector recolector;
	
	private List<Recolector> recolectores;

	/**
	 * cedula del empleado
	 */
	private String cedula;
	/**
	 * nombre del empleado
	 */
	private String nombre;
	/**
	 * apellido del empleado
	 */
	private String apellido;
	/**
	 * clave del empleado
	 */
	private String clave;
	/**
	 * email del empleado
	 */
	private String email;

	/**
	 * conexión con la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * carga la lista de familias
	 */
	@PostConstruct
	private void init() {
		recolectores = adminEJB.listarRecolectores();
	}

	/**
	 * Metodo para agregar Recolector a la pase de datos
	 * @return
	 */
	public String agregarRecolector() {

		Recolector recolector = new Recolector();
		recolector.setId(cedula);
		recolector.setApellido(apellido);
		recolector.setNombre(nombre);
		recolector.setClave(clave);
		recolector.setCorreo(email);
		recolector.setEstado(EstadoActividad.ACTIVO);

		if (adminEJB.crearRecolector(recolector)) {			
			return "/empleado/recolector/recolectores";
		}

		return null;
	}	

	/**
	 * permite obtener el recolector que se desea eliminar
	 */
	public void eliminarRecolector() {
		try {
			adminEJB.eliminarRecolector(recolector.getId());
			recolectores = adminEJB.listarRecolectores();
			Util.mostrarMensaje("Eliminación exitosa!!!", "Eliminación exitosa!!!");
		} catch (Exception e) {
			Util.mostrarMensaje(e.getMessage(), e.getMessage());
		}
	}
	
	/**
	 * @return the recolector
	 */
	public Recolector getRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(Recolector recolector) {
		this.recolector = recolector;
	}

	/**
	 * @return the recolectores
	 */
	public List<Recolector> getRecolectores() {
		return recolectores;
	}

	/**
	 * @param recolectores the recolectores to set
	 */
	public void setRecolectores(List<Recolector> recolectores) {
		this.recolectores = recolectores;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
