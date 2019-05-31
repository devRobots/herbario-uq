package co.cmamo;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

@FacesConfig(version = Version.JSF_2_3)
@Named("cambioBean")
@ApplicationScoped
public class CambioBean {
	
	private String atributo1;
	private String atributo2;
	
	public CambioBean() {
		super();
	}

	public void cambiar() {
		String temp = atributo1;
		atributo1 = atributo2;
		atributo2 = temp;
	}

	/**
	 * @return the atributo1
	 */
	public String getAtributo1() {
		return atributo1;
	}

	/**
	 * @param atributo1 the atributo1 to set
	 */
	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}

	/**
	 * @return the atributo2
	 */
	public String getAtributo2() {
		return atributo2;
	}

	/**
	 * @param atributo2 the atributo2 to set
	 */
	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}
}
