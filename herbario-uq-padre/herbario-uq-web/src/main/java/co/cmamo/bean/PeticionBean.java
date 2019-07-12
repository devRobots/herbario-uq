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
import co.cmamo.util.Util;
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
@Named(value = "peticionBean")
@ApplicationScoped
public class PeticionBean implements Serializable {

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
	 * Lista de Peticiones
	 */
	private List<Peticion> todas;
	/**
	 * Lista de Peticiones
	 */
	private List<Peticion> aceptadas;
	/**
	 * Lista de Peticiones
	 */
	private List<Peticion> rechazadas;
	/**
	 * Lista de Peticiones
	 */
	private List<Peticion> pendientes;
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
	 * Peticion
	 */
	private Peticion peticion;
	/**
	 * Peticion
	 */
	private Peticion aceptada;
	/**
	 * Peticion
	 */
	private Peticion rechazada;
	/**
	 * Peticion
	 */
	private Peticion pendiente;
	/**
	 * @return the peticion
	 */

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
		todas = adminEJB.listarPeticiones();
		aceptadas = adminEJB.listarPeticiones(EstadoPeticion.APROBADO);
		rechazadas = adminEJB.listarPeticiones(EstadoPeticion.RECHAZADO);
		pendientes = adminEJB.listarPeticiones(EstadoPeticion.PENDIENTE);
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
		
		peticion = null;
		peticion = new Peticion();
		peticion.setEstado(EstadoPeticion.PENDIENTE);
		peticion.setFecha(new Date());
		
		//Se crea Planta
		especie = new Planta();
		especie.setEspecie(nombre);
		especie = adminEJB.crearPlanta(especie);
		peticion.setEspecie(especie);
		
		//Se crea Solicitante
		Persona solicitante = new Persona();
		solicitante = adminEJB.buscarEmpleado(id_solicitante);
		peticion.setSolicitante(solicitante);
		
		//se crea la peticion
		peticion = adminEJB.crearPeticion(peticion);
		
		return "/recolector/peticion/peticiones_todas";
	}
	

	
	/**
	 * permite obtener la familia que se desea eliminar
	 */
	public void eliminarPeticion() {
		try {
			adminEJB.eliminarPeticion(peticion.getId());
			todas = adminEJB.listarPeticiones();
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
	public Peticion getPeticion() {
		return peticion;
	}

	/**
	 * @param peticion the peticion to set
	 */
	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
	}

	public List<Peticion> getTodas() {
		todas = adminEJB.listarPeticiones();
		return todas;
	}

	public void setTodas(List<Peticion> todas) {
		this.todas = todas;
	}

	public List<Peticion> getRechazadas() {
		pendientes = adminEJB.listarPeticiones(EstadoPeticion.RECHAZADO);
		return rechazadas;
	}

	public void setRechazadas(List<Peticion> rechazadas) {
		this.rechazadas = rechazadas;
	}

	public List<Peticion> getPendientes() {
		pendientes = adminEJB.listarPeticiones(EstadoPeticion.PENDIENTE);
		return pendientes;
	}

	public void setPendientes(List<Peticion> pendientes) {
		this.pendientes = pendientes;
	}

	public List<Peticion> getAceptadas() {
		aceptadas = adminEJB.listarPeticiones(EstadoPeticion.APROBADO);
		return aceptadas;
	}

	public void setAceptadas(List<Peticion> aceptadas) {
		this.aceptadas = aceptadas;
	}

	/**
	 * @return the aceptada
	 */
	public Peticion getAceptada() {
		return aceptada;
	}

	/**
	 * @param aceptada the aceptada to set
	 */
	public void setAceptada(Peticion aceptada) {
		this.aceptada = aceptada;
	}

	/**
	 * @return the rechazada
	 */
	public Peticion getRechazada() {
		return rechazada;
	}

	/**
	 * @param rechazada the rechazada to set
	 */
	public void setRechazada(Peticion rechazada) {
		this.rechazada = rechazada;
	}

	/**
	 * @return the pendiente
	 */
	public Peticion getPendiente() {
		return pendiente;
	}

	/**
	 * @param pendiente the pendiente to set
	 */
	public void setPendiente(Peticion pendiente) {
		this.pendiente = pendiente;
	} 
	

}
