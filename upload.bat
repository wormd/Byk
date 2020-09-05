echo off
echo -- Uploading scripts
scp prod-initenv.sh wormd@46.101.187.74:/home/wormd/byk
cd backend/target
echo -- Uploading backend
scp app-0.0.1-SNAPSHOT.jar classes/application-prod.properties classes/application-dev.properties wormd@46.101.187.74:/home/wormd/byk/backend
echo -- Uploading frontend
cd ../../frontend
scp -r dist/ wormd@46.101.187.74:/home/wormd/byk/frontend
echo -- Uploading db docker file and scripts
cd ..
scp docker-compose.yaml wormd@46.101.187.74:/home/wormd/byk/
cd prod-scripts/
scp * wormd@46.101.187.74:/home/wormd/byk/
echo -- Done