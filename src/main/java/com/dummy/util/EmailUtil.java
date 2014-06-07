package com.dummy.util;

import com.dummy.util.mail.MailSenderInfo;

public class EmailUtil {

	public static final String EMAIL_CONFIG_PATH = "E:\\spring too suit\\workplace\\link\\TRMS\\src\\main\\resources\\email.properties";

	public static void main(String[] args) {
		String path = EmailUtil.class.getResource("/email.properties")
				.getPath();
		System.out.println(path);
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setPropertiesByFile(path);
		// sms.sendHtmlMail(mailInfo);
	}

	// public static void sendEmailToAllAdmin(List<String> toAddressList,
	// String content, String subject) {
	// String url = "http://127.0.0.1:8080/trms";
	// if (content == null) {
	// content = "A new reservation need to approve,Please Check" + url;
	// }
	// if (subject == null) {
	// subject = "HSBC Training System:";
	// }
	// MailSender sms = new MailSender();
	// for (String toAddress : toAddressList) {
	// if (toAddress == null) {
	// continue;
	// }
	// System.out.println("toAddress-------------->>" + toAddress);
	// MailSenderInfo mailInfo = new MailSenderInfo();
	// mailInfo.setPropertiesByFile(C.Util.CONFIG_EMAIL_PATH);
	// mailInfo.setEmailContent(subject, content, toAddress);
	// sms.sendTextMail(mailInfo);
	// }
	// }
}
