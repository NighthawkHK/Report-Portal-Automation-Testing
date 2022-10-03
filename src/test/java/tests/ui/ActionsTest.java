package tests.ui;

import entities.User;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LeftPanelMenu;
import pages.LoginPage;

import static core.WebDriverManager.getDriver;

@Listeners(TestListener.class)
@Severity(SeverityLevel.NORMAL)
public class ActionsTest extends BaseTest {

    private final LoginPage loginPage = new LoginPage(getDriver());
    private final LeftPanelMenu leftPanelMenu = new LeftPanelMenu(getDriver());
    private static final String WIDGET_NAME = "INVESTIGATED PERCENTAGE OF LAUNCHES";

    @BeforeClass
    public void login() {
        loginPage.signIn(new User("default", "1q2w3e"));
        leftPanelMenu.openAllDashboardsTab()
                .selectDashboard("Demo Dashboard");
    }

    @Test
    @Description("Verify user are able to dragNdrop component")
    public void verifyDragNDropAction() {
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        int xAxisValueBeforeMove = dashboardPage.getWidgetLocation(WIDGET_NAME).getX();
        int xAxisValueForMove = xAxisValueBeforeMove > 500 ? -200 : 200;

        dashboardPage.moveWidgetByOffset(WIDGET_NAME, xAxisValueForMove, 0);
        Assert.assertEquals(dashboardPage.getWidgetLocation(WIDGET_NAME).getX(), xAxisValueBeforeMove + xAxisValueForMove);
    }

    @Test
    @Description("Verify user are able to resize component")
    public void verifyResizeAction() {
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        int widthValueBeforeResize = dashboardPage.getWidgetSize(WIDGET_NAME).getWidth();

        dashboardPage.resizeWidget(WIDGET_NAME, 100, 0);
        Assert.assertEquals(dashboardPage.getWidgetSize(WIDGET_NAME).getWidth(), widthValueBeforeResize + 100);
    }
}
