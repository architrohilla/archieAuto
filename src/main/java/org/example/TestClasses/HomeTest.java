package org.example.TestClasses;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.example.base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class HomeTest extends BaseTest {



    /*
    Method name : Login method
    Purpose : To login into Private client app
     */
    @Test(enabled=true)
    void testMethod1(){

        System.out.println(driver.getContextHandles());
        switchToWebView(driver);
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeTextField[@name=\"loginUsername\"]")));

        WebElement password = driver.findElement(AppiumBy.iOSNsPredicateString("name == 'passwordTextField'"));
//        username.click();
        username.sendKeys("test@projectyx.com");
//        password.click();
        password.sendKeys("projectyx");
        WebElement signInBtn = driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"SIGN IN\"]"));
        signInBtn.click();

        handlePermissionDialogs();

    }

    private static void switchToWebView(IOSDriver driver) {
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); // Print available contexts
            if (contextName.contains("WEBVIEW")) {
                driver.context(contextName);
                System.out.println("Switched to context: " + contextName);
                return; // Exit the method once switched to a WebView context
            }
        }
        System.out.println("No WebView context found.");
    }

    // Close icloud pop up and Select Mode
    @Test
    void testMethod2(){

        try {
            // Locate and interact with the 'Don't Save' button (example XPath, adjust as needed)
            WebElement dontSaveButton = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Not Now\"]"));
            dontSaveButton.click();
        } catch (Exception e) {
            System.out.println("No iCloud password popup found.");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement clientMode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Client Mode\"]")));
        clientMode.click();
    }

    @Test
    void testMethod3(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement closeTips = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther[@name=\"TipsViewController\"]/XCUIElementTypeOther[1]")));
        closeTips.click();
    }


    //Add an item to closet
    @Test
    void testMethod4(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement forYou = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeButton[@name=\"FOR YOU\"]")));
        forYou.click();
        WebElement closetMore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@value='Closet']/following-sibling::XCUIElementTypeStaticText[@value='MORE']\n")));
        closetMore.click();
        WebElement plusBtn = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Add\"]"));
        plusBtn.click();
        WebElement addItem = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Add An Item\"]"));
        addItem.click();
        WebElement addPic = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Picture\"]"));
        addPic.click();
        WebElement itemNameText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeTextView[@name=\"quickAddItemNameTextView\"]")));
        itemNameText.sendKeys("airpods");
        driver.hideKeyboard();
        WebElement category = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Category\"]"));
        category.click();
        WebElement selectCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeNavigationBar[@name=\"CATEGORY\"]")));
        WebElement tapBeauty = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Shoes\"]"));
        tapBeauty.click();
        WebElement beautySubCat = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Pumps\"]"));
        beautySubCat.click();
        WebElement selectDesigner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Select Designer\"]")));
        selectDesigner.click();
        WebElement designerSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther//XCUIElementTypeTextField[@value=\"Search Designers\"]")));
        designerSearch.sendKeys("A Golde");
        WebElement designerClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther[@name=\"designerClicked\"])[1]")));
        designerClick.click();
        WebElement selectSize = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Select Size\"]"));
        selectSize.click();
        WebElement selectUK = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"UK\"]"));
        selectUK.click();


    }

    private static void handlePermissions(IOSDriver driver) {
        // Example for handling permission dialog
        try {
            WebElement allowButton = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Allow']"));
            allowButton.click();
        } catch (WebDriverException e) {
            // Permission dialog not present
        }
    }

    public void handlePermissionDialogs() {
        try {
            // Perform actions in the app that trigger permission dialogs

            // Handle location permission dialog
            handleAlert("Allow While Using App");

            // Handle camera permission dialog
            handleAlert("OK");

            // Handle notification permission dialog
            handleAlert("Allow");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleAlert(String buttonText) {
        try {
            // Switch to the alert
            Alert alert = driver.switchTo().alert();
            // Find the button by its text and click it
            WebElement allowButton = driver.findElement(By.name(buttonText));
            allowButton.click();
        } catch (WebDriverException e) {
            System.out.println("No alert found with text: " + buttonText);
        }
    }

    private static void selectLastPhoto(IOSDriver driver) throws InterruptedException {
        // Example for navigating to the photo gallery and selecting the last photo
        WebElement photoButton = driver.findElement(By.id("photoButtonId"));
        photoButton.click();

        // Wait for the photo gallery to load
        Thread.sleep(3000); // Adjust the sleep duration as needed

        // Find and select the last photo
        WebElement lastPhoto = driver.findElement(By.xpath("//XCUIElementTypeCell[last()]"));
        lastPhoto.click();
    }
}
