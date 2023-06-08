group = "${group}.core"

dependencies {
    api(project(":core-domain"))
    api(project(":ports-driven"))
    api(project(":ports-driving"))
}
