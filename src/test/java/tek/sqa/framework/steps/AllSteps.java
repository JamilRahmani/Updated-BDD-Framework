package tek.sqa.framework.steps;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tek.sqa.framework.pages.CsrHomePage;
import tek.sqa.framework.pages.LoginPage;
import tek.sqa.framework.utilities.CommonUtility;
import tek.sqa.framework.utilities.DatabaseConnectionUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllSteps extends CommonUtility {

    private DatabaseConnectionUtility dbConnection;
    private List<Map<String, Object>> queryResult = new ArrayList<>();


    @Given("login as csr to insurance app with username {string} and password {string}")
    public void login_as_csr_to_insurance_app_with_username_and_password(String username, String password) {
        click(LoginPage.csrPortalLink);
        sendText(LoginPage.usernameInput, username);
        sendText(LoginPage.passwordInput, password);
        click(LoginPage.loginButton);
    }

    @Then("assert application title {string}")
    public void assert_application_title(String expectedTitle) {
        String appTitle = getElementText(CsrHomePage.appTitle);
        Assert.assertEquals(expectedTitle, appTitle);
    }

    @When("Login as CSR with db result")
    public void loginAsCSRWithDbResult() {
        if (this.queryResult.isEmpty()){
            throw new RuntimeException("No Result for query, check possible issue with query");
        }

       String username = this.queryResult.get(0).get("username").toString();
        login_as_csr_to_insurance_app_with_username_and_password(username, "tek_supervisor");
    }

    @Given("create database connection")
    public void createDatabaseConnection() {
        this.dbConnection = super.getDbConnection();
    }

    @When("execute query {string}")
    public void executeQuery(String query) {
        this.queryResult = dbConnection.convertResultToMap(query);
    }

    @When("print result")
    public void printResult() {
        System.out.println("Print result set ");
        System.out.println(this.queryResult);
    }

}
