FROM node:8
WORKDIR /home/
RUN git clone https://github.com/dongzhuming/math-training.git
RUN npm install -g quasar-cli
RUN cd /home/math-training/ui && npm install
RUN apt-get update && apt-get install -y openjdk-11-jdk
RUN echo "cd /home/math-training/ui" >> start.sh
RUN echo "quasar dev &" >> start.sh
RUN echo "cd .." >> start.sh
RUN echo "./mvnw spring-boot:run" >> start.sh
RUN chmod +x start.sh
RUN chmod +x mvnw
EXPOSE 18001
ENTRYPOINT ./start.sh
