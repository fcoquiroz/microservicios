#!/usr/bin/env bash

if [[ -z "${PORT}" ]]; then
  echo "Hay que definir la variable 'export PORT=81'"
  exit 1
else
  echo ""
fi


file="./build/libs/app.jar"
if [ -f "$file" ]
then
  echo "$file found."
else
  ./gradlew bootJar && cp build/libs/02_docker-compose.jar src/main/docker/app/app.jar
fi

cd src/main/docker && docker-compose -p app_${PORT} up --build 

