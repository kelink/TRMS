package com.dummy.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public EmailAuthenticator() {
	}

	public EmailAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

	// 需要注意security安全验证，初略版的email邮箱
	// 配置邮件发送基本配置将来使用配置文件配置
	public static void main(String[] args) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName(""); // 自己的邮箱
		mailInfo.setPassword("");// 自己的邮箱密码，用于验证

		mailInfo.setFromAddress(""); // /自己的邮箱
		mailInfo.setToAddress(""); // /对方的邮箱
		mailInfo.setSubject("设置邮箱标题");
		mailInfo.setContent("设置邮箱内容");

		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
		// sms.sendHtmlMail(mailInfo);// 发送html格式

	}
}