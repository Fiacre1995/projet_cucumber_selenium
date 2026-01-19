package hooks;

import core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utils.ScreenshotUtils;


public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();

        Allure.addAttachment(
                "Environment",
                "text/plain",
                System.getProperty("env", "dev")
        );
    }


    @After
    public void tearDown(Scenario scenario) {
        // Vérifier si le scénario a échoué
        if (scenario.isFailed()) {
            System.out.println("❌ Scénario échoué : " + scenario.getName());

            // Prendre une capture d'écran et l'attacher à Allure
            ScreenshotUtils.takeScreenshot();

            // Ajouter une description à Allure (optionnel)
            Allure.addDescription("Scénario échoué - capture d'écran générée");
        } else {
            System.out.println("✅ Scénario réussi : " + scenario.getName());
        }

        // Fermer le driver
        DriverFactory.quitDriver();
    }
}
