package tek.sqa.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CsrHomePage {

    public final static By appTitle = By.xpath("//mat-toolbar/span[1]");
    public final static By profileMenu = By.xpath("//mat-toolbar/button[2]");
    public final static By logoutButton = By.id("logout");
}
