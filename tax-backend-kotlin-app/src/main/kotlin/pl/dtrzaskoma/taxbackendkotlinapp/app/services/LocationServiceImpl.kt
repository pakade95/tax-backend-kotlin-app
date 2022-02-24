package pl.dtrzaskoma.taxbackendkotlinapp.app.services

import org.springframework.stereotype.Service
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.Location.LocationRepository
import java.util.*

@Service
class LocationServiceImpl(private val locationRepository: LocationRepository) : LocationService {

    override fun getLocationNames(): List<String> {
        return locationRepository.findAll().map(Location::toString)
    }

    override fun saveNewLocation(locationToWrite: Location): Optional<Location> {
        var savedEntity: Location? = null
        if (!checkIfLocationExists(locationToWrite)) {
            savedEntity = locationRepository.save(locationToWrite);
        }
        return Optional.ofNullable(savedEntity)
    }

    fun checkIfLocationExists(locationToWrite: Location): Boolean {
        return locationRepository.existsByStreetAndTownAndPostalCode(
            locationToWrite.street,
            locationToWrite.town,
            locationToWrite.postalCode
        )
    }
}