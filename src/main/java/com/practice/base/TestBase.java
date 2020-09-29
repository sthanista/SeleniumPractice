package com.practice.base;

import com.relevantcodes.extentreports.LogStatus;

import com.practice.utility.ExtentManager;
import com.practice.utility.ExtentTestManager;
//import com.practice.utility.WebEventListener;
import com.practice.utility.GetScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static EventFiringWebDriver e_driver;
    //public static WebEventListener eventListener;
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 20;
    public static Properties configProperties;

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources"
                    + "/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        String browserName = prop.getProperty("browser.name");
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\src\\main\\resources\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("FF")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\src\\main\\resources\\driver\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equals("Edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\driver\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        e_driver = new EventFiringWebDriver(driver);
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        //eventListener = new WebEventListener();
        //e_driver.register(eventListener);
        driver = e_driver;

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("browser.url"));
    }

    /**
     * @param method
     */
    @BeforeMethod
    public void beforeMethod(Method method) {

        ExtentTestManager.startTest(method.getName());
    }

    /**
     * @param result
     */
    @AfterMethod
    protected void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            {
                String screenShotPath = GetScreenShot.capture(driver);
                ExtentTestManager.getTest().log(LogStatus.FAIL, "Snapshot below" + ExtentTestManager.getTest().addScreenCapture(screenShotPath));
            }
            ExtentTestManager.endTest();
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
        } else {
            //ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
            String screenShotPath = GetScreenShot.capture(driver);
            //  ExtentTestManager.getTest().log(LogStatus.PASS,"Snapshot below" +test.addScreenCapture(screenShotPath));
            ExtentTestManager.getTest().log(LogStatus.PASS, "Snapshot below: " + ExtentTestManager.getTest().addScreenCapture(screenShotPath));

        }
        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();

    }
}