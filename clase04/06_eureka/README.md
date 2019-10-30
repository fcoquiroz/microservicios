= Ejecución

1. Levantar primero el Eureka Server
  ```
  cd eureka_server
  ./gradlew bootRun
  ```
  Dejara corriendo el servidor de Eureka en el puerto 8761. Se puede visitar el dashboard de Eureka en `http://localhost:8761/`

2. Construir el cliente
  ```
  cd miservicio
  ./gradlew bootJar
  ```

3. Ejecutar la primer instancia del servicio
  ```
  ./client.sh 1
  ```
  Observer la consola de Eureka, para verificar que el servicio se registro correctamente.

4. Ejecutar la segunda y tercera instancia del servicio (cada ejecución en distinta terminal)
  ```
  ./client.sh 2
  ./client.sh 3
  ```