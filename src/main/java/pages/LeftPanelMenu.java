package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.sidebars.AllDashboardsPage;
import pages.sidebars.AllLaunchesPage;
import pages.sidebars.DebugPage;

public class LeftPanelMenu extends BasePage {

    private static final String MENU_ITEM_LOCATOR = "//div[contains(@class, 'sidebar')]//a[contains(@href, '%s')]";

    @Step("Open all launches tab")
    public AllLaunchesPage openAllLaunchesTab() {
        findElementByLocator(By.xpath(String.format(MENU_ITEM_LOCATOR, MenuItem.LAUNCHES.getName()))).click();
        return new AllLaunchesPage();
    }

    @Step("Open all dashboards tab")
    public AllDashboardsPage openAllDashboardsTab() {
        findElementByLocator(By.xpath(String.format(MENU_ITEM_LOCATOR, MenuItem.DASHBOARDS.getName()))).click();
        return new AllDashboardsPage();
    }

    @Step("Open debug tab")
    public DebugPage openDebugTab() {
        findElementByLocator(By.xpath(String.format(MENU_ITEM_LOCATOR, MenuItem.DEBUG.getName()))).click();
        return new DebugPage();
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
