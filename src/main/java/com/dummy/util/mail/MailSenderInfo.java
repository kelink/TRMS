package com.dummy.util.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
/**  
 * 发送邮件需要使用的基本信息  
 */
import java.util.Properties;

public class MailSenderInfo {
	// 发送邮件的服务器的IP和端口
	private String mailServerHost;
	private String mailServerPort = "25";
	// 邮件发送者的地址
	private String fromAddress;
	// 邮件接收者的地址
	private String toAddress;
	// 登陆邮件发送服务器的用户名和密码
	private String userName;
	private String password;
	// 是否需要身份验证
	private boolean validate = false;
	// 邮件主题
	private String subject;
	// 邮件的文本内容
	private String content;
	// 邮件附件的文件名
	private String[] attachFileNames;

	public MailSenderInfo() {
		super();
	}

	/**
	 * 通过配置文件配置信息
	 */
	public void setPropertiesByFile(String configPath) {
		Properties prop = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(configPath));
			prop.load(inputStream);
			this.mailServerHost = prop.getProperty("mail.smtp.host").trim();
			this.mailServerPort = prop.getProperty("mail.smtp.port").trim();
			this.fromAddress = prop.getProperty("FromAddress").trim();
			this.userName = prop.getProperty("UserName").trim();
			this.password = prop.getProperty("Password").trim();
			String isValidate = prop.getProperty("isValidate").trim();
			;
			if (isValidate.equals("true")) {
				this.validate = true;
			} else {
				this.validate = false;
			}
		} catch (IOException e) {
			System.out.println("email  configure error，Please Check it");
			e.printStackTrace();
		}

	}

	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		// 从配置文件读取信息
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	/**
	 * 设置发送标题，内容，和接受者
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