# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Queue 관련 개인 프로젝트. Spring Boot 3.5.x (SNAPSHOT) 기반 웹 애플리케이션.

## Tech Stack

- Java 17
- Spring Boot 3.5.11-SNAPSHOT (spring-boot-starter-web)
- Gradle 8.14.4 (Groovy DSL)
- Lombok
- JUnit 5 (spring-boot-starter-test)

## Build & Run Commands

```bash
# 빌드
./gradlew build

# 애플리케이션 실행
./gradlew bootRun

# 전체 테스트 실행
./gradlew test

# 단일 테스트 클래스 실행
./gradlew test --tests "com.juno.queue.SomeTest"

# 단일 테스트 메서드 실행
./gradlew test --tests "com.juno.queue.SomeTest.someMethod"

# 클린 빌드
./gradlew clean build
```

## Architecture

- Base package: `com.juno.queue`
- Entry point: `QueueApplication.java`
- Spring Initializr로 생성된 초기 상태이며, 기능 구현이 추가될 예정

## Notes

- Spring snapshot repository(`https://repo.spring.io/snapshot`)를 사용 중이므로 네트워크 연결 필요
- Lombok 사용 시 IDE annotation processing 활성화 필요