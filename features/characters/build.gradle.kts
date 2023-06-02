plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "dev.diegodc.features.characters"
    compileSdk = ConfigData.compileSdkVersion
    defaultConfig {
        minSdk = ConfigData.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ConfigData.composeCompiler
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(Deps.kotlin)
    implementation(Deps.androidxNavigationCompose)

    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)

    implementation(Deps.hiltNavigationCompose)

    implementation(platform(Deps.composeBom))
    implementation(Deps.composeFoundation)
    implementation(Deps.composeFoundationLayout)
    implementation(Deps.composeMaterial3)
    implementation(Deps.composeRuntime)
    implementation(Deps.composeUi)
    implementation(Deps.composeUiGraphics)
    implementation(Deps.composeUiToolingPreview)

    implementation(Deps.coilKt)
    implementation(Deps.coilKtCompose)

    implementation(Deps.accompanistPlaceholder)
}