package tek.sqa.framework.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tek.sqa.framework.base.BaseSetup;

import java.time.Duration;

public class CommonUtility extends BaseSetup {

    public WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(20));
    }

    public WebElement waitTillClickable(WebElement element) {
        return this.getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    public WebElement waitTillClickable(By by) {
        return this.getWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitTillPresence(WebElement element) {
        return this.getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitTillPresence(By by) {
        return this.getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void click(WebElement element) {
        this.waitTillClickable(element).click();
    }

    public void sendText(WebElement element, String value) {
        this.waitTillPresence(element).sendKeys(value);
    }

    public String getElementText(WebElement element) {
        return this.waitTillPresence(element).getText();
    }

    public byte[] takeScreenShot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void click(By by) {
        waitTillClickable(by).click();
    }

    public void sendText(By by, String value) {
        this.waitTillPresence(by).sendKeys(value);
    }

    public String getElementText(By by) {
        return this.waitTillPresence(by).getText();
    }


}
