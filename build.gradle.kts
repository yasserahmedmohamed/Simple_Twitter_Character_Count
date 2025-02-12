// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    kotlin(libs.plugins.serialization.get().pluginId).version(libs.versions.serializationPlugin)
    alias(libs.plugins.android.library).apply(false)
}