package utils;

import core.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    /**
     * Prendre une capture d'écran et l'attacher à Allure Report
     */
    public static void takeScreenshot() {
        try {
            WebDriver driver = DriverFactory.getDriver();

            if (driver == null) {
                System.out.println("⚠️ Driver est null, impossible de prendre une capture d'écran");
                return;
            }

            // Prendre la capture d'écran
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);

            // Générer un nom unique avec timestamp
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
            String attachmentName = "Screenshot_" + timeStamp + ".png";

            // Attacher à Allure Report
            Allure.addAttachment(attachmentName, "image/png",
                    new ByteArrayInputStream(screenshotBytes), "png");

            System.out.println("✅ Screenshot attaché à Allure : " + attachmentName);

        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la capture d'écran : " + e.getMessage());
            e.printStackTrace();
        }
    }
}