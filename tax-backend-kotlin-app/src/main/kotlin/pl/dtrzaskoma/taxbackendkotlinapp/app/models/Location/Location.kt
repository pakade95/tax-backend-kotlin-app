package pl.dtrzaskoma.taxbackendkotlinapp.app.models.Location

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.Audit
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Location(

    var street: String = "",
    var town: String = "",
    var postalCode: String = "",

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private var id: Long = 0,
) : Audit() {

    data class Builder(
        var street: String = "",
        var town: String = "",
        var postalCode: String = "",
    ) {
        fun street(street: String) = apply { this.street = street }
        fun town(town: String) = apply { this.town = town }
        fun postalCode(postalCode: String) = apply { this.postalCode = postalCode }
        fun build() = Location(street, town, postalCode)
    }

    override fun toString(): String {
        return this.postalCode + " " + this.town + " " + this.street
    }

}
