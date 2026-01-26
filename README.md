# Exécuté mon projet en ligne de commande 
mvn clean test

# Afficher le dashboard de allure report rattaché au projet
allure serve target/allure-results

# Exécuté mon test en précisant l'environnement 
mvn clean test -Denv=qa

# Exécuté le test en mode headless
mvn clean test -Dheadless=true

# Générer le rapport avec allure report (Mode headless)
allure generate --output target/allure-report target/allure-results

# Ouvrir le rapport avec allure report (Mode headless)
allure open target/allure-report

# Utiliser les webhook pour prendre push en compte avec Webhook
cloudflared tunnel --url http://localhost:8080