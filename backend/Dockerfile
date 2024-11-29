FROM amazoncorretto:21
LABEL maintainer="https://github.com/itiszakk"
COPY ../build/libs/cashflow-backend.jar cashflow-backend.jar
EXPOSE ${BACKEND_PORT}
ENTRYPOINT ["java", "-jar", "cashflow-backend.jar"]