package co.cmamo;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;
import javax.persistence.Column;

import co.cmamo.ejb.AdminEJB;
import co.cmamo.util.Util;

@FacesConfig(version = Version.JSF_2_3)
@Named("empleadoBean")
@ApplicationScoped
public class EmpleadoBean {
	/**
	 * Id del empleado
	 */
	private String id;
	/**
	 * Nombre del empleado
	 */
	@Column(length = 50)
	private String nombre;
	/**
	 * Apellido del empleado
	 */
	@Column(length = 50)
	private String apellido;
	/**
	 * Correo del empleado
	 */
	@Column(unique = true)
	private String correo;
	/**
	 * Clave de acceso al herbario
	 */
	@Column(length = 18)
	private String clave;
	/**
	 * Salario del empleado
	 */
	private double salario;
	
	@EJB
	private AdminEJB adminEJB;
	
	public EmpleadoBean() {
		
	}
	
	public String registrar() {
		try {
			Empleado e = new Empleado();
			e.setApellido(apellido);
			e.setClave(clave);
			e.setCorreo(correo);
			e.setEstado(EstadoActividad.ACTIVO);
			e.setId(id);
			e.setNombre(nombre);
			e.setSalario(salario);
			e.setPeticiones(null);
			
			adminEJB.crearEmpleado(e);
			
			return "Listo!!";
		} catch (Exception e) {
			Util.mostrarMensaje(e.getMessage(), e.getMessage());
			
			return null;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}


	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
}
