package co.cmamo.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Util {
	public static void mostrarMensaje(String titulo, String mensaje) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}
}
