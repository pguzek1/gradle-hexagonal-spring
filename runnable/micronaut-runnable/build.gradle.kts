group = "${group}.runnable"


dependencies {
//    implementation(project(":core-services"))

    kapt(dependencyNotation = micronautLibs.bundles.micronaut.kapt)
    runtimeOnly(dependencyNotation = micronautLibs.bundles.micronaut.runtime)
    implementation(dependencyNotation = micronautLibs.bundles.micronaut.general)

    testAnnotationProcessor("io.micronaut:micronaut-inject-java")

    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
}

application {
    mainClass.set("pl.alfn.project.runnable.micronaut.MicronautApplicationKt")
}

graalvmNative.toolchainDetection.set(false)

micronaut {
    version(micronautLibs.versions.micronaut.general.get())
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("pl.alfn.project.runnable.micronaut.*")
    }
}
