import java.util.Properties

val localProperties = Properties().apply {
    load(File(rootDir, "local.properties").inputStream())
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin(libs.plugins.serialization.get().pluginId)
    id("kotlin-kapt")
    id(libs.plugins.dagger.hilt.android.get().pluginId)


}

android {
    namespace = "com.yasser.simpletwittercharactercount"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.yasser.simpletwittercharactercount"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "TwitterAccessToken", localProperties.getProperty("TwitterAccessToken") as String)
        buildConfigField("String", "TwitterAccessTokenSecret", localProperties.getProperty("TwitterAccessTokenSecret") as String)
        buildConfigField("String", "TwitterAPIKey", localProperties.getProperty("TwitterAPIKey") as String)
        buildConfigField("String", "TwitterAPIKeySecret", localProperties.getProperty("TwitterAPIKeySecret") as String)

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":twittercounterandtweet"))

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
}