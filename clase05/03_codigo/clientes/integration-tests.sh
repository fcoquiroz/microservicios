#!/usr/bin/env sh

if [[ -z "${PORT}" ]]; then
  echo "Hay que definir la variable 'export PORT=81'"
  exit 1
else
  echo ""
fi

#Set the current user and group
export CURRENT_UID=$(id -u):$(id -g)

# Ejecutamos los contenedores necesarios
# Se usara el codigo de salida del contenedor de la aplicacion
#        --exit-code-from app
# Si algun contenedor termina su proceso, se detienen todos los contenedores
#        --abort-on-container-exit
docker-compose -f docker-compose-integration-tests.yaml -p c5_clientes_${PORT} up --build --exit-code-from app --abort-on-container-exit
# Guardamos el codigo de salida del comando anterior
exit_code=$?

# Limpiamos todos los contenedores que levantamos
docker-compose -f docker-compose-integration-tests.yaml -p c5_clientes_${PORT} rm -f

# El codigo de salida es el de la ejecución de las pruebas
exit "$exit_code"

