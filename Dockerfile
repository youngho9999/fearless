FROM gradle:8-jdk17-alpine AS builder
WORKDIR /build

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함.
COPY build.gradle settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 빌더 이미지에서 애플리케이션 빌드
COPY . /build
RUN gradle build -x test --parallel

# APP
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 빌더 이미지에서 jar 파일만 복사
COPY --from=builder /build/build/libs/feardraft-*-SNAPSHOT.jar fearless.jar

ENTRYPOINT ["java","-jar","/app/fearless.jar"]