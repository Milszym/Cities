plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        applicationId = "net.szymon.miloch.netguru.cities"
        minSdkVersion(BuildVariables.minSdkVersion)
        targetSdkVersion(BuildVariables.targetSdkVersion)
        versionCode = BuildVariables.versionCode
        versionName = BuildVariables.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        manifestPlaceholders[BuildVariables.googleApiKeyName] = BuildVariables.googleApiKey
        buildConfigField(
            "String",
            BuildVariables.googleApiKeyName,
            "\"${BuildVariables.googleApiKey}\""
        )
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("$rootDir/buildSrc/debug.jks")
            storePassword = "bWJdMYJ4VrwByaHq"
            keyAlias = "debug"
            keyPassword = "Wu5CNGqsg9JZN87p"
        }
    }
}

dependencies {
    netguruModules(
        listOf(
            NetguruCitiesModule.BASE,
            NetguruCitiesModule.BUILD_CONFIG,
            NetguruCitiesModule.CITY_COLOR,
            NetguruCitiesModule.CITY_PERSISTENCE,
            NetguruCitiesModule.CITY_PERSISTENCE_IMPL,
            NetguruCitiesModule.CITY_PRODUCER,
            NetguruCitiesModule.CITY_PRODUCER_IMPL,
            NetguruCitiesModule.CITY_LOCATOR,
            NetguruCitiesModule.GOOGLE_PLACES,
            NetguruCitiesModule.MASTER_DETAILS,
            NetguruCitiesModule.LOGGER,
            NetguruCitiesModule.SCHEDULERS,
            NetguruCitiesModule.CITY_COLLECTOR
        )
    )

    kotlin()
    fragment()
    constraintLayout()
    recyclerView()
    dagger()
    daggerAndroid()
    rx()
    room()

    testImplementation("junit:junit:4.12")
}
