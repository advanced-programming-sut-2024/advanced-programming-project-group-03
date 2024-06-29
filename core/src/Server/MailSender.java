package Server;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.BASE64EncoderStream;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

public class MailSender {
    private static final String SERVICE_ACCOUNT_PATH = "core/src/Server/probending-98ca50d3b2a8.json";

    public void sendEmailWithCode(String recipientEmail, int code) throws Exception {
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(
                        new ByteArrayInputStream(SERVICE_ACCOUNT_PATH.getBytes()))
                .createScoped("https://mail.google.com/");

        credentials.refreshIfExpired();
        String accessToken = credentials.getAccessToken().getTokenValue();

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(""));
        msg.setRecipients(Message.RecipientType.TO, recipientEmail);
        msg.setSubject("Test Email");
        msg.setText("Hello, this is a test email. Your code is: " + code);

        SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
        try {
            t.connect("smtp.gmail.com", "your-email@gmail.com", null);
            t.issueCommand("AUTH XOAUTH2 " + new String(BASE64EncoderStream.encode(
                    ("user=probending@probending.iam.gserviceaccount.com\1auth=Bearer " + accessToken + "\1\1").getBytes())), 235);
            t.sendMessage(msg, msg.getAllRecipients());
        } finally {
            t.close();
        }
    }
}