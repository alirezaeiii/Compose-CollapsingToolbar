import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.addTestsDependencies

plugins {
    id("commons.android-library")
    id(BuildPlugins.NAVIGATION)
    id(BuildPlugins.HILT)
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildDependenciesVersions.COMPOSE
    }
}

dependencies {
    api(project(BuildModules.CORE))

    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.COIL)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_PREVIEW)
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(Dependencies.SWIPE_REFRESH)
    kapt(AnnotationProcessorsDependencies.HILT)

    addTestsDependencies()
}
