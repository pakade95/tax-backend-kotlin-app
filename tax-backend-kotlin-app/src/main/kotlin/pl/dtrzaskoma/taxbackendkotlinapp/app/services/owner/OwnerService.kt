package pl.dtrzaskoma.taxbackendkotlinapp.app.services.owner

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.owner.OwnerWriteModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner
import java.util.*

interface OwnerService {

    fun getOwnerCredentials(): List<String>
    fun saveNewOwner(ownerToWrite: OwnerWriteModel): Optional<Owner>
}