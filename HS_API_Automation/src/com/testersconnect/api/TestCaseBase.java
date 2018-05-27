package com.testersconnect.api;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;

public class TestCaseBase {
	ExtentHtmlReporter htmlReporter=null;
	ExtentReports extent=null;
	
	@BeforeTest
	public void init() {
		System.out.println("Executing Test Before");
		// initialize the HtmlReporter
		htmlReporter = new ExtentHtmlReporter("extent.html");

		// initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();

		// attach only HtmlReporter
		extent.attachReporter(htmlReporter);
		
	}
	
	@AfterTest
	public void cleanup() {
		System.out.println("Executing Test After");
		extent.flush();
	
	}
	
	
	
	
}
