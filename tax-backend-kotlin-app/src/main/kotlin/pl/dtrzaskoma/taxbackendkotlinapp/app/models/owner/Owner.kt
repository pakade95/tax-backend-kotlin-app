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
data class Owner(

    var firstName: String = "",

    var lastName: String = "",

    var emailAddress: String = "",

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL], orphanRemoval = true)
    var counterList: List<Counter>,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 0,

    ) : Audit() {
    data class Builder(
        var firstName: String,
        var lastName: String,
        var emailAddress: String,
        var counterList: List<Counter>,
    ) {
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun emailAddress(emailAddress: String) = apply { this.emailAddress = emailAddress }
        fun counterList(counterList: List<Counter>) = apply { this.counterList = counterList }
        fun build() = Owner(firstName, lastName, emailAddress, counterList)
    }
}
