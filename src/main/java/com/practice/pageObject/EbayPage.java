package com.practice.pageObject;

import com.practice.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class EbayPage extends TestBase {

    //We will add all the page object in this class.
    //We will do the operations on page object


    @FindBy(xpath = "//a[contains(text(),'Sign in')]")
    WebElement signIn;

    @FindBy(xpath = "//input[@id='userid']")
    WebElement userName;

    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement checkBox;

    @FindBy(xpath = "//button[@id='signin-continue-btn']")
    WebElement checkContinue;

    @FindBy(xpath = "//input[@id='pass']")
    WebElement password;

    @FindBy(xpath = "//button[@id='sgnBt']")
    WebElement sign;

    @FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
    WebElement robotCheck;


    public EbayPage() throws IOException {
        PageFactory.initElements(driver,this);
    }

    public void trySigningIn(String enterUserName, String enterPassword){
        signIn.click();
        userName.sendKeys(enterUserName);
        checkBox.click();
        checkContinue.click();
        password.sendKeys(enterPassword);
        sign.click();

        robotCheck.click();
    }



}
