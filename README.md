## db setup

```docker-compose up```

and setup user and database:

```
CREATE USER 'bykusr'@'%' IDENTIFIED BY 'pwdbykpwd';
GRANT ALL PRIVILEGES ON bykdb.* TO 'bykusr'@'localhost';
```

## To run db

```docker-compose up```

## To compile jar

```mvn clean install```

## To run jar

Use 

```./prod-run.sh user pwd token```

or setup env variables:
```
MYSQL_USER
MYSQL_PWD
SECRET_TOKEN
```
and run:

```java -jar -Dspring.profiles.active=prod snapshot.jar```