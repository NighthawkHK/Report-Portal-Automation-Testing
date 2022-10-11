package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LeftPanelMenu;
import pages.sidebars.DebugPage;

import java.util.List;

public class LaunchFilterDefinition {

    private final DebugPage debugPage = new DebugPage();

    @When("I open debug tab")
    public void openDebugTab() {
        new LeftPanelMenu().openDebugTab();
    }

    @Then("Columns with names are present on the page")
    public void verifyColumnsArePresent(DataTable dataTable) {
        List<String> columnNames = dataTable.asList();
        for (String columnName : columnNames) {
            debugPage.isColumnDisplayed(columnName);
        }
    }
}
