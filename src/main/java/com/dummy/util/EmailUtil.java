package com.dummy.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.dummy.util.mail.MailSender;
import com.dummy.util.mail.MailSenderInfo;

public class EmailUtil {

	public static final String EMAIL_CONFIG_PATH = "E:\\spring too suit\\workplace\\link\\TRMS\\src\\main\\resources\\email.properties";

	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr;
		String ip = null;
		addr = InetAddress.getLocalHost();
		ip = addr.getHostAddress().toString();// 获得本机IP
		String url = "http://" + ip + ":8080/trms";
		String path = EmailUtil.class.getResource("/email.properties")
				.getPath();
		String readlPath = path.substring(1, path.length());
		System.out.println(readlPath);
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setEmailContent("test", "name" + url, "1030041097@qq.com");
		mailInfo.setPropertiesByFile(path);
		MailSender.sendHtmlMail(mailInfo);
	}
	public static boolean sendEmailToAllAdmin(String toAddress, String content,
			String subject) {
		InetAddress addr;
		String ip = null;
		try {
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString();// 获得本机IP
			String url = "http://" + ip + ":8080/trms";
			if (content == null) {
				content = "A new reservation need to approve,Please Check:"
						+ url;
			}
			if (subject == null) {
				subject = "HSBC Training System";
			}
			String path = EmailUtil.class.getResource("/email.properties")
					.getPath();
			String realPath = path.substring(1, path.length());
			System.out.println("toAddress-------------->>" + toAddress);
			System.out.println("realPath-------------->>" + realPath);

			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setPropertiesByFile(realPath);
			mailInfo.setEmailContent(subject, content, toAddress);
			return MailSender.sendTextMail(mailInfo);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;

		}
	}
	public static boolean sendEmailToApplicant(String toAddress,
			String content, String subject) {
		InetAddress addr;
		String ip = null;
		try {
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString();// 获得本机IP
			String url = "http://" + ip + ":8080/trms";
			if (content == null) {
				content = "Your Reservation have been handlered,Please Check:"
						+ url;
			}
			if (subject == null) {
				subject = "HSBC Training System";
			}
			String path = EmailUtil.class.getResource("/email.properties")
					.getPath();
			String realPath = path.substring(1, path.length());
			System.out.println("toAddress-------------->>" + toAddress);
			System.out.println("realPath-------------->>" + realPath);

			MailSenderInfo mailInfo = new MailSenderInfo();
			// mailInfo.setPropertiesByFile(realPath);
			mailInfo.setEmailContent(subject, content, toAddress);
			return MailSender.sendTextMail(mailInfo);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;

		}

	}
}
