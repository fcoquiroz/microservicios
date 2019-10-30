#!/usr/bin/env bash


if [[ -z "${PORT}" ]]; then
  echo "Hay que definir la variable 'export PORT=81'"
  exit 1
else
  echo ""
fi


file="./build/libs/service.jar"
if [ -f "$file" ]
then
  echo "$file found."
else
  # se construye la aplicación
  ./gradlew clean build -x test
fi


cd build/libs

# se levantan los contenedores configurados
docker-compose -p app_${PORT} up  --build

docker-compose -p app_${PORT} stop

cd ../..
