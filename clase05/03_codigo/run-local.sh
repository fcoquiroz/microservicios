#!/usr/bin/env bash

cd registry && ./gradlew clean buildImage && cd ../gateway &&./gradlew clean buildImage && cd ../monitor && ./gradlew clean buildImage && cd ../clientes && ./gradlew clean buildImage && cd ../pedidos && ./gradlew clean buildImage

cd ..

docker-compose up 
docker-compose stop
docker-compose rm -f






