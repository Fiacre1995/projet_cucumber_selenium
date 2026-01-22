package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JsonDataReader {

    private static final Map<String, JsonNode> jsonFiles = new HashMap<>();

    static {
        loadAllJsonFiles();
    }

    private static void loadAllJsonFiles() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Path dataDir = Paths.get("src/test/resources/testdata");

            Files.list(dataDir)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> {
                        try {
                            String fileName = path.getFileName().toString().replace(".json", "");
                            JsonNode jsonNode = mapper.readTree(path.toFile());
                            jsonFiles.put(fileName, jsonNode);
                        } catch (Exception e) {
                            throw new RuntimeException("Erreur chargement JSON: " + path, e);
                        }
                    });

        } catch (Exception e) {
            throw new RuntimeException("Impossible de charger les fichiers JSON", e);
        }
    }

    public static String get(String file, String key, String field) {
        JsonNode fileNode = jsonFiles.get(file);

        if (fileNode == null) {
            throw new RuntimeException("Fichier JSON introuvable: " + file);
        }

        JsonNode valueNode = fileNode.get(key);

        if (valueNode == null) {
            throw new RuntimeException("Clé introuvable: " + key + " dans " + file);
        }

        JsonNode fieldNode = valueNode.get(field);

        if (fieldNode == null) {
            throw new RuntimeException("Champ introuvable: " + field);
        }

        return fieldNode.asText();
    }
}
