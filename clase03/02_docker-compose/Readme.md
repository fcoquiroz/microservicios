# Guardar docker image en archivo

```
docker save "tag imagen" -o archivo
```

# Cargar imagen de docker desde archivo

```
docker load -i archivo
```

# Limpieza

```
docker system prune
```

# Borrar imagen

```
docker rmi "tag imagen"
```

# Descargar de Registry

```
docker pull "tag imagen"
```

# Lista todos los contenedores, incluso los detenidos

```
docker ps -a
```

# Levanta contenedores especificados en docker-compose.yml

## --build construye los contenedores

```
docker-compose up --build

```
# Contenedores ejecutandose que estan especificados en docker-compose.yml

```
docker-compose ps
docker-compose stop id_contenedor
docker-compose up -d id_contenedor
```

# Herramientas
## ctop
https://github.com/bcicen/ctop


## Base de datos

```
psql -h localhost -U c7 -W c7_data
```

