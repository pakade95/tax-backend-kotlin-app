package pl.dtrzaskoma.taxbackendkotlinapp.app.services.location

import org.springframework.stereotype.Service
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.location.LocationWriteModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.location.LocationRepository
import java.util.*

@Service
class LocationServiceImpl(private val locationRepository: LocationRepository) : LocationService {

    override fun getLocationNames(): List<String> {
        return locationRepository.findAll().map(Location::toString)
    }

    override fun saveNewLocation(locationToWrite: LocationWriteModel): Optional<Location> {
        var savedEntity: Location? = null
        if (!checkIfLocationExists(locationToWrite)) {
            val entityToSave =
                Location(locationToWrite.street, locationToWrite.town, locationToWrite.postalCode, listOf())
            savedEntity = locationRepository.save(entityToSave)
        }
        return Optional.ofNullable(savedEntity)
    }

    fun checkIfLocationExists(locationToWrite: LocationWriteModel): Boolean {
        return locationRepository.existsByStreetAndTownAndPostalCode(
            locationToWrite.street,
            locationToWrite.town,
            locationToWrite.postalCode
        )
    }
}