package pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.audit.Audit
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Owner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var firstName: String = "",

    var lastName: String = "",

    var emailAddress: String = "",

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL], orphanRemoval = true)
    var counterList: List<Counter>,

    ) : Audit() {
    data class Builder(
        var id: Long = 0,
        var firstName: String = "",
        var lastName: String = "",
        var emailAddress: String = "",
        var counterList: List<Counter> = listOf(),
    ) {
        fun id(id: Long) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun emailAddress(emailAddress: String) = apply { this.emailAddress = emailAddress }
        fun counterList(counterList: List<Counter>) = apply { this.counterList = counterList }
        fun build() = Owner(id, firstName, lastName, emailAddress, counterList)
    }

    override fun toString(): String {
        return this.lastName + " " + this.firstName + " " + this.emailAddress
    }
}
