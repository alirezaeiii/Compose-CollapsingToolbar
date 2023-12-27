import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies

plugins {
    id("commons.android-library")
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
    api(Dependencies.APPCOMPAT)
    api(Dependencies.LIFECYCLE_VIEWMODEL)
    api(Dependencies.CORE_KTX)
    api(Dependencies.TIMBER)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.SWIPE_REFRESH)
    kapt(AnnotationProcessorsDependencies.HILT)
}