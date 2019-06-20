package co.cmamo.ejbs;

import java.util.Properties;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RequestScoped
public class JavaMailSession {
	@Resource(name="herbario-uq-mail")
	private Session mailSession;

	Message mensaje;

	public boolean enviarMensaje(String header, String text, String destinatario) {
		try {
			Properties props = new Properties();
			
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port","587");
			props.setProperty("mail.smtp.user", "herbario.uq.mail@gmail.com");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
			
			mailSession = Session.getDefaultInstance(props);
			
			mensaje = new MimeMessage(mailSession);
			
			Address from = new InternetAddress("herbario.uq.mail@gmail.com");
			mensaje.setFrom(from);

			Address to = new InternetAddress(destinatario);
			mensaje.addRecipient(RecipientType.TO, to);

			mensaje.setSubject(header);

			mensaje.setText(text);

//			mensaje.setHeader(header, "herbario-uq@uqvirtual.edu.co");
//			Date timeStamp = new Date();
//			mensaje.setSentDate(timeStamp);
			
			Transport t = mailSession.getTransport("smtp");
			t.connect("herbario.uq.mail@gmail.com","cosa1234");
			
			System.out.println(1);
			t.sendMessage(mensaje, mensaje.getAllRecipients());			
			
			t.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();;
			return false;
		}
	}
}
