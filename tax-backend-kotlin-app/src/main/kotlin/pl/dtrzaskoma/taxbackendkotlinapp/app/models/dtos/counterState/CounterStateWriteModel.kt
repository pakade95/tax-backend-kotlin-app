package pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counterState

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class CounterStateWriteModel(
    var counter: String = "",
    var state: String = "",
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var date: LocalDate = LocalDate.now(),
) {
}