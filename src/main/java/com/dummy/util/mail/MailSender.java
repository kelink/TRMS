package com.dummy.util.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * mailSender
 */
public class MailSender {

	/**
	 * send Text
	 * 
	 * @param mailInfo
	 * 
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		// authenticator
		EmailAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// authenticator
			authenticator = new EmailAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// mail session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// new Mail message
			Message mailMessage = new MimeMessage(sendMailSession);
			// fromAddres
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// set fromAddress
			mailMessage.setFrom(from);
			// InternetAddress for email
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// set subject
			mailMessage.setSubject(mailInfo.getSubject());
			// set date
			mailMessage.setSentDate(new Date());
			// set content
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);

			// sending
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * send HTML
	 * 
	 * @param mailInfo
	 * 
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// authenticator֤
		EmailAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// isValidate
		if (mailInfo.isValidate()) {
			authenticator = new EmailAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// mail session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// session to get mail Message
			Message mailMessage = new MimeMessage(sendMailSession);
			// from address
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// set from
			mailMessage.setFrom(from);
			// to Address
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// set subject
			mailMessage.setSubject(mailInfo.getSubject());
			// set date
			mailMessage.setSentDate(new Date());
			// MiniMultipart
			Multipart mainPart = new MimeMultipart();
			// new HTML body message
			BodyPart html = new MimeBodyPart();
			// set HTML content
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// set content
			mailMessage.setContent(mainPart);
			// sending
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}