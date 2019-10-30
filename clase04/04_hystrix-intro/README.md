# Ejecución
```
./gradlew clean bootRun

#Windows

gradlew.bat clean bootRun
```

## Comando de Hystrix sin RxJava

### Comando bloqueante
Para ejecutar de forma bloqueante el Hystrix Command (default) 
```
http :8080/sinrx/"microservices developer"
```
Lo anterior ejecutará de forma bloqueante el comando de Hystrix

### Comando NO bloqueante

Si se desea ejecutar de forma no bloqueante el comando de Hystrix
```
http :8080/sinrx/"microservices developer"\?modo=futuro
```

## Comando de Hystrix con RxJava


```
http :8080/rx/"microservices developer"
```

Aqui ejecutará 3 comando de Hystrix de forma paralela, el tiempo combinado máximo de respuesta siemrpe será el de mayor tiempo de ejecución de los 3 comandos. 


# Dashboard

Podemos ver el dashboard de métricas de Hystrix en `http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Factuator%2Fhystrix.stream`

# Pruebas de carga

Podemos realizar pruebas de carga con [`wrk`] [https://github.com/wg/wrk]

## Con el valor default de request concurrentes (10)

1. Carga sencilla:
  `wrk -c 2 -d 20s -t 2 http://localhost:8080/rx/developer`
2. Un poco de mas carga
  `wrk -c 3 -d 20s -t 2 http://localhost:8080/rx/developer`
3. Llevarlo al limite
  `wrk -c 4 -d 20s -t 2 http://localhost:8080/rx/developer`

## Aumento de los request concurrentes a 30

1. Carga sencilla:
  `wrk -c 2 -d 20s -t 2 http://localhost:8080/rx/developer`
2. Un poco de mas carga
  `wrk -c 6 -d 20s -t 2 http://localhost:8080/rx/developer`
3. Más carga
  `wrk -c 10 -d 20s -t 2 http://localhost:8080/rx/developer`
4. Llevarlo al limite
  `wrk -c 12 -d 20s -t 2 http://localhost:8080/rx/developer`