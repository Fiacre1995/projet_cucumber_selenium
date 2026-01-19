package steps;

import core.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import utils.ConfigReader;


public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Given("l'utilisateur est sur la page de login")
    public void openLoginPage() {
        loginPage.open(ConfigReader.get("baseUrl"));
    }

    @When("il saisit des identifiants valides")
    public void login() {
        loginPage.login("Admin", "admin123");
    }

    @When("il saisit un mot de passe invalide")
    public void loginInvalidPassword() {
        loginPage.login("Admin", "admin");
    }

    @Then("il est connecté avec succès")
    public void verifyLogin() {
        WebDriver driver = DriverFactory.getDriver();
        String expectedUrl = ConfigReader.get("dashbordUrl");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "L'URL actuelle est incorrecte !");
    }

    @Then("il reste sur la page de login avec message erreur")
    public void verifyLoginInvalidPassword() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }
}
