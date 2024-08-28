plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
}
apply("${rootDir.path}/gradle/script/application.gradle")
android {
    namespace = "com.github.availability.ad.sample.admob"
}
dependencies {
    implementation(project(":sample:base"))
    implementation(project(":availability:admob"))
    implementation(project(":availability:core"))
    implementation(libs.google.admob)
}