import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

enum class NetguruCitiesModule(val moduleName: String) {
    BUILD_CONFIG(":app:buildConfig"),
    BASE(":base"),
    CITY_PRODUCER(":cityProducer"),
    CITY_PRODUCER_IMPL(":cityProducer:cityProducerImpl"),
    CITY_LOCATOR(":cityLocator"),
    GOOGLE_PLACES(":googlePlaces"),
    MASTER_DETAILS(":masterDetails"),
    CITY_COLOR(":cityColor"),
    CITY_PERSISTENCE(":cityPersistence"),
    CITY_PERSISTENCE_IMPL(":cityPersistence:cityPersistenceImpl"),
    CITY_COLLECTOR(":cityCollector"),
    SCHEDULERS(":schedulers"),
    LOGGER(":logger"),
}

fun DependencyHandler.netguruModules(modules: List<NetguruCitiesModule>) {
    modules.forEach {
        implementation(project(it.moduleName))
    }
}