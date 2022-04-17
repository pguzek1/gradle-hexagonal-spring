group = "${group}.adapter.spring"

dependencies {
    implementation(project(":application-ports"))

    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-reactor", version = "${project.ext["kotlinxVersion"]}")
    implementation(group = "io.projectreactor.kotlin", name = "reactor-kotlin-extensions", version = "${project.ext["reactorKotlinVersion"]}")

    implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux", version = "${project.ext["springBootVersion"]}")
    implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin", version = "${project.ext["jacksonVersion"]}")

    testImplementation(group = "io.projectreactor", name = "reactor-test", version = "${project.ext["reactorVersion"]}")
}