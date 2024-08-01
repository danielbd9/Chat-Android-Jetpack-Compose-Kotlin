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
        jcenter() // Adicione se necessário
    }
}

rootProject.name = "Chat"
include(":app")
include(":libraries")
include(":libraries:network")
include(":features")
include(":features:chat")
include(":core")
