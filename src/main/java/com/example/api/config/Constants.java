package com.example.api.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants {

    // API Base URI and endpoint paths
    public static final String BASE_URI = "https://api.restful-api.dev";
    public static final String CREATE_OBJECT = "/objects"; 
    public static final String GET_OBJECT = "/objects/{id}"; 
    public static final String UPDATE_OBJECT = "/objects/{id}"; 
    public static final String PATCH_OBJECT = "/objects/{id}"; 
    public static final String DELETE_OBJECT = "/objects/{id}";
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    // Properties
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream inputStream = new FileInputStream("src/main/java/com/example/api/config/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Access methods for properties
    public static String getSenderEmail() {
        return properties.getProperty("senderEmail");
    }

    public static String getAppPassword() {
        return properties.getProperty("appPassword");
    }

    public static String getSmtpHost() {
        return properties.getProperty("smtpHost");
    }

    public static String getSmtpPort() {
        return properties.getProperty("smtpPort");
    }

    public static String getAllureFolder() {
        return properties.getProperty("allureFolder");
    }

    public static String getZipFilePath() {
        return properties.getProperty("zipFilePath");
    }

    public static String getLogFilePath() {
        return properties.getProperty("logFilePath");
    }

    public static String getRecipientEmail() {
        return properties.getProperty("recipientEmail");
    }

    public static String getEmailSubject() {
        return properties.getProperty("emailSubject");
    }

    public static String getEmailBody() {
        return properties.getProperty("emailBody");
    }
    
    public static String getSmtpAuth() {
        return properties.getProperty("smtpAuth");
    }

    public static String getSmtpStarttls() {
        return properties.getProperty("smtpStarttls");
    }
}
