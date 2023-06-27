plugins {
    id("org.checkerframework") version "0.6.28"
}

subprojects {
    repositories {
        mavenCentral()
    }

    apply<JavaPlugin>()
}