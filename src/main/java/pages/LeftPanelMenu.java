package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.sidebars.AllDashboardsPage;
import pages.sidebars.AllLaunchesPage;

public class LeftPanelMenu extends BasePage {

    private static final String MENU_ITEM_LOCATOR = "//div[contains(@class, 'sidebar')]//a[contains(@href, '%s')]";

    public LeftPanelMenu(WebDriver driver) {
        super(driver);
    }

    @Step("Open all launches tab")
    public AllLaunchesPage openAllLaunchesTab() {
        findElementByLocator(By.xpath(String.format(MENU_ITEM_LOCATOR, MenuItem.LAUNCHES.getName()))).click();
        return new AllLaunchesPage(driver);
    }

    @Step("Open all dashboards tab")
    public AllDashboardsPage openAllDashboardsTab() {
        findElementByLocator(By.xpath(String.format(MENU_ITEM_LOCATOR, MenuItem.DASHBOARDS.getName()))).click();
        return new AllDashboardsPage(driver);
    }

    private enum MenuItem {

        DASHBOARDS("dashboard"),
        LAUNCHES("launches"),
        FILTERS("filters"),
        DEBUG("userdebug"),
        PROJECT_MEMBERS("members");

        private final String name;

        MenuItem(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
