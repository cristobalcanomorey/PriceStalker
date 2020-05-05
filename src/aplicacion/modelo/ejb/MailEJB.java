package aplicacion.modelo.ejb;

import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.pojo.Contenido;
import aplicacion.modelo.pojo.Mail;
import aplicacion.modelo.pojo.ProductoScraped;
import aplicacion.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class MailEJB {

	public void enviarCorreoBajadaDePrecio(Usuario usuario, Contenido contenido, ProductoScraped productoScraped,
			String enlace) {
		Mail correo = new Mail("smtp.gmail.com", 587, "priceStalkerOfficial@gmail.com", "jL79kQnsNpkzeuw");
		String asunto = "¡Un producto de tu lista de Price Stalker ha bajado de precio!";
		String mensaje = "<p>Echa un vistazo al producto " + productoScraped.getNombre() + "</p>";
		mensaje += "<p>Tu precio: " + contenido.getPrecioObjetivo() + "</p>";
		mensaje += "<p>Precio actual: " + productoScraped.getPrecio() + "</p>";
		Double diferencia = contenido.getPrecioObjetivo().doubleValue() - productoScraped.getPrecio().doubleValue();
		mensaje += "<p>Diferencia: " + String.format("%.2f", diferencia) + "</p>";
		mensaje += "<p>¡Corre compra el producto antes de que vuelva a subir!</p>";
		mensaje += "<a href='" + enlace + "'>Enlace de compra</a>";
		sendMail(usuario.getCorreo(), asunto, mensaje, correo);
	}

	public boolean sendMail(String receptor, String asunto, String mensaje, Mail correo) {
		LogSingleton log = LogSingleton.getInstance();
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", correo.getHost());
		prop.put("mail.smtp.port", String.valueOf(correo.getPort()));
		prop.put("mail.smtp.ssl.trust", correo.getHost());

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(correo.getCorreo(), correo.getPassword());
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correo.getCorreo()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
			message.setSubject(asunto);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(mensaje, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			return true;
		} catch (Exception e) {
			log.getLoggerMailEJB().error("Se ha producido un error en MailEJB: " + e);
			return false;
		}
	}

}
