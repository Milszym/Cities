object BuildVariables {
    val minSdkVersion get() = 22
    val targetSdkVersion get() = 28
    val compileSdkVersion get() = targetSdkVersion
    val versionCode get() = 1
    val versionName get() = "1.0"
    const val googleApiKeyName = "GOOGLE_API_KEY"
    val googleApiKey get() = getEnv(googleApiKeyName) ?: ""
}

fun getEnv(name: String) = System.getenv(name)?.let { if (it.isBlank()) null else it }