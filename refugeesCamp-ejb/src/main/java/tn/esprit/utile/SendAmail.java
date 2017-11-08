package tn.esprit.utile;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendAmail {

	public boolean send(String jobOfferTitle, String refugeeEmail, int id_jobOffer, int id_refugee) {
		final String fromEmail = "refugeecamprecruitement@gmail.com";
		byte[] decodedValue = Base64.getDecoder().decode("MTIzIUFaRTEyMyFBWkU="); 
		final String password = new String(decodedValue, StandardCharsets.UTF_8);

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.setProperty("mail.smtp.ssl.enable", "true");// the life saver

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(refugeeEmail)); 
																									
			message.setSubject(" - You are a strong candidate for the job offer: " + jobOfferTitle);
																									
			
			String htmlcontent = "Congratulations,<br><br><br> You have been selected as a potential candidate for this job offer!"
					+ "<br> We know that job search is long process so we have decided to help you with the first few steps:"
					+ "<br><br>If you would like to generate your cover letter through our app go to : "
					+ "<a href='http://localhost:18080/refugeesCamp-web/api/joboffers/"+id_jobOffer+"/refugees/"+id_refugee+"/pdf"+"'>Generate my cover letter</a>"
					+ "<br><br>You can also make your own custom CV by following the link below :"
					+ "<br> <a href='https://goo.gl/qWzSPu'>Resume generator</a>"
					+ "<br><br> Make sure to watch these job interview videos for more information :"
					+ "<br> <a href='https://goo.gl/ecMUtu'>Job interview questions</a>"
					+ "<br> <a href='https://goo.gl/9VmfdD'>Body language tips to impress at your next job interview</a>"
					+ "<br><br><br> We wish you the best of luck! ";

			message.setContent(htmlcontent, "text/html; charset=utf-8");
			Transport.send(message);

			return true;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
