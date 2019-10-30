# Pasos para generar la imagen

1. Generamos el jar de la aplicación: `./gradlew clean build`
2. [Opcional] Hacemos login al DockerHub: `docker login`
3. Movernos al directorio de salida de construcción.: `cd build/libs`
4. Construimos la imagen: `docker build -t $usuario/api-rest-demo:0.0.1 .`
5. Empujar la imagen al DockerHub: `docker push $usuario/api-rest-demo:0.0.1`
