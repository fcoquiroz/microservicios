#!/usr/bin/env bash

if [[ -z "${PORT}" ]]; then
  echo "Hay que definir la variable 'export PORT=81'"
  exit 1
else
  echo ""
fi

#./gradlew bootRepackage && cp build/libs/app.jar src/main/docker/app/app.jar && 
cd src/main/docker && docker-compose -p app_${PORT} stop

