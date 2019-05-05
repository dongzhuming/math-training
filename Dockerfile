FROM openjdk:11-jdk
ADD . /home/
RUN chmod +x /home/mvnw
EXPOSE 18000
ENTRYPOINT /home/mvnw spring-boot:run