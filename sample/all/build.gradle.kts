plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
}
apply("${rootDir.path}/gradle/script/application.gradle")
android {
    namespace = "com.github.availability.ad.sample.all"
}
dependencies {
    implementation(files("${rootDir.path}/gradle/AudienceDebugSettings.aar"))
    implementation(project(":availability:core"))
    implementation(project(":availability:max"))
    implementation(project(":availability:admob"))
    implementation(project(":availability:audience"))
    implementation(project(":sample:base"))
    implementation(libs.google.admob)
    implementation(libs.facebook.audience)
    implementation(libs.applovin.max)
}