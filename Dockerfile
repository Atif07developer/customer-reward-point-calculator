FROM openjdk:11-jre-slim
WORKDIR /reward-points
COPY target/reward-points-0.0.1-SNAPSHOT.jar /reward-points/
EXPOSE 8080
CMD ["java","-jar","reward-points-0.0.1-SNAPSHOT.jar"]