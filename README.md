# SI Library 

## Contexte

Le service culturel d’une grande ville souhaite moderniser la gestion de ses bibliothèques. Pour cela, elle désire mettre à disposition de ses usagers, un système de suivi des prêts de leurs ouvrages.
Ce système comprendra :
- un site web (en responsive design) accessible aux usagers et permettant :
  - de rechercher des ouvrages et voir le nombre d’exemplaires disponibles.
  - de suivre leurs prêts en cours. Les prêts sont pour une période de 4 semaines (durée configurable).
  - de prolonger un prêt. Le prêt d’un ouvrage n’est prolongeable qu’une seule fois. La prolongation ajoute une nouvelle période de prêt (4 semaines, durée de prolongation configurable) à la période initiale.
- une application mobile iOS et Android fournissant les mêmes services que le site web.
- une application spécifique pour le personnel des bibliothèque permettant, entre autres, de gérer les emprunts et le livres rendus.
- un batch lancé régulièrement et qui enverra des mails de relance aux usagers n’ayant pas rendu les livres en fin de période de prêt

À vous de réaliser ce système !

Ce projet va être réalisé en plusieurs itérations. Vous ne réaliserez que la première(se référer au paragraphe « travail demandé » pour le
détail de cette itération).

## Contraintes
Le déploiement du système sera assuré par le personnel de la direction des systèmes d'information de la ville. Vous devez donc leur laisser la possibilité de modifier facilement les différents paramètres de configuration (URL et identifiant/mot de passe de la base de données, URL du webservice, envoi des mails...).

## Travail demandé
Vous devez réaliser :
- Une base de données PostgreSQL.
  - Un web service REST permettant :
    - l’identification des usagers via un identifiant et un mot de passe,
    - de remonter les disponibilités des ouvrages,
    - de gérer les prêts (nouveau prêt, prolongation, retour de prêt, état des prêts en cours / prolongés / non rendus à temps...).
- Une application web, basée sur un framework (Spring MVC, Spring Boot ou Apache Struts 2), servant d’interface pour les utilisateurs. Elle ne doit pas se connecter à la base de données, tout passe par le web-service.
- Un batch qui envoie un mail de relance aux usagers n’ayant pas rendu les ouvrages dont la période de prêt est terminée. Il ne doit pas se connecter à la base de données, tout passe par le web service.
- Une documentation générale (une dizaine de pages) décrivant la solution fonctionnelle et technique mise en place et sa mise en œuvre (configuration, déploiement). Ce document comprendra :
  - un diagramme de classes UML décrivant le domaine fonctionnel
  - les principales règles de gestion
  - le modèle physique de données (MPD). Si besoin, il peut faire l’objet d’un document PDF à part
  - La solution technique mise en place
  - La mise en œuvre du système (configuration, déploiement).
- Une documentation succincte expliquant comment déployer l’application. Un fichierREADME.mdsuffit. Le mentor doit être en mesure de déployer le système d’information chez lui pour le tester avant la soutenance.

Vous n’avez pas à développer les applications mobiles et le système des postes internes aux bibliothèques. Vous réaliserez le web service, l'application web et le batch en Java/JEE (JDK 8) avec les fonctionnalités décrites ci-dessus. Le web service et l'application web seront déployés sur un serveur Apache Tomcat 9.

Les différents composants seront packagés avec Maven :
- le web service : au format WAR, et un ZIP des fichiers de configuration nécessaires
- l’application web ouverte aux usagers : au format WAR, et un ZIP des fichiers de configuration nécessaires
- le batch : au format JAR exécutable ainsi qu'un fichier ZIP contenant le JAR exécutable, les dépendances Java nécessaires, les fichiers de configuration, un script shell (sh ou bash) de lancement.

## Technologies

### Projets
- Commun au trois projets :
  - Apache Maven
  - Spring Boot
  - Java JDK 8
- Web Service :
  - Spring Data
  - Swagger
- Site Client :
  - Thymeleaf
- Batch :
  - Sping Batch
  - Spring Mail

### Base de Données
- PostgreSQL v11
- PgAdmim

## Configuration et Déploiement

### Configuration
Pour configurer chaque projet, vous devez définir un variable d'environement système, où se trouve 3 fichiers :
- webservice.properties : qui permet de configurer le webservice
- client.properties : qui permet de configurer le client
- batch.properties : qui permet de configurer le batch
Les noms de ces fichiers ne sont pas modifiable !

#### Web Service
- ***server.port*** : correspond au port de connexion pour accéder au Web Service.
- ***debug*** : correspond à l’affichage des message de debug dans la console.
- ***spring.datasource.driverClassName*** : correspond au type de base de données à laquelle on va s’adresser.
- ***spring.datasource.url*** : correspond à l’url de la base de données.
- ***spring.datasource.username*** : correspond au nom d’utilisateur pour se connecter à la base de données.
- ***spring.datasource.password*** : correspond au mot de passe pour se connecter à la base de données.
- ***spring.jpa.database-platform*** : correspond au dialecte utilisé pour communiquer avec la base de données.

#### Site Client
- ***debug*** : correspond à l’affichage des message de debug dans la console.
- ***spring.application.name*** : correspond au nom de l’application
- ***library.serviceURL*** : correspond à l’url du Web Service
- ***library.defaultReservationWeeks*** : correspond au nombre de semaine avant que l’utilisateur se doit de ramener le livre. 
- ***library.extentReservationWeeks*** : correspond au nombre de semaine ajouter lors de la prolongation d’un prêt.
- ***library.numberElementByPage*** : correspond au nombre d’élément afficher par page dans l’affichage de la liste des livre et des auteurs.

#### Batch
- ***debug*** : correspond à l’affichage des message de debug dans la console.
- ***library.serviceURL*** : correspond à l’url du Web Service
- ***spring.mail.host*** : correspond à l’hôte du service mail
- ***spring.mail.port*** : correspond au port SMTP (en générale 587)
- ***spring.mail.username*** : correspond à l’identifiant du compte qui va envoyer les mail (en générale une adresse email)
- ***spring.mail.password*** : correspond au mot de passe du compte
- ***spring.mail.properties.mail.smtp.auth*** : correspond à « si oui ou non » il y a besoin d’une authentification
- ***spring.mail.properties.mail.smtp.starttls.enable*** : correspond à « si oui ou non » on active la connexion chiffré ssl/tls.  

### Déploiement
Une fois votre configuration terminer, deux choix s'offre à vous : package les projets en WAR ou en JAR

#### WAR
Si vous devez déployer sur des serveurs Apache, il vous faut packager les projets en WAR. Pour ceci, vérifier bien que dans le fichier pom.xml de chaque projet, la ligne suivante s'y trouve bien :
  <packaging>war</packaging>
Une fois fait, il ne vous suffit plus qu'à générer les WAR. Pour ceci, entrer la ligne suivant dans une console à la racine de chaque projet :
  mvn clean package

Contrairement à une application JAR, un application packager WAR à besoin d'un serveur pour être executé. Un simple serveur Apache fait l'affaire. Voici quelques liens :
- [Déployer un WAR sur Apache Tomcat](https://www.developper-jeux-video.com/deployer-fichier-war-tomcat/)
- [Déployer un WAR sur Glassfish](https://dzone.com/articles/how-deploy-war-file-using)

#### JAR
Pour déployer un JAR, c'est le même procédé, mais il vous faut remplacer la ligne :
  <packaging>war</packaging>
Par :
  <packaging>jar</packaging>

Ce fichier peut-être lancé directement et ne nécésite pas de serveur.

## Quelques images

Quelques images [ici](https://imgur.com/a/2Jz3FO8)
