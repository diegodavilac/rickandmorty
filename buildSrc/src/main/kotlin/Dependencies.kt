
object Versions {
    const val androidGradlePlugin = "7.4.1"
    const val kotlin = "1.8.0"

    const val kotlinxCoroutines = "1.6.4"
    const val kotlinxDatetime = "0.4.0"
    const val kotlinxSerializationJson = "1.4.1"
    const val appCompat = "1.5.1"
    const val material = "1.3.0"
    const val constraintLayout = "1.1.3"
    const val jUnit = "4.13.2"
    const val jUnitJupiter = "5.8.2"


    const val accompanist = "0.31.3-beta"
    const val androidxComposeBom = "2023.04.01"
    const val androidxComposeMaterial3 = "1.1.0-rc01"
    const val androidxCore = "1.9.0"
    const val androidxCoreSplashscreen = "1.0.0"
    const val androidxHiltNavigationCompose = "1.0.0"
    const val androidxLifecycle = "2.6.0-alpha05"
    const val androidxNavigation = "2.5.3"
    const val coil = "2.2.2"
    const val hilt = "2.44.2"
    const val hiltExt = "1.0.0"
    const val hiltNavigation = "1.1.0-alpha01"
    const val ktor = "2.3.0"
    const val okhttp = "4.10.0"

    const val mockk = "1.13.5"
    const val assertj = "3.22.0"
}

/**
 * To define plugins
 */
object BuildPlugins {
    val android by lazy { "com.android.application" }
    val androidLibrary by lazy { "com.android.library" }
    val kotlin by lazy { "org.jetbrains.kotlin.android" }
    val kotlinSerialization by lazy { "org.jetbrains.kotlin.plugin.serialization" }
    val hilt by lazy {"com.google.gms.google-services"}
}

/**
 * To define dependencies
 */
object Deps {
    val androidxAppCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }

    val composeBom by lazy { "androidx.compose:compose-bom:${Versions.androidxComposeBom}" }
    val composeFoundation by lazy {"androidx.compose.foundation:foundation"}
    val composeFoundationLayout by lazy {"androidx.compose.foundation:foundation-layout"}
    val composeMaterial3 by lazy {"androidx.compose.material3:material3"}
    val composeRuntime by lazy {"androidx.compose.runtime:runtime"}
    val composeUi by lazy { "androidx.compose.ui:ui" }
    val composeUiGraphics by lazy { "androidx.compose.ui:ui-graphics" }
    val composeUiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview" }

    val androidxNavigationCompose by lazy{
        "androidx.navigation:navigation-compose:${Versions.androidxNavigation}"
    }
    val androidxCoreKtx by lazy {
        "androidx.core:core-ktx:${Versions.androidxCore}"
    }

    val hiltAndroid by lazy {
        "com.google.dagger:hilt-android:${Versions.hilt}"
    }
    val hiltCompiler by lazy {
        "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }
    val hiltExtCompiler by lazy {
        "androidx.hilt:hilt-compiler:${Versions.hiltExt}"
    }
    val hiltExtWork by lazy {
        "androidx.hilt:hilt-work:${Versions.hiltExt}"
    }
    val hiltNavigationCompose by lazy {
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
    }

    val ktorCore by lazy {
        "io.ktor:ktor-client-core:${Versions.ktor}"
    }
    val ktorAndroid by lazy {
        "io.ktor:ktor-client-android:${Versions.ktor}"
    }
    val ktorOkhttp by lazy {
        "io.ktor:ktor-client-okhttp:${Versions.ktor}"
    }
    val ktorClientNegotation by lazy {
        "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    }
    val ktorSerializationJson by lazy {
        "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    }

    val ktorClientMock by lazy {
        "io.ktor:ktor-client-mock:${Versions.ktor}"
    }

    val androidxViewModelKtx by lazy {
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycle}"
    }

    val coilKt by lazy {
        "io.coil-kt:coil:${Versions.coil}"
    }
    val coilKtCompose by lazy {
        "io.coil-kt:coil-compose:${Versions.coil}"
    }

    val mockk by lazy {"io.mockk:mockk:${Versions.mockk}"}
    val mockkAndroid by lazy { "io.mockk:mockk-android:${Versions.mockk}" }
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val kotlinxCoroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}" }
    val assertjCore by lazy {
        "org.assertj:assertj-core:${Versions.assertj}"
    }
    val junitJupiter by lazy {
        "org.junit.jupiter:junit-jupiter-api:${Versions.jUnitJupiter}"
    }

    val accompanistPlaceholder by lazy{
        "com.google.accompanist:accompanist-placeholder:${Versions.accompanist}"
    }
}

object ConfigData {
    const val compileSdkVersion = 33
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 25
    const val targetSdkVersion = 33
    const val versionCode = 1
    const val versionName = "1.0"
    const val composeCompiler = "1.4.7"
}