FROM openjdk:11-jdk
ADD . /home/
EXPOSE 18000
ENTRYPOINT cd /home/mvnw spring-boot:run