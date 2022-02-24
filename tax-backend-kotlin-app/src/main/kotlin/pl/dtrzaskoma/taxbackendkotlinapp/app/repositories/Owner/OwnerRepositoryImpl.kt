package pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.Owner

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner

@Repository
interface OwnerRepositoryImpl : OwnerRepository, JpaRepository<Owner, Long> {
    override fun existsByFirstNameAndLastName(firstName: String, lastName: String): Boolean
}