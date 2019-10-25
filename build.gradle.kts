import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java 
    kotlin("jvm") version "1.3.50" apply false
}

group = "com.github.tmtsoftware.esw"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
        jcenter()
        maven("https://jitpack.io")
    }

    dependencies {
        compile(kotlin("stdlib-jdk8"))
        compile("com.github.tmtsoftware.esw:esw-ocs-impl_2.13:c964c43911dfbf88d0ffff10f9507f859d6eec2e")
        compile("com.github.tmtsoftware.csw:csw-params_2.13:1c2c2c4baaed0e14ae6b2dc579787b8f4b38616c")
        compile("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", "1.3.0")
        testCompile("junit", "junit", "4.12")
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}
