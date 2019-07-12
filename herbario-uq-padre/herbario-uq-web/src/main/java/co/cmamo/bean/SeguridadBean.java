package co.cmamo.bean;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import co.cmamo.ejbs.AdminEJB;
import co.cmamo.Administrador;
import co.cmamo.Empleado;
import co.cmamo.Persona;
import co.cmamo.Recolector;
import co.cmamo.util.Util;

/**
 * Permite manejar la sesi贸n de la aplicacion
 * 
 * @author Yesid Rosas
 * @author Camilo Quiceno
 * @version 1.0
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("seguridadBean")
@SessionScoped
public class SeguridadBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * usuario que tiene iniciada la sesion
	 */
	private Persona usuario;
	/**
	 * determina si esta autenticado o no
	 */
	private boolean autenticado;
	/**
	 * determina si esta autenticado o no
	 */
	private boolean admin;
	/**
	 * determina si esta autenticado o no
	 */
	private boolean empleado;
	/**
	 * determina si esta autenticado o no
	 */
	private boolean recolector;
	/**
	 * permite consultar la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * inicializa la info basica para el manejo de la sesion
	 */
	@PostConstruct
	private void init() {
		usuario = new Persona();
	}

	/**
	 * permite iniciar sesi贸n
	 */
	public void iniciarSesion() {

		Persona u = adminEJB.iniciarSesion(usuario.getCorreo(), usuario.getClave());

		if (u == null) {
			Util.mostrarMensaje("No se pudo iniciar sesion verifique sus credenciales",
					"No se pudo iniciar sesion verifique sus credenciales");
		} else {
			if (u instanceof Administrador) {
				admin = true;
			}
			else if (u instanceof Empleado) {
				empleado = true;
			}
			else if (u instanceof Recolector) {
				recolector = true;
			}
			
			usuario = u;
			autenticado = true;
		}
	}

	/**
	 * permite cerrar sesion
	 */
	public void cerrarSesion() {
		if (usuario != null) {
			usuario = null;
			autenticado = false;
			admin = false;
			empleado = false;
			recolector = false;
			init();
		}
	}
	
	/**
	 * navegaci贸n del menu principal
	 * @param i 
	 * @return
	 */
	public String navegacionEspecies(int i) {
		switch (i) {
		case 1:
			return "/admin/familia/familias"; // Lugar Donde se ven las familias
		case 2:
			return "/admin/familia/registrar_familia"; // Lugar donde se registra las familias
		case 3:
			return "/admin/genero/generos"; // Lugar donde se miran los generos
		case 4:
			return "/admin/genero/registrar_genero"; // Lugar donde se registran los generos
		case 5:
			return "/recolector/especies"; // Lugar donde se miran las especies
		default:
			return null;
		}
	}
	
	/**
	 * navegaci贸n del menu principal
	 * @param i 
	 * @return
	 */
	public String navegacionRecolector(int i) {
		switch (i) {
		case 1:
			return "/empleado/recolector/recolectores";// Lugar donde se miran los recolectores
		case 2:
			return "/empleado/recolector/registrar_recolector";// Lugar donde se registran los recolectores
			//TODO: falta validar
		default:
			return null;
		}
	}
	
	/**
	 * navegaci贸n del menu principal
	 * @param i 
	 * @return
	 */
	public String navegacionEmpleado(int i) {
		switch (i) {
		case 1:
			return "/admin/empleado/empleados";//Donde se miran los empleados 
		case 2:
			return "/admin/empleado/registrar_empleado";//Donde se registran los empleados 
		default:
			return null;
		}
	}
	
	/**
	 * navegaci贸n del menu principal
	 * @param i 
	 * @return
	 */
	public String navegacionPeticion(int i) {
		switch (i) {
		case 1:
			return "/recolector/peticion/registrar_especie";//
		case 2:
			return "/recolector/peticion/peticiones_todas";//
		case 3:
			return "/recolector/peticion/peticiones_pendientes";//
		case 4:
			return "/recolector/peticion/peticiones_aceptadas";//
		case 5:
			return "/recolector/peticion/peticiones_rechazadas";//
		default:
			return null;
		}
	}
	
	/**
	 * Permite hacer la conexion con gmail para poder enviar el correo
	 * 
	 * @param destinatario correo de recep
	 * @param asunto       Asunto del msj
	 * @param cuerpo       Cuerpo del msj
	 */
	private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente tambi閚.
		String remitente = "pruebaHerbario@gmail.com"; // Para la direcci髇 nombrecuenta@gmail.com

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "pruebaHerbario123"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticaci髇 mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			//Se podr韆n a馻dir varios de la misma manera
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, "pruebaHerbario123");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace(); // Si se produce un error
		}
	}

	/**
	 * Recuperar Clave
	 */
	public void recuperarClave() {

		String destinatario = usuario.getCorreo();
		try {

			enviarConGMail(destinatario, "Contrase馻",
					"Hola, su clave es:" + adminEJB.recuperarContrasenia(usuario.getCorreo()));
			Util.mostrarMensaje("Email enviado exitosamente", "Email enviado exitosamente");

		} catch (NullPointerException e) {
			Util.mostrarMensaje("Error", "El correo no existe");
		}

	}
	/**
	 * @return the usuario
	 */
	public Persona getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the autenticado
	 */
	public boolean isAutenticado() {
		return autenticado;
	}

	/**
	 * @param autenticado the autenticado to set
	 */
	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * @return the empleado
	 */
	public boolean isEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(boolean empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the recolector
	 */
	public boolean isRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(boolean recolector) {
		this.recolector = recolector;
	}
}
