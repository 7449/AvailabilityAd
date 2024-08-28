plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
}
apply("${rootDir.path}/gradle/script/library.gradle")
android {
    namespace = "com.github.availability.ad.branch.audience"
}
dependencies {
    compileOnly(project(":availability:core"))
    compileOnly(libs.facebook.audience)
}
