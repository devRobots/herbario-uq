package co.cmamo.excepciones;

public class ElementoInexistenteExcepcion extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ElementoInexistenteExcepcion(String mensaje) {
		super(mensaje);
	}
}
