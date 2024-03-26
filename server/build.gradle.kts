plugins {
    application
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("com.google.cloud.tools.jib")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("cloud.test.Server")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

jib {
    from {
        image = "eclipse-temurin:21-jre"
    }
    to {
		image = "iliks-server:6"
	}
}
