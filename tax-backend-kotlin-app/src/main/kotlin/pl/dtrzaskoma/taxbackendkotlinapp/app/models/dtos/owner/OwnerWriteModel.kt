package pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.owner

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner

class OwnerWriteModel(
    var firstName: String = "",
    var lastName: String = "",
    var emailAddress: String = "",
) {

    companion object {
        fun toOwner(ownerWriteModel: OwnerWriteModel): Owner {
            return Owner.Builder()
                .firstName(ownerWriteModel.firstName)
                .lastName(ownerWriteModel.lastName)
                .emailAddress(ownerWriteModel.emailAddress)
                .build()
        }
    }
}