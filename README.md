**ResultApp - Application de Consultation des Résultats d'Examen**

![HOME](Aspose.Words.37c1dd57-da6f-493b-9b56-481784131d6f.001.png)

**Description**

**ResultApp** est une application Windows qui permet aux étudiants de consulter leurs résultats d'examen national à l'aide de leur matricule. L'application interagit avec une base de données MySQL pour récupérer et afficher les résultats.

**Fonctionnalités principales :**

- **Recherche par Matricule** : L'utilisateur saisit son matricule, et en cliquant sur "Consulter mon résultat", il obtient son résultat d'examen.
- **Affichage du Résultat** : Le résultat est présenté visuellement en vert (succès) ou en rouge (échec) avec un emoji associé (heureux ou triste).
- **Détails sur le Résultat** : Un bouton "Afficher les détails" permet d'afficher des informations supplémentaires telles que :
  - Matricule
  - Nom et prénom
  - Date de naissance
  - École
  - Moyenne obtenue sur 20
- **Taux de Réussite** : Affichage du taux de réussite global en pourcentage.
- **Navigation** : L'utilisateur peut revenir à la page d'accueil grâce à un bouton "Retour".
- **Gestion des Exceptions** : Les erreurs, telles que la mauvaise connexion à la base de données, sont gérées efficacement.
- **Tests** : L'application inclut des tests unitaires avec JUnit et AssertJ.

**Fonctionnalités Détails :**

- ![ref1]Connexion à la base de données MySQL.
- ![ref1]Affichage du taux de réussite en pourcentage.
- ![ref1]Recherche par matricule.
- ![ref1]Affichage du résultat (Succès ou Échec) avec code couleur et emoji.
- ![ref1]Détails supplémentaires sur l'étudiant après clic sur "Afficher les détails".
- ![ref1]Retour à la page d'accueil.
- ![ref1]Tests unitaires avec JUnit et AssertJ.
- ![ref1]Gestion d'erreurs et exceptions.

**Technologies Utilisées :**

- **Java (JavaFX)** : Pour l'interface utilisateur.
- **JDBC (Java Database Connectivity)** : Pour la connexion à la base de données.
- **MySQL** : Base de données pour stocker les résultats d'examen.

**Outils :**

- **Eclipse IDE** : Environnement de développement pour écrire le code.
- **Scene Builder** : Outil graphique pour la conception des interfaces JavaFX.
- **MySQL Workbench** : Outil pour la gestion de la base de données.

**Utilisation :**

1. Cloner le dépôt et ouvrir le projet dans **Eclipse** ou **IntelliJ**.
2. Ajouter les bibliothèques suivantes :
   a. **JavaFX** pour les interfaces graphiques.
   b. **MySQL Connector/J** pour la connexion à la base de données.
   c. **JUnit 5** pour les tests unitaires.
   d. **AssertJ** pour des assertions plus expressives.

[ref1]: Aspose.Words.37c1dd57-da6f-493b-9b56-481784131d6f.002.png
