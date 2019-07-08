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
import co.cmamo.util.Util;

/**
 * Permite realizar una todas las operaciones para gestionar una genero
 * 
 * @author  yesid Rosas && Cristian Quiceno
 * @version 1.0
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "generoBean")
@ApplicationScoped
public class GeneroBean implements Serializable {

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
	 * familia asociada al genero
	 */
	private Familia familia;
	/**
	 * Genero seleccionado en la lista
	 */
	private Genero genero;

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
	}

	/**
	 * Registra un genero
	 * 
	 * @return navega a la lista de generos
	 */
	public String registrar() {
		try {

			Genero g = new Genero();
			g.setNombre(nombre);
			g.setFamilia(familia);
			g = adminEJB.crearGenero(g);
			
			genero = g;
			
			generos = adminEJB.listarGeneros();

			return "/admin/genero/generos";
			
		} catch (Exception e) {
			Util.mostrarMensaje(e.getMessage(), e.getMessage());
			return null;
		}
	}

	/**
	 * permite obtener la familia que se desea eliminar
	 */
	public void eliminarGenero() {
		try {
			adminEJB.eliminarGenero(genero.getId());
			generos = adminEJB.listarGeneros();
			Util.mostrarMensaje("Eliminación exitosa!!!", "Eliminación exitosa!!!");
		} catch (Exception e) {
			Util.mostrarMensaje(e.getMessage(), e.getMessage());
		}
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
		Util.mostrarMensaje("get", "get");
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Familia familia) {
		Util.mostrarMensaje("set", "set");
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

}
