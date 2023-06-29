rootProject.name = "midas"

include("common")
include("paper")
//include("fabric")
includeVersions("forge", "1_19_2")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.fabricmc.net/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.5.0")
}

fun includeVersions(common: String, vararg names: String) {
    names.forEach {
        setupSubproject("$common-$it", file("$common/$it"))
    }
}

fun setupSubproject(name: String, directory: File) = setupSubproject(name) {
    projectDir = directory
}

inline fun setupSubproject(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}