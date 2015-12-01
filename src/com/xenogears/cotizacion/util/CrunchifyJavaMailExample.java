package com.xenogears.cotizacion.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
public class CrunchifyJavaMailExample {
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	public static void main(String args[]) throws AddressException, MessagingException, IOException, DocumentException {
		generateAndSendEmail();
		System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
	}
 
	public static void generateAndSendEmail() throws AddressException, MessagingException, IOException, DocumentException {
 
		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("jo.sue.yep@hotmail.com"));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("jo.sue.yep@hotmail.com"));
		generateMailMessage.setSubject("Greetings from Crunchify..");
		
		
		
		String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
		
		String path =System.getProperty("user.dir")+"/WebContent"+"/WEB-INF"+"/resources/cotizacion.pdf";

		
		OutputStream file = new FileOutputStream(new File(path));
	    Document document = new Document();
	    PdfWriter.getInstance(document, file);
	    document.open();
	    HTMLWorker htmlWorker = new HTMLWorker(document);
	    
	    htmlWorker.parse(new StringReader(emailBody));
	    document.close();
	    file.close();
	    
	    File fs=new File(path);
	    DataSource source = new FileDataSource(fs);    
		
	    generateMailMessage.setDataHandler(new DataHandler(source));
	    generateMailMessage.setFileName("co.pdf");
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "kerneluserallteams", "qa123456");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}
