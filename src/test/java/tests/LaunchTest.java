package tests;

import entities.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LeftPanelMenu;
import pages.LoginPage;
import pages.sidebars.LaunchPage;
import tests.data.StaticDataProvider;

import java.util.Map;

import static core.WebDriverManager.getDriver;

public class LaunchTest extends BaseTest {

    private final LoginPage loginPage = new LoginPage(getDriver());
    private final LaunchPage launchPage = new LaunchPage(getDriver());
    private final LeftPanelMenu leftPanelMenu = new LeftPanelMenu(getDriver());

    @BeforeClass
    public void login() {
        loginPage.signIn(new User("default", "1q2w3e"));
        leftPanelMenu.openAllLaunchesTab();
    }

    @Test(dataProvider = "launches", dataProviderClass = StaticDataProvider.class)
    public void checkResultsOfLaunch(Integer runNumber, Map<String, Object> runInfo) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(runNumber, LaunchPage.ColumnName.TOTAL), runInfo.get("total"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(runNumber, LaunchPage.ColumnName.PASSED), runInfo.get("passed"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(runNumber, LaunchPage.ColumnName.FAILED), runInfo.get("failed"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(runNumber, LaunchPage.ColumnName.SKIPPED), runInfo.get("skipped"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(runNumber, LaunchPage.ColumnName.PRODUCT_BUG), runInfo.get("productBug"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(runNumber, LaunchPage.ColumnName.AUTO_BUG), runInfo.get("autobug"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(runNumber, LaunchPage.ColumnName.SYSTEM_ISSUE), runInfo.get("systemIssue"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(runNumber, LaunchPage.ColumnName.TO_INVESTIGATE), runInfo.get("toInvestigate"));
        softAssert.assertAll();
    }
}
