rootProject.name = "midas"

include("api")
include("common")
include("paper")
include("forge")
include("fabric")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.fabricmc.net/")
    }
}