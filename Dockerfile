FROM amazoncorretto:21
LABEL maintainer="https://github.com/itiszakk"
COPY build/libs/cashflow.jar cashflow.jar
EXPOSE ${BACKEND_PORT}
ENTRYPOINT ["java", "-jar", "cashflow.jar"]