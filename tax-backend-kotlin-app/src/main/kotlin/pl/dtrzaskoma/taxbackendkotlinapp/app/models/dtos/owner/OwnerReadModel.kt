package pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.owner

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner

class OwnerReadModel(
    var id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var emailAddress: String = "",
) {
    fun toOwnerReadModel(source: Owner): OwnerReadModel {
        return OwnerReadModel(source.id, source.firstName, source.lastName, source.emailAddress)
    }
}