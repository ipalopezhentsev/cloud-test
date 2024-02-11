plugins {
	java
	alias(libs.plugins.spring.boot) apply false
	id("io.spring.dependency-management") version "1.1.4" apply false
	alias(libs.plugins.jib) apply false
}