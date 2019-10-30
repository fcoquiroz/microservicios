# Dockerfile
 FROM postgres:9.6.1-alpine
# La imagen de Postgres permite agregar scripts personalizados 
# Se pueden usar para crear estructuras iniciales o cargar datos. 

COPY init-user-db.sh /docker-entrypoint-initdb.d/
