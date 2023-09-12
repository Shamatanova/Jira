package com.telerikacademy.testframework;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;
import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.Utils.getWebDriver;
import static com.telerikacademy.testframework.Utils.tearDownWebDriver;
import static java.lang.String.format;

public class UserActions {

    final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public UserActions() {
        this.driver = getWebDriver();
    }

    public static void loadBrowser(String baseUrlKey) {
        getWebDriver().get(getConfigPropertyByKey(baseUrlKey));
    }

    public static void quitDriver() {
        tearDownWebDriver();
    }

    public void clickElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);

        LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void selectIssueType(String issue)
    {
        String locator = getLocatorValueByKey("jira.createIssue.issueValues");
        List<WebElement> issueTypes = driver.findElements(By.xpath(locator));
        for(WebElement type : issueTypes)
        {
            if(type.getText().equals(issue))
            {
                type.click();
                break;
            }
        }
    }

    public void selectPriority(String priority)
    {
        String locator = getLocatorValueByKey("jira.createIssue.priorityValues");
        List<WebElement> priorityOptions = driver.findElements(By.xpath(locator));
        for(WebElement option : priorityOptions)
        {
            if(option.getText().equals(priority))
            {
                option.click();
                break;
            }
        }
    }
    public void enterText(String text)

    {

    }

    public void typeValueInField(String value, String field, Object... fieldArguments) {
        String locator = getLocatorValueByKey(field, fieldArguments);
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
    }

    //############# WAITS #########
    public void waitForElementVisible(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementVisibleUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    public void waitForElementClickable(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementToBeClickableUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    //############# WAITS #########
    public void waitForElementPresent(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Initialize Wait utility with default timeout from properties
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));
        // 2. Use the method that checks for Element present
        waitForElementVisible(locator,arguments);
        // 3. Fail the test with meaningful error message in case the element is not present
        waitForElementPresenceUntilTimeout(locator, defaultTimeout, arguments);
    }

    public void assertElementPresent(String locator) {
        Assert.assertNotNull(format("Element with %s doesn't present.", locator),
            driver.findElement(By.xpath(getUIMappingByKey(locator))));
    }

    public void assertElementAttribute(String locator, String attributeName, String attributeValue) {
        // TODO: Implement the method
        // 1. Find Element using the locator value from Properties
        String xpath = getLocatorValueByKey(locator);
        WebElement element = driver.findElement(By.xpath(xpath));
        // 2. Get the element attribute
        String value = element.getAttribute(attributeName);
        // 3. Assert equality with expected value
        Assert.assertEquals(format("Element with locator %s doesn't match", attributeName), getLocatorValueByKey(attributeValue), value);
    }

    private String getLocatorValueByKey(String locator) {
        return format(getUIMappingByKey(locator));
    }

    private String getLocatorValueByKey(String locator, Object[] arguments) {
        return format(getUIMappingByKey(locator), arguments);
    }

    private void waitForElementVisibleUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        } catch (Exception exception) {
            Assert.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    public void scrollDown(String locator)
    {
        String xpath = getLocatorValueByKey(locator);
        WebElement element = driver.findElement(By.xpath(xpath));

        Actions actions = new Actions(driver);
        actions.scrollToElement(element);
      //  actions.moveToElement(element);
        actions.perform();
    }

    private void waitForElementToBeClickableUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception exception) {
            Assert.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementPresenceUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assert.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    public void hoverElement(String key, Object... arguments) {
        // TODO: Implement the method
        // 1. Get locator value from properties by key
        String locator = getLocatorValueByKey(key, arguments);
        // 2. Add Log entry for the action to be performed
        LOGGER.info("Hovering on element " + key);
        // 3. Perform a hover Action
        WebElement element = driver.findElement(By.xpath(locator));
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public String getElementAttribute(String locator, String attributeName) { // attributeName = po id, x-path..
        // TODO: Implement the method
        // 1. Find Element using the locator value from Properties
        String locatorKey = getLocatorValueByKey(locator);
        WebElement element = driver.findElement(By.xpath(locatorKey));
        // 2. Get the element attribute
        // 3. Return the expected value
        return element.getAttribute(attributeName);
    }

    public WebElement getElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);
        LOGGER.info("Hovering on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));
        return element;
    }

    public void dragAndDropElement(String fromElementLocator, String toElementLocator)
    {
        String fromLocator = getLocatorValueByKey("trello.createdCard.getCardByName");
        WebElement fromElement = driver.findElement(By.xpath(fromLocator));

        String toLocator = getLocatorValueByKey("trello.createdList.getListByName");
        WebElement toElement = driver.findElement(By.xpath(toLocator));


        Actions act = new Actions(driver);
        act.dragAndDrop(fromElement, toElement).build().perform();
//        Action dragAndDrop = act.clickAndHold(fromElement)
//                .moveToElement(toElement)
//                .release(toElement)
//                .build();
//        dragAndDrop.perform();
    }
    public void moveCardToList(WebElement card, WebElement list) {
        Actions act = new Actions(driver);
        Action dragAndDrop = act.clickAndHold(card)
                .moveToElement(list)
                .release(list)
                .build();
        dragAndDrop.perform();
    }


}
