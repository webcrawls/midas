repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(project(":common"))

    compileOnly("io.papermc.paper:paper-api:1.20-R0.1-SNAPSHOT") {
        // ignore paper's dependencies in our build
        // we'll bring our own versions in
        // todo figure out if this is still necessary?
        isTransitive = false
    }
}