package util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class SendEmail extends Base {
	public static void sendEmail() throws EmailException {
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(props.getProperty("extentReport_path") + ExtentReportGenerator.filename1 + ".html");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("URL_AutomationTestReport");
		attachment.setName(ExtentReportGenerator.filename1 + ".html");

		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.office365.com");
		email.setSmtpPort(587);
		email.setStartTLSRequired(true);
		email.setAuthenticator(
				new DefaultAuthenticator(props.getProperty("sender"), props.getProperty("sender_password")));

		email.setFrom(props.getProperty("sender"));
		email.setSubject("Automation test Report: URL status");
		email.setMsg(
				"Please find URL status automation test report and failed test case screenshots attached with this email.");

		String[] recipients = { "nisha.soni@mahait.org", "mallikarjun.kopuri@mahait.org", "amjad.sayyed@mahait.org" };

		email.addTo(recipients);

		email.attach(attachment);
		email.send();
	}

}
