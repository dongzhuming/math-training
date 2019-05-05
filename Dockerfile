FROM openjdk:11-jdk
ADD . /home/
EXPOSE 18000
ENTRYPOINT /home/mvnw spring-boot:run