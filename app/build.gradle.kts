plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("com.google.firebase.appdistribution")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.faswet.sportify"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.faswet.sportify"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/INDEX.LIST"
        }
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13" // Match Compose version
    }
    kotlinOptions {
        freeCompilerArgs = listOf("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode")
    }
}

configurations.all {
    resolutionStrategy {
        force("io.grpc:grpc-okhttp:1.74.0")
        force("io.grpc:grpc-protobuf-lite:1.74.0")
        force("io.grpc:grpc-stub:1.74.0")
        force("io.grpc:grpc-core:1.74.0")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    //firebase
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.core)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.ai)
    implementation(libs.firebase.appcheck)
    implementation(libs.firebase.appcheck.debug)
    implementation(libs.firebase.appcheck.playintegrity)
    implementation(libs.firebase.appdistribution)
    implementation(libs.firebase.appdistribution.api)
    implementation(libs.firebase.functions)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.crashlytics.ndk)
    implementation(libs.firebase.dataconnect)
    implementation(libs.firebase.dynamic.module.support)
    implementation(libs.firebase.dynamic.links)
    implementation(libs.firebase.inappmessaging)
    implementation(libs.firebase.inappmessaging.display)
    implementation(libs.firebase.installations)
    implementation(libs.firebase.ml.modeldownloader)
    implementation(libs.firebase.perf)
    implementation(libs.firebase.config)
    implementation(libs.play.services.ads)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.material) // or latest


    implementation(libs.androidx.compose.material3)

    // Material 3 theme support
    implementation(libs.androidx.compose.material3.material3)

    // Other required Compose libraries
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.ui.text)
    debugImplementation(libs.ui.tooling)

    // Firebase BOM if used
    implementation(platform(libs.firebase.bom))

    // Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.hilt.work)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.gson)

    implementation(libs.androidx.biometric)

    implementation(libs.androidx.security.crypto)
    implementation(libs.accompanist.systemuicontroller)

    // Google Drive API
    implementation(libs.google.api.client.android)
    implementation(libs.google.api.client.gson)
    implementation(libs.google.http.client.android)
    implementation(libs.google.http.client.gson)

    // Google Drive v3 (generated API client)
    implementation(libs.google.api.services.drive.vv3rev1971250)
    // Google Sign-In (for OAuth2)
    implementation(libs.play.services.auth)

    // Firebase services
    implementation(libs.google.firebase.inappmessaging.display)

    // gRPC dependencies (required manually to avoid crash)
    implementation(libs.grpc.okhttp)
    implementation(libs.grpc.protobuf.lite)
    implementation(libs.grpc.stub)
    implementation(libs.grpc.core)
}