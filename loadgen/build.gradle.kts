plugins {
	kotlin("jvm") version "2.1.10"
	application
}

group = "ru.kholopov"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation(kotlin("test"))
}

application {
	mainClass.set("ru.kholopov.loadgen.MainKt")
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(21)
}
