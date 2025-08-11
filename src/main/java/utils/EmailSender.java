package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.*;
import java.util.*;
import com.example.api.config.Constants;

public class EmailSender {

    public static void sendEmailWithAttachments(String toEmail, String subject, String body, String logPath, String zipPath) {
        final String fromEmail = Constants.getSenderEmail();  // Sender email from Constants
        final String password = Constants.getAppPassword();  // App password from Constants

        Properties props = new Properties();
        props.put("mail.smtp.host", Constants.getSmtpHost());  // SMTP host from Constants
        props.put("mail.smtp.port", Constants.getSmtpPort());  // SMTP port from Constants
        props.put("mail.smtp.auth", Constants.getSmtpAuth());  // SMTP auth setting from Constants
        props.put("mail.smtp.starttls.enable", Constants.getSmtpStarttls());  // SMTP STARTTLS from Constants

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // subject is not null before sending
            if (subject == null) {
                subject = "No Subject Provided";  // Default subject if null
            }

            // Create a new email message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);

            // Create the email body part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Attach the log file
            MimeBodyPart logAttachment = new MimeBodyPart();
            logAttachment.attachFile(new File(logPath));

            // Attach the zip file
            MimeBodyPart zipAttachment = new MimeBodyPart();
            zipAttachment.attachFile(new File(zipPath));

            // Combine the email body and attachments
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(logAttachment);
            multipart.addBodyPart(zipAttachment);

            // Set the email content
            msg.setContent(multipart);

            // Send the email
            Transport.send(msg);
            System.out.println("Email sent successfully!");

            // Wait for it to sync into the "Sent" folder
            Thread.sleep(5000);

            // Verify the email is in the Sent folder
            boolean isSent = checkSentFolder(fromEmail, password, subject);
            if (isSent) {
                System.out.println("Verified in Sent folder: Email is present.");
            } else {
                System.out.println("Email not found in Sent folder.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkSentFolder(String email, String password, String expectedSubject) {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");

            Session session = Session.getInstance(props);
            Store store = session.getStore();
            store.connect("imap.gmail.com", email, password);

            Folder sent = store.getFolder("[Gmail]/Sent Mail");  // Gmail specific folder
            sent.open(Folder.READ_ONLY);

            
            Message[] messages = sent.getMessages(Math.max(1, sent.getMessageCount() - 10), sent.getMessageCount());
            for (Message message : messages) {
                String subject = message.getSubject();
                
                if (subject != null && subject.equals(expectedSubject)) {
                    return true;
                }
            }

            sent.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
