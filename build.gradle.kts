/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.70"

    // Apply the application plugin to add support for building a CLI application.
    application

    jacoco
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    compile(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use Java Tokenizer
    compile("org.eclipse.jdt:org.eclipse.jdt.core:3.21.0")

    // Use Reactive Extension
    compile("io.reactivex.rxjava2:rxkotlin:2.4.0")

    // Use args4j
    compile("args4j:args4j:2.33")

    // Use the Kotlin test library.
    testCompile("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testCompile("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application.
    mainClassName = "io.github.t45k.scm.SCMMainKt"
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "io.github.t45k.scm.SCMMainKt"
    }

    from(
        configurations.compile.get().map {
            if (it.isDirectory) it else zipTree(it)
        }
    )
    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}
