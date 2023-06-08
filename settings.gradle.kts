import org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        val kotlinVersion = "1.8.21"

        create("general") {
            version("java", "17")
            version("kotlin", kotlinVersion)
        }

        create("commonLibs") {
            version("kotlin", kotlinVersion)
            version("axion", "1.15.3")

            version("kotlin-logging", "3.0.5")
            version("logback", "1.4.7")
            version("fasterxml-uuids", "4.2.0")

            version("kotest", "5.6.2")
            version("kotlinx", "1.7.1")
            version("mockk", "1.13.5")

            // plugin
            plugin("kotlinJvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
            plugin("axion", "pl.allegro.tech.build.axion-release").versionRef("axion")

            // runtime
            library("logback", "ch.qos.logback", "logback-classic").versionRef("logback")

            // implementation
            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
            library("kotlin-std", "org.jetbrains.kotlin", "kotlin-stdlib").versionRef("kotlin")
            library("kotlin-logging", "io.github.microutils", "kotlin-logging").versionRef("kotlin-logging")
            library("fasterxml-uuids", "com.fasterxml.uuid", "java-uuid-generator").versionRef("fasterxml-uuids")

            // test
            library("kotest-runner", "io.kotest", "kotest-runner-junit5").versionRef("kotest")
            library("kotest-assertions", "io.kotest", "kotest-assertions-core").versionRef("kotest")
            library("kotest-property", "io.kotest", "kotest-property").versionRef("kotest")
            library("kotlinx-coroutines-dbg", "org.jetbrains.kotlinx", "kotlinx-coroutines-debug").versionRef("kotlinx")
            library("mockk", "io.mockk", "mockk").versionRef("mockk")

            // bundle
            bundle("logback-runtime", listOf("logback"))
            bundle("kotlin-impl", listOf("kotlin-reflect", "kotlin-std", "kotlin-logging", "fasterxml-uuids"))
            bundle("kotest-test", listOf("kotest-runner", "kotest-assertions", "kotest-property", "kotlinx-coroutines-dbg", "mockk"))
        }

        create("springLibs") {
            version("kotlin", kotlinVersion)
            version("spring-boot", "3.1.0")

            version("kotest-spring", "1.1.3")
            version("mockk-spring", "4.0.2")

            // plugin
            plugin("springKotlin", "org.jetbrains.kotlin.plugin.spring").versionRef("kotlin")
            plugin("spring", "org.springframework.boot").versionRef("spring-boot")

            // implementation
            library("spring-starter", "org.springframework.boot", "spring-boot-starter").versionRef("spring-boot")

            // testIntegration
            library("spring-test", "org.springframework.boot", "spring-boot-starter-test").versionRef("spring-boot")
            library("kotest-spring", "io.kotest.extensions", "kotest-extensions-spring").versionRef("kotest-spring")
            library("mockk-spring", "com.ninja-squad", "springmockk").versionRef("mockk-spring")

            // bundle
            bundle("kotest-spring", listOf("spring-test", "kotest-spring", "mockk-spring"))
        }

        create("wiremockLibs") {
            version("wiremock", "3.0.0-beta-8")
            version("kotest-wiremock", "1.0.3")

            // testIntegration
            library("wiremock", "com.github.tomakehurst", "wiremock").versionRef("wiremock")
            library("kotest-wiremock", "io.kotest.extensions", "kotest-extensions-wiremock").versionRef("kotest-wiremock")

            // bundle
            bundle("kotest-wiremock", listOf("wiremock", "kotest-wiremock"))
        }

        create("micronautLibs") {
            version("kotlin", kotlinVersion)
            version("micronaut-plugin", "3.7.10")

            version("micronaut-general", "3.9.3")

            // plugin
            plugin("micronaut", "io.micronaut.application").versionRef("micronaut-plugin")
            plugin("kotlin-kapt", "org.jetbrains.kotlin.kapt").versionRef("kotlin")
            plugin("kotlin-allOpen", "org.jetbrains.kotlin.plugin.allopen").versionRef("kotlin")

            // kapt
            library("micronaut-httpValidation", "io.micronaut", "micronaut-http-validation").versionRef("micronaut-general")

            // runtime
            library("jackson-module-kotlin", "com.fasterxml.jackson.module", "jackson-module-kotlin").withoutVersion()

            // implementation
            library("micronaut-httpClient", "io.micronaut", "micronaut-http-client").versionRef("micronaut-general")
            library("micronaut-jacksonDatabind", "io.micronaut", "micronaut-jackson-databind").versionRef("micronaut-general")
            library("micronaut-validation", "io.micronaut", "micronaut-validation").versionRef("micronaut-general")
            library("micronaut-runtime", "io.micronaut.kotlin", "micronaut-kotlin-runtime").withoutVersion()
            library("jakarta-annotation", "jakarta.annotation", "jakarta.annotation-api").withoutVersion()

            // bundle
            bundle("micronaut-general", listOf("micronaut-httpClient", "micronaut-jacksonDatabind", "micronaut-validation", "micronaut-runtime", "jakarta-annotation"))
            bundle("micronaut-kapt", listOf("micronaut-httpValidation"))
            bundle("micronaut-runtime", listOf("jackson-module-kotlin"))
        }
    }
}


rootProject.name = "hexagonal"


// + -------------- +
// |      core      |
// + -------------- +
include(":application-core-domain")
project(":application-core-domain").projectDir = file("application/core/domain")
project(":application-core-domain").name = "core-domain"

include(":application-core-services")
project(":application-core-services").projectDir = file("application/core/services")
project(":application-core-services").name = "core-services"


// + -------------- +
// |     ports      |
// + -------------- +
include(":application-ports-model")
project(":application-ports-model").projectDir = file("application/ports/model")
project(":application-ports-model").name = "ports-model"

include(":application-ports-driven")
project(":application-ports-driven").projectDir = file("application/ports/driven")
project(":application-ports-driven").name = "ports-driven"

include(":application-ports-driving")
project(":application-ports-driving").projectDir = file("application/ports/driving")
project(":application-ports-driving").name = "ports-driving"


// + -------------- +
// |    adapters    |
// + -------------- +
include(":application-adapter-spring-persistence-inmemory")
project(":application-adapter-spring-persistence-inmemory").projectDir = file("application/adapters/spring-persistence-inmemory")
project(":application-adapter-spring-persistence-inmemory").name = "spring-persistence-inmemory"

include(":application-adapter-spring-persistence-scylla")
project(":application-adapter-spring-persistence-scylla").projectDir = file("application/adapters/spring-persistence-scylla")
project(":application-adapter-spring-persistence-scylla").name = "spring-persistence-scylla"

include(":application-adapter-spring-rest-reactive")
project(":application-adapter-spring-rest-reactive").projectDir = file("application/adapters/spring-rest-reactive")
project(":application-adapter-spring-rest-reactive").name = "spring-rest-reactive"


// + -------------- +
// |    runnable    |
// + -------------- +
include(":spring-runnable")
project(":spring-runnable").projectDir = file("runnable/spring-runnable")
project(":spring-runnable").name = "spring-runnable"

include(":micronaut-runnable")
project(":micronaut-runnable").projectDir = file("runnable/micronaut-runnable")
project(":micronaut-runnable").name = "micronaut-runnable"
