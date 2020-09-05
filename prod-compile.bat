echo off
cd backend/
echo -- Compiling backend..
.\mvnw clean install -DskipTests
echo -- Done
cd ../frontend
echo -- Compiling frontend..
npm run ng build --prod