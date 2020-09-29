package com.practice.testCases;

import com.practice.base.TestBase;
import com.practice.pageObject.FacebookSignUp;
//import com.practice.pageObject.RegisterPage;
//import com.practice.utility.TestDataProvider;
import com.practice.utility.TestDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class TC01_registerUser extends TestBase {
    //RegisterPage registerPage;
    FacebookSignUp signup;

    public TC01_registerUser() throws IOException {
    }
    //It will contain your actual test cases.

    @BeforeMethod
    public void setup(){
        initialization();
    }

    @DataProvider
    public Object[][] getData(){
        TestDataProvider testDataProvider = new TestDataProvider();
        Object testData[][] = testDataProvider.getDataFromSheet("SignUp");
        return testData;
    }


    @Test(dataProvider = "getData")
//    public void verifyRegisterUser(String firstName, String lastName, String email, String password) throws IOException {
//        registerPage = new RegisterPage();
//        registerPage.register(firstName,lastName,email,password);
    public void verifySignUp(String firstName, String lastName, String email, String password, String month, String day, String year, String gender) throws IOException {
        signup = new FacebookSignUp();
        signup.signUp(firstName,lastName,email,password,month,day,year,gender);




    }
}
