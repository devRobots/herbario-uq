package co.cmamo;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.cmamo.ejbs.AdminEJB;
import co.cmamo.util.Util;

@FacesConfig(version = Version.JSF_2_3)
@Named("seguridadBean")
@SessionScoped
public class SeguridadBean implements Serializable{
	/*
	 * Permite manejar la sesion de un suario en la app
	 */

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private boolean autenticado;
	/**
	 * 
	 */
	private Persona usuario;
	/**
	 * 
	 */
	@EJB
	private AdminEJB adminEJB;

	

	/*
	 * 
	 */
	@PostConstruct
	private void init() {
		usuario = new Persona();
		autenticado = false;
	}

	/**
	 * 
	 */
	public void iniciarSesion() {
		Persona u = adminEJB.iniciarSesion(usuario.getCorreo(), usuario.getClave());
		if (u != null) {
			usuario = u;
			autenticado = true;
		} else {
			Util.mostrarMensaje("Valide sus Credenciales", "Valide sus Credenciales");
		}
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
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * retorna Usuario
	 * @return
	 */
	public Persona getUsuario() {
		return usuario;
	}
}
