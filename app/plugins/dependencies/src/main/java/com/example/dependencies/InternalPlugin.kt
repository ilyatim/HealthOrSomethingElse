package com.example.dependencies

import org.gradle.api.*

class InternalPlugin : Plugin<Project> {
    override fun apply(target: Project) {}
}
