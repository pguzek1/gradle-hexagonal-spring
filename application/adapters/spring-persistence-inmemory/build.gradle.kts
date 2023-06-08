group = "${group}.adapters.spring.persistence"

dependencies {
    api(project(":ports-driven"))

    implementation(springLibs.spring.starter)

    "testIntegrationImplementation"("org.testcontainers:testcontainers:1.17.6")

    "testIntegrationImplementation"("org.testcontainers:mariadb:1.18.3")
    "testIntegrationImplementation"("org.mariadb.jdbc:mariadb-java-client:3.1.4")

    "testIntegrationImplementation"("org.testcontainers:mongodb:1.17.6")

//    "testIntegrationImplementation"("io.kotest.extensions:kotest-extensions-testcontainers:1.3.4")
}
