plugins {
    id("java")
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"

}

group = "org.fastcampus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // JUnit 4 호환성 추가
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")



}

tasks.test {
    useJUnitPlatform()
}