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
        set("kotlinxVersion", "1.6.0")
        set("kotestVersion", "5.2.2")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(group = "org.jetbrains.kotlin", name = "kotlin-reflect", version = "${project.ext["kotlinVersion"]}")
        implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8", version = "${project.ext["kotlinVersion"]}")

        testImplementation(group = "io.kotest", name = "kotest-runner-junit5", version = "${project.ext["kotestVersion"]}")
        testImplementation(group = "io.kotest", name = "kotest-assertions-core", version = "${project.ext["kotestVersion"]}")
        testImplementation(group = "io.kotest", name = "kotest-property", version = "${project.ext["kotestVersion"]}")
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

//    val testIntegration: SourceSet by sourceSets.creating {
//        val integrationProjectPath = "${projectDir}/src/testIntegration"
//        java {
//            srcDirs("${integrationProjectPath}/java", "${integrationProjectPath}/kotlin")
//        }
//        resources {
//            srcDirs("${integrationProjectPath}/resources", "${projectDir}/src/test/resources")
//        }
//        compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
//        runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
//    }
//
//    configurations {
//        get(testIntegration.implementationConfigurationName).extendsFrom(configurations.testImplementation.get())
//        get(testIntegration.compileOnlyConfigurationName).extendsFrom(configurations.testCompileOnly.get())
//        get(testIntegration.runtimeOnlyConfigurationName).extendsFrom(configurations.testRuntimeOnly.get())
//    }

//    tasks.register<Test>("testIntegration") {
//        description = "Runs integration tests."
//        group = "verification"
//        useJUnitPlatform()
//
//        testClassesDirs += sourceSets["testIntegration"].output.classesDirs
//        classpath += sourceSets["testIntegration"].runtimeClasspath
//        classpath += sourceSets["testIntegration"].output
//
//        shouldRunAfter(tasks.test)
//    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    testing {
        suites {
            val testIntegration by registering(JvmTestSuite::class) {
                testType.set(TestSuiteType.INTEGRATION_TEST)
                val testIntegrationPath = "${projectDir}/src/testIntegration"

                sources {
                    java {
                        setSrcDirs(setOf("${testIntegrationPath}/kotlin", "${testIntegrationPath}/java"))
                    }
                    resources {
                        setSrcDirs(setOf("${testIntegrationPath}/resources"))
                    }
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
                            useJUnitPlatform {
                                includeTags("integration", "e2e")
                            }
//                            filter {
//                                isFailOnNoMatchingTests = false
//                            }
//                            testLogging {
//                                exceptionFormat = FULL
//                                showExceptions = true
//                                showStandardStreams = true
//                                events(PASSED, FAILED, SKIPPED, STANDARD_OUT, STANDARD_ERROR)
//                            }
//                            shouldRunAfter(test)
//                            finalizedBy("jacocoTestReport")
                        }
                    }
                }
            }
        }
    }

    tasks.check {
        dependsOn(tasks.getByName("testIntegration"))
    }

    idea {
        module {
            testSourceDirs.addAll(project.sourceSets["testIntegration"].java.srcDirs)
            testResourceDirs.addAll(project.sourceSets["testIntegration"].resources.srcDirs)
        }
    }
}

subprojects {

}

//.gitignore gradlew gradlew.bat gradle .idea .git