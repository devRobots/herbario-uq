package co.cmamo.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.cmamo.ejbs.AdminEJB;
import co.cmamo.EstadoPeticion;
import co.cmamo.Familia;
import co.cmamo.Genero;
import co.cmamo.Persona;
import co.cmamo.Peticion;
import co.cmamo.Planta;

/**
 * Permite realizar una todas las operaciones para gestionar una genero
 * 
 * @author Yesid Rosas && Cristian Quiceno
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
	 * Imagen de la especie a registrar
	 */
	private byte[] imagen;
	/**
	 * nombre del genero a registrar
	 */
	private String id_solicitante;
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
	 * permite invocar la capa de negocio para registrar una planta en
	 * la base de datos
	 * 
	 * @return nombre de la página con información de las plantas
	 */
	public String crearPeticion() {
		Peticion peticion = new Peticion();
		Planta p = new Planta();
		p.setGenero(genero);
		p.setEspecie(nombre);
		p.setImagen(imagen);
		
		peticion.setEspecie(p);
		peticion.setEstado(EstadoPeticion.PENDIENTE);
		peticion.setFecha(new Date());
		
		Persona solicitante = adminEJB.buscarEmpleado(id_solicitante);
		peticion.setSolicitante(solicitante);
		
		return "/admin/especie/especies";
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
	
	/**
	 * Get Id del solicitante
	 * @return
	 */
	public String getId_solicitante() {
		return id_solicitante;
	}

	/**
	 * Set Id del solicitante
	 * @param id_solicitante
	 */
	public void setId_solicitante(String id_solicitante) {
		this.id_solicitante = id_solicitante;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(File imagen) {
		//Obtiene el tamanio
		long tamanio = imagen.length();
		
		byte[] bytes = new byte[(int)tamanio];
			
		try {
			InputStream is = new FileInputStream(imagen);
			int numRead = 0;
			numRead = is.read(bytes);
			is.close();
		} 
		catch (Exception e) {
			
		}
		this.imagen = bytes;
	}

}
