DB_USER=%1
DB_PWD=%2
SECRET_TOKEN=%3

java -jar -Dspring.profiles.active=prod snapshot.jar