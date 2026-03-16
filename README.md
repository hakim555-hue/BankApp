# Application Web Jakarta EE - Gestion de Comptes Utilisateurs

Application Web Jakarta EE pour la gestion de comptes bancaires. Architecture MVC avec JPA, EJB et JSF.

## Prérequis

- JDK 17+
- Maven 3.8+
- Serveur d'application : **Payara Server** ou **GlassFish** 7+
- MySQL 8+

## Configuration MySQL

### 1. Créer la base de données et l'utilisateur

```sql
CREATE DATABASE bankdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'bankuser'@'localhost' IDENTIFIED BY 'bankpass';
GRANT ALL PRIVILEGES ON bankdb.* TO 'bankuser'@'localhost';
FLUSH PRIVILEGES;
```

### 2. Personnaliser la connexion (optionnel)

Si vos paramètres MySQL diffèrent, modifiez `src/main/webapp/WEB-INF/glassfish-resources.xml` :

- `serverName` : hôte MySQL (défaut: localhost)
- `portNumber` : port (défaut: 3306)
- `databaseName` : nom de la base (défaut: bankdb)
- `user` / `password` : identifiants

## Déploiement

### Avec Payara/GlassFish

1. **Compiler** :
   ```bash
   mvn clean package
   ```

2. **Déployer** : Copier `target/BankEntityWebApplication.war` dans le répertoire `autodeploy` de Payara, ou utiliser la console d'administration.

3. **Accéder** : http://localhost:8080/BankEntityWebApplication/faces/index.xhtml

### Création manuelle du DataSource (si glassfish-resources ne fonctionne pas)

Dans la console Payara (Admin Console) :

1. Resources → JDBC → Connection Pools → New
   - Pool Name: `BankPersistencePool`
   - Resource Type: `javax.sql.DataSource`
   - Datasource Classname: `com.mysql.cj.jdbc.MysqlDataSource`

2. Additional Properties :
   - serverName: localhost
   - portNumber: 3306
   - databaseName: bankdb
   - user: bankuser
   - password: bankpass

3. Resources → JDBC → JDBC Resources → New
   - JNDI Name: `jdbc/bankpersistence`
   - Pool Name: BankPersistencePool

## Structure du projet

```
BankEntityWebApplication/
├── src/main/java/
│   ├── entities/
│   │   ├── Account.java          # Entité JPA
│   │   └── AccountFacade.java    # EJB Stateless
│   └── my.presentation/
│       └── AccountInfoView.java   # Bean JSF (CDI)
├── src/main/webapp/
│   ├── index.xhtml               # Page principale
│   ├── response.xhtml            # Liste des comptes
│   ├── resources/css/styles.css
│   └── WEB-INF/
│       ├── web.xml
│       ├── beans.xml
│       ├── faces-config.xml
│       └── glassfish-resources.xml
└── src/main/resources/META-INF/
    └── persistence.xml
```

## Test

1. Ouvrir http://localhost:8080/BankEntityWebApplication/faces/index.xhtml
2. Remplir : Client Name "Alice", Balance 1000
3. Cliquer "Create Account"
4. Vérifier dans MySQL : `SELECT * FROM ACCOUNT;`
