package steps;

import core.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DynamicDataFactory;
import utils.JsonDataReader;
import utils.WaitUtils;


public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Given("l'utilisateur est sur la page de login")
    public void openLoginPage() {
        loginPage.open(ConfigReader.get("baseUrl"));
    }

    @When("il saisit des identifiants valides")
    public void login() {
        // Appel aux données à partir de mon fichier JSON
        String username = JsonDataReader.get("users", "validUser", "username");
        String password = JsonDataReader.get("users", "validUser", "password");

        loginPage.login(username, password);
    }

    @When("il saisit un mot de passe invalide")
    public void loginInvalidPassword() {
        // Appel aux données à partir de mon fichier JSON
        String username = JsonDataReader.get("users", "passwordInvalid", "username");
        String password = JsonDataReader.get("users", "passwordInvalid", "password");

        // Utilisé les données dynamics
        String passwordFaker = DynamicDataFactory.randomPassword();

        loginPage.login(username, passwordFaker);
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
