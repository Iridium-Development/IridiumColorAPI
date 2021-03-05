plugins {
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    maven("https://repo.codemc.org/repository/nms/")
}

dependencies {
    implementation("org.spigotmc:spigot:1.16.5-R0.1-SNAPSHOT")
}

group = "com.iridium"
version = "1.0.3"
description = "IridiumColorAPI"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
