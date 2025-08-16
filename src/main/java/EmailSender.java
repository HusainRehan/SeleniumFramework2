import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailSender {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String senderEmail = "demo05061998@gmail.com";
		final String appPassword = "wilaufeepnubajue";
		final String recipientEmail = "demo05061998@gmail.com";

		// SMTP server properties
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.port", "587");

		// create a session with authentication
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, appPassword);
			}
		});
		session.setDebug(true);

		try {// create email message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("this email is from java with attachment");
//			message.setText("Hi \n this is email \n Best Regards \n Rehan \n QA ");
//			Transport.send(message);

			// add attachment
			// email body
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Hello , this is a test email with attachment");
			// email attachment
			MimeBodyPart attachmentPart = new MimeBodyPart();
			String filePath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
			System.out.println(filePath);
			attachmentPart.attachFile(new File(filePath));

			// combine body and attachment parts

			MimeMultipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(textPart);
			multiPart.addBodyPart(attachmentPart);
			message.setContent(multiPart);
			Transport.send(message);
			System.out.println("Email sent successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
