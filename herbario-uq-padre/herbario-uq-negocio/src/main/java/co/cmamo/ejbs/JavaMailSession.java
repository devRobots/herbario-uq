package co.cmamo.ejbs;

import java.util.Date;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailSession {
	@Resource(name = "herbario-uq-mail")
	private Session mailSession;

	// Crea el objeto del mensaje
	Message mensaje;

	public void enviarMensaje(String header, String text, String destinatario) {
		mensaje = new MimeMessage(mailSession);
		try {
			// Lista de destinatarios separados por ,
			mensaje.setRecipients(RecipientType.TO, InternetAddress.parse(destinatario));
			// Asunto del correo electronico
			mensaje.setSubject(header);
			// Cuerpo del mensaje, puede ser codigo HTML
			mensaje.setText(text);
			// Esto no es obligatorio pero es una buena practica para indeciar la
			// construccion del mensaje
			mensaje.setHeader(header, "herbario-uq@uqvirtual.edu.co");
			// Ajuste de la fecha del mensaje
			Date timeStamp = new Date();
			mensaje.setSentDate(timeStamp);
			// Uso del medoto “send” para enviar el mensaje
			Transport.send(mensaje);
		} catch (Exception e) {
			// manejo de la excepcion del metodo
		}
	}
}
