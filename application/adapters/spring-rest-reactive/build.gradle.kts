group = "${group}.adapters.spring.rest"

dependencies {
    api(project(":ports-driven"))

    implementation(springLibs.spring.starter)
}
