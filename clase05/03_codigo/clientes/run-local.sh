#!/usr/bin/env sh

if [[ -z "${PORT}" ]]; then
  echo "Hay que definir la variable 'export PORT=81'"
  exit 1
else
  echo ""
fi

./gradlew clean buildImage


#Set the current user and group
export CURRENT_UID=$(id -u):$(id -g)

#docker-compose -f docker-compose-local-env.yaml up --build --abort-on-container-exit
docker-compose -f docker-compose-local-env.yaml -p c5_clientes_${PORT} up --build
docker-compose -f docker-compose-local-env.yaml -p c5_clientes_${PORT} stop
docker-compose -f docker-compose-local-env.yaml -p c5_clientes_${PORT} rm -f
