package pl.dtrzaskoma.taxbackendkotlinapp.app.services.owner

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.owner.OwnerRepository
import java.util.*

class MockOwnerRepository : OwnerRepository, MockRepository<Owner> {

    constructor(list:MutableList<Owner>){
        mockDatabase.addAll(list)
    }

    override fun existsByFirstNameAndLastName(firstName: String, lastName: String): Boolean {
        return findByFirstNameAndLastName(firstName, lastName).isPresent
    }

    override fun findByFirstNameAndLastName(firstName: String, lastName: String): Optional<Owner> {
        return mockDatabase.stream().filter { owner ->
            firstName == owner.firstName && lastName == owner.lastName
        }.findFirst()
    }

    override fun findById(id: Long): Optional<Owner> {
        return mockDatabase.stream().filter { owner -> id == owner.id }.findFirst()
    }

    override fun deleteById(id: Long) {
        findById(id).map { owner -> mockDatabase.remove(owner) }
    }

    override fun existsById(id: Long): Boolean {
        return findById(id).isPresent
    }

    override fun save(element: Owner): Owner {
        element.id = (mockDatabase.size + 1).toLong()
        return super.save(element)
    }

}