plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

dependencies {
    // core 의존성 추가
    implementation(project(":core"))
    implementation(project(":infra"))
    implementation(project(":batch"))

    implementation("org.springframework.boot:spring-boot-starter-security:3.2.5")

    // jwt
    implementation("com.auth0:java-jwt:4.3.0")

    // 개발 문서 dependency
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
}
