package co.cmamo;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.cmamo.ejbs.AdminEJB;

@FacesConfig(version = Version.JSF_2_3)
@Named("generoDosBean")
@ApplicationScoped
public class GeneroDosBean {

	/**
	 * 
	 */
	private String nombre;
	/**
	 * 
	 */
	private Familia familia;
	/**
	 * lista de familias disponibles
	 */
	private List<Familia> familias;
	/**
	 * Referencia la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * Inicia los elementos necesarios en el bean
	 */
	@PostConstruct
	private void init() {
		familias = adminEJB.listarFamilias();
	}

	/**
	 * Carga los datos para hacer el registro de un genero en la capa de negocio
	 * 
	 * @return
	 */
	public String registrar() {
		try {
			Genero genero = new Genero();
			genero.setNombre(nombre);
			genero.setFamilia(familia);

			adminEJB.crearGenero(genero);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */

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
	 * @return the familia
	 */
	public Familia getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	/**
	 * @return the familias
	 */
	public List<Familia> getFamilias() {
		return familias;
	}

	/**
	 * @param familias the familias to set
	 */
	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
	}
}
