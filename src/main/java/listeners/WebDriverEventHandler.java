package listeners;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebDriverEventHandler implements WebDriverListener {

    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
    }
}
