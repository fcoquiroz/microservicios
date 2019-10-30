FROM openjdk:8u151-slim
#Definicion de un volumen
VOLUME /tmp
ADD app.jar /app.jar

#Definicion de un puerto que abre
EXPOSE 8080
EXPOSE 8081

RUN sh -c 'touch /app.jar'
# java.security.egd es para acelerar el calculo de numeros random
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]