package tek.sqa.framework.base;

import org.openqa.selenium.WebDriver;
import tek.sqa.framework.config.*;
import tek.sqa.framework.utilities.DatabaseConnectionUtility;
import tek.sqa.framework.utilities.ReadYamlFiles;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class BaseSetup {

    private static WebDriver webDriver;
    private final ReadYamlFiles environmentVariables;

    public BaseSetup() {
        // reading Env Property Yaml files here
        String filePath = System.getProperty("user.dir") + "/src/main/resources/env_config.yml";
        try {
            environmentVariables = ReadYamlFiles.getInstance(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Failed for Load environment context. check possible file path errors");
            throw new RuntimeException("Failed for Load environment context with message " + e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void setupBrowser() {
        HashMap uiProperties = environmentVariables.getYamlProperty("ui");
        System.out.println(uiProperties);
        String url = uiProperties.get("url").toString();
        Browser browser;
        switch (uiProperties.get("browser").toString().toLowerCase()) {
            case "chrome":
                if ((boolean) uiProperties.get("headless")) {
                    browser = new ChromeHeadless();
                } else {
                    browser = new ChromeBrowser();
                }
                webDriver = browser.openBrowser(url);
                break;
            case "firefox":
                browser = new FireFoxBrowser();
                webDriver = browser.openBrowser(url);
                break;
            case "edge":
                browser = new EdgeBrowser();
                webDriver = browser.openBrowser(url);
                break;
            default:
                throw new RuntimeException("Unknown Browser check environment properties");
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.of(20, ChronoUnit.SECONDS));
    }


    public void quitBrowser() {
        if (webDriver != null)
            webDriver.quit();
    }

    public DatabaseConnectionUtility getDbConnection() {
       HashMap dbProperties = this.environmentVariables.getYamlProperty("db");
       return new DatabaseConnectionUtility(
              (String) dbProperties.get("url"),
               (String) dbProperties.get("db_username"),
               (String) dbProperties.get("db_password")
       );
    }

}
