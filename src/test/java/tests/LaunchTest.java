package tests;

import entities.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LeftPanelMenu;
import pages.LoginPage;
import pages.sidebars.AllLaunchesPage;
import tests.data.StaticDataProvider;

import java.util.Map;

import static core.WebDriverManager.getDriver;
import static pages.sidebars.AllLaunchesPage.ColumnName;

public class LaunchTest extends BaseTest {

    private final LoginPage loginPage = new LoginPage(getDriver());
    private final AllLaunchesPage allLaunchesPage = new AllLaunchesPage(getDriver());
    private final LeftPanelMenu leftPanelMenu = new LeftPanelMenu(getDriver());

    @BeforeClass
    public void login() {
        loginPage.signIn(new User("default", "1q2w3e"));
        leftPanelMenu.openAllLaunchesTab();
    }

    @Test(dataProvider = "launches", dataProviderClass = StaticDataProvider.class)
    public void checkResultsOfLaunch(Integer runNumber, Map<String, Object> runInfo) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(runNumber, ColumnName.TOTAL), runInfo.get("total"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(runNumber, ColumnName.PASSED), runInfo.get("passed"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(runNumber, ColumnName.FAILED), runInfo.get("failed"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(runNumber, ColumnName.SKIPPED), runInfo.get("skipped"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(runNumber, ColumnName.PRODUCT_BUG), runInfo.get("productBug"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(runNumber, ColumnName.AUTO_BUG), runInfo.get("autobug"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(runNumber, ColumnName.SYSTEM_ISSUE), runInfo.get("systemIssue"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(runNumber, ColumnName.TO_INVESTIGATE), runInfo.get("toInvestigate"));
        softAssert.assertAll();
    }
}
