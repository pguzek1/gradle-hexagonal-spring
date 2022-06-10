import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.spring") version "1.6.21" apply false
    id("org.springframework.boot") version "2.7.0" apply false
    id("pl.allegro.tech.build.axion-release") version "1.13.14"
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
        set("javaVersion", JavaVersion.VERSION_17)
        set("kotlinVersion", "1.6.21")

        set("kotlinxVersion", "1.6.2")
        set("kotlinLoggingVersion", "2.1.23")

        set("kotestVersion", "5.3.0")
        set("kotestSpringVersion", "1.1.1")
        set("mockkVersion", "1.12.4")
        set("mockkSpringVersion", "3.1.1")

        set("reactorVersion", "3.4.18")
        set("reactorKotlinVersion", "1.1.6")

        set("springBootVersion", "2.7.0")
        set("springFrameworkVersion", "5.3.20")
//        set("springSecurityVersion", "5.7.1")

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
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showStandardStreams = true
            events(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.STANDARD_OUT, TestLogEvent.STANDARD_ERROR)
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
                testType.set(TestSuiteType.INTEGRATION_TEST)

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
            implementation(group = "org.springframework", name = "spring-tx", version = "${project.ext["springFrameworkVersion"]}")
            implementation(group = "org.springframework", name = "spring-webflux", version = "${project.ext["springFrameworkVersion"]}")
//            implementation(group = "org.springframework.security", name = "spring-security-core", version = "${project.ext["springSecurityVersion"]}")

            //FIXME: testIntegration handler is not registered in root file (workaround)
            "testIntegrationImplementation"(group = "org.springframework.boot", name= "spring-boot-starter-test", version = "${project.ext["springBootVersion"]}")
            "testIntegrationImplementation"(group = "io.kotest.extensions", name = "kotest-extensions-spring", version = "${project.ext["kotestSpringVersion"]}")
            "testIntegrationImplementation"(group = "com.ninja-squad", name = "springmockk", version = "${project.ext["mockkSpringVersion"]}")
        }
    }
}

//FIXME: exclude rootProject build directory (alternative will be available in gradle 7.6)
tasks.forEach { it.enabled = false }
gradle.buildFinished {
    buildDir.deleteRecursively()
}

// Foldable ProjectView
//.gitignore gradlew gradlew.bat gradle .idea .git
