plugins {
    java
    `maven-publish`
}

group = "com.iridium"
version = "1.0.9"
description = "IridiumColorAPI"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    maven("https://repo.rosewooddev.io/repository/public/")
    mavenCentral()
}

dependencies {
    implementation("org.spigotmc:spigot:1.17.1")
    implementation("org.apache.commons:commons-lang3:3.13.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.mockito:mockito-inline:4.0.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.0.0")
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks {
    build {
        dependsOn(test)
    }

    test {
        useJUnitPlatform()
        setForkEvery(1)
    }
}
