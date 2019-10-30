

gradlew.bat bootRepackage

copy build\\libs\\app.jar src\\main\\docker\\app\\app.jar
cd src\\main\\docker
docker-compose up --build

