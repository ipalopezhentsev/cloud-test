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
		image = "iliks-server"
	}
}
