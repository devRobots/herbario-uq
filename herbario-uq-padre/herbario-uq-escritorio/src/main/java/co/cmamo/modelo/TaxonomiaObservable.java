package co.cmamo.modelo;

import co.cmamo.Familia;
import co.cmamo.Genero;
import co.cmamo.Planta;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Permite transformar una persona en formato observable
 *
 * @author EinerZG
 * @version 1.0
 */
public class TaxonomiaObservable {

	/**
	 * cedula observable de un empleado
	 */
	private StringProperty id;
	/**
	 * nombre observable de una persona
	 */
	private StringProperty nombre;

	private Object taxonomia;

	/**
	 *
	 * @param cedula
	 * @param nombre
	 */
	public TaxonomiaObservable(long id, String nombre) {
		this.id = new SimpleStringProperty(id+"");
		this.nombre = new SimpleStringProperty(nombre);
	}

	/**
	 * constructor que genera con empleado observable con base a un empleado
	 *
	 * @param empleado que se quiere volver observable
	 */
	public TaxonomiaObservable(Object planta) {
		this.taxonomia = planta;
		
		if (planta instanceof Planta) {
			id = new SimpleStringProperty(((Planta)planta).getId()+"");
			nombre = new SimpleStringProperty(((Planta)planta).getEspecie());
		}
		else if (planta instanceof Genero) {
			id = new SimpleStringProperty(((Genero)planta).getId()+"");
			nombre = new SimpleStringProperty(((Genero)planta).getNombre());
		}
		else if (planta instanceof Familia) {
			id = new SimpleStringProperty(((Familia)planta).getId()+"");
			nombre = new SimpleStringProperty(((Familia)planta).getNombre());
		}
	}

	/**
	 * @return the cedula
	 */
	public StringProperty getId() {
		return id;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setId(StringProperty id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public StringProperty getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	public Object getTaxonomia() {
		return taxonomia;
	}

	public void setTaxonomia(Object taxonomia) {
		this.taxonomia = taxonomia;
	}
}
