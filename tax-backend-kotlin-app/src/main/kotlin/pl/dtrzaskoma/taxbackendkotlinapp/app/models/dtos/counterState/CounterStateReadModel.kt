package pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counterState

import org.springframework.format.annotation.DateTimeFormat
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import java.time.LocalDate

class CounterStateReadModel(
    var id: Long = 0,
    var state: String = "",
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var date: LocalDate = LocalDate.now(),
) {
    companion object {
        fun toCounterStateReadModel(source: CounterState): CounterStateReadModel {
            return CounterStateReadModel(source.id, source.state, source.date)
        }
    }
}