package co.cmamo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Informacion basica de cada una de las personas asociadas a la entidad
 * bancaria
 * 
 * @author EinerZG
 * @version 1.0 23/03/2019
 */
@Entity
public class Registro implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id del Registro
	 */
	@Id
	@Column(nullable = false, unique = true)
	private String id;
	/**
	 * Estado del Registro
	 */
	private String estado;
	/**
	 * fecha del registro
	 */
	private Date fecha;
	
	/**
	 * Constructor de Persona
	 */
	public Registro() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Get estado
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * set estado
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Get fecha
	 * @return Fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * Set Fecha
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
