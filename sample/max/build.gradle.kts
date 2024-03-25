plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
}
apply("${rootDir.path}/gradle/script/application.gradle")
android {
    namespace = "com.github.availability.ad.sample.max"
}
dependencies {
    implementation(project(":sample:base"))
    implementation(project(":availability:max"))
    implementation(project(":availability:core"))
    implementation(libs.max)
}