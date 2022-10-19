package tek.sqa.framework.pages;

import org.openqa.selenium.By;

public class LoginPage {

    public final static By usernameInput = By.name("username");
    public final static By passwordInput = By.name("password");
    public final static By loginButton = By.id("loginButton");
    public final static By csrPortalLink = By.xpath("//span[contains(text(),'CSR Portal')]/..");
    public final static By errorMessage = By.xpath("//app-banner/div[contains(@class,'error')]");
}
