# Change Logs

## Samedi 22 Juin 2019

### FIX #2 - Personnalisation du message à envoyer pour le rappel d'un livre
- Personnalisation des messages de rappel via batch.properties
  - Posibilité d'écrire via batch.properties, un message avec des balise qui seront remplacer par le programme par :
    - %firstname% : est remplacé par le prénom de l'utilisateur.
    - %lastname% : est remplacé par le nom de l'utilisateur.
    - %nickname% : est remplacé par le pseudonyme de l'utilisateur.
    - %bookname% : est remplacé par le nom du livre.
    - %bookref% : est remplacé par la référence du livre.
    - %begindate% : est remplacé par la date de début de location. Format dd/MM/yyyy
    - %enddate% : est remplacé par la date de fin de location. Format dd/MM/yyyy
    - \n : pour un retour à la ligne.


### FIX #4 - Ajout de commentaires sous les livres
- Ajout du système de commentaire.
  - Ajout de scripts SQL
  - Création de la table "comments" :
    - id : L'ID du commentaire
    - author_id : Clé étrangère fournisant un lien vers l'utilisateur qui a écris ce commentaire.
    - creation_time : Date et heure à laquelle a été écrit le commentaire.
    - content : Contenu du commentaire.
    - book_reference : Clé étrangère vers la référence du livre commenté.
    

### FIX #5 - Pourvoir noter les livres (1 à 5 étoiles)
- Ajout du système de notation par étoile aux commentaires.
  - Modification de la table "comments" :
    - Ajout de la colone "notation"
  - Ajout du système sur la section prévu à l'écriture des commentaire
  - Affichage de la moyenne parmis les informations.
  - Affichage de la notation choisi par le lecteur au dessus de son commentaire.

## Samedi 20 Juin 2019

### FIX #1 - Externalisation de la config
- Externalisation de la config dans un dossier référencé par une variable d'environement APP_HOME.
  - Définition d'une variable d'environement "APP_HOME" qui mène au dossier où se trouve les fichiers de configuration
  - Ajout de l'annotation @PropertySource(value = "file:${APP_HOME}/[nom_fichier].properties")

### FIX #3 - Ajout des derniers livres ajoutés - derniers livres empruntés sur la page d’accueil
- Ajout des derniers livres enpruntés et ajoutés sur la page d'acceuil.
  - Modification du fichier index.html
  - Modification du Home Controller
