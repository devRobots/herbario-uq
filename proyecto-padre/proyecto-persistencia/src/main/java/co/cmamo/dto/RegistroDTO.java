package co.cmamo.dto;

import co.cmamo.Planta;

public class RegistroDTO {
	/**
	 * Atributos del RegistroDto 
	 */
	/**
	 * Id de la Peticion del registro
	 */
	private String peticionId;
	/**
	 * Planta del registro
	 */
	private Planta planta;
	/**
	 * id de la persona del Registro
	 */
	private String persona_id;
	/**
	 * correo del registro
	 */
	private String correo;
	/**
	 * Constructor del RegistroDtO
	 * @param peticionId
	 * @param planta
	 * @param persona_id
	 * @param correo
	 */
	public RegistroDTO(String peticionId, Planta planta, String persona_id, String correo) {
		super();
		this.peticionId = peticionId;
		this.planta = planta;
		this.persona_id = persona_id;
		this.correo = correo;
	}
	/**
	 * @return the peticionId
	 */
	public String getPeticionId() {
		return peticionId;
	}
	/**
	 * @param peticionId the peticionId to set
	 */
	public void setPeticionId(String peticionId) {
		this.peticionId = peticionId;
	}
	/**
	 * @return the planta
	 */
	public Planta getPlanta() {
		return planta;
	}
	/**
	 * @param planta the planta to set
	 */
	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
	/**
	 * @return the persona_id
	 */
	public String getPersona_id() {
		return persona_id;
	}
	/**
	 * @param persona_id the persona_id to set
	 */
	public void setPersona_id(String persona_id) {
		this.persona_id = persona_id;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

	
}
