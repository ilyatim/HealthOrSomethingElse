plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "com.example.dependencies"
version = "0.0.1"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins.register("dependencies") {
        id = "dependencies"
        implementationClass = "com.example.dependencies.InternalPlugin"
    }
}