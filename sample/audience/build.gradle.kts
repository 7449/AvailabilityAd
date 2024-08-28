plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
}
apply("${rootDir.path}/gradle/script/application.gradle")
android {
    namespace = "com.github.availability.ad.sample.audience"
}
dependencies {
    implementation(files("${rootDir.path}/gradle/AudienceDebugSettings.aar"))
    implementation(project(":sample:base"))
    implementation(project(":availability:audience"))
    implementation(project(":availability:core"))
    implementation(libs.facebook.audience)
}