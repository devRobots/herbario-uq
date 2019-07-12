package co.cmamo.bean;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.cmamo.ejbs.AdminEJB;
import co.cmamo.Persona;

@FacesConfig(version = Version.JSF_2_3)
@Named("loginBean")
@ApplicationScoped
public class LoginBean {

	/**
	 * Conexion a la Capa negocio
	 */
	@EJB
	private AdminEJB adminEJB;
	/**
	 * Contrasenia del correo
	 */
	private String contrasenia;
	/**
	 * Correo de la persona
	 */
	String email;

	public String ingresar() {

		Persona p = new Persona();
		p = adminEJB.buscarPersonaPorCorreo(email);

		System.out.println(p.getNombre());
		if (p != null) {
			String tipo = p.getClass().getSimpleName();

			switch (tipo) {
			
			case "Administrador":

				return "/sesion_admininistrador";

			case "Empleado":

				return "/sesion_empleado";
			case "Recolector":

				return "/sesion_recolector";
			case "Usuario":

				return "/sesion_usuario";

			default:

				return "";
			}

		}
		return "";

	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "LoginBean [toString()=" + "loginBean" + "]";
	}

}