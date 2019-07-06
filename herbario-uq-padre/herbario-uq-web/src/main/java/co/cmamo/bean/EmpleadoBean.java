package co.cmamo.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.cmamo.Empleado;
import co.cmamo.EstadoActividad;
import co.cmamo.ejbs.AdminEJB;

/**
 * Permite manejar todas las operaciones empleados
 * 
 * @author EinerZG
 * @version 1.0
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "empleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

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
	 * salario del empleado
	 */
	private double salario;

	/**
	 * conexión con la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	public String agregarEmpleado() {

		Empleado empleado = new Empleado();
		empleado.setId(cedula);
		empleado.setApellido(apellido);
		empleado.setNombre(nombre);
		empleado.setClave(clave);
		empleado.setCorreo(email);
		empleado.setSalario(2400000);
		empleado.setEstado(EstadoActividad.ACTIVO);

		if (adminEJB.crearEmpleado(empleado)) {			
			return "/index";
		}

		return null;
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

	/**
	 * @return the salario
	 */
	public double getSalario() {
		return salario;
	}

	/**
	 * @param salario the salario to set
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}

}
