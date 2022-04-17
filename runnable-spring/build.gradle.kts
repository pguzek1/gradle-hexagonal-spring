plugins {
    id("org.springframework.boot") version "2.6.6"
    id("org.jetbrains.kotlin.plugin.spring") version "1.6.20"
}

group = "${group}.runnable"

dependencies {
    implementation(project(":application-ports"))
    implementation(project(":core-services"))
    implementation(project(":adapter-spring-rest-reactive"))
    implementation(project(":adapter-memory-persistence-store"))

    implementation(group = "org.springframework.boot", name = "spring-boot-starter", version = "${project.ext["springBootVersion"]}")

    testIntegrationImplementation(group = "org.springframework.boot", name= "spring-boot-starter-test", version = "${project.ext["springBootVersion"]}")
    testIntegrationImplementation(group = "io.kotest.extensions", name = "kotest-extensions-spring", version = "${project.ext["kotestSpringVersion"]}")
}
