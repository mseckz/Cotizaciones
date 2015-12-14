package com.xenogears.cotizacion.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import com.xenogears.cotizacion.model.Cotizacion;
import com.xenogears.cotizacion.model.Usuario;

public class Email {
	public static void sendEmailWithAttachments(String host, String port,
			final String userName, final String password, String toAddress,
			String subject, String message, String attachFiles)
			throws AddressException, MessagingException, IOException {
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		MimeBodyPart attachPart = new MimeBodyPart();
		attachPart.attachFile(attachFiles);
		multipart.addBodyPart(attachPart);
		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}

	public static void sendEmail(String host, String port,
			final String userName, final String password, String toAddress,
			String subject, String message) throws MessagingException {

		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");
		
		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);

		
		// sends the e-mail
		Transport.send(msg);
		
		System.out.println("EMAIL ENVIADO");
	}

	/**
	 * Test sending e-mail with attachments
	 * 
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void sentEmailUsuario(Usuario usuario) throws IOException,
			DocumentException {
		// SMTP info
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "kerneluserallteams@gmail.com";
		String password = "qa123456";

		// message info
		String mailTo = usuario.getCorreo();
		String subject = "Bienvenido a Xenogears";

		String message = "" + "<!DOCTYPE html>" + "	<html>" + "	<body>"
				+ "	<h3>Bienvenido "
				+ usuario.getVendedor().getNombreCompleto()
				+ " a Xenogears</h3>"
				+ "	<legend> Sus credenciales son: </legend>"
				+ "	<legend>Correo: " + usuario.getCorreo() + "</legend>"
				+ "	<legend>Password: " + usuario.getClaveSinEncriptar()
				+ "</legend>" + "	</body>" + "	</html>";

		try {
			sendEmail(host, port, mailFrom, password, mailTo,
					subject, message);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}
	}

	public void sentEmailParams(Cotizacion cotizacion) throws IOException,
			DocumentException {
		// SMTP info
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "kerneluserallteams@gmail.com";
		String password = "qa123456";
		Date date=new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

		// message info
		String mailTo = cotizacion.getCliente().getEmail();
		String subject = "Su Cotizacion ha sido procesada";
		
		/**
		 * ASDASD
		 */
		 
		String razon,header,datosInteresado,datosCotizacion,parteFinal,message;
		header=""+
				"<!DOCTYPE html>"+
		"<html>"+
		"<head>"+
		"<meta charset='UTF-8'>"+
			"<title>Cotizacion N°"+cotizacion.getCodigoCotizacion()+"</title>"+
		"</head>"+
		"<body style='height: auto;width: 900px;margin-left: auto;margin-right: auto;background-image: url(asdsahttp://lparchive.org/Xenogears-%28by-The-Dark-Id%29/Update%2001/1-prologue11.jpg);'>"+
		" <h2 style='text-align: center;'>Cotizacion N°"+ cotizacion.getCodigoCotizacion()+"</h2>"+
		"<div style='float:left;padding-top:40px;'>"+
			"<legend>Fecha de Cotizacion : "+formateador.format(date) +"</legend><br>"+
			"<legend>Tipo de Cambio : 3.372</legend><br>"+
		"</div>"+
		"<div style='float:right;'>"+
			"<img style='width:150px;height:60px;' src=http://lparchive.org/Xenogears-%28by-The-Dark-Id%29/Update%2001/1-prologue11.jpg>"+
		"</div>"+
		"<div style='padding-top:120px;'>"+
			"<h4>Datos del Interesado</h4>"+
		"</div>"+
			
		"<div>";
			if(cotizacion.getCliente().getIdTipoCliente()==1){
				if (cotizacion.getCliente().getRazonSocia()==null) {
					razon="N/A";
				}else
				{
					razon=cotizacion.getCliente().getRazonSocia();
				}
				datosInteresado="<legend>Nombre Completo : "+cotizacion.getCliente().getNombreCompleto()+"</legend><br>"+
						"<legend>" +cotizacion.getCliente().getDescripTipoDoc()+" : " +cotizacion.getCliente().getNumeroDocumento()+"</legend><br>"+
						"<legend>Razon Social : " + razon +"</legend><br>"+
					"</div>"+
						"<div>"+
						"<table class='GeneratedTable'>"+
						"<thead>"+
						"<tr>"+
						"<th>Marca</th>"+
						"<th>Modelo</th>"+
						"<th>Año</th>"+
						"<th>Cantidad</th>"+
						"<th>Precio</th>"+
						"<th>Subtotal</th>"+
						"<th>Moneda</th>"+
						"</tr>"+
						"</thead>"+
						"<tbody>";
				
			}
			else{
				datosInteresado="<legend>Razon Social : " + cotizacion.getCliente().getRazonSocia() +"</legend>"+
						"<legend>RUC : "+cotizacion.getCliente().getNumeroDocumento()+"</legend>"+
						"<legend>Representante Legal: "+cotizacion.getCliente().getNombreCompleto()+"</legend>"+
					"</div>"+
						"<div>"+
						"<table class='GeneratedTable'>"+
							"<thead>"+
							"<tr>"+
							"<th>Marca</th>"+
							"<th>Modelo</th>"+
							"<th>Año</th>"+
							"<th>Cantidad</th>"+
							"<th>Precio</th>"+
							"<th>Subtotal</th>"+
							"<th>Moneda</th>"+
							"</tr>"+
							"</thead>"+
							"<tbody>";
			}
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < cotizacion.getDetalle().size(); i++) {
			stringBuilder.append("<tr>");
			stringBuilder.append("<td>"+cotizacion.getDetalle().get(i).getAuto().getMarca()+"</td>");
			stringBuilder.append("<td>"+cotizacion.getDetalle().get(i).getAuto().getModelo()+"</td>");
			stringBuilder.append("<td>"+cotizacion.getDetalle().get(i).getAuto().getAnio()+"</td>");
			stringBuilder.append("<td>"+cotizacion.getDetalle().get(i).getCantidad()+"</td>");
			stringBuilder.append("<td>"+cotizacion.getDetalle().get(i).getAuto().getPrecio()+"</td>");
			stringBuilder.append("<td>"+cotizacion.getDetalle().get(i).getSubtotal()+"</td>");
			stringBuilder.append("<td>"+cotizacion.getIdTipoMoneda()+"</td>");
			stringBuilder.append("</tr>");
		}
		datosCotizacion=stringBuilder.toString();
		
		parteFinal=	"</tbody>"
			+ "</table><br>"+
			"<legend>Importe Total : "+cotizacion.getImporte()+"</legend><br>"+
		"</div>"+
		"</body>"+
		"</html>";
		message=header+datosInteresado+datosCotizacion+parteFinal;
		System.err.println(message);
		// String pathattachFiles =
		// System.getProperty("user.dir")+"/WebContent"+"/WEB-INF"+"/resources/cotizacion.pdf";
		String pathattachFiles = Constantes.RUTA_PROYECTO
				+ "/WebContent/WEB-INF/resources/cotizacion.pdf";

		OutputStream file = new FileOutputStream(new File(pathattachFiles));
		Document document = new Document();
		PdfWriter.getInstance(document, file);
		document.open();
		HTMLWorker htmlWorker = new HTMLWorker(document);

		htmlWorker.parse(new StringReader(message));
		document.close();
		file.close();

		try {
			sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
					subject, message, pathattachFiles);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}
	}
}
