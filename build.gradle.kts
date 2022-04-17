import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.20"
    id("pl.allegro.tech.build.axion-release") version "1.13.6"
    id("jvm-test-suite")
    id("idea")
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "pl.allegro.tech.build.axion-release")
    apply(plugin = "idea")
    apply(plugin = "jvm-test-suite")

    group = "me.project"
    version = scmVersion.version

    ext.apply {
        set("kotlinVersion", "1.6.20")
        set("kotlinxVersion", "1.6.1")
        set("kotlinLoggingVersion", "2.1.20")
        set("kotestVersion", "5.2.3")
        set("kotestSpringVersion", "1.1.0")
        set("mockkVersion", "1.12.3")
        set("mockkSpringVersion", "3.1.1")
        set("reactorVersion", "3.4.17")
        set("reactorKotlinVersion", "1.1.6")
        set("springBootVersion", "2.6.6")
        set("springFrameworkVersion", "5.3.18")
        set("jacksonVersion", "2.13.2")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(group = "org.jetbrains.kotlin", name = "kotlin-reflect", version = "${project.ext["kotlinVersion"]}")
        implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8", version = "${project.ext["kotlinVersion"]}")

        implementation(group ="io.github.microutils", name = "kotlin-logging", version = "${project.ext["kotlinLoggingVersion"]}")

        testImplementation(group = "io.kotest", name = "kotest-runner-junit5", version = "${project.ext["kotestVersion"]}")
        testImplementation(group = "io.kotest", name = "kotest-assertions-core", version = "${project.ext["kotestVersion"]}")
        testImplementation(group = "io.kotest", name = "kotest-property", version = "${project.ext["kotestVersion"]}")
        testImplementation(group = "io.mockk", name = "mockk", version = "${project.ext["mockkVersion"]}")

        testImplementation(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-debug", version = "${project.ext["kotlinxVersion"]}")
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    testing {
        suites {
            @Suppress("UnstableApiUsage")
            val testIntegration by registering(JvmTestSuite::class) {
                testType.set(TestSuiteType.INTEGRATION_TEST)

                sources {
                    compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
                    runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
                }

                configurations {
                    get(sources.implementationConfigurationName).extendsFrom(configurations.testImplementation.get())
                    get(sources.compileOnlyConfigurationName).extendsFrom(configurations.testCompileOnly.get())
                    get(sources.runtimeOnlyConfigurationName).extendsFrom(configurations.testRuntimeOnly.get())
                }

                targets {
                    all {
                        testTask.configure {
                            useJUnitPlatform()

                            testClassesDirs += sourceSets["testIntegration"].output.classesDirs
                            classpath += sourceSets["testIntegration"].runtimeClasspath
                            classpath += sourceSets["testIntegration"].output


                            testLogging {
                                exceptionFormat = TestExceptionFormat.FULL
                                showExceptions = true
                                showStandardStreams = true
                                events(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.STANDARD_OUT, TestLogEvent.STANDARD_ERROR)
                            }

                            shouldRunAfter(tasks.test)
                        }
                    }
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

    tasks.check {
        dependsOn(tasks.getByName("testIntegration"))
    }
}

subprojects {

}

//TODO: exclude rootProject build directory
gradle.buildFinished {
    project.buildDir.deleteRecursively()
}

//.gitignore gradlew gradlew.bat gradle .idea .git