# JPA-SQL-EXEMPLE
Exemple basic de l'utilisation d'une implementation de l'api JPA (EclispeLink) avec une base de donnée SQL (MySql)

###Comment puis-je importer ce projet dans NetBeans ?

    Télécharger le projet https://github.com/khalidafla/JPA-SQL-EXEMPLE/archive/master.zip (Button Download Zip)

    Dézipper et enlever le suffix master du nom de dossier

    Coller le dossier dans votre workspace

    Ouvrer votre IDE : ouvrir projet -> spécifier le chemin -> OK

    Si Netbeans le reconait pas en tant qu'un projets maven, vous devez crée un projets maven pour activer les fonctionalités maven: NewProjet -> catégorie : maven, projet : java application > next.. > finish

Une fois crée essayer supprimer le projet recement crée et importer ce projet.

###Pré-requis :

- Un serveur JEE comme GlassFish (tomCat n'est pas un serveur JEE désoler)
- Une base de donnée vide.
- Un pool de connection configurer (https://computingat40s.wordpress.com/how-to-setup-a-jdbc-connection-in-glassfish/) faites attention a ce que le nom de la ressource du pool (JNDI Name) ait le meme nom que celui que j'ai utiliser dans le fichier persistence.xml si vous avez crée un pool avec un nom différents n'oublier pas de changer l'ancien(jdbc/hbtestres) par le votre.

### BD
- Dans la base de données j'enregistrer les données des articles avec leurs commentaires associées. (association OneToMany).
- Vous aller trouver les instructions d'interaction avec la base de données dans le fichier JpaTest.java, amusez vous biens a le modifier.

### Remarques
- Vous n'etes pas besoins de crée le schéma de la BD (les tables) manuelement ils seront generer a chaque execution.
