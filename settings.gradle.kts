pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "rik_stats"
include(":app")
include(":feature:line-chart:line-chart-api")
include(":feature:line-chart:line-chart-impl")
include(":core:network-client:network-client-api")
include(":core:network-client:network-client-impl")
include(":feature:circular-chart:circular-chart-api")
include(":feature:circular-chart:circular-chart-impl")
include(":feature:viewers-list:viewers-list-api")
include(":feature:viewers-list:viewers-list-impl")
