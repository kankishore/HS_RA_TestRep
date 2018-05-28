package com.testersconnect.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//import com.jayway.restassured.specification.RequestSpecification;







public class HSTestSuite extends TestCaseBase {

	String HAPIKEY="f6f089f1-b2ad-4aa2-859d-fe82dff3bb4c";
	String companyID="";
	ExtentTest test;
	
	@Test(priority=1)
	public void verify_Create_Company() {
		System.out.println("executing test1");
		test = extent.createTest("verifyCreateCompany", "Verify that Company can be Created in HS");
		RestAssured.baseURI="https://api.hubapi.com";
		String CREATE_COMPANY_URL="https://api.hubapi.com/companies/v2/companies?hapikey="+HAPIKEY;
		String REQUEST_BODY="{ \"properties\": [ { \"name\": \"name\", \"value\": \"Infosys\" } , {\"name\": \"description\", \"value\": \"Services & Consulting\"} , {\"name\":\"domain\",\"value\":\"infosys.com\"} ] } ";
		
		
		companyID=given().contentType("application/json").body(REQUEST_BODY).
		when().post(CREATE_COMPANY_URL).then().statusCode(200).extract().path("companyId").toString();
		
		System.out.println("Company Created with ID "+companyID);
		
		if(companyID!=null) {
			test.log(Status.PASS, "Company Created with ID "+companyID);
		}else {
			test.log(Status.FAIL, "Company Not Created");
		}
		
		
	}
	
	@Test(priority=2)
	public void verify_Update_Company() {
		test = extent.createTest("verifyUpdateCompany", "Verify that Company can be Updated in HS");
		String UPDATE_COMPANY_URL="https://api.hubapi.com/companies/v2/companies/"+companyID+"?hapikey="+HAPIKEY;
		String REQUEST_BODY="{ \"properties\": [ {\"name\": \"description\", \"value\": \"A far better Infosys than before | Infy 2.0\"}]}";
		
		Response response=given().contentType("application/json").body(REQUEST_BODY).
		when().put(UPDATE_COMPANY_URL);
		
		int statusCode=response.getStatusCode();
		//.then().statusCode(200);
		
		if(statusCode==200) {
			test.log(Status.PASS, "Company with ID "+companyID+" Updated Successfully");
		}else {
			test.log(Status.FAIL, "Company Updation Failed");
		}
		
		
		
	}
	
	
	@Test(priority=3)
	public void verify_Delete_Company() {
		test=extent.createTest("verifyDeleteCompany", "Verify that Company can be Deleted in HS");
		String DELETE_COMPANY_URL="https://api.hubapi.com/companies/v2/companies/"+companyID+"?hapikey="+HAPIKEY;
		
		boolean deleteConfirmation=given().delete(DELETE_COMPANY_URL).then().statusCode(200).extract().path("deleted");
		
		if(deleteConfirmation==true) {
			test.log(Status.PASS, "Company with ID "+companyID+" Deleted Successfully");
		}else {
			test.log(Status.FAIL, "Company Deletion Failed");
		}
		
		Assert.assertEquals(deleteConfirmation, true);
		
	}
	
	@Test(priority=4)
	public void test4() {
		System.out.println("Executing test4");
	}
	
}
