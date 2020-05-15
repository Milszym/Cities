package net.szymon.miloch.netguru.cities.city.locator

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityLocator @Inject constructor(
    private val placesClient: PlacesClient
) {

    fun locateByCityName(name: String, onLocated: (Result<LatLng>) -> Unit) {
        val sessionToken = AutocompleteSessionToken.newInstance()
        val searchCityRequest = FindAutocompletePredictionsRequest.builder()
            .setSessionToken(sessionToken)
            .setQuery(name)
            .setTypeFilter(TypeFilter.CITIES)
            .build()
        placesClient.findAutocompletePredictions(searchCityRequest)
            .addOnSuccessListener {
                val predictions = it.autocompletePredictions
                if (!predictions.isNullOrEmpty()) {
                    locateByPlaceId(
                        cityName = name,
                        placeId = predictions.first().placeId,
                        onLocated = onLocated,
                        sessionToken = sessionToken
                    )
                } else {
                    onLocated(Result.failure(LocationException(name)))
                }
            }.addOnFailureListener {
                onLocated(Result.failure(LocationException(name)))
            }
    }

    private fun locateByPlaceId(
        cityName: String,
        placeId: String,
        sessionToken: AutocompleteSessionToken,
        onLocated: (Result<LatLng>) -> Unit
    ) {
        val placeDetailsRequest = createFetchCityRequest(
            placeId = placeId,
            sessionToken = sessionToken
        )
        placesClient.fetchPlace(placeDetailsRequest)
            .addOnSuccessListener {
                val latLng = it.place.latLng
                if (latLng != null) {
                    onLocated(Result.success(latLng))
                } else {
                    onLocated(Result.failure(LocationException(cityName)))
                }
            }.addOnFailureListener {
                onLocated(Result.failure(it))
            }
    }

    private fun createFetchCityRequest(
        placeId: String,
        sessionToken: AutocompleteSessionToken
    ) =
        FetchPlaceRequest.builder(placeId, listOf(Place.Field.LAT_LNG))
            .setSessionToken(sessionToken)
            .build()

    class LocationException(name: String) :
        IllegalStateException("Could not found LatLng for provided city name: $name.")
}