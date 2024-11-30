FROM node:23-alpine
LABEL maintainer="https://github.com/itiszakk"
EXPOSE ${FRONTEND_PORT}
COPY package*.json .
COPY public/ ./public/
COPY src/ ./src/
RUN npm install
CMD ["npm", "start", "build"]