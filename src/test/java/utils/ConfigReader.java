package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            String env = System.getProperty("env", "dev"); // dev par défaut
            String fileName = "config/config-" + env + ".properties";

            InputStream input =
                    ConfigReader.class
                            .getClassLoader()
                            .getResourceAsStream(fileName);

            if (input == null) {
                throw new RuntimeException("Fichier config introuvable : " + fileName);
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Erreur chargement config", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
