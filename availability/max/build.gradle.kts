plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
}
apply("${rootDir.path}/gradle/script/library.gradle")
android {
    namespace = "com.github.availability.ad.branch.max"
}
dependencies {
    compileOnly(project(":availability:core"))
    compileOnly(libs.applovin.max)
}