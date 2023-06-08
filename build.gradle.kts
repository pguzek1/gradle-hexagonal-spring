import java.lang.Runtime.getRuntime
import org.gradle.accessors.dm.LibrariesForCommonLibs
import org.gradle.accessors.dm.LibrariesForGeneral
import org.gradle.accessors.dm.LibrariesForMicronautLibs
import org.gradle.accessors.dm.LibrariesForSpringLibs
import org.gradle.accessors.dm.LibrariesForWiremockLibs
import org.gradle.api.attributes.TestSuiteType.INTEGRATION_TEST
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(commonLibs.plugins.axion)
    alias(commonLibs.plugins.kotlinJvm)

    alias(springLibs.plugins.springKotlin) apply false
    alias(springLibs.plugins.spring) apply false

    alias(micronautLibs.plugins.micronaut) apply false

    `jvm-test-suite`
}

// project configuration
subprojects {
    apply(plugin = commonLibs.plugins.kotlinJvm.get().pluginId)
    apply(plugin = commonLibs.plugins.axion.get().pluginId)

    group = "pl.alfn.project"
    version = scmVersion.version

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion))
        }
    }

    tasks {
        withType<JavaCompile> {
            options.compilerArgs = listOf("-Xlint:all", "-parameters")
        }

        withType<KotlinCompile> {
            compilerOptions {
                apiVersion.set(kotlinVersion)
                freeCompilerArgs.set(listOf("-Xjsr305=strict"))
                languageVersion.set(kotlinVersion)
                useK2.set(false) // FIXME
            }
        }
    }
}

// test configuration
subprojects {
    apply(plugin = "jvm-test-suite")

    tasks {
        withType<Test> {
            useJUnitPlatform()
            maxParallelForks = 1.coerceAtLeast(getRuntime().availableProcessors() / 2)

            testLogging {
                exceptionFormat = FULL
                events(FAILED, SKIPPED)
            }
        }

        testing {
            suites {
                register<JvmTestSuite>("testIntegration") {
                    testType.set(INTEGRATION_TEST)

                    sources {
                        compileClasspath += sourceSets.test.get().output
                        runtimeClasspath += sourceSets.test.get().output
                    }

                    configurations {
                        get(sources.implementationConfigurationName).extendsFrom(testImplementation.get())
                        get(sources.compileOnlyConfigurationName).extendsFrom(testCompileOnly.get())
                        get(sources.runtimeOnlyConfigurationName).extendsFrom(testRuntimeOnly.get())
                    }

                    targets {
                        all {
                            testTask.configure {
                                testClassesDirs += sourceSets[this@register.name].output.classesDirs

                                classpath += sourceSets[this@register.name].runtimeClasspath
                                classpath += sourceSets[this@register.name].output

                                shouldRunAfter(this@tasks.test)
                            }
                        }
                    }

                    this@tasks.check {
                        dependsOn(this@register)
                    }
                }
            }
        }
    }
}

// dependencies configuration
subprojects {
    dependencies {
        runtimeOnly(commonLibs.bundles.logback.runtime)
        implementation(commonLibs.bundles.kotlin.impl)
        testImplementation(commonLibs.bundles.kotest.test)
    }

    if (name.contains("spring")) {
        apply(plugin = springLibs.plugins.springKotlin.get().pluginId)
        if (name.contains("run")) {
            apply(plugin = springLibs.plugins.spring.get().pluginId)
        }

        dependencies {
            val testIntegrationImplementation by configurations
            testIntegrationImplementation(springLibs.bundles.kotest.spring)
        }
    }

    if (name.contains("micronaut")) {
        apply(plugin = micronautLibs.plugins.kotlin.kapt.get().pluginId)
        apply(plugin = micronautLibs.plugins.kotlin.allOpen.get().pluginId)
        if (name.contains("run")) {
            apply(plugin = micronautLibs.plugins.micronaut.get().pluginId)
        }
    }
}

val Project.general get() = rootProject.extensions.getByName("general") as LibrariesForGeneral
val Project.javaVersion: String get() = rootProject.general.versions.java.get()
val Project.kotlinVersion: KotlinVersion get() = KotlinVersion.fromVersion(rootProject.general.versions.kotlin.get().substringBeforeLast('.'))
val Project.commonLibs get() = rootProject.extensions.getByName("commonLibs") as LibrariesForCommonLibs
val Project.springLibs get() = rootProject.extensions.getByName("springLibs") as LibrariesForSpringLibs
val Project.wiremockLibs get() = rootProject.extensions.getByName("wiremockLibs") as LibrariesForWiremockLibs
val Project.micronautLibs get() = rootProject.extensions.getByName("micronautLibs") as LibrariesForMicronautLibs

// Foldable ProjectView
// .gitignore gradlew gradlew.bat gradle .idea .git
