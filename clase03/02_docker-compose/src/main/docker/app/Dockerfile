FROM adoptopenjdk:11.0.4_11-jre-openj9-0.15.1

EXPOSE 8080

ADD app.jar /app.jar
ADD wait-for-it.sh /wait-for-it.sh
ADD start.sh /start.sh
RUN sh -c 'chmod +x /*.sh'

#ENTRYPOINT ["java", "-jar", "/app.jar"]
ENTRYPOINT ["/wait-for-it.sh","postgres:5432", "--", "/start.sh"]