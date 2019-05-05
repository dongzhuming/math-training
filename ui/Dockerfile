FROM node:8
WORKDIR /home/
ADD ./ /home/
RUN npm install -g vue-cli
RUN npm install -g quasar-cli
RUN npm install
EXPOSE 18001
ENTRYPOINT quasar dev
