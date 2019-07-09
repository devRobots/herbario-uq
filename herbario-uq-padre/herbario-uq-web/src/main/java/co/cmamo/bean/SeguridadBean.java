package co.cmamo.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.cmamo.ejbs.AdminEJB;
import co.cmamo.Administrador;
import co.cmamo.Empleado;
import co.cmamo.Persona;
import co.cmamo.Recolector;
import co.cmamo.util.Util;

/**
 * Permite manejar la sesión de la aplicacion
 * 
 * @author Yesid Rosas
 * @author Camilo Quiceno
 * @version 1.0
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("seguridadBean")
@SessionScoped
public class SeguridadBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * usuario que tiene iniciada la sesion
	 */
	private Persona usuario;
	/**
	 * determina si esta autenticado o no
	 */
	private boolean autenticado;
	/**
	 * determina si esta autenticado o no
	 */
	private boolean admin;
	/**
	 * determina si esta autenticado o no
	 */
	private boolean empleado;
	/**
	 * determina si esta autenticado o no
	 */
	private boolean recolector;
	/**
	 * permite consultar la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * inicializa la info basica para el manejo de la sesion
	 */
	@PostConstruct
	private void init() {
		usuario = new Persona();
	}

	/**
	 * permite iniciar sesión
	 */
	public void iniciarSesion() {

		Persona u = adminEJB.iniciarSesion(usuario.getCorreo(), usuario.getClave());

		if (u == null) {
			Util.mostrarMensaje("No se pudo iniciar sesion verifique sus credenciales",
					"No se pudo iniciar sesion verifique sus credenciales");
		} else {
			if (u instanceof Administrador) {
				admin = true;
			}
			else if (u instanceof Empleado) {
				empleado = true;
			}
			else if (u instanceof Recolector) {
				recolector = true;
			}
			
			usuario = u;
			autenticado = true;
		}
	}

	/**
	 * permite cerrar sesion
	 */
	public void cerrarSesion() {
		if (usuario != null) {
			usuario = null;
			autenticado = false;
			admin = false;
			empleado = false;
			recolector = false;
			init();
		}
	}
	
	/**
	 * navegación del menu principal
	 * @param i 
	 * @return
	 */
	public String navegacionEspecies(int i) {
		switch (i) {
		case 1:
			return "/admin/familia/familias";
		case 2:
			return "/admin/familia/registrar_familia";
		case 3:
			return "/admin/genero/generos";
		case 4:
			return "/admin/genero/registrar_genero";
		case 5:
			return "/recolector/especies";
		default:
			return null;
		}
	}
	
	/**
	 * navegación del menu principal
	 * @param i 
	 * @return
	 */
	public String navegacionRecolector(int i) {
		switch (i) {
		case 1:
			return "/empleado/recolector/recolectores";
		case 2:
			return "/empleado/recolector/registrar_recolector";
		default:
			return null;
		}
	}
	
	/**
	 * navegación del menu principal
	 * @param i 
	 * @return
	 */
	public String navegacionEmpleado(int i) {
		switch (i) {
		case 1:
			return "/admin/empleado/empleados";
		case 2:
			return "/admin/empleado/registrar_empleado";
		default:
			return null;
		}
	}
	
	/**
	 * navegación del menu principal
	 * @param i 
	 * @return
	 */
	public String navegacionPeticion(int i) {
		switch (i) {
		case 1:
			return "/recolector/peticion/registrar_especie";
		case 2:
			return "/recolector/peticion/peticiones_todas";
		case 3:
			return "/recolector/peticion/peticiones_pendientes";
		case 4:
			return "/recolector/peticion/peticiones_aceptadas";
		case 5:
			return "/recolector/peticion/peticiones_rechazadas";
		default:
			return null;
		}
	}

	/**
	 * @return the usuario
	 */
	public Persona getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the autenticado
	 */
	public boolean isAutenticado() {
		return autenticado;
	}

	/**
	 * @param autenticado the autenticado to set
	 */
	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * @return the empleado
	 */
	public boolean isEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(boolean empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the recolector
	 */
	public boolean isRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(boolean recolector) {
		this.recolector = recolector;
	}
}
