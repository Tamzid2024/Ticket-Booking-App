plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.ticketbookingapp.project02"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ticketbookingapp.project02"
        minSdk = 23
        targetSdk = 23
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation ("com.github.bumptech.glide:glide:4.13.0")
    implementation ("com.github.ismaeldivita:chip-navigation-bar:1.4.0")
    implementation(libs.firebase.database)
    implementation(libs.activity)
    implementation(libs.coordinatorlayout)
}
