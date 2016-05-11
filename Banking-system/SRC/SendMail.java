package se.bank.src;


import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public void email(String subject,String email) {
		
		final String username = "worldwide.banking123@gmail.com";
		final String password = "ashish31";
		String Email = email;
		Properties props = new Properties();
		//String subject = "One TIme Password is " + num + "Sent from Worldwide Bank";
		
		//System.out.println(test);

		props.setProperty("mail.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);

		Message msg = new MimeMessage(session);

		try {
			msg.setSubject("Worldwide Bank");
			//msg.setText("One TIme Password is " + num + "Sent from Worldwide Bank");
			msg.setText(subject);
			msg.setFrom(new InternetAddress("worldwide.banking123@gmail.com", "Bank"));
			//msg.setRecipient(Message.RecipientType.TO, new InternetAddress("jadhavashish41@yahoo.in"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(Email));
			//Ritikasingh889@gmail.com

			Transport.send(msg);
		} catch (MessagingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Finished.");
	}

}
