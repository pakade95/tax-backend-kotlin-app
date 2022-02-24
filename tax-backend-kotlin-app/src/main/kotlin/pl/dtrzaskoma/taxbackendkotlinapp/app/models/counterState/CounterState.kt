package pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState

import org.springframework.format.annotation.DateTimeFormat
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.audit.Audit
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import java.time.LocalDate
import javax.persistence.*

@Entity
data class CounterState(

    var state: String = "",

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var date: LocalDate = LocalDate.now(),

    @ManyToOne
    @JoinColumn(name = "counter_id", nullable = false)
    var counter: Counter,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 0
) : Audit() {

    data class Builder(
        var state: String,
        var date: LocalDate,
        var counter: Counter,
    ) {
        fun state(state: String) = apply { this.state = state }
        fun date(date: LocalDate) = apply { this.date = date }
        fun counter(counter: Counter) = apply { this.counter = counter }
        fun build() = CounterState(state, date, counter)
    }
}