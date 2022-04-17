group = "${group}.runnable"

dependencies {
    implementation(project(":application-ports"))
    implementation(project(":core-services"))
    implementation(project(":adapter-spring-rest-reactive"))
    implementation(project(":adapter-memory-persistence-store"))

    implementation(group = "org.springframework.boot", name = "spring-boot-starter", version = "${project.ext["springBootVersion"]}")
}
