plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android")
    id ("org.jetbrains.kotlin.kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.vullpes.financeapp"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = "com.vullpes.financeapp"
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfig.extensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)

    implementation(libs.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.work)
    implementation(libs.work.runtime.ktx)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.navigation.compose)

    implementation(libs.firebase.auth.ktx)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.room.runtime)


    implementation(libs.junit)
    implementation(libs.junit.ext)


    implementation(project(":ui:category"))
    implementation(project(":ui:charts"))
    implementation(project(":ui:components"))
    implementation(project(":ui:login"))
    implementation(project(":ui:profile"))
    implementation(project(":ui:transaction"))
    implementation(project(":ui:uiregister"))
    implementation(project(":ui:home"))
    implementation(project(":common"))
    implementation(project(":firebase"))
    implementation(project(":room"))
    implementation(project(":sharedPreferences"))
    implementation(project(":domain:account"))
    implementation(project(":domain:category"))
    implementation(project(":domain:charts"))
    implementation(project(":domain:authentication"))
    implementation(project(":domain:transaction"))
    implementation(project(":data:account"))
    implementation(project(":data:category"))
    implementation(project(":data:charts"))
    implementation(project(":data:authentication"))
    implementation(project(":data:transaction"))





}