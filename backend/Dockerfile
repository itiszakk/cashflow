FROM amazoncorretto:21-alpine
LABEL maintainer="https://github.com/itiszakk"
EXPOSE ${BACKEND_PORT}
COPY ../build/libs/cashflow-backend.jar .
ENTRYPOINT ["java", "-jar", "cashflow-backend.jar"]