package utils;

import core.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionHandler {

    private ActionHandler() {
        throw new IllegalStateException("This is Utility class.");
    }

    private static final Actions ACTIONS = new Actions(DriverSingleton.getDriver());

    public static void moveElementByOffset(final WebElement webElement, final int xOffset, final int yOffset) {
        ACTIONS.dragAndDropBy(webElement, xOffset, yOffset)
                .release()
                .build()
                .perform();
    }

    public static void resizeElement(final WebElement webElement, final int xOffset, final int yOffset) {
        ACTIONS.moveToElement(webElement)
                .clickAndHold(webElement)
                .moveByOffset(xOffset, yOffset)
                .release()
                .build()
                .perform();
    }
}
