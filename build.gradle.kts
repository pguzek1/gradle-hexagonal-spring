plugins {
    kotlin("jvm") version "1.6.20"
    id("pl.allegro.tech.build.axion-release") version "1.13.6"
}

group = "me.project"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}