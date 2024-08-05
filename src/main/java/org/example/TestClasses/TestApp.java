package org.example.TestClasses;

import org.example.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;

public class TestApp extends BaseTest {

    @Test(enabled = true)
    void testHome1() throws InterruptedException {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Please check your setup method.");
        }
        WebElement box1 = driver.findElement(By.name("IntegerA"));
        WebElement box2 = driver.findElement(By.name("IntegerB"));
        int a = 12;
        int b = 23;
        CharSequence aa = Integer.toString(a);
        CharSequence bb = Integer.toString(b);
        box1.sendKeys(aa);
        box2.sendKeys(bb);
        WebElement computeBtn = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"ComputeSumButton\"]"));
        computeBtn.click();
        Thread.sleep(2000);
        String ans = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Answer\"]")).getText();
        Assert.assertEquals(Integer.parseInt(ans),a+b);
        Thread.sleep(2000);
    }

    // Handle alert
    @Test(enabled=true)
    void testMethod2(){
        WebElement alertBtn = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"show alert\"]"));
        alertBtn.click();
        if (driver.switchTo().alert().getText().contains("Cool")) {
            driver.switchTo().alert().accept();
        } else {
            driver.switchTo().alert().dismiss();
        }
    }

    //Method to handle slider
    @Test(enabled=false)
    void testMethod3(){

        WebElement slider = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"disabled button\"]")); // Adjust the locator strategy

        // Get current slider value
        String sliderValue = slider.getAttribute("value");
        System.out.println("Current slider value: " + sliderValue);

        // Calculate the start and end points for the slider
        int startX = slider.getLocation().getX();
        int startY = slider.getLocation().getY() + (slider.getSize().getHeight() / 2);
        int endX = startX + slider.getSize().getWidth();

        // Move the slider to 50%
        int moveToX = (int) (startX + (endX - startX) * 0.5);

        // Optionally, get the new slider value to verify
        sliderValue = slider.getAttribute("value");
        System.out.println("New slider value: " + sliderValue);

    }

    // Interact with map - Pinch, Zoom etc
    @Test(enabled = true)
    void testMethod4(){
        driver.hideKeyboard();
        WebElement element1 = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Test Gesture\"]"));
        element1.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeMap")));

        Actions actions = new Actions(driver);

// Perform the tap action
        actions.click(element).perform();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        int startX = 100;
        int startY = 500;
        int endX = 100;
        int endY = 100;

        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));

    }
}
