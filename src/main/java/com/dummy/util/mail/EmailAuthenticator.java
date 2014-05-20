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

	// ��Ҫע��security��ȫ��֤�����԰��email����
	// �����ʼ����ͻ������ý���ʹ�������ļ�����
	public static void main(String[] args) {
		// �������Ҫ�������ʼ�
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName(""); // �Լ�������
		mailInfo.setPassword("");// �Լ����������룬������֤

		mailInfo.setFromAddress(""); // /�Լ�������
		mailInfo.setToAddress(""); // /�Է�������
		mailInfo.setSubject("�����������");
		mailInfo.setContent("������������");

		// �������Ҫ�������ʼ�
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// ���������ʽ
		// sms.sendHtmlMail(mailInfo);// ����html��ʽ

	}
}