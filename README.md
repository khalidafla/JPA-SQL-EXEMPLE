# JPA-SQL-EXEMPLE
Exemple basic de l'utilisation d'une implementation de l'api JPA (EclispeLink) avec une base de donnée SQL (MySql)

Pré-requis :

- Un serveur JEE comme GlassFish (tomCat n'est pas un serveur JEE désoler)
- Une base de donnée vide.
- Un pool de connection configurer (https://computingat40s.wordpress.com/how-to-setup-a-jdbc-connection-in-glassfish/) faites attention a ce que le nom de la ressource du pool (JNDI Name) ait le meme nom que celui que j'ai utiliser dans le fichier persistence.xml si vous avez crée un pool avec un nom différents n'oublier pas de changer l'ancien(jdbc/hbtestres) par le votre.

### BD
- Dans la base de données j'enregistrer les données des articles avec leurs commentaires associées. (association OneToMany).
- Vous aller trouver les instructions d'interaction avec la base de données dans le fichier JpaTest.java, amusez vous biens a le modifier.

### Remarques
- Vous n'etes pas besoins de crée le schéma de la BD (les tables) manuelement ils seront generer a chaque execution.
