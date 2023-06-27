plugins {
    `maven-publish`
    `java-library`
}

group = "live.webcrawls.midas"
version = "1.0.0"

dependencies {
    api(project(":api"))

    api("org.spongepowered:configurate-hocon:4.0.0")
    api("net.kyori:adventure-text-minimessage:4.14.0")
}