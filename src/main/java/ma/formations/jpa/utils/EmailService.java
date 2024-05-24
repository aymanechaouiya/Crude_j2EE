package ma.formations.jpa.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Authenticator;



public class EmailService {

    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME = "aymanechaouiya053@gmail.com";
    private static final String PASSWORD = "your-email-password";

    public static void sendEmail(String toEmail, String subject, String body) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", SMTP_SERVER);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
