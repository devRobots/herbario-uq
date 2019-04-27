package co.cmamo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * Informacion basica de cada una de las personas asociadas a la entidad
 * bancaria
 * 
 * @author EinerZG
 * @version 1.0 23/03/2019
 */
@Entity
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id de la persona
	 */
	@Id
	@Column(nullable= false,unique= true)

	private String id;
	/**
	 * Nombre de la persona
	 */
	@Column(length=50)
	private String nombre;
	/**
	 * Apellido de la persona
	 */
	@Column(length= 50)
	private String apellido;
	/**
	 * Correo de la persona
	 */
	@Column(unique= true)
	private String correo;
	/**
	 * Clave de acceso al herbario
	 */
	@Column(length=18)
	private String clave;
	/**
	 * Estado (activo o inactivo)
	 */
	private EstadoActividad estado;

	/**
	 * Constructor de Persona
	 */
	public Persona() {
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
		Persona other = (Persona) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return id;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.id = cedula;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the estado
	 */
	@Enumerated(EnumType.STRING)
	public EstadoActividad getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoActividad estado) {
		this.estado = estado;
	}

}
