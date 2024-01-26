# 第一阶段：使用 Maven 构建应用
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# 复制 Maven 依赖文件
COPY pom.xml .

# 下载依赖并生成缓存
RUN mvn dependency:go-offline

# 复制其他源代码并构建应用
COPY src src
RUN mvn package -DskipTests

## 第二阶段：使用 OpenJDK 21 作为基础镜像
FROM eclipse-temurin:21.0.2_13-jre-ubi9-minimal

# 设置工作目录
WORKDIR /app

# 复制编译好的 Spring Boot JAR 文件
COPY --from=build /app/target/demo-0.0.1.jar app.jar

# 暴露应用程序的端口
EXPOSE 8080

# 启动 Spring Boot 应用
CMD ["java", "-jar","-Dspring.profiles.active=prod", "app.jar"]
