plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

android {

    compileSdkVersion(BuildVariables.targetSdkVersion)

    defaultConfig {
        minSdkVersion(BuildVariables.minSdkVersion)
        targetSdkVersion(BuildVariables.targetSdkVersion)
        versionCode = BuildVariables.versionCode
        versionName = BuildVariables.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }

    resourcePrefix(project.name)
}

dependencies {
    unitTests()
}