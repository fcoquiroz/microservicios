
# Corriendo `wrk` instalado de forma local

```
wrk -c 40 -t 20 -d 120s http://localhost:80${PORT}/ -s nuevo_cliente.lua
```

# Corriendo `wrk` como contenedor

```
docker run --network host --rm -v $(pwd):/data skandyla/wrk -c 20 -t 10 -d 2m -s \
  nuevo_cliente.lua  http://localhost:8081/
```

El parámetro `--network host`, le dice a Docker que elimine el aislamiento de red y que use el stack de red de la máquina huesped.