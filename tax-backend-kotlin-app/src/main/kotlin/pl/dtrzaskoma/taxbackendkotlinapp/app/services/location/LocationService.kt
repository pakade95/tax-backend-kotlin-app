package pl.dtrzaskoma.taxbackendkotlinapp.app.services.location

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.location.LocationWriteModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location
import java.util.*

interface LocationService {

    fun getLocationNames():List<String>
    fun saveNewLocation(locationToWrite:LocationWriteModel): Optional<Location>
}