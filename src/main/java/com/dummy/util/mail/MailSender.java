package com.dummy.util.mail;

import java.util.Date;
import java.util.List;
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

import com.dummy.common.C;

/**
 * mailSender
 */
public class MailSender {

	public static void main(String[] args) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setPropertiesByFile(C.Util.CONFIG_EMAIL_PATH);
		mailInfo.setEmailContent("test", "test", "1030041097@qq.com");
		MailSender sms = new MailSender();
		//
		sms.sendTextMail(mailInfo);

		// sms.sendHtmlMail(mailInfo);
	}

	public static void sendEmailToAllAdmin(List<String> toAddressList,
			String content, String subject) {
		String url = "http://127.0.0.1:8080/trms";
		if (content == null) {
			content = "A new reservation need to approve,Please Check" + url;
		}
		if (subject == null) {
			subject = "HSBC Training System:";
		}
		MailSender sms = new MailSender();
		for (String toAddress : toAddressList) {
			if (toAddress == null) {
				continue;
			}
			System.out.println("toAddress-------------->>" + toAddress);
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setPropertiesByFile(C.Util.CONFIG_EMAIL_PATH);
			mailInfo.setEmailContent(subject, content, toAddress);
			sms.sendTextMail(mailInfo);
		}
	}
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