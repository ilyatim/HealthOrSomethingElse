plugins {
    //id("java-library")
    //id("org.jetbrains.kotlin.jvm")
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "com.example.dependencies"
version = "0.0.1"

repositories {
    mavenCentral()
}

/*java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}*/

gradlePlugin {
    plugins.register("dependencies") {
        id = "dependencies"
        implementationClass = "com.example.dependencies.InternalPlugin"
    }
}