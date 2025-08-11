package com.example.api.utils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;

public class LoggerSetup {
    public static void setupLogging() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        // Setting the log level to DEBUG
        context.getLogger("ROOT").setLevel(Level.DEBUG);

        // Setting up encoder and appender
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n");
        encoder.start();

        FileAppender fileAppender = new FileAppender();
        fileAppender.setContext(context);
        fileAppender.setName("FileLogger");
        fileAppender.setFile("C:\\Users\\sibr\\Desktop\\DummyRestApi\\log\\api-test-logs.txt");
        fileAppender.setEncoder(encoder);
        fileAppender.start();

        // Add the file appender to the root logger
        context.getLogger("ROOT").detachAndStopAllAppenders();
        context.getLogger("ROOT").addAppender(fileAppender);
    }
}
