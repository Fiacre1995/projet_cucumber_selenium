@smoke @login
Feature: Authentification

  Scenario: Connexion valide
    Given l'utilisateur est sur la page de login
    When il saisit des identifiants valides
    Then il est connecté avec succès

  Scenario: Mot de passe incorrect
    Given l'utilisateur est sur la page de login
    When il saisit un mot de passe invalide
    Then il reste sur la page de login avec message erreur