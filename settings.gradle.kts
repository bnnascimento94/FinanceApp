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
include(":domain:account")
include(":data:account")
include(":domain:authentication")
include(":data:authentication")
include(":data:category")
include(":domain:category")
include(":domain:charts")
include(":data:charts")
include(":data:transaction")
include(":domain:transaction")
include(":ui:home")
include(":common")
