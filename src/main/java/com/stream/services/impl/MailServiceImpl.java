package com.stream.services.impl;

import java.util.Properties;

import org.springframework.stereotype.Service;

import com.stream.services.MailService;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

	@Override
	public void sendMail(String email, String code) {
		
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("olympicstreaming7777@gmail.com", "ixuvakfrkdwkifqq");
			}
			
		});
		   
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("olympicstreaming7777@gmail.com", false));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email, false));
			msg.setSubject("Account Activation");
			msg.setText("""
					Please activate your account by clicking on this link.
					http://localhost:8080/auth/activation/"""+code);
			Transport.send(msg);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}
