plugins {
    kotlin("jvm")
    kotlin("plugin.jpa")
    id("org.springframework.boot")
    kotlin("plugin.serialization") version "1.9.21"
}

dependencies {
    implementation(project(":core"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // json serializer
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-webflux
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("com.google.code.gson:gson:2.8.8")

    // mariadb
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.4")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    mainClass.set("com.zzekak.ZzekakApplication")
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    mainClass.set("com.zzekak.ZzekakApplication")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.zzekak.ZzekakApplication"
    }
}
