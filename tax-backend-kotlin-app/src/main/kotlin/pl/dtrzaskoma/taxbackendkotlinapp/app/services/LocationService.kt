package pl.dtrzaskoma.taxbackendkotlinapp.app.services

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location
import java.util.*

interface LocationService {

    fun getLocationNames():List<String>
    fun saveNewLocation(locationToWrite:Location): Optional<Location>
}