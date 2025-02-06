plugins {
    id("java")
}

group = "org.fastcampus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // JUnit 4 호환성 추가
    testImplementation("junit:junit:4.13.2")
}

tasks.test {
    useJUnitPlatform()
}