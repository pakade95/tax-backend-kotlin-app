package pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.owner

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner
import java.util.*

interface OwnerRepository {
    fun save(owner: Owner): Owner
    fun existsByFirstNameAndLastName(firstName: String, lastName: String): Boolean
    fun findAll(): List<Owner>
    fun findByFirstNameAndLastName(firstName: String, lastName: String): Optional<Owner>
    fun findById(id: Long): Optional<Owner>
    fun deleteById(id: Long): Unit
    fun existsById(id: Long): Boolean
}