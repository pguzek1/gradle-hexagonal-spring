group = "${group}.adapters.spring.persistence"

dependencies {
    api(project(":ports-driven"))

    implementation(springLibs.spring.starter)

    "testIntegrationImplementation"("org.testcontainers:testcontainers:1.19.0")

    "testIntegrationImplementation"("org.testcontainers:mariadb:1.19.0")
    "testIntegrationImplementation"("org.mariadb.jdbc:mariadb-java-client:3.1.4")

    "testIntegrationImplementation"("org.testcontainers:mongodb:1.19.0")

//    "testIntegrationImplementation"("io.kotest.extensions:kotest-extensions-testcontainers:1.3.4")
}
