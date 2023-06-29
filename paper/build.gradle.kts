plugins {
    id("com.github.johnrengelman.shadow") version ("8.1.1")
}

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(project(":common"))
    implementation("cloud.commandframework:cloud-paper:1.8.3")
    implementation("net.kyori:adventure-text-serializer-plain:4.14.0")
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
}

tasks {
    shadowJar {
        relocate("org.spongepowered", "live.webcrawls.midas.paper.dependency.spongepowered")
        relocate("cloud.commandframework", "live.webcrawls.midas.paper.dependency.cloud")
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}
