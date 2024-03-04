import java.util.Properties
import java.io.FileInputStream
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android")
    id ("org.jetbrains.kotlin.kapt")
    id("com.google.gms.google-services")
    id("com.github.triplet.play") version "3.9.0"
}

// Create a variable called keystorePropertiesFile, and initialize it to your
// keystore.properties file, in the rootProject folder.
val keystorePropertiesFile = rootProject.file("app/keystore.properties")

// Initialize a new Properties() object called keystoreProperties.
val keystoreProperties = Properties()

// Load your keystore.properties file into the keystoreProperties object.
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "com.vullpes.financeapp"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = "com.vullpes.financeapp"
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = 4
        versionName = "1.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName("release")
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
        kotlinCompilerExtensionVersion = ProjectConfig.      extensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

play {
    serviceAccountCredentials.set(file("service-account.json"))
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