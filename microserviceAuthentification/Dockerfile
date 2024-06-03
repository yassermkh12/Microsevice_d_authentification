
FROM openjdk:17-jdk-alpine

ADD target/spring-boot-microservice-authentication.jar spring-boot-microservice-authentication.jar

# Exposer le port sur lequel l'application va tourner
EXPOSE 8080

# Définir la commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "spring-boot-microservice-authentication.jar"]