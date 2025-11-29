# مرحله 1: ساخت (Build Stage)
FROM eclipse-temurin:17-jdk-slim as builder
WORKDIR /app
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw clean package -DskipTests

# مرحله 2: اجرای نهایی (Runtime Stage)
FROM eclipse-temurin:17-jre-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
