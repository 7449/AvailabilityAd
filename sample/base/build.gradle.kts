plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
}
android {
    namespace = "com.github.availability.ad.sample"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testOptions.targetSdk = libs.versions.targetSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(libs.versions.jvmTarget.get().toInt())
    }
}
dependencies {
    implementation(project(":availability:core"))
    implementation(libs.appcompat)
    implementation(libs.material)
}