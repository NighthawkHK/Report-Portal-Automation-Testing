package steps;

import entities.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.LeftPanelMenu;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WidgetStepDefinition {

    private final User testUser = new User("default", "1q2w3e");

    @Given("I am logged in")
    public void login() {
        new LoginPage().signIn(testUser);
    }

    @Given("I open a dashboard with name {string}")
    public void openDashboardPage(String dashboardName) {
        new LeftPanelMenu()
                .openAllDashboardsTab()
                .selectDashboard(dashboardName);
    }

    @When("I add a new {string} widget with name {string}")
    public void addWidget(String widgetType, String widgetName) {
        new DashboardPage()
                .clickAddNewWidget()
                .selectType(widgetType)
                .clickNextStepButton()
                .chooseFilter()
                .clickNextStepButton()
                .setWidgetName(widgetName)
                .clickAddButton();
    }

    @Then("A widget with name {string} should be displayed on the page")
    public void verifyWidgetPresence(String widgetName) {
        assertThat(new DashboardPage().isWidgetDisplayed(widgetName), is(true));
    }
}
