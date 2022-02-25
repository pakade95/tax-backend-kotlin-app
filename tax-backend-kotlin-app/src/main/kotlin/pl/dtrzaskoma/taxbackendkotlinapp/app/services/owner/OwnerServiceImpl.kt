package pl.dtrzaskoma.taxbackendkotlinapp.app.services.owner

import org.springframework.stereotype.Service
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.owner.OwnerReadModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.owner.OwnerWriteModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.owner.OwnerRepository
import java.util.*

@Service
class OwnerServiceImpl(private val ownerRepository: OwnerRepository) : OwnerService {

    override fun getOwnerCredentials(): List<String> {
        return ownerRepository.findAll().map(Owner::toString)
    }

    override fun saveNewOwner(ownerToWrite: OwnerWriteModel): Optional<Owner> {
        var savedEntity: Owner? = null
        if (!checkIfOwnerExists(ownerToWrite)) {
            val ownerToSave:Owner = OwnerWriteModel.toOwner(ownerToWrite)
            savedEntity = ownerRepository.save(ownerToSave)
        }
        return Optional.ofNullable(savedEntity)
    }

    fun checkIfOwnerExists(ownerToWrite: OwnerWriteModel): Boolean {
        return ownerRepository.existsByFirstNameAndLastName(ownerToWrite.firstName, ownerToWrite.lastName);
    }
}