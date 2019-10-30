#!/usr/bin/env bash

# se construye la aplicación
./gradlew clean buildImage -x test

cd docker

# se levantan los contenedores configurados
# con -d el docker se va al segundo plano
#docker-compose up -d --build

docker-compose up --build