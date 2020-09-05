## db setup

```docker-compose up```

and setup user and database:

```
CREATE USER 'bykusr'@'%' IDENTIFIED BY 'pwdbykpwd';
GRANT ALL PRIVILEGES ON bykdb.* TO 'bykusr'@'localhost';
```

## To run db

```docker-compose up```

## To compile backend jar

```mvn clean install```

## To run backend jar

Use 

```./prod-run.sh user pwd token```

or setup env variables:
```
DB_USER
DB_PWD
SECRET_TOKEN
```
and run:

```java -jar -Dspring.profiles.active=prod snapshot.jar```