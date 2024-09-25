plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-core:2.3.0")
    implementation("io.ktor:ktor-server-netty:2.2.4")
    implementation("io.ktor:ktor-serialization-gson-jvm:2.3.0")
    implementation("io.ktor:ktor-server-content-negotiation:2.2.4")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("io.ktor:ktor-server-status-pages:2.2.4")
    implementation("ch.qos.logback:logback-classic:1.2.11")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}