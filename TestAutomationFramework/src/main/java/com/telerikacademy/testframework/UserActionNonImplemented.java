package com.telerikacademy.testframework;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.telerikacademy.testframework.Utils.*;
import static java.lang.String.format;

public class UserActionNonImplemented {
    final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public UserActionNonImplemented() {
        this.driver = getWebDriver();
    }
    private String getLocatorValueByKey(String locator, Object[] arguments) {
        return format(getUIMappingByKey(locator), arguments);
    }

    public void switchToIFrame(String iframe) {
        // TODO: Implement the method
        // 1. Get iframe locator value from properties by key
        String locator = getLocatorValueByKey(iframe, null);
        // 2. Add Log entry for the action to be performed
        LOGGER.info("Switching to Iframe " + iframe);
        // 3. Switch to the frame
        WebElement element = driver.findElement(By.xpath(locator));
        driver.switchTo().frame(element);
    }

    public boolean isElementPresent(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Get default timeout from properties
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));
        // 2. Initialize Wait utility
        // 3. Try to wait for element present
        return waitForElementPresentUntilTimeout(locator, defaultTimeout, arguments);
        // 4. return true/false if the element is/not present
    }

    private boolean waitForElementPresentUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private boolean waitForElementVisibleUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean isElementVisible(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Get default timeout from properties
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));
        // 2. Initialize Wait utility
        // 3. Try to wait for element visible
        return waitForElementPresentUntilTimeout(locator, defaultTimeout, arguments);
        // 4. return true/false if the element is/not visible
    }

    public void waitFor(long timeOutMilliseconds) {
        try {
            Thread.sleep(timeOutMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    //############# ASSERTS #########
    public void assertNavigatedUrl(String urlKey) {
        // TODO: Implement the method
        // 1. Get Current URL
        String currentUrl = driver.getCurrentUrl();
        // 2. Get expected url by urlKey from Properties
        String expectedUrl = getConfigPropertyByKey(urlKey);

        Assert.assertEquals(format("Expected URL %s is different than current URL.", urlKey),
                expectedUrl, currentUrl);
    }

    public void pressKey(Keys key) {
        // TODO: Implement the method
        // 1. Initialize Actions
        Actions action = new Actions(driver);
        // 2. Perform key press
        action.sendKeys(key).build().perform();
    }


    private String getLocatorValueByKey(String locator) {
        return format(getUIMappingByKey(locator));
    }
}
