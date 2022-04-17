plugins {
    id("org.springframework.boot") version "2.6.6"
    id("org.jetbrains.kotlin.plugin.spring") version "1.6.20"
}

group = "${group}.adapter.spring"

dependencies {
    implementation(project(":application-ports"))

    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-reactor", version = "${project.ext["kotlinxVersion"]}")
    implementation(group = "io.projectreactor.kotlin", name = "reactor-kotlin-extensions", version = "${project.ext["reactorKotlinVersion"]}")

    implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux", version = "${project.ext["springBootVersion"]}")
    implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin", version = "${project.ext["jacksonVersion"]}")

    testImplementation(group = "io.projectreactor", name = "reactor-test", version = "${project.ext["reactorVersion"]}")

    testIntegrationImplementation(group = "org.springframework.boot", name= "spring-boot-starter-test", version = "${project.ext["springBootVersion"]}")
    testIntegrationImplementation(group = "io.kotest.extensions", name = "kotest-extensions-spring", version = "${project.ext["kotestSpringVersion"]}")
    testIntegrationImplementation(group = "com.ninja-squad", name = "springmockk", version = "${project.ext["mockkSpringVersion"]}")
}