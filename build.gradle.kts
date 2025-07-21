import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.palantir.git-version") version "3.1.0"
}

group = "ai.arpi"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2025.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val registry = "eb-registry.kr.ncr.ntruss.com"
val repository = "prototype/${project.name}"
val gitVersion: groovy.lang.Closure<String> by extra
val versionDetails: groovy.lang.Closure<out com.palantir.gradle.gitversion.VersionDetails> by extra

val commitHash = versionDetails().gitHash.substring(0, 8)

gradle.projectsEvaluated {
	tasks.named<BootBuildImage>("bootBuildImage") {
		imageName.set("$registry/$repository:$commitHash")
		tags.add("$registry/$repository:latest")

		publish.set(true)
		docker.publishRegistry {
			url.set(registry)
			username.set(System.getenv("NCLOUD_ACCESS_KEY"))
			password.set(System.getenv("NCLOUD_SECRET_KEY"))
		}
	}
}