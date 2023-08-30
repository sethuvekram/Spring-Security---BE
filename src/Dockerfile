FROM adoptopenjdk:8-jdk-hotspot

WORKDIR /app

COPY target/myapp.jar /app/myapp.jar

EXPOSE 8080

CMD ["java", "-jar", "myapp.jar"]
