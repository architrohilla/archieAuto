package org.example.base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BaseTest {
    protected static IOSDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException{
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        XCUITestOptions options = new XCUITestOptions();
        options.usePrebuiltWda();
        options.setPlatformName("iOS");
//        options.setPlatformVersion("17.5");
//        options.setDeviceName("iPhone 15");
        options.setUdid("00008130-000C54D01142001C");
//        options.setApp("/Users/shubhamgoyal/Library/Developer/Xcode/DerivedData/TestApp-dquombjfyeyjhianesqajpyhpsrj/Build/Products/Debug-iphoneos/TestApp.app");
//      options.setApp("/Users/shubhamgoyal/Downloads/my-demo-app-ios-main.zip");
//        options.setApp("/Users/shubhamgoyal/Library/Developer/Xcode/DerivedData/TestApp-dquombjfyeyjhianesqajpyhpsrj/Build/Products/Debug-iphonesimulator/TestApp.app");
        options.setBundleId("com.ProjectyxLLC.Projectyx.Appstore");
//        options.setBundleId("com.archie.TestApp");
        options.setAutomationName("XCUITest");
        options.usePrebuiltWda();
//        options.setShowXcodeLog(true);
        String appiumServerUrl = "http://0.0.0.0:4723";
        URI uri = URI.create(appiumServerUrl);
        URL url = uri.toURL();
        driver = new IOSDriver(url, options);

    }

    @Test(enabled=false)
    void testMethod1() throws InterruptedException {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Please check your setup method.");
        }
        WebElement box1 = driver.findElement(By.name("IntegerA"));
        WebElement box2 = driver.findElement(By.name("IntegerB"));
        int a = 12;
        int b = 23;
        box1.sendKeys("12");
        box2.sendKeys("23");
        WebElement computeBtn = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"ComputeSumButton\"]"));
        computeBtn.click();
        Thread.sleep(2000);
        String ans = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Answer\"]")).getText();
        Assert.assertEquals(Integer.parseInt(ans),a+b);
        driver.getPageSource();
        Thread.sleep(5000);
        driver.hideKeyboard();
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}