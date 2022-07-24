package pages;

import entities.User;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Waiter;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='login']")
    private WebElement loginInput;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Sign in with user: {0}")
    public DashboardPage signIn(User user) {
        Waiter.getExplicitWaiter(driver, 10).until(ExpectedConditions.visibilityOf(loginInput));
        loginInput.sendKeys(user.getName());
        passwordInput.sendKeys(user.getPassword());
        loginButton.click();
        return new DashboardPage(driver);
    }
}
