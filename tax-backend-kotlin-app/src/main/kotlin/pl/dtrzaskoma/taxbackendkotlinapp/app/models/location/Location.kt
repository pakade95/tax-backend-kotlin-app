package pl.dtrzaskoma.taxbackendkotlinapp.app.models.location

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.audit.Audit
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import javax.persistence.*

@Entity
class Location(

    var street: String = "",
    var town: String = "",
    var postalCode: String = "",

    @OneToMany(mappedBy = "location", orphanRemoval = true, cascade = [CascadeType.ALL])
    var counterList: List<Counter>,

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0,
) : Audit() {

    data class Builder(
        var id: Long=0,
        var street: String="",
        var town: String="",
        var postalCode: String="",
        var counterList: List<Counter> = listOf(),
    ) {
        fun id(id: Long) = apply { this.id = id }
        fun street(street: String) = apply { this.street = street }
        fun town(town: String) = apply { this.town = town }
        fun postalCode(postalCode: String) = apply { this.postalCode = postalCode }
        fun counterList(counterList: List<Counter>) = apply { this.counterList = counterList }
        fun build() = Location(street, town, postalCode, counterList, id)
    }

    override fun toString(): String {
        return this.postalCode + " " + this.town + " " + this.street
    }

}
