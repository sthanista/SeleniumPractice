package com.practice.pageObject;

import com.practice.base.TestBase;
import com.practice.utility.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class FacebookSignUp extends TestBase {

    public FacebookSignUp() throws IOException {
        super();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[contains(text(),'Create New Account')]")
    WebElement createAccount;

    @FindBy(xpath = "//input[@name='firstname']")
    WebElement enterFirstName;

    @FindBy(xpath = "//input[@name='lastname']")
    WebElement enterLastName;

    @FindBy(xpath = "//input[@name='reg_email__']")
    WebElement enterEmail;

    @FindBy(xpath = "//input[@name='reg_email_confirmation__']")
    WebElement reEnterEmail;

    @FindBy(xpath = "//input[@name='reg_passwd__']")
    WebElement enterPassword;

    @FindBy(xpath = "//select[@id='month']")
    WebElement enterMonth;

    @FindBy(xpath = "//select[@id='day']")
    WebElement enterDay;

    @FindBy(xpath = "//select[@id='year']")
    WebElement enterYear;

    @FindBy(xpath = "//span[@class='_5k_2 _5dba']")
    WebElement selectGender;

    public void signUp(String firstName, String lastName, String email, String password, String month, String day, String year, String gender){
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying signup options for facebook");
        createAccount.click();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Clicked on create account button");
        enterFirstName.sendKeys(firstName);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering first name");
        enterLastName.sendKeys(lastName);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering last name");
        enterEmail.sendKeys(email);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering email name");
        reEnterEmail.sendKeys(email);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Re-Entering first name");
        enterPassword.sendKeys(password);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering password");
        enterMonth.sendKeys(month);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting Month");
        enterDay.sendKeys(day);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting day");
        enterYear.sendKeys(year);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting year");
        // WebElement element = selectGender;
        List<WebElement> radios = driver.findElements(By.xpath("//span[@class='_5k_2 _5dba']"));
        System.out.println(radios);
        for(WebElement radio:radios){
            if(radio.getText().equals(gender)){
                radio.click();

            }

        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting gender");


    }
}

