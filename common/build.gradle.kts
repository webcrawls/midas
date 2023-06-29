plugins {
    `maven-publish`
    `java-library`
}

group = "live.webcrawls.midas"
version = "1.0.0"

dependencies {
    api("org.spongepowered:configurate-hocon:4.0.0")
    api("net.kyori:adventure-text-minimessage:4.14.0")
    api("cloud.commandframework:cloud-core:1.8.3")
}