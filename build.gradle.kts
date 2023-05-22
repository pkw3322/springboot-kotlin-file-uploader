import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"

	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.5.31"
	kotlin("plugin.jpa") version "1.5.31"

	id ("org.jetbrains.kotlin.plugin.allopen") version "1.5.21"
	id ("org.jetbrains.kotlin.plugin.noarg") version "1.5.21"
}

noArg {
	annotation("javax.persistence.Entity")
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("com.querydsl:querydsl-jpa")


	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group="junit", module="unit")
	}

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
	testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("com.github.f4b6a3:ulid-creator:5.1.0")
	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
