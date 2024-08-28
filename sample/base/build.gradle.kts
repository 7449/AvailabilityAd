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
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
    }
    kotlin {
        jvmToolchain(libs.versions.jvmTarget.get().toInt())
    }
}
dependencies {
    implementation(project(":availability:core"))
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
}