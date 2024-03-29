package tests.ui;

import entities.User;
import entities.models.Launch;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LeftPanelMenu;
import pages.LoginPage;
import pages.sidebars.AllLaunchesPage;
import tests.data.TestDataProvider;

public class LaunchTest extends BaseTest {

    @BeforeClass
    public void login() {
        new LoginPage().signIn(new User("default", "1q2w3e"));
        new LeftPanelMenu().openAllLaunchesTab();
    }

    @Test(dataProvider = "launches", dataProviderClass = TestDataProvider.class)
    public void checkResultsOfLaunch(Launch launchInfo) {
        AllLaunchesPage allLaunchesPage = new AllLaunchesPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(launchInfo.getOrder(), AllLaunchesPage.ColumnName.TOTAL),
                launchInfo.getResults().get("total"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(launchInfo.getOrder(), AllLaunchesPage.ColumnName.PASSED),
                launchInfo.getResults().get("passed"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(launchInfo.getOrder(), AllLaunchesPage.ColumnName.FAILED),
                launchInfo.getResults().get("failed"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(launchInfo.getOrder(), AllLaunchesPage.ColumnName.SKIPPED),
                launchInfo.getResults().get("skipped"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(launchInfo.getOrder(), AllLaunchesPage.ColumnName.PRODUCT_BUG),
                launchInfo.getResults().get("productBug"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(launchInfo.getOrder(), AllLaunchesPage.ColumnName.AUTO_BUG),
                launchInfo.getResults().get("autobug"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(launchInfo.getOrder(), AllLaunchesPage.ColumnName.SYSTEM_ISSUE),
                launchInfo.getResults().get("systemIssue"));
        softAssert.assertEquals(allLaunchesPage.getValueFromSpecificColumn(launchInfo.getOrder(), AllLaunchesPage.ColumnName.TO_INVESTIGATE),
                launchInfo.getResults().get("toInvestigate"));
        softAssert.assertAll();
    }
}
