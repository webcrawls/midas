plugins {
    `java-library`
    `maven-publish`
}

group = "live.webcrawls.midas"
version = "1.0.0"

dependencies {
    api("net.kyori:adventure-api:4.14.0")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
