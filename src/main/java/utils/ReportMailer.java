package utils;

import java.io.IOException;
import com.example.api.config.Constants;

public class ReportMailer {

    public static void sendReportAndLogs() {
        String allureFolder = Constants.getAllureFolder(); 
        String zipPath = Constants.getZipFilePath();  
        String logPath = Constants.getLogFilePath();  
        String recipientEmail = Constants.getRecipientEmail(); 
        String emailSubject = Constants.getEmailSubject();  
        String emailBody = Constants.getEmailBody();  

        try {
            System.out.println("Zipping Allure report...");
            ZipUtils.zipFolder(allureFolder, zipPath);
        } catch (IOException e) {
            System.out.println("Failed to zip Allure report: " + e.getMessage());
            return;
        }

        try {
            System.out.println("Sending email with report and logs...");
            EmailSender.sendEmailWithAttachments(
                recipientEmail,  // From config
                emailSubject,  // From config
                emailBody,  // From config
                logPath,
                zipPath
            );
        } catch (Exception e) {
            System.out.println("Email sending failed: " + e.getMessage());
        }
    }
}
