package co.cmamo.excepciones;

public class ElementoRepetidoExcepcion extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ElementoRepetidoExcepcion(String mensaje) {
		super(mensaje);
	}
}
