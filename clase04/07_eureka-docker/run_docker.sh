#!/usr/bin/env bash

cd services/registry/
./gradlew clean buildImage

cd ../..

cd services/miservicio/
./gradlew clean buildImage

cd ../../docker

# se levantan los contenedores configurados
docker-compose up --build

docker-compose stop
docker-compose kill
docker-compose rm -f

cd ..