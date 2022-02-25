package pl.dtrzaskoma.taxbackendkotlinapp.app.services.counterState

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import java.util.*

interface CounterStateService {

    fun saveNewCounterState(counterToWrite:CounterState): Optional<Counter>
}