package tests.ui;

import entities.User;
import entities.models.Launch;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LeftPanelMenu;
import pages.LoginPage;
import pages.sidebars.LaunchPage;
import tests.data.StaticDataProvider;

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
    public void checkResultsOfLaunch(Launch launchInfo) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(launchInfo.getOrder(), LaunchPage.ColumnName.TOTAL),
                launchInfo.getResults().get("total"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(launchInfo.getOrder(), LaunchPage.ColumnName.PASSED),
                launchInfo.getResults().get("passed"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(launchInfo.getOrder(), LaunchPage.ColumnName.FAILED),
                launchInfo.getResults().get("failed"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(launchInfo.getOrder(), LaunchPage.ColumnName.SKIPPED),
                launchInfo.getResults().get("skipped"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(launchInfo.getOrder(), LaunchPage.ColumnName.PRODUCT_BUG),
                launchInfo.getResults().get("productBug"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(launchInfo.getOrder(), LaunchPage.ColumnName.AUTO_BUG),
                launchInfo.getResults().get("autobug"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(launchInfo.getOrder(), LaunchPage.ColumnName.SYSTEM_ISSUE),
                launchInfo.getResults().get("systemIssue"));
        softAssert.assertEquals(launchPage.getValueFromSpecificColumn(launchInfo.getOrder(), LaunchPage.ColumnName.TO_INVESTIGATE),
                launchInfo.getResults().get("toInvestigate"));
        softAssert.assertAll();
    }
}
