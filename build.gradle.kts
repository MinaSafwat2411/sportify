// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.gms.google-services") version "4.4.3" apply false
    id("com.google.dagger.hilt.android") version "2.57" apply false
    id("com.google.devtools.ksp") version "2.2.0-2.0.2"

    id("com.google.firebase.crashlytics") version "3.0.5" apply false
    id("com.google.firebase.firebase-perf") version "2.0.0" apply false
    id("com.google.firebase.appdistribution") version "5.1.1" apply false
}