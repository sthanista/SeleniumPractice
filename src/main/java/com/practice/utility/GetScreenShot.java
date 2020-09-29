package com.practice.utility;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GetScreenShot {

    private GetScreenShot() { }

    public static String capture(WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File scrFile = ts.getScreenshotAs(OutputType.FILE);
        String encodedBase64 = null;
        FileInputStream fileInputStreamReader = null;
        try {
            fileInputStreamReader = new FileInputStream(scrFile);
            byte[] bytes = new byte[(int)scrFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
        } catch (FileNotFoundException e) {
            ExtentTestManager.getTest().log(LogStatus.ERROR, e.getMessage());
        }finally {
            fileInputStreamReader.close();
        }
        return "data:image/png;base64,"+encodedBase64;
    }
}
