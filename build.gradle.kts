plugins {
    id("org.checkerframework") version "0.6.28"
}

subprojects {
    repositories {
        mavenCentral()
    }

    apply<JavaPlugin>()
}

allprojects {
    group = "live.webcrawls.midas"
    version = "1.0.0"
}