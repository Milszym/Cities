import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.kotlin() {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${DependencyVersions.kotlin}")
}

fun DependencyHandler.androidCore() {
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
}

fun DependencyHandler.dagger() {
    implementation("com.google.dagger:dagger:${DependencyVersions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${DependencyVersions.dagger}")
}

fun DependencyHandler.daggerAndroid() {
    implementation("com.google.dagger:dagger-android:${DependencyVersions.dagger}")
    implementation("com.google.dagger:dagger-android-support:${DependencyVersions.dagger}")
    kapt("com.google.dagger:dagger-android-processor:${DependencyVersions.dagger}")
}

fun DependencyHandler.fragment() {
    implementation("androidx.fragment:fragment:${DependencyVersions.fragment}")
    implementation("androidx.fragment:fragment-ktx:${DependencyVersions.fragment}")
}

fun DependencyHandler.constraintLayout() {
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
}

fun DependencyHandler.recyclerView() {
    implementation("androidx.recyclerview:recyclerview:1.1.0")
}

fun DependencyHandler.googleMaps(dependencyTarget: (DependencyHandler.(Any) -> Dependency?) = DependencyHandler::implementation) {
    dependencyTarget(
        "com.google.android.gms:play-services-maps:17.0.0"
    )
}

fun DependencyHandler.googlePlaces(dependencyTarget: (DependencyHandler.(Any) -> Dependency?) = DependencyHandler::implementation) {
    dependencyTarget("com.google.android.libraries.places:places:2.2.0")
}

fun DependencyHandler.rx() {
    implementation("io.reactivex.rxjava2:rxjava:2.2.9")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
}

fun DependencyHandler.room() {
    implementation("androidx.room:room-runtime:${DependencyVersions.room}")
    kapt("androidx.room:room-compiler:${DependencyVersions.room}")
    implementation("androidx.room:room-ktx:${DependencyVersions.room}")
}

fun DependencyHandler.material() {
    implementation("com.google.android.material:material:1.1.0")
}

fun DependencyHandler.unitTests() {
    testImplementation("androidx.arch.core:core-testing:2.0.0")
    testImplementation("androidx.test.ext:junit:1.1.0")
    testImplementation("org.mockito:mockito-core:2.24.5")
    testImplementation("io.mockk:mockk:1.8.13")

    testImplementation("junit:junit:4.12")
    testImplementation("androidx.test:core:1.2.0")
    testImplementation("androidx.test:runner:1.2.0")
    testImplementation("androidx.test:rules:1.2.0")
    testImplementation("androidx.test.ext:junit:1.1.1")
}
