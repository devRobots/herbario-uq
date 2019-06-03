package co.cmamo;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

@FacesConfig(version = Version.JSF_2_3)
@Named("cindexBean")
@ApplicationScoped
public class IndexBean {
	private String correo;
	private String clave;
	
	public IndexBean() {
	
	}
	
	public boolean verificar() {
		if (correo.equals("ysrosast_1@uqvirtual.edu.co")) {
			if (clave.equals("12345")) {
				return true;
			}
		}
		
		return false;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
