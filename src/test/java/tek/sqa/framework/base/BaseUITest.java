package tek.sqa.framework.base;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import tek.sqa.framework.utilities.CommonUtility;


public class BaseUITest extends CommonUtility {
    //Cucumber Hooks
    @Before
    public void setupTests() {
        super.setupBrowser();
    }

    @After
    public void closeTests(Scenario scenario) {
        if (scenario.isFailed()) {
           byte[] screenShot = takeScreenShot();
           scenario.attach(screenShot, "image/png", scenario.getName());
        }
        super.quitBrowser();
    }

}
