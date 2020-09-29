package com.practice.pageObject;

import com.practice.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LandingPage extends TestBase {

    Actions ac = new Actions(driver);

    @FindBy(xpath = "(//a[contains(text(),'Electronics')])[2]")
    WebElement electro;

    @FindBy(xpath = "//a[contains(text(),'Computers, Tablets & More')]")
    WebElement dropDownOption;

    @FindBy(xpath = "//div[contains(text(),'MacBooks')]")
    WebElement selectChoice;

   public void land(){
       ac.moveToElement(electro).build().perform();
   }

   public void dropDown(){
       dropDownOption.click();
   }

   public void choices(){
       selectChoice.click();
   }
    public LandingPage() throws IOException {
        PageFactory.initElements(driver,this);
    }

}
