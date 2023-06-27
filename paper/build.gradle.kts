plugins {
    id("com.github.johnrengelman.shadow") version ("8.1.1")
}

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(project(":api"))
    implementation(project(":common"))
    compileOnly("net.kyori:adventure-text-serializer-plain:4.14.0")

    compileOnly("io.papermc.paper:paper-api:1.20-R0.1-SNAPSHOT") {
        // ignore paper's dependencies in our build
        // we'll bring our own versions in
        // todo figure out if this is still necessary?
        isTransitive = false
    }
}

tasks {
    shadowJar {
        relocate("org.spongepowered", "live.webcrawls.midas.paper.")
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}
