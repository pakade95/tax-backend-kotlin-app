package pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.audit.Audit
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner
import javax.persistence.*

@Entity
class Counter(

    @Column(unique = true)
    var counterName: String = "",

    @OneToMany(mappedBy = "counter", cascade = [CascadeType.ALL], orphanRemoval = true)
    var counterState: List<CounterState>,

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    var location: Location,

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    var owner: Owner,

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0,

    ) : Audit() {

    data class Builder(
        var counterName: String,
        var counterState: List<CounterState>,
        var location: Location,
        var owner: Owner,
    ) {
        fun counterName(counterName: String) = apply { this.counterName = counterName }
        fun counterState(counterState: List<CounterState>) = apply { this.counterState = counterState }
        fun location(location: Location) = apply { this.location = location }
        fun owner(owner: Owner) = apply { this.owner = owner }
        fun build() = Counter(counterName, counterState, location, owner);
    }

}
