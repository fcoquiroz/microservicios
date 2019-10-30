FROM openjdk:8u181-jre-slim-stretch 
VOLUME /tmp
ADD service.jar /app.jar
ADD wait-for-it.sh /wait-for-it.sh
ADD start.sh /start.sh
RUN sh -c 'touch /app.jar'
RUN sh -c 'chmod +x /*.sh'

ENTRYPOINT ["/wait-for-it.sh","postgres:5432", "--", "/start.sh"]