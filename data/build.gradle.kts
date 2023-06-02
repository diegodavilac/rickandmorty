plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlinx-serialization")
}

android {
    namespace = "dev.diegodc.data"
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
}

dependencies {
    implementation(project(":core:common"))

    implementation(Deps.kotlin)
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)

    implementation(Deps.ktorCore)
    implementation(Deps.ktorAndroid)
    implementation(Deps.ktorOkhttp)
    implementation(Deps.ktorClientNegotation)
    implementation(Deps.ktorSerializationJson)

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    implementation(Deps.kotlinxCoroutinesCore)

    testImplementation(Deps.mockk)
    testImplementation(Deps.mockkAndroid)
    testImplementation(Deps.junit)
    testImplementation(Deps.assertjCore)
    testImplementation(Deps.junitJupiter)
    testImplementation(Deps.ktorClientMock)
}