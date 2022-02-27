package pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.location

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.location.LocationRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.helpers.MockRepository
import java.util.*

class MockLocationRepository(list: MutableList<Location>) : LocationRepository, MockRepository<Location>() {

    init {
        mockDatabase.addAll(list)
    }

    override fun existsByStreetAndTownAndPostalCode(street: String, town: String, postalCode: String): Boolean {
        return findByStreetAndTownAndPostalCode(street, town, postalCode).isPresent
    }

    override fun findByStreetAndTownAndPostalCode(
        street: String,
        town: String,
        postalCode: String
    ): Optional<Location> {
        return mockDatabase.stream().filter { location ->
            street == location.street && town == location.town
                    && postalCode == location.postalCode
        }.findFirst()
    }

    override fun findById(id: Long): Optional<Location> {
        return mockDatabase.stream().filter { location -> id == location.id }.findFirst()
    }

    override fun deleteById(id: Long) {
        findById(id).map { location -> mockDatabase.remove(location) }
    }

    override fun existsById(id: Long): Boolean {
        return findById(id).isPresent
    }

    override fun save(element: Location): Location {
        element.id = (mockDatabase.size + 1).toLong()
        return super.save(element)
    }
}