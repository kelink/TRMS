package com.dummy.util.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
/**  
 * �����ʼ���Ҫʹ�õĻ���Ϣ  
 */
import java.util.Properties;

public class MailSenderInfo {

	private String mailServerHost;
	private String mailServerPort = "25";
	private String fromAddress;
	private String toAddress;
	private String userName;
	private String password;

	private boolean validate = false;

	private String subject;
	private String content;
	private String[] attachFileNames;

	public MailSenderInfo() {
		this.mailServerHost = "smtp.qq.com";
		this.mailServerPort = "25";
		this.userName = "2804438948@qq.com";
		this.password = "luo1992724";
		this.fromAddress = "2804438948@qq.com";
		this.validate = true;
	}

	/**
	 * configure email sender by properties
	 */
	public void setPropertiesByFile(String emailconfigPath) {
		Properties prop = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(emailconfigPath));
			prop.load(inputStream);
			this.mailServerHost = prop.getProperty("mail.smtp.host").trim();
			this.mailServerPort = prop.getProperty("mail.smtp.port").trim();
			this.fromAddress = prop.getProperty("FromAddress").trim();
			this.userName = prop.getProperty("UserName").trim();
			this.password = prop.getProperty("Password").trim();
			String isValidate = prop.getProperty("isValidate").trim();;
			if (isValidate.equals("true")) {
				this.validate = true;
			} else {
				this.validate = false;
			}
		} catch (IOException e) {
			System.out.println("email  configure error,please Check it");
			e.printStackTrace();
		}

	}

	/**
	 * set base configuration
	 */
	public Properties getProperties() {
		// set base configuration
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	/**
	 * set Email content
	 * 
	 * @return
	 */
	public void setEmailContent(String subject, String content, String toAddress) {
		this.subject = subject;
		this.content = content;
		this.toAddress = toAddress;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}

	@Override
	public String toString() {
		return "MailSenderInfo [mailServerHost=" + mailServerHost
				+ ", mailServerPort=" + mailServerPort + ", fromAddress="
				+ fromAddress + ", toAddress=" + toAddress + ", userName="
				+ userName + ", password=" + password + ", validate="
				+ validate + ", subject=" + subject + ", content=" + content
				+ ", attachFileNames=" + Arrays.toString(attachFileNames) + "]";
	}
}