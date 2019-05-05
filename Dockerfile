FROM openjdk:11-jdk
ADD . /home/
RUN chmod +x /home/mvnw
WORKDIR /home
EXPOSE 18000
ENTRYPOINT ./mvnw spring-boot:run