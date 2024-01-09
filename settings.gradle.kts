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

rootProject.name = "FinanceApp"
include(":app")
include(":util")
include(":ui:uiregister")
include(":room")
include(":sharedPreferences")
include(":firebase")
include(":ui:login")
include(":ui:category")
include(":ui:charts")
include(":ui:components")
include(":ui:profile")
include(":ui:transaction")
