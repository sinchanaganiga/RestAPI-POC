package com.example.api.tests;

import org.testng.annotations.Test;

import utils.ReportMailer;

public class EndToEndTest {

    @Test
    public void runAllTests() {
        org.testng.TestNG testng = new org.testng.TestNG();
        testng.setTestClasses(new Class[]{
                CreateObjectTest.class,
                GetObjectTest.class,
                UpdateObjectTest.class,
                PatchObjectTest.class,
                DeleteObjectTest.class
        });
        testng.run();
        
     // Call reusable method for zip + email
        ReportMailer.sendReportAndLogs();

        System.out.println("End-to-end flow completed.");
    }
}


        
  
