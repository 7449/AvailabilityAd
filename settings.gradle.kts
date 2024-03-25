pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        mavenLocal()
    }
}
include(
    ":sample:all",
    ":sample:base",
    ":sample:max",
    ":sample:admob",
    ":sample:audience"
)
include(
    ":availability:core",
    ":availability:admob",
    ":availability:max",
    ":availability:audience",
)