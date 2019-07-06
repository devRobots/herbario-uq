package co.cmamo.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.cmamo.ejbs.AdminEJB;
import co.cmamo.Familia;
import co.cmamo.Genero;
import co.cmamo.Planta;

/**
 * Permite realizar una todas las operaciones para gestionar una genero
 * 
 * @author EinerZG
 * @version 1.0
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "especieBean")
@ApplicationScoped
public class EspecieBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * nombre del genero a registrar
	 */
	private String nombre;
	/**
	 * lista de familias existentes
	 */
	private List<Familia> familias;
	/**
	 * lista de generos existentes
	 */
	private List<Genero> generos;
	/**
	 * lista de generos existentes
	 */
	private List<Planta> especies;
	/**
	 * familia asociada al genero
	 */
	private Familia familia;
	/**
	 * Genero seleccionado en la lista
	 */
	private Genero genero;
	/**
	 * Genero seleccionado en la lista
	 */
	private Planta especie;

	/**
	 * Conexión con la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * inicializa la lista de familias
	 */
	@PostConstruct
	private void inicializar() {
		familias = adminEJB.listarFamilias();
		generos = adminEJB.listarGeneros();
		especies = adminEJB.listarPlantas();
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
	 * @return the familias
	 */
	public List<Familia> getFamilias() {
		familias = adminEJB.listarFamilias();
		return familias;
	}

	/**
	 * @param familias the familias to set
	 */
	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
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
	 * @return the generos
	 */
	public List<Genero> getGeneros() {
		generos = adminEJB.listarGeneros();
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	/**
	 * @return the generos
	 */
	public List<Planta> getEspecies() {
		especies = adminEJB.listarPlantas();
		return especies;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setEspecies(List<Planta> especies) {
		this.especies = especies;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	/**
	 * @return the especie
	 */
	public Planta getEspecie() {
		return especie;
	}

	/**
	 * @param especie the especie to set
	 */
	public void setEspecie(Planta especie) {
		this.especie = especie;
	}

}
