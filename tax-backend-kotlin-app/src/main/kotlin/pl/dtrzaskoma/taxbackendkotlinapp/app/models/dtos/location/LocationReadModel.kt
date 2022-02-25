package pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.location

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location

class LocationReadModel(
    var id: Long = 0,
    var postalCode: String = "",
    var town: String = "",
    var street: String = "",
) {
    companion object {
        fun toLocationReadModel(source: Location): LocationReadModel {
            return LocationReadModel(source.id, source.postalCode, source.town, source.street)
        }
    }
}