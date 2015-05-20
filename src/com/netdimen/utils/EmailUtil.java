package com.netdimen.utils;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.netdimen.config.Config;

public class EmailUtil {

	public static void Send(String subject, String content) {
	      // Recipient's email ID needs to be mentioned.
	      String to =  Config.getInstance().getProperty("mail.recipient");

	      // Sender's email ID needs to be mentioned
	      String from =  Config.getInstance().getProperty("mail.sender");

	      // Assuming you are sending email from localhost
	      String host = Config.getInstance().getProperty("system.smtpHost");

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(subject);

	         // Now set the actual message
	         message.setText(content);

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent email successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}

}
