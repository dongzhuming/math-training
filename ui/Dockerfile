FROM node:8
WORKDIR /home/
RUN git clone https://github.com/dongzhuming/math-training.git
RUN npm install -g quasar-cli
RUN cd /home/math-training/ui && npm install
RUN apt-get update && apt-get install -y default-jdk
RUN echo "cd /home/math-training" >> start.sh \
&& echo "git pull" >> start.sh \
&& echo "cd /home/math-training/ui" >> start.sh \
&& echo "quasar dev &" >> start.sh
RUN echo "cd .." >> start.sh
RUN echo "./mvnw spring-boot:run" >> start.sh
RUN chmod +x start.sh
RUN chmod +x /home/math-training/mvnw
EXPOSE 18001
ENTRYPOINT ./start.sh
