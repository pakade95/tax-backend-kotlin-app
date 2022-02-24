package pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.Location

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.Location.Location
import java.util.*

interface LocationRepository {

    fun save(location: Location):Location
    fun existsByStreetAndTownAndPostalCode(street:String, town:String, postalCode:String):Boolean
    fun findAll():List<Location>
    fun findByStreetAndTownAndPostalCode(street:String, town:String, postalCode: String): Optional<Location>
    fun findById(id:Long):Optional<Location>
    fun deleteById(id:Long):Unit
    fun existsById(id:Long):Unit
}