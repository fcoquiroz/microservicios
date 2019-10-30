# Comandos

## Invocar servicio de customer

```
http :9090/customer/v1/customer name=Domingo email=domingo@dss.com
```

## Como obtener las reglas de ruteo de `Zuul`

```
http :9090/actuator/routes
```

# Docker

## Escalamiento horizontal manual

Los comandos de docker-compose, se ejecuten, en la carpeta en donde se encuentra el archivo `docker-compose.yaml`

Asumiendo que tengo una sola instancia del servicio `customerservice`, puedo hacer un `scale up` con el siguiente comando:

```
$ docker-compose up -d --scale customerservice=3
```

`customerservice`, es el nombre del `service` en la configuración de `Docker Compose`


Asumiendo que tengo 3 instancias del servicio `customerservice`, puedo hacer un `scale down` con el siguiente comando:

```
$ docker-compose up -d --scale customerservice=1
```

## Volumenes de Docker


### Listar volumenes que Docker gestiona

```
docker volume ls
```

### Inpeccionar un volumen en particular

```
docker volume inspect nombre_del_volumen
```