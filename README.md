## db setup

```docker-compose up```

and setup user and database:

```
CREATE USER 'youruserhere'@'%' IDENTIFIED BY 'youpwdhere';
GRANT ALL PRIVILEGES ON db.* TO 'youruserhere'@'localhost';
```

## To run db

```docker-compose up```

## To compile backend jar

```mvn clean install```

## To compile frontend

```npm run ng build -- --prod --base-href /byk/ --deploy-url ./```

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

```java -jar -Dspring.profiles.active=dev snapshot.jar```
