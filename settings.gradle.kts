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
    }
}
rootProject.name = "rickandmorty"
include (":app")
include (":data")
include (":features:characters")
include (":features:episodes")
include (":features:locations")
include(":core:common")
include(":core:ui")
