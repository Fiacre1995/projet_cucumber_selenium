package pages;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.WaitUtils;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button.orangehrm-login-button")
    private WebElement loginButton;

    @FindBy(css = "p.oxd-alert-content-text")
    private WebElement errorMessage;

    public void open(String url) {
        driver.get(url);
    }

    public void login(String username, String password) {
        WaitUtils.waitForVisibility(usernameInput).sendKeys(username);
        WaitUtils.waitForVisibility(passwordInput).sendKeys(password);
        WaitUtils.waitForClickable(loginButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        return WaitUtils.waitForVisibility(errorMessage).isDisplayed();
        //return errorMessage.isDisplayed();
    }
}
