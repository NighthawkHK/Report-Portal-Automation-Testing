package edu.reportportal.tests.ui;

import edu.reportportal.entities.User;
import edu.reportportal.listeners.TestNGListener;
import edu.reportportal.pages.DashboardPage;
import edu.reportportal.pages.LeftPanelMenu;
import edu.reportportal.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
@Severity(SeverityLevel.NORMAL)
public class ActionsTest extends BaseTest {

    private static final String WIDGET_NAME = "INVESTIGATED PERCENTAGE OF LAUNCHES";
    private final DashboardPage dashboardPage = new DashboardPage();

    @BeforeClass
    public void login() {
        new LoginPage().signIn(new User("default", "1q2w3e"));
        new LeftPanelMenu().openAllDashboardsTab()
                .selectDashboard("Demo Dashboard");
    }

    @Test
    @Description("Verify user are able to drag&drop component")
    public void verifyDragNDropAction() {
        int xAxisValueBeforeMove = dashboardPage.getWidgetLocation(WIDGET_NAME).getX();
        int xAxisValueForMove = xAxisValueBeforeMove > 500 ? -200 : 200;

        dashboardPage.moveWidgetByOffset(WIDGET_NAME, xAxisValueForMove, 0);
        Assert.assertEquals(dashboardPage.getWidgetLocation(WIDGET_NAME).getX(), xAxisValueBeforeMove + xAxisValueForMove);
    }

    @Test
    @Description("Verify user are able to resize component")
    public void verifyResizeAction() {
        int widthValueBeforeResize = dashboardPage.getWidgetSize(WIDGET_NAME).getWidth();

        dashboardPage.resizeWidget(WIDGET_NAME, 100, 0);
        Assert.assertEquals(dashboardPage.getWidgetSize(WIDGET_NAME).getWidth(), widthValueBeforeResize + 100);
    }
}
