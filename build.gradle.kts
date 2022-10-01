import org.gradle.api.JavaVersion.VERSION_17
import org.gradle.api.attributes.TestSuiteType.INTEGRATION_TEST
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.kotlin.plugin.spring") version "1.7.10" apply false
    id("org.springframework.boot") version "2.7.2" apply false
    id("pl.allegro.tech.build.axion-release") version "1.14.0"
    id("jvm-test-suite")
    id("idea")
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "pl.allegro.tech.build.axion-release")
    apply(plugin = "jvm-test-suite")
    apply(plugin = "idea")

    group = "org.example.project"
    version = scmVersion.version

    ext.apply {
        set("javaVersion", VERSION_17)
        set("kotlinVersion", "1.7.10")

        set("kotlinxVersion", "1.6.4")
        set("kotlinLoggingVersion", "2.1.23")

        set("kotestVersion", "5.4.1")
        set("kotestSpringVersion", "1.1.2")
        set("mockkVersion", "1.12.5")
        set("mockkSpringVersion", "3.1.1")

        set("reactorVersion", "3.4.22")
        set("reactorKotlinVersion", "1.1.7")

        set("springBootVersion", "2.7.2")
        set("springFrameworkVersion", "5.3.22")
//        set("springSecurityVersion", "5.7.2")

        set("jacksonVersion", "2.13.3")
    }

    repositories {
        mavenCentral()
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = project.ext["javaVersion"] as JavaVersion
        targetCompatibility = project.ext["javaVersion"] as JavaVersion
    }

    tasks.withType<Test> {
        useJUnitPlatform()

        testLogging {
            exceptionFormat = FULL
            showExceptions = true
            showStandardStreams = true
            events(PASSED, FAILED, SKIPPED, STANDARD_OUT, STANDARD_ERROR)
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = (project.ext["javaVersion"] as JavaVersion).majorVersion
        }
    }

    testing {
        suites {
            @Suppress("UnstableApiUsage")
            val testIntegration by registering(JvmTestSuite::class) {
                val taskName = "testIntegration"
                testType.set(INTEGRATION_TEST)

                dependencies {
                    implementation(project)
                }

                sources {
                    compileClasspath += sourceSets.test.get().output
                    runtimeClasspath += sourceSets.test.get().output
                }

                configurations {
                    get(sources.implementationConfigurationName).extendsFrom(configurations.testImplementation.get())
                    get(sources.compileOnlyConfigurationName).extendsFrom(configurations.testCompileOnly.get())
                    get(sources.runtimeOnlyConfigurationName).extendsFrom(configurations.testRuntimeOnly.get())
                }

                targets {
                    all {
                        testTask.configure {
                            testClassesDirs += sourceSets[taskName].output.classesDirs

                            classpath += sourceSets[taskName].runtimeClasspath
                            classpath += sourceSets[taskName].output

                            shouldRunAfter(tasks.test)
                        }
                    }
                }

                tasks.check {
                    dependsOn(tasks.getByName(taskName))
                }

                idea {
                    module {
                        testSourceDirs.addAll(sources.java.srcDirs)
                        testResourceDirs.addAll(sources.resources.srcDirs)
                    }
                }
            }
        }
    }

    dependencies {
        implementation(group = "org.jetbrains.kotlin", name = "kotlin-reflect", version = "${project.ext["kotlinVersion"]}")
        implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8", version = "${project.ext["kotlinVersion"]}")

        implementation(group = "io.github.microutils", name = "kotlin-logging", version = "${project.ext["kotlinLoggingVersion"]}")

        // ---

        testImplementation(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-debug", version = "${project.ext["kotlinxVersion"]}")

        testImplementation(group = "io.kotest", name = "kotest-runner-junit5", version = "${project.ext["kotestVersion"]}")
        testImplementation(group = "io.kotest", name = "kotest-assertions-core", version = "${project.ext["kotestVersion"]}")
        testImplementation(group = "io.kotest", name = "kotest-property", version = "${project.ext["kotestVersion"]}")

        testImplementation(group = "io.mockk", name = "mockk", version = "${project.ext["mockkVersion"]}")
    }
}

subprojects {
    if (name.contains("spring")) {
        if (name.contains("runnable")) apply(plugin = "org.springframework.boot")
        apply(plugin = "org.jetbrains.kotlin.plugin.spring")


        dependencies {
//            implementation(group = "org.springframework", name = "spring-tx", version = "${project.ext["springFrameworkVersion"]}")
//            implementation(group = "org.springframework", name = "spring-webflux", version = "${project.ext["springFrameworkVersion"]}")
//            implementation(group = "org.springframework.security", name = "spring-security-core", version = "${project.ext["springSecurityVersion"]}")

            //FIXME: testIntegration handler is not registered in root file (workaround)
            "testIntegrationImplementation"(group = "org.springframework.boot", name= "spring-boot-starter-test", version = "${project.ext["springBootVersion"]}")
            "testIntegrationImplementation"(group = "io.kotest.extensions", name = "kotest-extensions-spring", version = "${project.ext["kotestSpringVersion"]}")
            "testIntegrationImplementation"(group = "com.ninja-squad", name = "springmockk", version = "${project.ext["mockkSpringVersion"]}")
        }
    }
}

tasks.wrapper {
    gradleVersion = "7.5.1"
}

//FIXME: exclude rootProject build directory (alternative will be available in gradle 7.6)
tasks.forEach {
    if (it.name !in listOf("wrapper")) {
        it.enabled = false
    }
}
gradle.buildFinished {
    buildDir.deleteRecursively()
}

// Foldable ProjectView
//.gitignore gradlew gradlew.bat gradle .idea .git
