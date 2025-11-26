# App de gestion de projet perso

appliquation fait pour remplacer jira uniquement accessible depuis le mail insa

sauvegarde des info de l'application dans un document text par exemple (ou dans un document xml/json)

## commande mvn

### création d'un projet:

mvn archetype:generate \
  -DgroupId=fh.app_gestion \
  -DartifactId=App-gestion \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false

Attention à la version de la JDK dans le pom.xml

### compilation: 

mvn clean compile

### exécution:

java -cp .\target\App-gestion-1.0.jar fh.app_gestion.app.App_Gestion

### Test

mvn test

### A faire

Modifier l'emplacement ou les fichier sont enregistrer + amélioré l'affichage + créer un bouton suprimer pour retirer les tache déjà effectuer et ne pas encombré l'application
