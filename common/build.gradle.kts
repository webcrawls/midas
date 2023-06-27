plugins {
    `java-library`
}

dependencies {
    api(project(":api"))
    api("org.spongepowered:configurate-hocon:4.0.0")
    api("net.kyori:adventure-text-minimessage:4.14.0")
}