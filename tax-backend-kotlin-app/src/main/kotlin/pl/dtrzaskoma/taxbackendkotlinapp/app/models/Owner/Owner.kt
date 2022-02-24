package pl.dtrzaskoma.taxbackendkotlinapp.app.models.Owner

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.Audit
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Owner(
    var firstName: String = "",
    var lastName: String = "",
    var emailAddress: String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 0,
) : Audit() {
    data class Builder(
        var firstName: String = "",
        var lastName: String = "",
        var emailAddress: String = "",
    ) {
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun emailAddress(emailAddress: String) = apply { this.emailAddress = emailAddress }
        fun build() = Owner(firstName, lastName, emailAddress)
    }
}
