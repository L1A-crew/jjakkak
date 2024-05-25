plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-web:3.2.5")
    api("org.springframework.boot:spring-boot-starter-security:3.2.5")

    // 직렬화 라이브러리
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // 인증 관련 라이브러리 의존성 추가
    api("com.auth0:java-jwt:4.3.0")

    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
}
